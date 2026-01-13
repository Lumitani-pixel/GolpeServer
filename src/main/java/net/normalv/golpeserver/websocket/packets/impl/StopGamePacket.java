package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.websocket.packets.Packet;

public class StopGamePacket extends Packet {
    public String reason;

    public StopGamePacket(String reason) {
        super("stop_game");
        this.reason = reason;
    }
}
