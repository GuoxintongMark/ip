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

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        Storage.save(tasks);

        return "-> Nice! I've marked this task as done:\n"
                + "->   " + task;
    }
}
