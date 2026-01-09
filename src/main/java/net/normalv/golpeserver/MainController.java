package net.normalv.golpeserver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainController {
    private static MainController instance;

    @FXML
    private Button startButton;

    @FXML
    private TextArea logs;

    public MainController() {
        instance = this;
    }

    @FXML
    protected void onStartButtonClick() throws InterruptedException {
        if(MainApplication.getServer() == null) {
            MainApplication.createServer();
            startButton.setText("Stop");
        } else {
            MainApplication.getServer().stop();
            MainApplication.setServer(null);
            startButton.setText("Start");
        }
    }

    public void addTextToLog(String text) {
        logs.appendText(text+"\n");
    }

    public static MainController getInstance() {
        return instance;
    }
}
