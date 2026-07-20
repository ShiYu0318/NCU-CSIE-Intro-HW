package hw.sample.myPackage;

import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SampleController {
    @FXML private Group loginGroup, welcomeGroup;
    @FXML private TextField accountField, captchaField;
    @FXML private ImageView captchaImage;
    @FXML private Button refreshBtn, submitBtn;
    @FXML private Text welcomeText;

    private String currentCaptcha;
    private int failCount = 0;
    private boolean locked = false;

    @FXML
    public void initialize() {
        generateCaptcha();
    }

    @FXML
    private void onRefreshCaptcha() {
        if (!locked) {
            captchaField.clear();
            generateCaptcha();
        }
    }

    @FXML
    private void onSubmit() {
        if (locked) return;

        String input = captchaField.getText().trim();
        if (input.equalsIgnoreCase(currentCaptcha)) {
            showWelcome();
        } else {
            failCount++;
            if (failCount >= 5) {
                lockForSeconds(10);
            }
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "驗證碼錯誤！剩餘 " + Math.max(0, 5 - failCount) + " 次機會"
            );
            alert.showAndWait();
        }
    }

    @FXML
    private void onBack() {
        // 回到登入頁
        welcomeGroup.setVisible(false);
        loginGroup.setVisible(true);
        // 重新啟用
        locked = false;
        failCount = 0;
        refreshBtn.setDisable(false);
        submitBtn.setDisable(false);
        accountField.clear();
        captchaField.clear();
        generateCaptcha();
    }

    private void showWelcome() {
        String user = accountField.getText().trim();
        welcomeText.setText(user + "，歡迎！");
        loginGroup.setVisible(false);
        welcomeGroup.setVisible(true);
    }

    private void lockForSeconds(int sec) {
        locked = true;
        refreshBtn.setDisable(true);
        submitBtn.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(sec));
        pause.setOnFinished(e -> {
            locked = false;
            failCount = 0;
            refreshBtn.setDisable(false);
            submitBtn.setDisable(false);
            generateCaptcha();
        });
        pause.play();
    }

    private void generateCaptcha() {
        currentCaptcha = randomText(5);
        // 用 Group + Text 來畫驗證碼
        Group g = new Group();
        Random rnd = new Random();
        for (int i = 0; i < currentCaptcha.length(); i++) {
            Text t = new Text(String.valueOf(currentCaptcha.charAt(i)));
            t.setFont(Font.font(24));
            t.setX(10 + i * 20 + rnd.nextInt(6));
            t.setY(30 + rnd.nextInt(10));
            t.setRotate(rnd.nextInt(20) - 10);
            g.getChildren().add(t);
        }
        // snapshot 成為 Image
        WritableImage img = new WritableImage(120, 40);
        g.snapshot(new SnapshotParameters(), img);
        captchaImage.setImage(img);
    }

    private String randomText(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
