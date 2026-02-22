package lucy;

import java.util.ArrayList;

import lucy.command.Command;
import lucy.command.UndoCommand;
import lucy.exception.LucyException;
import lucy.parser.Parser;
import lucy.storage.Storage;
import lucy.task.Task;

/**
 * Core logic of the Lucy chatbot.
 * <p>
 * Responsible for processing user input and returning
 * response messages. Does not handle UI rendering.
 */
public class Lucy {

    private final Parser parser = new Parser();
    private final ArrayList<Task> tasks;
    private Command lastCommand;

    /**
     * Creates a Lucy instance and loads saved tasks.
     */
    public Lucy() {
        ArrayList<Task> loaded;
        try {
            loaded = Storage.load();
        } catch (LucyException e) {
            loaded = new ArrayList<>();
        }
        this.tasks = loaded;
    }

    /**
     * Processes a user input and returns Lucy's response.
     *
     * @param input User input command.
     * @return Response message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);

            if (command instanceof UndoCommand) {
                if (lastCommand == null || !lastCommand.isUndoable()) {
                    return "-> Nothing to undo.";
                }
                String reply = lastCommand.undo(tasks);
                lastCommand = null;
                return reply;
            }

            String reply = command.execute(tasks);

            if (command.isUndoable()) {
                lastCommand = command;
            }

            return reply;

        } catch (LucyException e) {
            return "-> OOPS!!! " + e.getMessage();
        }
    }
}
