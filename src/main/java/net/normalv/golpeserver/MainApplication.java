package net.normalv.golpeserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.normalv.golpeserver.manager.PacketManager;
import net.normalv.golpeserver.websocket.Server;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainApplication extends Application {
    private static Server server;

    public static PacketManager packetManager;

    @Override
    public void start(Stage stage) throws IOException {
        packetManager = new PacketManager();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ServerMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Golpe Server");
        stage.setScene(scene);
        stage.show();
    }

    public static void createServer() {
        server = new Server(new InetSocketAddress("localhost", 1598));
        server.start();
    }

    public static void setServer(Server s) {
        server = s;
    }

    public static Server getServer() {
        return server;
    }
}
