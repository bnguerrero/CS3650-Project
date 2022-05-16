public class TransientTask extends Task {
    public TransientTask(String name, int date, Runtime runtime)
    {
        super(name, date, runtime);
        type = Type.TRANSIENT;
    }
}
