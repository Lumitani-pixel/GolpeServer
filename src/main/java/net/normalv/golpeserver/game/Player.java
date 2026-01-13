package net.normalv.golpeserver.game;

import net.normalv.golpeserver.manager.CardManager;

import java.util.List;
import java.util.UUID;

public class Player {
    private List<CardManager.Card> handCards;
    private UUID uuid;
    private String name;

    public Player(List<CardManager.Card> initialCards, UUID uuid, String name) {
        handCards = initialCards;
        this.uuid = uuid;
        this.name = name;
    }

    public boolean hasCard(CardManager.Card card) {
        return handCards.contains(card);
    }

    public void addCard(CardManager.Card card) {
        handCards.add(card);
    }

    public void addCards(List<CardManager.Card> cards) {
        handCards.addAll(cards);
    }

    public List<CardManager.Card> getHandCards() {
        return handCards;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
