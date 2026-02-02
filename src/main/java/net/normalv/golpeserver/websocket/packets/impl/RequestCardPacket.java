package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.websocket.packets.Packet;

public class RequestCardPacket extends Packet {
    public RequestCardPacket() {
        super("request_card");
    }
}
