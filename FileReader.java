import java.io.*;
import java.util.*;
public class FileReader {
    private Schedule schedule;

    public FileReader(Schedule schedule){
        this.schedule = schedule;
    }

    public void read(String filename) throws Exception{
        Scanner in = new Scanner(new File(filename));
        String next1 = "";
        String next2 = "";
        ArrayList<String> taskData = new ArrayList<String>();
        ArrayList<Task> newTasks = new ArrayList<Task>();
        String type = "";
        try{
            in.next();
            while(in.hasNext()){
                next1 = in.next();
                taskData.add(next1.substring(1, next1.length()-2));
                next1 = in.next();
                if(next1.equals("{")){
                    while(!(next1.equals("},") || next1.equals("}"))){
                        next1 = in.next();
                        next1 = in.next();
                        if(next1.substring(next1.length()-1).equals(",")){
                            next1 = next1.substring(1, next1.length()-2);
                        }
                        else if(next1.substring(next1.length()-1).equals("\"")){
                            next1 = next1.substring(1, next1.length()-1);
                        }
                        taskData.add(next1);
                        // count++;
                    }
                }
                else{
                    throw new Exception("Invalid format");
                }
                if(taskData.get(1).equals("TRANSIENT")){
                    Runtime runtime = new Runtime();
                    runtime.startTime = Double.parseDouble(taskData.get(3));
                    runtime.duration = Double.parseDouble(taskData.get(4));
                    Task newTask = new TransientTask(taskData.get(0), Integer.parseInt(taskData.get(1)), runtime);
                    if(taskData.get(5).equals("True")){
                        newTask.setAntiTask(true);
                    }
                    else if(taskData.get(5).equals("False")){
                        newTask.setAntiTask(false);
                    }
                    else{
                        throw new Exception("Invalid format");
                    }
                    schedule.checkConflicts(newTask);
                    newTasks.add(newTask);
                }
                else if(taskData.get(1).equals("RECURRING")){
                    Runtime runtime = new Runtime();
                    runtime.startTime = Double.parseDouble(taskData.get(3));
                    runtime.duration = Double.parseDouble(taskData.get(4));
                    Task newTask = new RecurringTask(taskData.get(0), Integer.parseInt(taskData.get(1)), runtime, Integer.parseInt(taskData.get(7)), Integer.parseInt(taskData.get(8)));
                    schedule.checkConflicts(newTask);
                    newTasks.add(newTask);

                }
                else{
                    throw new Exception("Invalid format");
                }
                in.next();
                in.next();
            }
        }
        catch (Exception e){
            throw new Exception("Invalid format");
        }
        for(Task task : newTasks){
            schedule.addTask(task);
        }
    }
}
