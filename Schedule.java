import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class Schedule {
    public ArrayList<Task> taskList;

    public Schedule() {
        taskList = new ArrayList<Task>();
    }

    public Schedule(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addTask(RecurringTask task) {
        taskList.add(task);
    }

    public void addTask(TransientTask task) {
        taskList.add(task);
    }
    //returns the index location in list of the first task with taskName
    public int findTask(String taskName) {
        int taskIndex = -1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().equals(taskName)) {
                taskIndex = i;
                break;
            }
        }
        return taskIndex;
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public void viewTask(Task task) throws javax.naming.directory.InvalidAttributeValueException {
        System.out.println("Task: " + task.getName());
        System.out.println("Date: " + task.getDate());
        System.out.println("Start Time: " + task.getStartTime());
        System.out.println("Duration: " + task.getDuration());
        System.out.println("Antitask: " + task.hasAntiTask());
        if (task.getType() == type.TRANSIENT){
            System.out.println("Type: Transient");
        }
        if (task.getType() == type.RECURRING){
            System.out.println("Type: Recurring");
            System.out.println("End Date: " + task.getEndDate());
            System.out.println("Frequency: " + task.getFrequency());
            System.out.println();
            for (int i = 0; i < task.getTasks().size(); i++){
                System.out.println("Recurrence " + (i+1));
                System.out.println("Date: " + task.getDate());
                System.out.println("Start Time: " + task.getStartTime());
                System.out.println("Duration: " + task.getDuration());
                // System.out.println("Antitask: " + task.hasAntiTask());
            }
        }
    }

    public void editTask(Task task, String newName, int newDate, Runtime newRuntime, boolean antiTaskVal,
            int newEndDate) {
        type taskType = task.getType();
        int taskFreq = task.getFrequency();
        deleteTask(task);
        if (taskType == type.TRANSIENT) {
            TransientTask modifiedTask = new TransientTask(newName, newDate, newRuntime);
            modifiedTask.setAntiTask(antiTaskVal);
            addTask(modifiedTask);
        }
        if (taskType == type.RECURRING) {
            RecurringTask modifiedTask = new RecurringTask(newName, newDate, newRuntime, newEndDate, taskFreq);
            // for (int i = 0; i < task.getTasks().size(); i++){
            //     task.getTasks().get(i).setDate(newDate);
            // }
            modifiedTask.setAntiTask(antiTaskVal);
            addTask(modifiedTask);
        }

    }

    public void deleteTask(Task task) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().equals(task.getName())) {
                taskList.remove(i);
                break;
            }
        }
    }

    public boolean checkConflicts(Task newTask) throws IllegalArgumentException, InvalidAttributeValueException, javax.naming.directory.InvalidAttributeValueException {
        //if the type is recurring, recursively check.
        //call checkConflicts on each transient task contained in the recurring task.
        //if there is an overlap at any point, this will return false.
        //imperceptible bug: this will check each task in the recurring task's name as well. Small optimization issue
        if (newTask.getType() == type.RECURRING){
            for (Task t : newTask.getTasks()) {
                if (!checkConflicts(t))
                    return false;
            }
        }

        //check conflicts of newTask against each task in taskList
        for (Task task : taskList) {
            //if newTask does not have a unique name, return false 
            if(task.getName().equals(newTask.getName())) {
                return false; 
            }
            //if a task in taskList is recurring, check that tasks list of recurring tasks
            if(task.getType().equals(type.RECURRING)) {
                //if task is not an anti-task, check if it conflicts with newTask
                if(!task.hasAntiTask()) {
                    if(!conflictHelper(newTask.getDate(), task.getDate(), newTask.getRuntime(), task.getRuntime())) {
                        return false;
                    }
                }
                //for each recurring task t of task, if t is not an ant-task, check for conflicts
                for (Task t : task.getTasks()) {
                    if(!t.hasAntiTask()) {
                        if(!conflictHelper(newTask.getDate(), task.getDate(), newTask.getRuntime(), task.getRuntime())) {
                            return false;
                        }
                    }
                }

            }
            //if a task in taskList is transient, check for conflicts 
            else if(task.getType().equals(type.TRANSIENT)) {
                if(!task.hasAntiTask()) {
                    if(!conflictHelper(newTask.getDate(), task.getDate(), newTask.getRuntime(), task.getRuntime())) {
                        return false;
                    }
                }
            }
            else {
                throw new IllegalArgumentException("Invalid task type for conflict checking.");
            }

        }

        return true; 
    }
        

   
    //checks the runtimes, if they overlap, this says false.
    //this currently doesn't consider tasks that wrap past midnight, as it only checks for things that start on the same day.
    private boolean conflictHelper(int date1, int date2, Runtime r1, Runtime r2) {
        if(date1 == date2) {
            double endTime1 = r1.startTime + r1.duration;
            double endTime2 = r2.startTime + r2.duration;

            // starts at the same time or in the middle of an already existing event
            if(r1.startTime >= r2.startTime && r1.startTime < endTime2) {
                return false;
            }
            // ends at the same time or in the middle of an already existing event
            else if(endTime1 > r2.startTime && endTime1 <= endTime2) {
                return false;
            }
             // starts before an already existing event but ends after it
            else if(r1.startTime <= r2.startTime && endTime1 >= endTime2) {
                return false;
            }
        }
        return true;
        
        // //first order by start time.
        // //first is the earlier one
        // if (r1.startTime > r2.startTime){
        //     first = r2;
        //     second = r1;
        // }
        // else if (r1.startTime < r2.startTime)
        // { 
        //     first = r1;
        //     second = r2;
        // }
        // else
        //     return false; //it is impossible for them to start at the same time and not overlap.
        
        // //then define their endpoints
        // double firstend = first.startTime + first.duration;
        // //double secondend = second.startTime + second.duration; //it doesn't matter when the second starts
         
        // //the first will always start before the second.
        // //if the first ends after the second starts, no matter what that is an overlap.
        // //if the first ends before the second starts, no matter what, that will not be an overlap.
        // if (firstend <= second.startTime)
        //     return true;
        // else
        //     return false;
    }

    // public void writeSchedule(String filename) {
    // FileWriter writer = new FileWriter();
    // writer.write(taskList);
    // }

    // public void readSchedule(String filename) {
    // FileReader reader = new FileReader(this);
    // reader.read(filename);
    // }

    // public void writeScheduleDuration(String filename, int duration) {


}

