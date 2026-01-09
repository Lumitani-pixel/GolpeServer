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
        System.out.println("New connection: "+webSocket.getRemoteSocketAddress());
        MainController.getInstance().addTextToLog("New connection: "+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println(webSocket.getRemoteSocketAddress()+" disconnected because of "+reason+". Exit code: "+code);
        MainController.getInstance().addTextToLog(webSocket.getRemoteSocketAddress()+" disconnected because of "+reason+". Exit code: "+code);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Got message from: "+webSocket.getRemoteSocketAddress()+". Content: "+message);
        MainController.getInstance().addTextToLog("Got message from: "+webSocket.getRemoteSocketAddress()+". Content: "+message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.out.println(e.getMessage()+" on "+webSocket.getRemoteSocketAddress());
        MainController.getInstance().addTextToLog(e.getMessage()+" on "+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onStart() {
        System.out.println("Started Golpe Server instance");
        MainController.getInstance().addTextToLog("Started Golpe Server instance");
    }
}
