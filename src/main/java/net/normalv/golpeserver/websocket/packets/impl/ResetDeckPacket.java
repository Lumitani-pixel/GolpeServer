package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.websocket.packets.Packet;

public class ResetDeckPacket extends Packet {
    public ResetDeckPacket() {
        super("reset_deck");
    }
}
