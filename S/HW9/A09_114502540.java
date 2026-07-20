package hw.hw9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class A09_114502540 extends Application
{
    private static final double GAME_W = 800;
    private static final double GAME_H = 500;
    private static final double RADIUS = 15;
    private static final double PAD_W = 100;
    private static final double PAD_H = 12;
    private static final double PAD_SPD = 15;
    private static final int INIT_TIME = 60;

    private static final Color[] NORMAL_COLORS = {
        Color.ROYALBLUE, Color.TOMATO, Color.MEDIUMSEAGREEN,
        Color.MEDIUMPURPLE, Color.DARKORANGE, Color.DEEPPINK
    };

    private Pane gamePane;
    private Label infoLabel;
    private Rectangle paddle;

    private final List<Ball> balls = new ArrayList<>();
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private final Random rand = new Random();

    private int score = 0;
    private int timeLeft = INIT_TIME;
    private double speed = 1.0;
    private boolean running = false;

    private Timeline gameLoop;
    private Timeline countdown;
    private Timeline bonusSpawn;

    private class Ball
    {
        final Circle circle;
        double dx, dy;
        boolean gold;
        private double dragOffX, dragOffY;
        private boolean wasDragged;

        Ball(double x, double y, boolean gold)
        {
            this.gold = gold;
            circle = new Circle(x, y, RADIUS);
            applyColor();
            dx = (rand.nextBoolean() ? 1 : -1) * (rand.nextDouble() * 2 + 1);
            dy = rand.nextDouble() * 2 + 1;
            setupMouseEvents();
        }

        void applyColor()
        {
            circle.setFill(gold ? Color.GOLD : NORMAL_COLORS[rand.nextInt(NORMAL_COLORS.length)]);
            circle.setStroke(gold ? Color.DARKGOLDENROD : Color.TRANSPARENT);
            circle.setStrokeWidth(gold ? 2 : 0);
        }

        void setupMouseEvents()
        {
            circle.setOnMousePressed(e ->
            {
                if (e.getButton() == MouseButton.PRIMARY)
                {
                    dragOffX = circle.getCenterX() - e.getX();
                    dragOffY = circle.getCenterY() - e.getY();
                    wasDragged = false;
                    e.consume();
                }
            });
            circle.setOnMouseDragged(e ->
            {
                if (e.getButton() == MouseButton.PRIMARY)
                {
                    wasDragged = true;
                    double nx = Math.max(RADIUS, Math.min(GAME_W - RADIUS, e.getX() + dragOffX));
                    double ny = Math.max(RADIUS, Math.min(GAME_H - RADIUS, e.getY() + dragOffY));
                    circle.setCenterX(nx);
                    circle.setCenterY(ny);
                    e.consume();
                }
            });
            circle.setOnMouseClicked(e ->
            {
                if (e.getButton() == MouseButton.PRIMARY && !wasDragged && running)
                {
                    score += gold ? 5 : 1;
                    gold = !gold;
                    applyColor();
                    refreshInfo();
                    e.consume();
                }
            });
        }
    }

    @Override
    public void start(Stage primaryStage)
    {
        infoLabel = new Label();
        infoLabel.setFont(javafx.scene.text.Font.font("Arial", 14));
        HBox topBar = new HBox(infoLabel);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(6));
        topBar.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ccc; -fx-border-width: 0 0 1 0;");

        gamePane = new Pane();
        gamePane.setPrefSize(GAME_W, GAME_H);
        gamePane.setStyle("-fx-background-color: lightyellow;");

        paddle = new Rectangle(GAME_W / 2 - PAD_W / 2, GAME_H - 50, PAD_W, PAD_H);
        paddle.setFill(Color.DARKBLUE);
        gamePane.getChildren().add(paddle);

        gamePane.setOnMouseClicked(e ->
        {
            if (e.getButton() == MouseButton.SECONDARY && running)
                addBallAt(e.getX(), e.getY(), false);
        });

        Button btnStart = new Button("Start");
        Button btnPause = new Button("Pause");
        Button btnReset = new Button("Reset");
        Button btnAdd = new Button("Add Ball");
        Button btnRemove = new Button("Remove Ball");
        Button btnExit = new Button("Exit");

        btnStart.setOnAction(e -> startGame());
        btnPause.setOnAction(e -> pauseGame());
        btnReset.setOnAction(e -> resetGame());
        btnAdd.setOnAction(e -> addBall());
        btnRemove.setOnAction(e -> removeBall());
        btnExit.setOnAction(e -> Platform.exit());

        HBox btnBar = new HBox(10, btnStart, btnPause, btnReset, btnAdd, btnRemove, btnExit);
        btnBar.setAlignment(Pos.CENTER);
        btnBar.setPadding(new Insets(8));
        btnBar.setStyle("-fx-background-color: #e8e8e8;");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(gamePane);
        root.setBottom(btnBar);

        Scene scene = new Scene(root);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e ->
        {
            handleKeyDown(e.getCode());
            switch (e.getCode())
            {
                case UP: case DOWN: case LEFT: case RIGHT:
                    e.consume();
                    break;
                default: break;
            }
        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> pressedKeys.remove(e.getCode()));

        primaryStage.setTitle("Bouncing Ball Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        addBallAt(GAME_W / 2, GAME_H / 4, false);
        refreshInfo();
    }

    private void startGame()
    {
        if (running) return;
        running = true;

        gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> gameStep()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        countdown = new Timeline(new KeyFrame(Duration.seconds(1), e ->
        {
            timeLeft--;
            refreshInfo();
            if (timeLeft <= 0) endGame();
        }));
        countdown.setCycleCount(Timeline.INDEFINITE);
        countdown.play();

        bonusSpawn = new Timeline(new KeyFrame(Duration.seconds(10), e ->
        {
            if (running)
                addBallAt(rand.nextDouble() * (GAME_W - 2 * RADIUS) + RADIUS,
                          rand.nextDouble() * (GAME_H / 3) + RADIUS, true);
        }));
        bonusSpawn.setCycleCount(Timeline.INDEFINITE);
        bonusSpawn.play();
    }

    private void pauseGame()
    {
        if (!running) return;
        if (gameLoop.getStatus() == javafx.animation.Animation.Status.RUNNING)
        {
            gameLoop.pause();
            countdown.pause();
            bonusSpawn.pause();
        }
        else
        {
            gameLoop.play();
            countdown.play();
            bonusSpawn.play();
        }
    }

    private void resetGame()
    {
        stopTimelines();
        running = false;
        score = 0;
        timeLeft = INIT_TIME;
        speed = 1.0;
        pressedKeys.clear();

        for (Ball b : balls)
            gamePane.getChildren().remove(b.circle);
        balls.clear();

        paddle.setX(GAME_W / 2 - PAD_W / 2);
        addBallAt(GAME_W / 2, GAME_H / 4, false);
        refreshInfo();
    }

    private void endGame()
    {
        stopTimelines();
        running = false;
        refreshInfo();
    }

    private void stopTimelines()
    {
        if (gameLoop != null) gameLoop.stop();
        if (countdown != null) countdown.stop();
        if (bonusSpawn != null) bonusSpawn.stop();
    }

    private void addBall()
    {
        addBallAt(rand.nextDouble() * (GAME_W - 2 * RADIUS) + RADIUS,
                  rand.nextDouble() * (GAME_H / 3) + RADIUS, false);
    }

    private void addBallAt(double x, double y, boolean gold)
    {
        Ball b = new Ball(x, y, gold);
        balls.add(b);
        gamePane.getChildren().add(b.circle);
        paddle.toFront();
        refreshInfo();
    }

    private void removeBall()
    {
        if (!balls.isEmpty())
        {
            gamePane.getChildren().remove(balls.remove(balls.size() - 1).circle);
            refreshInfo();
        }
    }

    private void gameStep()
    {
        if (pressedKeys.contains(KeyCode.LEFT))
            paddle.setX(Math.max(0, paddle.getX() - PAD_SPD));
        if (pressedKeys.contains(KeyCode.RIGHT))
            paddle.setX(Math.min(GAME_W - PAD_W, paddle.getX() + PAD_SPD));

        List<Ball> toRemove = new ArrayList<>();

        for (Ball b : balls)
        {
            double cx = b.circle.getCenterX();
            double cy = b.circle.getCenterY();
            double nx = cx + b.dx * speed;
            double ny = cy + b.dy * speed;

            if (nx - RADIUS <= 0)      { b.dx =  Math.abs(b.dx); nx = RADIUS; }
            if (nx + RADIUS >= GAME_W) { b.dx = -Math.abs(b.dx); nx = GAME_W - RADIUS; }
            if (ny - RADIUS <= 0)      { b.dy =  Math.abs(b.dy); ny = RADIUS; }

            double padTop = paddle.getY();
            if (b.dy > 0
                    && ny + RADIUS >= padTop
                    && cy + RADIUS <= padTop + PAD_H + 2
                    && nx + RADIUS > paddle.getX()
                    && nx - RADIUS < paddle.getX() + PAD_W)
            {
                b.dy = -Math.abs(b.dy);
                ny = padTop - RADIUS;
                score += b.gold ? 10 : 1;
                refreshInfo();
            }

            if (ny - RADIUS > GAME_H)
            {
                toRemove.add(b);
                continue;
            }

            b.circle.setCenterX(nx);
            b.circle.setCenterY(ny);
        }

        if (!toRemove.isEmpty())
        {
            for (Ball b : toRemove)
            {
                balls.remove(b);
                gamePane.getChildren().remove(b.circle);
            }
            refreshInfo();
        }
    }

    private void handleKeyDown(KeyCode code)
    {
        pressedKeys.add(code);
        switch (code)
        {
            case A: addBall(); break;
            case D: removeBall(); break;
            case UP:
                speed = Math.round(Math.min(speed + 0.1, 5.0) * 10.0) / 10.0;
                refreshInfo();
                break;
            case DOWN:
                speed = Math.round(Math.max(speed - 0.1, 0.1) * 10.0) / 10.0;
                refreshInfo();
                break;
            case R: resetGame(); break;
            default: break;
        }
    }

    private void refreshInfo()
    {
        infoLabel.setText(String.format(
            "Bouncing Ball Game   Score: %d   Time: %d   Speed: %.1fx   Balls: %d",
            score, timeLeft, speed, balls.size()
        ));
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
