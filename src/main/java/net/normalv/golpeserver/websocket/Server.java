package net.normalv.golpeserver.websocket;

import net.normalv.golpeserver.MainController;
import net.normalv.golpeserver.game.Player;
import net.normalv.golpeserver.game.Session;
import net.normalv.golpeserver.websocket.packets.Packet;
import net.normalv.golpeserver.websocket.packets.PacketCodec;
import net.normalv.golpeserver.websocket.packets.impl.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.UUID;

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

        for(Player player : session.getPlayers()) {
            if(player.getWebSocket() != webSocket) continue;
            log(player.getName()+" left");
            if(session.isRunning()) session.stopGame(player.getName()+" left midgame");
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        log("Got message from: "+webSocket.getRemoteSocketAddress()+". Content: "+message);
        Packet packet = PacketCodec.decode(message);

        if(packet instanceof RegisterPacket registerPacket) {
            handleRegistration(registerPacket, webSocket);
        }
        else if(packet instanceof CardPacket cardPacket) {
            boolean dealtCard = session.dealCard(webSocket, cardPacket.card);

            if(dealtCard && session.getCurrentPlayer().getHandCards().isEmpty()) {
                broadcast(PacketCodec.encode(new StopGamePacket("Player won!")));
                return;
            }
            session.sendNextMove(dealtCard);
        }
        else if(packet instanceof CantDealPacket) {
            if(!session.getCurrentPlayer().getWebSocket().equals(webSocket)) return;
            else if(session.getCurrentPlayer().hasRequestedCard()) {
                session.getCurrentPlayer().setRequestedCard(false);
                session.sendNextMove(true);
                return;
            }

            session.getCurrentPlayer().setRequestedCard(true);
            webSocket.send(PacketCodec.encode(new CardPacket(session.getCardFromDeck(true))));
            session.sendNextMove(false);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        log(e.getMessage()+" on "+webSocket.getRemoteSocketAddress()+" stacktrace: "+ Arrays.toString(e.getStackTrace()));
    }

    @Override
    public void onStart() {
        log("Started Golpe Server");
        session = new Session(2);
        log("Opened new Session with max player 2");
    }

    private void handleRegistration(RegisterPacket registerPacket, WebSocket webSocket) {
        UUID uuid = UUID.randomUUID();
        Player player = new Player(uuid, registerPacket.name, webSocket);

        session.addPlayer(player);
        log(player.getName()+" joined assigned uuid: "+player.getUuid());

        webSocket.send(PacketCodec.encode(new ConfirmRegistrationPacket(uuid)));
    }

    public Session getActiveSession() {
        return session;
    }

    //TODO: Implement logger limiter
    public static void log(String msg) {
        System.out.println(msg);
        //MainController.getInstance().addTextToLog(msg);
    }
}
