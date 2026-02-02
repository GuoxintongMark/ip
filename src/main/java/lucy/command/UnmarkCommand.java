package lucy.command;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;
import lucy.ui.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) throws LucyException {
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        Task task = tasks.get(index);
        task.markUnDone();
        Storage.save(tasks);
        ui.showMarked(task, false);
    }
}
