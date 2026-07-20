package hw.sample.myPackage3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SampleController {

    @FXML
    private Button btn;

    @FXML
    private Text message;

    @FXML
    private void handleClick() {
        message.setText("Hello, JavaFX!");
    }
}