// //check the conflicts between the newly added task and every other task in the list.
// for(Task task : taskList) {
//     //first check name conflicts
//     if (newTask.getName() == task.getName())
//         return false;
    
//     //then check timing conflicts
//     if (task.getType() == type.RECURRING) { //if it is a recurring task, look through the set{
//         if(!task.hasAntiTask())
//             if(conflictHelper(newTask.getDate(), task.getDate(), newTask.getRuntime(), task.getRuntime())) {
//                 for (Task t : task.getTasks()) {
//                     if(!t.hasAntiTask()) {
//                         if(!conflictHelper(newTask.getDate(), t.getDate(), newTask.getRuntime(), t.getRuntime())) {
//                             return false;
//                         }
//                     }//skip this if the task has an anti task.
                
            
//                 // if (t.getDate() == newTask.getDate()) //if the dates are the same, check their runtimes
//                 //     if(!task.hasAntiTask()) //skip this if the task has an anti task.
//                 //         if(!conflictHelper(t.getRuntime(), newTask.getRuntime()))             
//                 //             return false;
//         }
//     } else {
//         return false; 
//     }
//     }
//     //if it is a transient task, check the 2 tasks
//     else if (task.getType() == type.TRANSIENT){
//         if(!task.hasAntiTask()) //skip this if the task has an anti task.
//             return conflictHelper(newTask.getDate(), task.getDate(), newTask.getRuntime(), task.getRuntime());

//             // if (task.getDate() == newTask.getDate()) //if the dates are the same, check their runtimes
//             //     if (!conflictHelper(t.getRuntime, newTask.getRuntime))
//             //         return false;
//     }
    
//     //in case the type superclass is thrown into here, since its basically the same as a transient class it actually would work
//     //but since there may be future subclasses without implementation, this was safer.
//     else 
//         throw new IllegalArgumentException("Invalid task type for conflict checking.");
//  }
// return true;