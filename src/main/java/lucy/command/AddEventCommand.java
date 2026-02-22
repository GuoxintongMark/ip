package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Event;
import lucy.task.Task;

/**
 * Adds an event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    private Task addedTask;

    /**
     * Creates a command to add an event task.
     *
     * @param description Description of the event task.
     * @param from Start date-time string (e.g. yyyy-MM-dd HHmm).
     * @param to End date-time string (e.g. yyyy-MM-dd HHmm).
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        addedTask = new Event(description, from, to);
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
