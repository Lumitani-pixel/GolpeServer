package net.normalv.golpeserver.game;

import net.normalv.golpeserver.manager.CardManager;
import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Player {
    private List<CardManager.Card> handCards = new ArrayList<>();
    private UUID uuid;
    private String name;
    private WebSocket webSocket;
    private boolean requestedCard;

    public Player(UUID uuid, String name, WebSocket webSocket) {
        this.uuid = uuid;
        this.name = name;
        this.webSocket = webSocket;
    }

    //FIXME: Cards are not always detected right
    public boolean hasCard(CardManager.Card card) {
        for(CardManager.Card handCard : handCards) {
            if(handCard.value().equals(card.value()) && handCard.color().equals(card.color()) && Arrays.equals(handCard.nextColors(), card.nextColors())) return true;
        }
        return false;
    }

    public void addCard(CardManager.Card card) {
        handCards.add(card);
    }

    public void addCards(List<CardManager.Card> cards) {
        handCards.addAll(cards);
    }

    public void removeCard(CardManager.Card card) {
        handCards.remove(card);
    }

    public void removeCards(List<CardManager.Card> cards) {
        handCards.removeAll(cards);
    }

    public List<CardManager.Card> getHandCards() {
        return handCards;
    }

    public void setRequestedCard(boolean requestedCard) {
        this.requestedCard = requestedCard;
    }

    public boolean hasRequestedCard() {
        return requestedCard;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }
}
