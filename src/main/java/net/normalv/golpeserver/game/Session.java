package net.normalv.golpeserver.game;

import net.normalv.golpeserver.MainApplication;
import net.normalv.golpeserver.MainController;
import net.normalv.golpeserver.manager.CardManager;
import net.normalv.golpeserver.websocket.packets.PacketCodec;
import net.normalv.golpeserver.websocket.packets.impl.CardPacket;
import net.normalv.golpeserver.websocket.packets.impl.NextMovePacket;
import net.normalv.golpeserver.websocket.packets.impl.StopGamePacket;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<CardManager.Card> cardDeck;
    private List<CardManager.Card> dealtCards;
    private List<Player> players = new ArrayList<>();
    private int maxPlayers;
    private int currentPlayerIndex = 0;
    private boolean running = false;

    public Session(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void startGame() {
        if(players.isEmpty()){
            stopGame("No Players in Session");
            return;
        }

        cardDeck = MainApplication.cardManager.getCardsFromJson(true);
        running = true;

        // Send a deck of 7 cards to each player
        for(Player player : players) {
            for(CardManager.Card card : getCardsFromDeck(7)) {
                player.getWebSocket().send(PacketCodec.encode(new CardPacket(card)));
                player.addCard(card);
            }
        }
        // Lay down one inital card
        dealtCards.add(cardDeck.removeFirst());

        // Tell the first player it's their move
        sendNextMove();
    }

    public void stopGame(String reason) {
        running = false;
        if(players.isEmpty()) return;
        MainApplication.getServer().broadcast(PacketCodec.encode(new StopGamePacket(reason)));
    }

    public void sendNextMove() {
        Player player = getNextPlayer(true);
        MainApplication.getServer().broadcast(PacketCodec.encode(new NextMovePacket(player.getUuid(), dealtCards.getLast())));
    }

    public List<CardManager.Card> getCardsFromDeck(int amount) {
        List<CardManager.Card> cards = new ArrayList<>(amount);
        for(int i = 0; i<amount; i++) {
            cards.add(getCardFromDeck(true));
        }

        return cards;
    }

    public CardManager.Card getCardFromDeck(boolean removeCard) {
        CardManager.Card card = cardDeck.getFirst();
        if(removeCard) cardDeck.removeFirst();

        return card;
    }

    public void addPlayer(Player player) {
        if(players.size()>=maxPlayers) return;
        MainController.getInstance().setPlayerCount(players.size());
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
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

    public List<Player> getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public boolean isRunning() {
        return running;
    }
}
