public class RecurringTask extends Task {
    private int startDate;
    private int endDate;
    private int frequency;
    public RecurringTask(String name, String type, int date, double startTime, double duration, boolean hasAntiTask, int startDate, int endDate, int frequency)
    {
        this.name = name;
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.hasAntiTask = hasAntiTask;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
    }
    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}
