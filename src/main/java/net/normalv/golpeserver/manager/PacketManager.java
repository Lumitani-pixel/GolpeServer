package net.normalv.golpeserver.manager;

import net.normalv.golpeserver.websocket.packets.Packet;
import net.normalv.golpeserver.websocket.packets.impl.ChatPacket;

import java.util.HashMap;
import java.util.Map;

public class PacketManager {
    private final Map<String, Class<? extends Packet>> PACKETS = new HashMap<>();

    public PacketManager() {
        register("chat", ChatPacket.class);
    }

    public void register(String id, Class<? extends Packet> clazz) {
        PACKETS.put(id, clazz);
    }

    public Class<? extends Packet> get(String id) {
        return PACKETS.get(id);
    }
}
