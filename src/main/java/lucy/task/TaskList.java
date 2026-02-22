package lucy.task;

import java.util.ArrayList;
import java.util.List;

import lucy.exception.LucyException;

/**
 * Represents a list of tasks in the application.
 * Provides operations to add, remove, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "Task list should never be null after construction";
    }

    /**
     * Creates a task list initialized with the given tasks.
     *
     * @param loadedTasks List of tasks loaded from storage.
     */
    public TaskList(List<Task> loadedTasks) {
        assert loadedTasks != null : "Loaded tasks should not be null";
        this.tasks = new ArrayList<>(loadedTasks);
        assert tasks != null : "Task list should never be null after construction";
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        assert tasks != null : "Task list should never be null";
        return tasks.size();
    }

    /**
     * Returns whether this task list contains no tasks.
     *
     * @return {@code true} if the task list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        assert tasks != null : "Task list should never be null";
        return tasks.isEmpty();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Zero-based index of the task.
     * @return The task at the given index.
     * @throws LucyException If the index is out of range.
     */
    public Task get(int index) throws LucyException {
        assert tasks != null : "Task list should never be null";

        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }

        Task task = tasks.get(index);
        assert task != null : "Task stored in list should not be null";
        return task;
    }

    public void add(Task task) {
        assert task != null : "Cannot add null task to TaskList";

        int oldSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == oldSize + 1 : "Task was not added properly";
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index Zero-based index of the task.
     * @return The removed task.
     * @throws LucyException If the index is out of range.
     */
    public Task remove(int index) throws LucyException {
        assert tasks != null : "Task list should never be null";

        int oldSize = tasks.size();
        Task removed = get(index);
        tasks.remove(index);

        assert tasks.size() == oldSize - 1 : "Task was not removed properly";
        assert removed != null : "Removed task should not be null";

        return removed;
    }

    /**
     * Finds tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword Keyword to search for.
     * @return List of matching tasks.
     */
    public List<Task> find(String keyword) {
        assert keyword != null : "Keyword should not be null";

        String key = keyword.toLowerCase();
        List<Task> matches = new ArrayList<>();

        for (Task t : tasks) {
            assert t != null : "Task in list should not be null";

            if (t.description.toLowerCase().contains(key)) {
                matches.add(t);
            }
        }

        assert matches != null : "Find result should not be null";
        return matches;
    }

    public List<Task> asUnmodifiableList() {
        assert tasks != null : "Task list should never be null";

        List<Task> copy = List.copyOf(tasks);
        assert copy != null : "Returned list should not be null";

        return copy;
    }
}
