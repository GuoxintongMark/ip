import java.util.Scanner;

public class Lucy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial greeting
        System.out.println("____________________________________________________________");
        System.out.println("-> Hello! I'm Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        System.out.println("____________________________________________________________");

        String[] tasks = new String[100];
        int count = 0;// the number of the stored tasks

        while (true) {
            // Read user input
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("-> Bye. Hope to see you again soon! (*´∀`)~♥");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                if (count == 0) {
                    System.out.println("-> No tasks yet!");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.println("-> " + (i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                // add new tasks
                if (count < tasks.length) {
                    tasks[count] = command;
                    count++;
                    System.out.println("____________________________________________________________");
                    System.out.println("-> added: " + command);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("-> Task list is full!");
                    System.out.println("____________________________________________________________");
                }
            }

            // Echo command back
//            System.out.println("____________________________________________________________");
//            System.out.println("-> " + command);
//            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
