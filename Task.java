import java.util.ArrayList;

enum type {
    TRANSIENT, RECURRING
}

public class Task {

    protected String name;
    protected int date;
    protected Runtime runtime;
    protected boolean hasAntiTask;
    protected type typeVal;

    //values for RecurringTask
    protected int endDate; // the date gets split into 2, so when pointing to a recurring task, we are
    // actually pointing to the first task in the list
    protected int frequency;
    protected ArrayList<TransientTask> tasks; // A recurring task is a collection of transient tasks

    Task(String name, int date, Runtime runtime) {
        this.name = name;
        this.date = date;
        this.runtime = runtime;
        hasAntiTask = false;
    }
    
    //name stuff
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    //date stuff
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
    
    //type stuff
    public type getType() {
        return typeVal;
    }

    //runtime stuff
    public double getStartTime() {
        return runtime.startTime;
    }

    public void setStartTime(double startTime) {
        runtime.startTime = startTime;
    }

    public double getDuration() {
        return runtime.duration;
    }

    public void setDuration(double duration) {
        runtime.duration = duration;
    }
    
    public Runtime getRuntime(Runtime runtime){
        return runtime;
    }
    
    public Runtime setRuntime(Runtime runtime){
        this.runtime = runtime;
    }
    
    //anti task stuff
    public boolean hasAntiTask() {
        return this.hasAntiTask;
    }

    public void setAntiTask(boolean AntiTask) {
        hasAntiTask = AntiTask;
    }


    //methods for RecurringTask:

    public ArrayList<TransientTask> getTasks() {
        return tasks;
    }

    public int getEndDate() {
        return endDate;
    }

    // I don't know that we want to be able to change the end date within this
    // class, it might be better to just make a new recurring task entirely
    // changing the end date would mean adding to or removing from the end of the
    // list of classes
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getFrequency() {
        return frequency;
    }

    // I don't know that we want to be able to change the frequency within this
    // class, it might be better to just make a new recurring task entirely
    // changing the frequency would mean changing the set of classes within the task
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
