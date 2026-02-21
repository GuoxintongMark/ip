package lucy.command;

import java.util.ArrayList;

import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Exits the chat.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(ArrayList<Task> tasks) {
        return "-> Bye. Hope to see you again soon! (*´∀`)~♥";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
