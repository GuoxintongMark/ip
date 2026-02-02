import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DIR = "data";
    private static final String FILE = "data/lucy.txt";

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static ArrayList<Task> load() throws LucyException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dir = new File(DIR);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(FILE);
        if (!file.exists()) return tasks;

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
                if (parts.length < 4) throw new LucyException("Corrupted data file.");
                LocalDate by = LocalDate.parse(parts[3], DATE_FORMAT);
                task = new Deadline(desc, by);
                break;

            case "E":
                if (parts.length < 5) throw new LucyException("Corrupted data file.");
                LocalDateTime from = LocalDateTime.parse(parts[3], DATETIME_FORMAT);
                LocalDateTime to = LocalDateTime.parse(parts[4], DATETIME_FORMAT);
                task = new Event(desc, from, to);
                break;

            default:
                throw new LucyException("Corrupted data file.");
        }

        if ("1".equals(done)) task.markAsDone();
        else if ("0".equals(done)) task.markUnDone();
        else throw new LucyException("Corrupted data file.");

        return task;
    }
}
