import java.util.ArrayList;

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

    }

    public void deleteTask(Task task) {

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
