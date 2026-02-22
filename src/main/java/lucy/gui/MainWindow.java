package lucy.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lucy.Lucy;

/**
 * Controller for the main window.
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private final Lucy lucy = new Lucy();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.bot("-> Hello! I'm Lucy. (′゜ω。‵)\n-> What can I do for you?"));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lucy.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.user(input),
                DialogBox.bot(response)
        );

        userInput.clear();
        if (lucy.isExit()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
