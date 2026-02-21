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

    /**
     * Creates a command to add a deadline task.
     *
     * @param description Description of the deadline task.
     * @param by Due date of the task in yyyy-MM-dd format.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        Storage.save(tasks);

        return "-> Got it. I've added this task:\n"
                + "->   " + task + "\n"
                + "-> Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }
}
