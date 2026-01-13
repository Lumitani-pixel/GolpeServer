package net.normalv.golpeserver.game;

import net.normalv.golpeserver.manager.CardManager;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<CardManager.Card> cardDeck;
    private List<Player> players = new ArrayList<>();
    private int maxPlayers;
    private int currentPlayerIndex = 0;

    public Session(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void startGame() {
    }

    public CardManager.Card getCardFromDeck(boolean removeCard) {
        CardManager.Card card = cardDeck.getFirst();;
        if(removeCard) cardDeck.removeFirst();

        return card;
    }

    public void addPlayer(Player player) {
        if(players.size()>=maxPlayers) return;
        players.add(player);
    }

    public Player getNextPlayer(boolean updatePlayerIndex) {
        Player player = players.get(getNextPlayerIndex());
        if(updatePlayerIndex) currentPlayerIndex = getNextPlayerIndex();

        return player;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private int getNextPlayerIndex() {
        return currentPlayerIndex+1 > maxPlayers ? 0 : currentPlayerIndex+1;
    }
}
