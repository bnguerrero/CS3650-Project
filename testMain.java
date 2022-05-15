public class testMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Schedule testSchedule = new Schedule();

        System.out.println("Please select an option below: \n 1. Create a task \n 2. View a task \n 3. Delete a task \n 4. Edit a task"
        + "\n 5. Write the schedule to a file \n 6. Read the Schedule to a file \n 7. View schedule for 1 day \n 8. View schedule for 1 week"
        + "\n 9. View schedule for 1 month \n 10. Write schedule for 1 day \n 11. Write schedule for 1 week \n 12. Write schedule for 1 month");
        int option = scan.nextInt();

        if (option ==1){
            testSchedule.addTask();
        }
        if (option ==2){
            testSchedule.viewTask(task);
        }
        if (option ==3){
            testSchedule.deleteTask(task);
        }
        if (option ==4){
            testSchedule.editTask(task);
        }
        if (option ==4){
    




        scan.close();
    }
}
