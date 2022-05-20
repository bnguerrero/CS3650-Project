/*
import org.json.simple.JSONException;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.*;
public class FileWriter{

    public FileWriter(){
    }

    public void write(ArrayList<Task> taskList ,String filename){
        JSONOBject json = makeJson(taskList);
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject makeJson(ArrayList<Task> taskList){
        JSONObject json = new JSONObject();
        String taskName = "";
        String type = "";
        for(Task task : schedule.taskList){
            JSONObject taskJson = new JSONObject();
            taskName = task.getName();
            taskJson.put("Date", Integer.toString(task.getDate()));
            type = task.getType().toString();
            taskJson.put("Type", type);
            taskJson.put("Start Time", task.getStartTime() + "");
            taskJson.put("Duration", task.getDuration() + "");
            if(task.hasAntiTask()){
                taskJson.put("Has Anti-Task", "True");
            }
            else{
                taskJson.put("Has Anti-Task", "False");
            }
            if(type.equals("RECURRING")){
                taskJson.put("Tasks", makeJson(task.getTasks()));
                taskJson.put("End Date", Integer.toString(task.getEndDate()));
                taskJson.put("Frequency", Integer.toString(task.getFrequency()));
            }
            json.put(taskName, taskJson);
        }
        return json;
    }
}
*/
