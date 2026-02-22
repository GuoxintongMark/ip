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
import lucy.command.UndoCommand;
import lucy.command.UnmarkCommand;
import lucy.exception.LucyException;

/**
 * Handles the interpretation of user input and executes task-related logic.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     */
    public Command parse(String input) throws LucyException {

        assert input != null : "Parser.parse() received null input";

        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.equals("undo")) {
            return new UndoCommand();
        }

        if (input.startsWith("todo")) {
            String desc = after(input, "todo");
            assert desc != null : "after() returned null for todo";

            if (desc.isEmpty()) {
                throw new LucyException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(desc);
        }

        if (input.startsWith("deadline")) {
            String rest = after(input, "deadline");

            assert rest != null : "after() returned null for deadline";

            String[] parts = rest.split(" /by ");

            assert parts != null : "Split result is null for deadline";

            if (parts.length < 2) {
                throw new LucyException("lucy.task.Deadline must have /by.");
            }

            return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
        }

        if (input.startsWith("event")) {
            String rest = after(input, "event");

            assert rest != null : "after() returned null for event";

            String[] parts = rest.split(" /from | /to ");

            assert parts != null : "Split result is null for event";

            if (parts.length < 3) {
                throw new LucyException("lucy.task.Event must have /from and /to.");
            }

            return new AddEventCommand(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim()
            );
        }

        if (input.startsWith("find")) {
            String keyword = input.length() > 4 ? input.substring(4).trim() : "";

            assert keyword != null : "Keyword should never be null";

            if (keyword.isEmpty()) {
                throw new LucyException("Find keyword cannot be empty.");
            }

            return new FindCommand(keyword);
        }

        if (input.startsWith("mark")) {
            String idxStr = after(input, "mark");

            assert idxStr != null : "after() returned null for mark";

            int idx = parseIndex(idxStr);
            return new MarkCommand(idx);
        }

        if (input.startsWith("unmark")) {
            String idxStr = after(input, "unmark");

            assert idxStr != null : "after() returned null for unmark";

            int idx = parseIndex(idxStr);
            return new UnmarkCommand(idx);
        }

        if (input.startsWith("delete")) {
            String idxStr = after(input, "delete");

            assert idxStr != null : "after() returned null for delete";

            int idx = parseIndex(idxStr);
            return new DeleteCommand(idx);
        }

        throw new LucyException("I'm sorry, but I don't know what that means :-(");
    }

    private int parseIndex(String raw) throws LucyException {

        assert raw != null : "parseIndex() received null raw string";

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

        assert input != null : "after() received null input";
        assert keyword != null : "after() received null keyword";

        return input.length() <= keyword.length()
                ? ""
                : input.substring(keyword.length()).trim();
    }
}
