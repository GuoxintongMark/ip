package lucy.command;

import java.util.ArrayList;

import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) {
        ui.showList(tasks);
    }
}
