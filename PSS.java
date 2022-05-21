import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributeValueException;

public class PSS {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Schedule taskSchedule = new Schedule();

        while (true) {
            System.out.println();
            System.out.println(
                    "Please select an option below: \n 1. Create a task \n 2. View a task \n 3. Delete a task \n 4. Edit a task"
                            + "\n 5. Write the schedule to a file \n 6. Read the Schedule to a file \n 7. View schedule for 1 day \n 8. View schedule for 1 week"
                            + "\n 9. View schedule for 1 month \n 10. Write schedule for 1 day \n 11. Write schedule for 1 week \n 12. Write schedule for 1 month \n 0. Exit");
            int option = input.nextInt();

            switch (option) {
                case 1: {
                
                    System.out.println("Create Transient or Recurring task?");
                	System.out.println("Press 1 for Transient");
                	System.out.println("Press 2 for Recurring");
                	int choice = input.nextInt();

                    if(choice==1){
                        System.out.println("What is the name of the event?");
                        String name = input.nextLine();
                        System.out.println("When does the event start?");
                        runtime.startTime = input.nextDouble();
                        System.out.println("How long is the event?");
                        runtime.duration = input.nextDouble();
                        System.out.println("What is the date of the event? (YYYYMMDD)");
                        int date = input.nextInt();
                        Task newTask = new TransientTask(name, date, runtime);
                        System.out.println("Does this have an associated anti-task? (y/n)");
                        String choice = input.nextLine();
                        if(choice.equals("y")){
                            newTask.setAntiTask(true);
                        }
                        else{
                            newTask.setAntiTask(true);
                        }
                        taskSchedule.checkConflicts(newTask);
                        taskSchedule.add(newTask);
                    }
                    else if(choice==2){
                        System.out.println("What is the name of the event?");
                        String name = input.nextLine();
                        System.out.println("When does the event start?");
                        runtime.startTime = input.nextDouble();
                        System.out.println("How long is the event?");
                        runtime.duration = input.nextDouble();
                        System.out.println("What is the date of the event? (YYYYMMDD)");
                        int date = input.nextInt();
                        System.out.println("What is the end date of the event? (YYYYMMDD)");
                        int endDate = input.nextInt();
                        System.out.println("Frequency? (1 for daily, 7 for weekly)");
                        int recFreq = input.nextInt();
                        Task newTask = new RecurringTask(name, date, runtime, endDate, recFreq);
                        taskSchedule.checkConflicts(newTask);
                        taskSchedule.add(newTask);
                    }

                    
                    
                    // for testing
                    //Runtime testRuntime = new Runtime();
                    //testRuntime.startTime = 20;
                    //testRuntime.duration = 60;
                    // RecurringTask testTask1 = new RecurringTask(recTask, recStartDate, testRuntime, recEndDate, recFreq);
                    // TransientTask testTask2 = new TransientTask(transTask, transDate, testRuntime);
                    //RecurringTask testTask1 = new RecurringTask("testTask1", 19900107, testRuntime, 19900130, 7);
                    //TransientTask testTask2 = new TransientTask("testTask2", 19900204, testRuntime);
                    //taskSchedule.addTask(testTask1);
                    //taskSchedule.addTask(testTask2);
                    break;
                }
                case 2: {
                    System.out.println("Enter task to view:");
                    input.nextLine();
                    String taskName = input.nextLine();
                    int loc = taskSchedule.findTask(taskName);
                    if (loc == -1) {
                        System.out.println("Error: task not found");
                        break;
                    }
                    taskSchedule.viewTask(taskSchedule.getTask(loc));
                    break;
                }
                case 3: {
                    System.out.println("Enter task to delete:");
                    input.nextLine();
                    String taskName = input.nextLine();
                    int loc = taskSchedule.findTask(taskName);
                    if (loc == -1) {
                        System.out.println("Error: task not found");
                        break;
                    }
                    taskSchedule.deleteTask(taskSchedule.getTask(loc));
                    break;
                }
                case 4: {
                    System.out.println("Enter task to edit:");
                    input.nextLine();
                    String taskName = input.nextLine();
                    int loc = taskSchedule.findTask(taskName);
                    if (loc == -1) {
                        System.out.println("Error: task not found");
                        break;
                    }
                    while (true) {
                        String newName = taskSchedule.getTask(loc).getName();
                        int newDate = taskSchedule.getTask(loc).getDate();
                        double newStartTime = taskSchedule.getTask(loc).getStartTime();
                        double newDuration = taskSchedule.getTask(loc).getDuration();
                        boolean antiTaskStatus = taskSchedule.getTask(loc).hasAntiTask();
                        int newEndDate = taskSchedule.getTask(loc).getEndDate();
                        System.out.println(
                                "Edit values: 1) Name 2) Date 3) Start time \n 4) Duration 5) Antitask Status 6) End date \n 7) Complete Edit");
                        int key = input.nextInt();
                        switch (key) {
                            case 1:
                                System.out.println("New Name:");
                                newName = input.nextLine();
                                break;
                            case 2:
                                System.out.println("New Start Date:");
                                newDate = input.nextInt();
                                break;
                            case 3:
                                System.out.println("New Start Time:");
                                newStartTime = input.nextInt();
                                break;
                            case 4:
                                System.out.println("New Duration: ");
                                newDuration = input.nextInt();
                                break;
                            case 5:
                                System.out.println("Toggle antitask status (1 = True, 2 = False):");
                                int antiTaskVal = input.nextInt();
                                antiTaskStatus = false;
                                if (antiTaskVal == 1)
                                    antiTaskStatus = true;
                                if (antiTaskVal == 2)
                                    antiTaskStatus = false;
                                taskSchedule.getTask(loc).setAntiTask(antiTaskStatus);
                                break;
                            case 6:
                                System.out.println("New End Date: ");
                                newEndDate = input.nextInt();
                                break;
                            case 7:
                                Runtime newRuntime = new Runtime();
                                newRuntime.startTime = newStartTime;
                                newRuntime.duration = newDuration;
                                taskSchedule.editTask(taskSchedule.getTask(loc), newName, newDate, newRuntime, antiTaskStatus, newEndDate);
                                break;
                            default:
                                break;
                        }
                    }
                }
                case 5: {
                    System.out.println("Enter the name of the new .json file");
                    input.nextLine();
                    taskSchedule.writeSchedule(input.nextLine());
                    break;
                }
                case 6: {
                    System.out.println("Enter the name of the .json file to upload");
                    input.nextLine();
                    taskSchedule.readSchedule(input.nextLine());
                    break;
                }
                case 7: {
                    // taskSchedule.viewScheduleDuration(filename, duration);
                    break;
                }
                case 8: {
                    // taskSchedule.viewScheduleDuration(filename, duration);
                    break;
                }
                case 9: {
                    // taskSchedule.viewScheduleDuration(filename, duration);
                    break;
                }
                case 10: {
                    System.out.println("Enter the name of the new .json file: ");
                    String filename = input.next();
                    System.out.println("Enter the start date: ");
                    int startDate = input.nextInt();
                    String s = Integer.toString(startDate);
                    int endDate = durationHelper(s, 1);
                    taskSchedule.writeScheduleDuration(filename, startDate, endDate);
                    break;
                }
                case 11: {
                    System.out.println("Enter the name of the new .json file: ");
                    String filename = input.next();
                    System.out.println("Enter the start date: ");
                    int startDate = input.nextInt();
                    String s = Integer.toString(startDate);
                    int endDate = durationHelper(s, 2);
                    taskSchedule.writeScheduleDuration(filename, startDate, endDate);
                    break;
                }
                case 12: {
                    System.out.println("Enter the name of the new .json file: ");
                    String filename = input.next();
                    System.out.println("Enter the start date: ");
                    int startDate = input.nextInt();
                    String s = Integer.toString(startDate);
                    int endDate = durationHelper(s, 3);
                    taskSchedule.writeScheduleDuration(filename, startDate, endDate);
                    break;
                }
                case 0: {
                    input.close();
                    System.exit(0);
                }
                default:
                    break;
            }

        }

    }
    //return the proper end date
    private static int durationHelper(String dateString, int increment) {
        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int day = Integer.parseInt(dateString.substring(6, 8));

        LocalDate start = LocalDate.of(year, month, day);
        LocalDate end = start;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        //day 
        if(increment == 1) {
            end = start.plusDays(1);
        } 
        //month
        else if(increment == 2) {
            end = start.plusWeeks(1);
        }
        //year
        else if(increment == 3) {
            end = start.plusMonths(1);
        }
        else {
            throw new IllegalArgumentException();
        }

        String temp = end.format(formatter);

        return Integer.parseInt(temp);
    }
}
