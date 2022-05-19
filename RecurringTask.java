import java.util.ArrayList;

public class RecurringTask extends Task {
    //date is the same as start date
    private int endDate; //the date gets split into 2, so when pointing to a recurring task, we are actually pointing to the first task in the list
    private int frequency;
    private ArrayList<TransientTask> tasks; //A recurring task is a collection of transient tasks
    
    public RecurringTask(String name, int date, Runtime runtime, int endDate, int frequency) throws IllegalArgumentException {
        super(name, date, runtime);
        
        //check that the frequency is a 1 or a 7
        if (frequency != 1 && frequency != 7)
            throw IIllegalArgumentException("Frequency must be a 1 or a 7"); //this may be better handled in main, but for purposes of this project we don't really have time to discuss that.
        this.frequency = frequency;
        
        this.type = type.RECURRING;
        this.endDate = endDate;
        
        instantiateTasks();
    }
    
    private void instantiateTasks(){}
    
    public ArrayList<TransientTask> getTasks{
        return tasks;
    }
    
    public int getStartDate() {
        return date;
    }

    //I don't know that we want to be able to change the end date within this class, it might be better to just make a new recurring task entirely
        //changing the end date would mean adding to or removing from the beginning of the list of classes
    public void setStartDate(int startDate) {
        this.startDate = date;
    }

    public int getEndDate() {
        return endDate;
    }

    //I don't know that we want to be able to change the end date within this class, it might be better to just make a new recurring task entirely
        //changing the end date would mean adding to or removing from the end of the list of classes
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getFrequency() {
        return frequency;
    }

    //I don't know that we want to be able to change the frequency within this class, it might be better to just make a new recurring task entirely
        //changing the frequency would mean changing the set of classes within the task
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}
