
public class RecurringTask extends Task {


    public RecurringTask(String name, int date, Runtime runtime, int endDate, int frequency)
            throws IllegalArgumentException {
                
        super(name, date, runtime);

        // check that the frequency is a 1 or a 7
        if (frequency != 1 && frequency != 7)
            throw new IllegalArgumentException("Frequency must be a 1 or a 7"); // this may be better handled in main, but
                                                                             // for purposes of this project we don't
                                                                             // really have time to discuss that.
        this.frequency = frequency;
        typeVal = type.RECURRING;
        this.endDate = endDate;

        instantiateTasks();
    }


}
