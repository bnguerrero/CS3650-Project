import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.directory.InvalidAttributeValueException;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Enum getType() {
        return typeVal;
    }

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

    public boolean hasAntiTask() {
        return this.hasAntiTask;
    }

    public void setAntiTask(boolean AntiTask) {
        hasAntiTask = AntiTask;
    }


    //methods for RecurringTask:

    public void instantiateTasks() {

    }

    //creates a list of transient tasks for a recurring task 
    public ArrayList<TransientTask> getTasks() throws InvalidAttributeValueException {
        String s = Integer.toString(date);
        String e = Integer.toString(endDate);

        int startYear = Integer.parseInt(s.substring(0, 4));
        int startMonth = Integer.parseInt(s.substring(4, 6));
        int startDay = Integer.parseInt(s.substring(6, 8));

        int endYear = Integer.parseInt(e.substring(0, 4));
        int endMonth = Integer.parseInt(e.substring(4, 6));
        int endDay = Integer.parseInt(e.substring(6, 8));

        //create LocalDate object for start and end date 
        LocalDate start = LocalDate.of(startYear, startMonth, startDay);
        LocalDate end = LocalDate.of(endYear, endMonth, endDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        /**
         * if frequency is 1, increment start by one day until start > end, create a transient task
         * with the new date and same runtime, add this new task to the list of tasks
        */
        if(frequency == 1) {
            while(start.isBefore(end) || start.isEqual(end)) {
                start = start.plusDays(1);
                String newDate = start.format(formatter);
                TransientTask t = new TransientTask(name, Integer.parseInt(newDate), runtime);
                tasks.add(t);
            }
        }
          /**
         * if frequency is 7, increment start by one week until start > end, create a transient task
         * with the new date and same runtime, add this new task to the list of tasks
        */
        else if(frequency == 7) {
            while(start.isBefore(end) || start.isEqual(end)) {
                start = start.plusWeeks(1);
                String newDate = start.format(formatter);
                TransientTask t = new TransientTask(name, Integer.parseInt(newDate), runtime);
                tasks.add(t);
            }
        }
        else {
            throw new InvalidAttributeValueException();
        }
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

    public Runtime getRuntime() {
        return runtime;
    }

}
