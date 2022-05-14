
public class Task {

    protected String name;
    protected String type;
    protected int date;
    protected double startTime;
    protected double duration;
    protected boolean hasAntiTask;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public boolean getAntiTask() {
        return hasAntiTask;
    }

    public void setHasAntiTask(boolean hasAntiTask) {
        this.hasAntiTask = hasAntiTask;
    }
}