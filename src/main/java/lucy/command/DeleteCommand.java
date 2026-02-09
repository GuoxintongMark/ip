package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Deletes the task by the index.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) throws LucyException {
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        Task removed = tasks.remove(index);
        Storage.save(tasks);
        ui.showDeleted(removed, tasks.size());
    }
}
