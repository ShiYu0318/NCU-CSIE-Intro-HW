package hw.sample.myPackage2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class SampleController {

    @FXML private Button btnUpdateText;
    @FXML private Text labelText;
    @FXML private ImageView imageView;
    @FXML private Circle circle;
    @FXML private Text groupLabel;

    @FXML
    private void initialize() {
        // Load image from file
        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/JavaFX_Logo.png/600px-JavaFX_Logo.png");
        imageView.setImage(image);
    }

    @FXML
    private void handleUpdateText() {
        labelText.setText("Hello, JavaFX!");
        groupLabel.setText("Updated!");
        circle.setStyle("-fx-fill: coral;");
    }
}