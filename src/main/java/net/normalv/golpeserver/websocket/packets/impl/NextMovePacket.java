package net.normalv.golpeserver.websocket.packets.impl;

import net.normalv.golpeserver.manager.CardManager;
import net.normalv.golpeserver.websocket.packets.Packet;

import java.util.UUID;

public class NextMovePacket extends Packet {
    public UUID nextPlayersUuid;
    public CardManager.Card currentCard;

    public NextMovePacket(UUID nextPlayersUuid, CardManager.Card currentCard) {
        super("next_move");
        this.nextPlayersUuid = nextPlayersUuid;
        this.currentCard = currentCard;
    }
}
