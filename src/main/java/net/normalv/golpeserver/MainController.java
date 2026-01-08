package net.normalv.golpeserver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Button startButton;

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
}
