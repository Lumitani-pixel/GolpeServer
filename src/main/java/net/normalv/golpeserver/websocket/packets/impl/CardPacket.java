package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.manager.CardManager;
import net.normalv.golpeserver.websocket.packets.Packet;

public class CardPacket extends Packet {
    public CardManager.Card card;

    public CardPacket(CardManager.Card card) {
        super("card");
        this.card = card;
    }
}
