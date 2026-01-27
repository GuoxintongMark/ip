import java.util.ArrayList;
import java.util.Scanner;

public class Lucy {

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printAddMessage(Task task, int count) {
        printLine();
        System.out.println("-> Got it. I've added this task:");
        System.out.println("->   " + task);
        System.out.println("-> Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
        printLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printLine();
        System.out.println("-> Hello! I'm Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        printLine();

        while (true) {
            try {
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("bye")) {
                    printLine();
                    System.out.println("-> Bye. Hope to see you again soon! (*´∀`)~♥");
                    printLine();
                    break;
                }

                if (command.equals("list")) {
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
                    continue;
                }

                if (command.startsWith("mark ")) {
                    int index = parseIndex(command, 5, tasks.size());
                    tasks.get(index).markAsDone();

                    printLine();
                    System.out.println("-> Nice! I've marked this task as done:");
                    System.out.println("->   " + tasks.get(index));
                    printLine();
                    continue;
                }

                if (command.startsWith("unmark ")) {
                    int index = parseIndex(command, 7, tasks.size());
                    tasks.get(index).markUnDone();

                    printLine();
                    System.out.println("-> OK, I've marked this task as not done yet:");
                    System.out.println("->   " + tasks.get(index));
                    printLine();
                    continue;
                }

                if (command.startsWith("delete ")) {
                    int index = parseIndex(command, 7, tasks.size());
                    Task removed = tasks.remove(index);

                    printLine();
                    System.out.println("-> Noted. I've removed this task:");
                    System.out.println("->   " + removed);
                    System.out.println("-> Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    continue;
                }

                if (command.startsWith("todo ")) {
                    String desc = command.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new LucyException("The description of a todo cannot be empty.");
                    }
                    Task task = new Todo(desc);
                    tasks.add(task);
                    printAddMessage(task, tasks.size());
                    continue;
                }

                if (command.startsWith("deadline ")) {
                    String[] parts = command.substring(9).split(" /by ");
                    if (parts.length < 2) {
                        throw new LucyException("Deadline must have /by.");
                    }
                    Task task = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(task);
                    printAddMessage(task, tasks.size());
                    continue;
                }

                if (command.startsWith("event ")) {
                    String[] parts = command.substring(6).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new LucyException("Event must have /from and /to.");
                    }
                    Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(task);
                    printAddMessage(task, tasks.size());
                    continue;
                }

                throw new LucyException("I'm sorry, but I don't know what that means :-(");

            } catch (LucyException e) {
                printLine();
                System.out.println("-> OOPS!!! " + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

    private static int parseIndex(String command, int start, int size) throws LucyException {
        try {
            int index = Integer.parseInt(command.substring(start).trim()) - 1;
            if (index < 0 || index >= size) {
                throw new LucyException("That task number does not exist.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new LucyException("Please provide a valid task number.");
        }
    }
}
