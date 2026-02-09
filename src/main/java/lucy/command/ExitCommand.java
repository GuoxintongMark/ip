package lucy.command;

import java.util.ArrayList;

import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Exits the chat.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
