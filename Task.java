
public class Task {

    protected String name;
    protected int date;
    protected Runtime runtime;
    protected boolean hasAntiTask;
    public enum type {TRANSIENT, RECURRING}

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

    public type getType() {
        return type;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getStartTime() {
        return runtime.start;
    }

    public void setStartTime(double startTime) {
        runtime.start = startTime;
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
