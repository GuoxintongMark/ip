package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Represents an executable user command in the Lucy chatbot.
 * Each command performs an action on the task list and interacts
 * with the user interface.
 */
public abstract class Command {
    public abstract String execute(ArrayList<Task> tasks) throws LucyException;

    public boolean isExit() {
        return false;
    }
}
