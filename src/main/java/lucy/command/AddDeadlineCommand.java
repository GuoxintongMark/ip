package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Deadline;
import lucy.task.Task;

/**
 * Adds a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;
    private Task addedTask;

    /**
     * Creates a command to add a deadline task.
     *
     * @param description Description of the deadline task.
     * @param by Due date string (e.g. yyyy-MM-dd).
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        addedTask = new Deadline(description, by);
        tasks.add(addedTask);
        Storage.save(tasks);

        return "-> Got it. I've added this task:\n"
                + "->   " + addedTask + "\n"
                + "-> Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public String undo(ArrayList<Task> tasks) throws LucyException {
        if (addedTask == null) {
            throw new LucyException("Nothing to undo.");
        }
        boolean removed = tasks.remove(addedTask);
        if (!removed) {
            throw new LucyException("Unable to undo: task no longer exists.");
        }
        Storage.save(tasks);

        return "-> Undone: removed task\n"
                + "->   " + addedTask + "\n"
                + "-> Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
    }
}
