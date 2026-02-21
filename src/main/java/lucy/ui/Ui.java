package lucy.ui;

import java.util.List;
import java.util.Scanner;

import lucy.task.Task;

/**
 * Handles all interactions between the Lucy chatbot and the user.
 * Responsible for displaying messages and reading user input.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a horizontal divider line to separate sections of output.
     */
    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        printLine();
        System.out.println("-> Hello! I'm lucy.Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        printLine();
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showBye() {
        printLine();
        System.out.println("-> Bye. Hope to see you again soon! (*´∀`)~♥");
        printLine();
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The trimmed command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printLine();
        System.out.println("-> OOPS!!! " + message);
        printLine();
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param task The task that was added.
     * @param count The total number of tasks in the list.
     */
    public void showAdded(Task task, int count) {
        printLine();
        System.out.println("-> Got it. I've added this task:");
        System.out.println("->   " + task);
        System.out.println("-> Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
        printLine();
    }

    /**
     * Displays all tasks currently stored in the task list.
     *
     * @param tasks The list of tasks to display.
     */
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

    /**
     * Displays a message indicating that a task has been marked or unmarked.
     *
     * @param task The task that was updated.
     * @param done {@code true} if the task was marked as done,
     *             {@code false} if it was marked as not done.
     */
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

    /**
     * Displays the list of tasks that match a search keyword.
     *
     * @param matches The list of matching tasks.
     */
    public void showFindResults(List<Task> matches) {
        printLine();
        if (matches.isEmpty()) {
            System.out.println("-> No matching tasks found.");
        } else {
            System.out.println("-> Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println("-> " + (i + 1) + ". " + matches.get(i));
            }
        }
        printLine();
    }

    /**
     * Displays a confirmation message after deleting a task.
     *
     * @param removed The task that was removed.
     * @param remaining The number of tasks remaining in the list.
     */
    public void showDeleted(Task removed, int remaining) {
        printLine();
        System.out.println("-> Noted. I've removed this task:");
        System.out.println("->   " + removed);
        System.out.println("-> Now you have " + remaining + (remaining == 1 ? " task" : " tasks") + " in the list.");
        printLine();
    }

    /**
     * Displays a generic message.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }
}
