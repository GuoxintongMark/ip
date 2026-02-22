package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;
import lucy.task.Todo;

/**
 * Adds a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;
    private Task addedTask;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        addedTask = new Todo(description);
        tasks.add(addedTask);
        Storage.save(tasks);

        return "-> Got it. I've added this task:\n"
                + "->   " + addedTask + "\n"
                + "-> Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    @Override
    public String undo(ArrayList<Task> tasks) throws LucyException {
        tasks.remove(addedTask);
        Storage.save(tasks);
        return "-> Undone: removed task " + addedTask;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

}
