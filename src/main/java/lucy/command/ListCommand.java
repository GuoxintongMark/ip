package lucy.command;

import java.util.ArrayList;

import lucy.task.Task;

/**
 * Displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "-> No tasks yet!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("-> Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("-> ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
