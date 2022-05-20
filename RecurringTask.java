import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class RecurringTask extends Task {

    public RecurringTask(String name, int date, Runtime runtime, int endDate, int frequency)
            throws IllegalArgumentException, InvalidAttributeValueException {

        super(name, date, runtime);

        // check that the frequency is a 1 or a 7
        // 1 is daily, 7 is weekly
        if (frequency != 1 && frequency != 7)
            throw new IllegalArgumentException("Frequency must be a 1 or a 7"); // this may be better handled in main,
                                                                                // but
                                                                                // for purposes of this project we don't
                                                                                // really have time to discuss that.
        this.frequency = frequency;
        typeVal = Type.RECURRING;
        this.endDate = endDate;
        tasks = new ArrayList<TransientTask>();
        instantiateTasks();
    }

    public void instantiateTasks() throws InvalidAttributeValueException {
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
    }

    // private void instantiateTasks() {
    //     String dateString = String.valueOf(date);
    //     int years = Integer.parseInt(dateString.substring(0, 4));
    //     int months = Integer.parseInt(dateString.substring(4, 6));
    //     int days = Integer.parseInt(dateString.substring(6, 8));
    //     String endDateString = String.valueOf(endDate);
    //     int endYears = Integer.parseInt(endDateString.substring(0, 4));
    //     int endMonths = Integer.parseInt(endDateString.substring(4, 6));
    //     int endDays = Integer.parseInt(endDateString.substring(6, 8));
    //     switch (frequency) {
    //         case 1:
    //             for (int i = years; i < endYears; i++) {
    //                 for (int j = months; j < endMonths; j++) {
    //                     for (int k = days; k < endDays; k++) {
    //                         String newDateString = String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
    //                         int newDate = Integer.parseInt(newDateString);
    //                         TransientTask newTask = new TransientTask(name, newDate, runtime);
    //                         tasks.add(newTask);
    //                     }
    //                 }
    //             }
    //             break;
    //         case 7:
    //             for (int i = years; i < endYears; i++) {
    //                 for (int j = months; j < endMonths; j++) {
    //                     for (int k = days; k < endDays; k += 7) {
    //                         String newDateString = String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
    //                         int newDate = Integer.parseInt(newDateString);
    //                         TransientTask newTask = new TransientTask(name, newDate, runtime);
    //                         tasks.add(newTask);
    //                     }
    //                 }
    //             }
    //             break;
    //     }
    // }

}
