package lucy;

import lucy.command.Command;
import lucy.exception.LucyException;
import lucy.parser.Parser;
import lucy.storage.Storage;
import lucy.task.Task;
import lucy.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class LucyApp {
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final ArrayList<Task> tasks;

    public LucyApp() {
        ArrayList<Task> loaded;
        try {
            loaded = Storage.load();
        } catch (LucyException e) {
            loaded = new ArrayList<>();
        }
        this.tasks = loaded;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (LucyException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
