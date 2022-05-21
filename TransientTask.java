public class TransientTask extends Task  {
    public TransientTask(String name, int date, Runtime runtime)
    {
        super(name, date, runtime);
        typeVal = Type.TRANSIENT;
        endDate = -1;
        frequency = 0;
        tasks = null;
    }
}
