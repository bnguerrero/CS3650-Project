import java.util.ArrayList;

import Task.type;

public class Schedule {
    private ArrayList<Task> taskList;

    public Schedule() {
        taskList = new ArrayList<Task>();
    }

    public Schedule(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask() {
    }

    public void viewTask(Task task) {
        System.out.println("Task: " + task.getName());
        System.out.println("Type: " ); //not yet implemented
        System.out.println("Start Time: ");
        System.out.println("Duration: ");
        System.out.println("Antitask: " + task.hasAntiTask());
        // if (task.getType() == TRANSIENT){

        // }
    }

    public void editTask(Task task) {
    }

    public void deleteTask(Task task) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName() == task.getName()) {
                taskList.remove(i);
                break;
            }
        }
    }

    public boolean checkConflicts(Task task) {
        return true;
    }

    public void writeSchedule(String filename) {

    }

    public void viewSchedule(String filename) {

    }

    public void writeScheduleDuration(String filename, int duration) {

    }

    public void viewScheduleDuration(String filename, int duration) {

    }
}
