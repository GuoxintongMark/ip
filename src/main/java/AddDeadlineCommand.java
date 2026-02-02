import java.util.ArrayList;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) throws LucyException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        Storage.save(tasks);
        ui.showAdded(task, tasks.size());
    }
}
