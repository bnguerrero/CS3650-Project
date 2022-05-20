
public class RecurringTask extends Task {

    public RecurringTask(String name, int date, Runtime runtime, int endDate, int frequency)
            throws IllegalArgumentException {

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

        instantiateTasks();
    }

    private void instantiateTasks() {
        String dateString = String.valueOf(date);
        int years = Integer.parseInt(dateString.substring(0, 4));
        int months = Integer.parseInt(dateString.substring(4, 6));
        int days = Integer.parseInt(dateString.substring(6, 8));
        String endDateString = String.valueOf(endDate);
        int endYears = Integer.parseInt(endDateString.substring(0, 4));
        int endMonths = Integer.parseInt(endDateString.substring(4, 6));
        int endDays = Integer.parseInt(endDateString.substring(6, 8));
        switch (frequency) {
            case 1:
                for (int i = years; i < endYears; i++) {
                    for (int j = months; j < endMonths; j++) {
                        for (int k = days; k < endDays; k++) {
                            String newDateString = String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
                            int newDate = Integer.parseInt(newDateString);
                            TransientTask newTask = new TransientTask(name, newDate, runtime);
                            tasks.add(newTask);
                        }
                    }
                }
                break;
            case 7:
                for (int i = years; i < endYears; i++) {
                    for (int j = months; j < endMonths; j++) {
                        for (int k = days; k < endDays; k += 7) {
                            String newDateString = String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
                            int newDate = Integer.parseInt(newDateString);
                            TransientTask newTask = new TransientTask(name, newDate, runtime);
                            tasks.add(newTask);
                        }
                    }
                }
                break;
        }
    }

}
