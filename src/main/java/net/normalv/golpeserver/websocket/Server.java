package net.normalv.golpeserver.websocket;

import net.normalv.golpeserver.MainController;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

//TODO implement better logging
public class Server extends WebSocketServer {
    public Server(InetSocketAddress socketAddress) {
        super(socketAddress);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        log("New connection: "+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        log(webSocket.getRemoteSocketAddress()+" disconnected because of "+reason+". Exit code: "+code);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        log("Got message from: "+webSocket.getRemoteSocketAddress()+". Content: "+message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        log(e.getMessage()+" on "+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onStart() {
        log("Started Golpe Server");
    }

    public static void log(String msg) {
        System.out.println(msg);
        MainController.getInstance().addTextToLog(msg);
    }
}
