package lucy;

import java.util.ArrayList;

import lucy.command.Command;
import lucy.exception.LucyException;
import lucy.parser.Parser;
import lucy.storage.Storage;
import lucy.task.Task;
import lucy.ui.Ui;

/**
 * Coordinates the main execution flow of the Lucy chatbot application.
 * Responsible for initializing components, loading stored tasks,
 * and running the main command-processing loop.
 */
public class LucyApp {
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final ArrayList<Task> tasks;

    /**
     * Creates a new Lucy application instance.
     * <p>
     * Loads previously saved tasks from storage. If loading fails,
     * an empty task list is initialized instead.
     */
    public LucyApp() {
        ArrayList<Task> loaded;
        try {
            loaded = Storage.load();
        } catch (LucyException e) {
            loaded = new ArrayList<>();
        }
        this.tasks = loaded;
    }

    /**
     * Runs the main interaction loop of the chatbot.
     * <p>
     * Continuously reads user commands, parses and executes them,
     * and displays responses until an exit command is issued.
     */
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
