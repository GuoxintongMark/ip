package lucy.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A dialog box consisting of an avatar and a text label.
 */
public class DialogBox extends HBox {

    private static final double AVATAR_SIZE = 40;

    private DialogBox(String text, Image avatar, Pos alignment, boolean avatarOnRight) {
        Label label = new Label(text);
        label.setWrapText(true);

        ImageView avatarView = new ImageView(avatar);
        avatarView.setFitWidth(AVATAR_SIZE);
        avatarView.setFitHeight(AVATAR_SIZE);
        avatarView.setPreserveRatio(true);

        setAlignment(alignment);
        setSpacing(10);
        setPadding(new Insets(5, 10, 5, 10));

        if (avatarOnRight) {
            getChildren().addAll(label, avatarView);
        } else {
            getChildren().addAll(avatarView, label);
        }
    }

    private static Image loadImage(String path) {
        return new Image(DialogBox.class.getResourceAsStream(path));
    }

    public static DialogBox user(String text) {
        return new DialogBox(text, loadImage("/images/p1.png"), Pos.CENTER_RIGHT, true);
    }

    public static DialogBox bot(String text) {
        return new DialogBox(text, loadImage("/images/p2.png"), Pos.CENTER_LEFT, false);
    }
}