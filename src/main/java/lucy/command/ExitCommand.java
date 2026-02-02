package lucy.command;

import lucy.task.Task;
import lucy.ui.Ui;

import java.util.ArrayList;

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
