package lucy.command;

import lucy.exception.LucyException;
import lucy.task.Task;
import lucy.ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Ui ui) throws LucyException, LucyException;

    public boolean isExit() {
        return false;
    }
}
