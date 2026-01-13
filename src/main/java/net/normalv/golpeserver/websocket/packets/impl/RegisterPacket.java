package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.websocket.packets.Packet;

public class RegisterPacket extends Packet {
    public String name;

    public RegisterPacket(String name) {
        super("register");
        this.name = name;
    }
}
