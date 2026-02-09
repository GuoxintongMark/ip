package lucy.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lucy.exception.LucyException;
import lucy.task.Deadline;
import lucy.task.Event;
import lucy.task.Task;
import lucy.task.Todo;

/**
 * Handles loading tasks from and saving tasks to the local storage file.
 * Tasks are stored in a text file using a predefined format and are
 * reconstructed when the application starts.
 */
public class Storage {
    private static final String DIR = "data";
    private static final String FILE = "data/lucy.txt";

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the data directory or file does not exist, an empty task list
     * will be returned.
     *
     * @return A list of tasks loaded from storage.
     * @throws LucyException If an error occurs while reading the data file
     *                       or the file contents are corrupted.
     */
    public static ArrayList<Task> load() throws LucyException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(FILE);
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseLine(line));
            }
        } catch (IOException e) {
            throw new LucyException("Failed to load data file.");
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws LucyException If an error occurs while writing to the data file.
     */
    public static void save(List<Task> tasks) throws LucyException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Task t : tasks) {
                pw.println(t.toFileString());
            }
        } catch (IOException e) {
            throw new LucyException("Failed to save data file.");
        }
    }

    private static Task parseLine(String line) throws LucyException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new LucyException("Corrupted data file.");
        }

        String type = parts[0];
        String done = parts[1];
        String desc = parts[2];

        Task task;

        switch (type) {
        case "T":
            task = new Todo(desc);
            break;

        case "D":
            if (parts.length < 4) {
                throw new LucyException("Corrupted data file.");
            }
            LocalDate by = LocalDate.parse(parts[3], DATE_FORMAT);
            task = new Deadline(desc, by);
            break;

        case "E":
            if (parts.length < 5) {
                throw new LucyException("Corrupted data file.");
            }
            LocalDateTime from = LocalDateTime.parse(parts[3], DATETIME_FORMAT);
            LocalDateTime to = LocalDateTime.parse(parts[4], DATETIME_FORMAT);
            task = new Event(desc, from, to);
            break;

        default:
            throw new LucyException("Corrupted data file.");
        }

        if ("1".equals(done)) {
            task.markAsDone();
        } else if ("0".equals(done)) {
            task.markUnDone();
        } else {
            throw new LucyException("Corrupted data file.");
        }

        return task;
    }
}
