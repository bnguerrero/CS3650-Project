import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.*;
public class JsonWriter{

    private int startDate = -1;
    private int endDate = -1;
    public JsonWriter(int startDate, int endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public JsonWriter(){
    }

    public void write(ArrayList<Task> taskList, String filename){
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
        int date = 0;
        for(Task task : taskList){
            date = task.getDate();
            if((date<startDate || date>endDate) && !(startDate == -1 || endDate == -1)){
                continue;
            }
            JSONObject taskJson = new JSONObject();
            taskName = task.getName();
            taskJson.put("Date", Integer.toString(date));
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
                taskJson.put("End Date", Integer.toString(task.getEndDate()));
                taskJson.put("Frequency", Integer.toString(task.getFrequency()));
            }
            json.put(taskName, taskJson);
        }
        return json;
    }
}
