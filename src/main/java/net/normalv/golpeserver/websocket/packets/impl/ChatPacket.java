package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.websocket.packets.Packet;

public class ChatPacket extends Packet {
    public String message;

    public ChatPacket(String message) {
        super("chat");
        this.message = message;
    }
}
