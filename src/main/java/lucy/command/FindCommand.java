package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Finds tasks whose descriptions contain a given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a find command with the given keyword.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) throws LucyException {
        ArrayList<Task> matches = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }

        ui.showFindResults(matches);
    }
}
