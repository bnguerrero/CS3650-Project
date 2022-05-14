public class TransientTask extends Task {
    public TransientTask(String name, String type, int date, double startTime, double duration, boolean hasAntiTask)
    {
        this.name = name;
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.hasAntiTask = hasAntiTask;
    }
}
