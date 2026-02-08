package lucy.task;

import lucy.exception.LucyException;

import java.util.ArrayList;
import java.util.List;

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
    }

    /**
     * Creates a task list initialized with the given tasks.
     *
     * @param loadedTasks List of tasks loaded from storage.
     */
    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
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
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index Zero-based index of the task.
     * @return The removed task.
     * @throws LucyException If the index is out of range.
     */
    public Task remove(int index) throws LucyException {
        Task removed = get(index);
        tasks.remove(index);
        return removed;
    }

    public List<Task> asUnmodifiableList() {
        return List.copyOf(tasks);
    }
}
