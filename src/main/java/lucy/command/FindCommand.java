package lucy.command;

import java.util.ArrayList;

import lucy.task.Task;

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
    public String execute(ArrayList<Task> tasks) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }

        if (matches.isEmpty()) {
            return "-> No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("-> Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append("-> ").append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
