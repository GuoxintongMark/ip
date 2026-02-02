import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        printLine();
        System.out.println("-> Hello! I'm Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        printLine();
    }

    public void showBye() {
        printLine();
        System.out.println("-> Bye. Hope to see you again soon! (*´∀`)~♥");
        printLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        printLine();
        System.out.println("-> OOPS!!! " + message);
        printLine();
    }

    public void showAdded(Task task, int count) {
        printLine();
        System.out.println("-> Got it. I've added this task:");
        System.out.println("->   " + task);
        System.out.println("-> Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
        printLine();
    }

    public void showList(List<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("-> No tasks yet!");
        } else {
            System.out.println("-> Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("-> " + (i + 1) + ". " + tasks.get(i));
            }
        }
        printLine();
    }

    public void showMarked(Task task, boolean done) {
        printLine();
        if (done) {
            System.out.println("-> Nice! I've marked this task as done:");
        } else {
            System.out.println("-> OK, I've marked this task as not done yet:");
        }
        System.out.println("->   " + task);
        printLine();
    }

    public void showDeleted(Task removed, int remaining) {
        printLine();
        System.out.println("-> Noted. I've removed this task:");
        System.out.println("->   " + removed);
        System.out.println("-> Now you have " + remaining + (remaining == 1 ? " task" : " tasks") + " in the list.");
        printLine();
    }
}
