import java.util.Scanner;
public class testMain {
    private static Task task;
    private static String filename;
    private static int duration;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Schedule testSchedule = new Schedule();

        
        System.out.println("Please select an option below: \n 1. Create a task \n 2. View a task \n 3. Delete a task \n 4. Edit a task"
        + "\n 5. Write the schedule to a file \n 6. Read the Schedule to a file \n 7. View schedule for 1 day \n 8. View schedule for 1 week"
        + "\n 9. View schedule for 1 month \n 10. Write schedule for 1 day \n 11. Write schedule for 1 week \n 12. Write schedule for 1 month");
        int option = scan.nextInt();

        switch(option)
        {
            case 1:{
                testSchedule.addTask();
            }
            case 2:{
                testSchedule.viewTask(task);
            }
            case 3:{
                testSchedule.deleteTask(task);
            }
            // case 4:{
            //     testSchedule.editTask(task);
            // }
            // case 5:{
            //     testSchedule.writeSchedule(filename);
            // }
            // case 6:{
            //     testSchedule.readSchedule(filename);
            // }
            // case 7:{
            //     testSchedule.viewScheduleDuration(filename, duration);
            // }
            // case 8:{
            //     testSchedule.viewScheduleDuration(filename, duration);
            // }
            // case 9:{
            //     testSchedule.viewScheduleDuration(filename, duration);
            // }
            // case 10:{
            //     testSchedule.writeScheduleDuration(filename, duration);
            // }
            // case 11:{
            //     testSchedule.writeScheduleDuration(filename, duration);
            // }
            // case 12:{
            //     testSchedule.writeScheduleDuration(filename, duration);
            // }
        }
    




        scan.close();
    }
}
