//an extension of the exception class, used when there is an overlap in tasks
public class OverlapException extends Exception{
  private Task task1; //the first task
  private Task task2; //the overlapping task
  
  public OverlapException(Task t1, Task t2)
  {
    task1 = t1;
    task2 = t2;
  }
  
  public Task getTask1() {return task1;}
  public Task getTask2() {return task2;}
}
