package net.normalv.golpeserver.websocket.packets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.normalv.golpeserver.MainApplication;

public final class PacketCodec {
    private static final Gson GSON = new Gson();

    public static String encode(Packet packet) {
        return GSON.toJson(packet);
    }

    public static Packet decode(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String type = obj.get("type").getAsString();
        Class<? extends Packet> clazz = MainApplication.packetManager.get(type);

        if (clazz == null)
            throw new IllegalStateException("Unknown packet type: " + type);

        return GSON.fromJson(obj, clazz);
    }
}
