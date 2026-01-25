import java.util.Scanner;

public class Lucy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial greeting
        System.out.println("____________________________________________________________");
        System.out.println("-> Hello! I'm Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] tasks = new Task[100];
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
                    System.out.println("-> Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println("-> " + (i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("mark ")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                tasks[index].markAsDone();

                System.out.println("____________________________________________________________");
                System.out.println("-> Nice! I've marked this task as done:");
                System.out.println("->   " + tasks[index]);
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("unmark ")) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                tasks[index].markUnDone();

                System.out.println("____________________________________________________________");
                System.out.println("-> OK, I've marked this task as not done yet:");
                System.out.println("->   " + tasks[index]);
                System.out.println("____________________________________________________________");
            } else {
                tasks[count] = new Task(command);
                count++;

                System.out.println("____________________________________________________________");
                System.out.println(" added: " + command);
                System.out.println("____________________________________________________________");
            }

            // Echo command back
//            System.out.println("____________________________________________________________");
//            System.out.println("-> " + command);
//            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
