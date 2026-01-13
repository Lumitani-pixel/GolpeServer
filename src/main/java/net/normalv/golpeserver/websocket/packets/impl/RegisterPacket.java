package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.websocket.packets.Packet;

import java.util.UUID;

public class RegisterPacket extends Packet {
    public UUID uuid;
    public String name;

    public RegisterPacket(UUID uuid, String name) {
        super("register");
        this.uuid = uuid;
        this.name = name;
    }
}
