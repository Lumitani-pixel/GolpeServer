package net.normalv.golpeserver;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainController {
    private static MainController instance;

    @FXML
    private Button startButton;
    @FXML
    private Button startGameButton;

    @FXML
    private Label playerCount;

    @FXML
    private TextArea logs;

    public MainController() {
        instance = this;
    }

    @FXML
    protected void onStartButtonClick() throws InterruptedException {
        if(MainApplication.getServer() == null) {
            MainApplication.createServer();
            startButton.setText("Stop Server");
        } else {
            MainApplication.getServer().stop();
            MainApplication.setServer(null);
            startButton.setText("Start Server");
        }
    }

    @FXML
    protected  void onStartGameButton() throws InterruptedException {
        if(MainApplication.getServer().getActiveSession().isRunning()) {
            MainApplication.getServer().getActiveSession().stopGame("Force closed by server operator");
            startGameButton.setText("Start Game");
        } else {
            MainApplication.getServer().getActiveSession().startGame();
            startGameButton.setText("Stop Game");
        }
    }

    public void setPlayerCount(int count) {
        playerCount.setText(count+"/"+MainApplication.getServer().getActiveSession().getMaxPlayers()+" Players");
    }

    public void addTextToLog(String text) {
        Platform.runLater(() -> logs.appendText(text + "\n"));
    }

    public static MainController getInstance() {
        return instance;
    }
}
