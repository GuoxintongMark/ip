package lucy.parser;

import lucy.command.AddDeadlineCommand;
import lucy.command.AddEventCommand;
import lucy.command.AddTodoCommand;
import lucy.command.Command;
import lucy.command.DeleteCommand;
import lucy.command.ExitCommand;
import lucy.command.FindCommand;
import lucy.command.ListCommand;
import lucy.command.MarkCommand;
import lucy.command.UnmarkCommand;
import lucy.exception.LucyException;

/**
 * Handles the interpretation of user input and executes task-related logic.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     * <p>
     * This method analyzes the given input string, determines the type
     * of command requested by the user, and constructs an appropriate
     * {@link Command} object to represent that action.
     *
     * @param input The full command entered by the user.
     * @return A {@link Command} representing the parsed user instruction.
     * @throws LucyException If the input is invalid command.
     */
    public Command parse(String input) throws LucyException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("todo")) {
            String desc = after(input, "todo");
            if (desc.isEmpty()) {
                throw new LucyException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(desc);
        }

        if (input.startsWith("deadline")) {
            String rest = after(input, "deadline");
            String[] parts = rest.split(" /by ");
            if (parts.length < 2) {
                throw new LucyException("lucy.task.Deadline must have /by.");
            }
            return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
        }

        if (input.startsWith("event")) {
            String rest = after(input, "event");
            String[] parts = rest.split(" /from | /to ");
            if (parts.length < 3) {
                throw new LucyException("lucy.task.Event must have /from and /to.");
            }
            return new AddEventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
        }

        if (input.startsWith("find")) {
            String keyword = input.length() > 4 ? input.substring(4).trim() : "";
            if (keyword.isEmpty()) {
                throw new LucyException("Find keyword cannot be empty.");
            }
            return new FindCommand(keyword);
        }


        if (input.startsWith("mark")) {
            String idxStr = after(input, "mark");
            int idx = parseIndex(idxStr);
            return new MarkCommand(idx);
        }

        if (input.startsWith("unmark")) {
            String idxStr = after(input, "unmark");
            int idx = parseIndex(idxStr);
            return new UnmarkCommand(idx);
        }

        if (input.startsWith("delete")) {
            String idxStr = after(input, "delete");
            int idx = parseIndex(idxStr);
            return new DeleteCommand(idx);
        }

        throw new LucyException("I'm sorry, but I don't know what that means :-(");
    }

    private int parseIndex(String raw) throws LucyException {
        try {
            int idx = Integer.parseInt(raw.trim()) - 1;
            if (idx < 0) {
                throw new LucyException("That task number does not exist.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new LucyException("Please provide a valid task number.");
        }
    }

    private String after(String input, String keyword) {
        return input.length() <= keyword.length()
                ? ""
                : input.substring(keyword.length()).trim();
    }
}
