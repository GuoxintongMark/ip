package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Task;
import lucy.task.Todo;
import lucy.ui.Ui;

/**
 * Adds a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) throws LucyException {
        Task task = new Todo(description);
        tasks.add(task);
        Storage.save(tasks);
        ui.showAdded(task, tasks.size());
    }
}
