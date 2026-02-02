import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int index) throws LucyException {
        if (index < 0 || index >= tasks.size()) {
            throw new LucyException("That task number does not exist.");
        }
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws LucyException {
        Task removed = get(index);
        tasks.remove(index);
        return removed;
    }

    public List<Task> asUnmodifiableList() {
        return List.copyOf(tasks);
    }
}
