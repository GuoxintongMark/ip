import java.util.Scanner;

public class Lucy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial greeting
        System.out.println("____________________________________________________________");
        System.out.println("-> Hello! I'm Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            // Read user input
            String command = scanner.nextLine().trim();

            // bye
            if (command.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("-> Bye. Hope to see you again soon! (*´∀`)~♥");
                System.out.println("____________________________________________________________");
                break;
            }

            // Echo command back
            System.out.println("____________________________________________________________");
            System.out.println("-> " + command);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
