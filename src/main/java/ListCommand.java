import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui) {
        ui.showList(tasks);
    }
}
