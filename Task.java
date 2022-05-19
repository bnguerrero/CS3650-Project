enum type {
    TRANSIENT, RECURRING
}

public class Task {

    protected String name;
    protected int date;
    protected Runtime runtime;
    protected boolean hasAntiTask;
    protected type typeVal;

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

    public double getStartTime() {
        return runtime.startTime;
    }

    public Enum getType() {
        return typeVal;
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
}
