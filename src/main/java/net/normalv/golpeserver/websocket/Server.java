package net.normalv.golpeserver.websocket;

import net.normalv.golpeserver.MainController;
import net.normalv.golpeserver.game.Player;
import net.normalv.golpeserver.game.Session;
import net.normalv.golpeserver.websocket.packets.Packet;
import net.normalv.golpeserver.websocket.packets.PacketCodec;
import net.normalv.golpeserver.websocket.packets.impl.RegisterPacket;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

//TODO implement better logging
public class Server extends WebSocketServer {
    private Session session;

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
        Packet packet = PacketCodec.decode(message);

        if(packet instanceof RegisterPacket registerPacket) {
            Player player = new Player(registerPacket.uuid, registerPacket.name);
            session.addPlayer(player);
            log(player.getName()+" joined with uuid: "+player.getUuid());
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        log(e.getMessage()+" on "+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onStart() {
        log("Started Golpe Server");
        session = new Session(2);
        log("Opened new Session with max player 2");
    }

    public Session getActiveSession() {
        return session;
    }

    public static void log(String msg) {
        System.out.println(msg);
        MainController.getInstance().addTextToLog(msg);
    }
}
