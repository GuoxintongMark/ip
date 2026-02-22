package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;

/**
 * Marks the task by the index.
 */
public class MarkCommand extends Command {
    private final int index;
    private boolean previousState;
    private Task target;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        target = tasks.get(index);
        previousState = target.isDone();
        target.markAsDone();
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        Storage.save(tasks);

        return "-> Nice! I've marked this task as done:\n"
                + "->   " + task;
    }

    @Override
    public String undo(ArrayList<Task> tasks) throws LucyException {
        if (!previousState) {
            target.markUnDone();
        }
        Storage.save(tasks);
        return "-> Undone: restored task state.";
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
