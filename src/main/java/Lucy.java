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

        printLine();
        System.out.println("-> Hello! I'm Lucy. (′゜ω。‵)");
        System.out.println("-> What can I do for you?");
        printLine();

        Task[] tasks = new Task[100];
        int count = 0;

        while (true) {
            try {
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("bye")) {
                    printLine();
                    System.out.println("-> Bye. Hope to see you again soon! (*´∀`)~♥");
                    printLine();
                    break;
                }

                else if (command.equals("list")) {
                    printLine();
                    if (count == 0) {
                        System.out.println("-> No tasks yet!");
                    } else {
                        System.out.println("-> Here are the tasks in your list:");
                        for (int i = 0; i < count; i++) {
                            System.out.println("-> " + (i + 1) + "." + tasks[i]);
                        }
                    }
                    printLine();
                }

                else if (command.equals("todo")) {
                    throw new LucyException("The description of <todo> cannot be empty.");
                }

                else if (command.startsWith("todo ")) {
                    String desc = command.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new LucyException("The description of <todo> cannot be empty.");
                    }
                    tasks[count++] = new Todo(desc);
                    printAddMessage(tasks[count - 1], count);
                }

                else if (command.startsWith("deadline ")) {
                    String[] parts = command.substring(9).split(" /by ");
                    if (parts.length < 2) {
                        throw new LucyException("<Deadline> must have /by <time>.");
                    }
                    tasks[count++] = new Deadline(parts[0].trim(), parts[1].trim());
                    printAddMessage(tasks[count - 1], count);
                }

                else if (command.startsWith("event ")) {
                    String[] parts = command.substring(6).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new LucyException("<Event> must have /from <start> /to <end>.");
                    }
                    tasks[count++] = new Event(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim()
                    );
                    printAddMessage(tasks[count - 1], count);
                }

                else if (command.startsWith("mark ")) {
                    int index = parseIndex(command.substring(5), count);
                    tasks[index].markAsDone();
                    printLine();
                    System.out.println("-> Nice! I've marked this task as done:");
                    System.out.println("->   " + tasks[index]);
                    printLine();
                }

                else if (command.startsWith("unmark ")) {
                    int index = parseIndex(command.substring(7), count);
                    tasks[index].markUnDone();
                    printLine();
                    System.out.println("-> OK, I've marked this task as not done yet:");
                    System.out.println("->   " + tasks[index]);
                    printLine();
                }

                else {
                    throw new LucyException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (LucyException e) {
                printLine();
                System.out.println("-> OOPS!!! " + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

    private static int parseIndex(String input, int count) throws LucyException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            if (index < 0 || index >= count) {
                throw new LucyException("Task number is out of range.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new LucyException("Please provide a valid task number.");
        }
    }
}
