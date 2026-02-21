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

    /**
     * Creates a command to add a event task.
     *
     * @param description Description of the event task.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws LucyException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        Storage.save(tasks);

        return "-> Got it. I've added this task:\n"
                + "->   " + task + "\n"
                + "-> Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }
}
