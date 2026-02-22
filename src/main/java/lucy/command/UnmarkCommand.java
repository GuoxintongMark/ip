package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    private Task targetTask;
    private boolean wasDoneBefore;

    /**
     * Creates a command to unmark a task.
     *
     * @param index Zero-based index of the task.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }

        targetTask = tasks.get(index);
        wasDoneBefore = targetTask.isDone();

        targetTask.markUnDone();
        Storage.save(tasks);

        return "-> OK, I've marked this task as not done yet:\n"
                + "->   " + targetTask;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public String undo(ArrayList<Task> tasks) throws LucyException {
        if (targetTask == null) {
            throw new LucyException("Nothing to undo.");
        }

        if (wasDoneBefore) {
            targetTask.markAsDone();
        } else {
            targetTask.markUnDone();
        }

        Storage.save(tasks);

        return "-> Undone: restored task state\n"
                + "->   " + targetTask;
    }

    /**
     * Checks whether a task is done by inspecting its string form.
     * This avoids adding new public APIs just for Undo.
     *
     * @param task Task to check.
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    private boolean isDone(Task task) {
        // Task.toString() format: [T][X] desc  OR  [T][ ] desc
        return task.toString().contains("[X]");
    }
}
