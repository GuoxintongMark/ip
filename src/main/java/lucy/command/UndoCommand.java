package lucy.command;

import java.util.ArrayList;

import lucy.task.Task;

/**
 * Command to undo the most recent undoable command.
 */
public class UndoCommand extends Command {

    @Override
    public String execute(ArrayList<Task> tasks) {
        // Lucy.getResponse handles logic
        return "";
    }
}
