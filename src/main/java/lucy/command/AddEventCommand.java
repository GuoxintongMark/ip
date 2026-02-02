package lucy.command;

import lucy.exception.LucyException;
import lucy.storage.Storage;
import lucy.task.Event;
import lucy.task.Task;
import lucy.ui.Ui;

import java.util.ArrayList;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) throws LucyException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        Storage.save(tasks);
        ui.showAdded(task, tasks.size());
    }
}
