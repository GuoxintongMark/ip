package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;

/**
 * Deletes the task by the index.
 */
public class DeleteCommand extends Command {
    private final int index;
    private Task removedTask;
    private int removedIndex;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        removedTask = tasks.remove(index);
        removedIndex = index;
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        Task removed = tasks.remove(index);
        Storage.save(tasks);

        return "-> Noted. I've removed this task:\n"
                + "->   " + removed + "\n"
                + "-> Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    @Override
    public String undo(ArrayList<Task> tasks) throws LucyException {
        tasks.add(removedIndex, removedTask);
        Storage.save(tasks);
        return "-> Undone: restored task " + removedTask;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
