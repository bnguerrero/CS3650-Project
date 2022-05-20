import java.util.Scanner;

public class PSS {
    public static void main(String[] args) {
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
                    // for testing
                    Runtime testRuntime = new Runtime();
                    testRuntime.startTime = 20;
                    testRuntime.duration = 60;
                    RecurringTask testTask1 = new RecurringTask("testTask1", 1, testRuntime, 100, 7);
                    TransientTask testTask2 = new TransientTask("testTask2", 40, testRuntime);
                    taskSchedule.addTask(testTask1);
                    taskSchedule.addTask(testTask2);
                    break;
                }
                case 2: {
                    System.out.println("Enter task to view:");
                    input.nextLine();
                    String taskName = input.nextLine();
                    int loc = taskSchedule.findTask(taskName);
                    if (loc == -1)
                        System.out.println("Error: task not found");
                    taskSchedule.viewTask(taskSchedule.getTask(loc));
                    break;
                }
                case 3: {
                    System.out.println("Enter task to delete:");
                    input.nextLine();
                    String taskName = input.nextLine();
                    int loc = taskSchedule.findTask(taskName);
                    if (loc == -1)
                        System.out.println("Error: task not found");
                    taskSchedule.deleteTask(taskSchedule.getTask(loc));
                    break;
                }
                case 4: {
                    // taskSchedule.editTask(task);
                    break;
                }
                case 5: {
                    // taskSchedule.writeSchedule(filename);
                    break;
                }
                case 6: {
                    // taskSchedule.readSchedule(filename);
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
                    // taskSchedule.writeScheduleDuration(filename, duration);
                    break;
                }
                case 11: {
                    // taskSchedule.writeScheduleDuration(filename, duration);
                    break;
                }
                case 12: {
                    // taskSchedule.writeScheduleDuration(filename, duration);
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
}
