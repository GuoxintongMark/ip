import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = "data/lucy.txt";

    public static List<Task> load() throws LucyException {
        List<Task> tasks = new ArrayList<>();

        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(FILE_PATH);
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

    public static void save(List<Task> tasks) throws LucyException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                pw.println(task.toFileString());
            }
        } catch (IOException e) {
            throw new LucyException("Failed to save data file.");
        }
    }

    private static Task parseLine(String line) throws LucyException {
        String[] parts = line.split(" \\| ");

        switch (parts[0]) {
            case "T":
                Todo t = new Todo(parts[2]);
                if (parts[1].equals("1")) t.markAsDone();
                return t;

            case "D":
                Deadline d = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) d.markAsDone();
                return d;

            case "E":
                Event e = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) e.markAsDone();
                return e;

            default:
                throw new LucyException("Corrupted data file.");
        }
    }
}
