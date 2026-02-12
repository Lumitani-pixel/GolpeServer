package net.normalv.golpeserver.manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardManagerTest {
    private CardManager cardManager = new CardManager();

    @Test
    void canLayCardTrueTest() {
        assertTrue(cardManager.canLayCard(new CardManager.Card("Black", new String[] {"Blue", "Red"}, "PlusTwo"), new CardManager.Card("Red", new String[] {"Red"}, "5")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Black", new String[] {"Green", "Blue", "Red", "Yellow"}, "Block"), new CardManager.Card("Green", new String[] {"Green"}, "20")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Green", new String[] {"Green"}, "20"), new CardManager.Card("Green", new String[] {"Green"}, "5")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Blue", new String[] {"Blue"}, "PlusOne"), new CardManager.Card("Blue", new String[] {"Blue"}, "5")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Red", new String[] {"Red"}, "5"), new CardManager.Card("Red", new String[] {"Red"}, "5")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Yellow", new String[] {"Yellow"}, "25"), new CardManager.Card("Green", new String[] {"Green"}, "25")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Yellow", new String[] {"Yellow"}, "40"), new CardManager.Card("Red", new String[] {"Red"}, "40")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Green", new String[] {"Green"}, "35"), new CardManager.Card("Green", new String[] {"Green"}, "35")));
        assertTrue(cardManager.canLayCard(new CardManager.Card("Red", new String[] {"Red"}, "20"), new CardManager.Card("Black", new String[] {"Green", "Blue", "Red", "Yellow"}, "Block")));
    }

    @Test
    void canLayCardFalseTest() {
        assertFalse(cardManager.canLayCard(new CardManager.Card("Red", new String[] {"Red"}, "5"), new CardManager.Card("Blue", new String[] {"Blue"}, "15")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Black", new String[] {"Blue", "Red"}, "PlusTwo"), new CardManager.Card("Green", new String[] {"Green"}, "20")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Green", new String[] {"Green"}, "20"), new CardManager.Card("Red", new String[] {"Red"}, "5")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Blue", new String[] {"Blue"}, "PlusOne"), new CardManager.Card("Green", new String[] {"Green"}, "5")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Red", new String[] {"Red"}, "5"), new CardManager.Card("Green", new String[] {"Green"}, "PlusOne")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Yellow", new String[] {"Yellow"}, "25"), new CardManager.Card("Green", new String[] {"Green"}, "15")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Yellow", new String[] {"Yellow"}, "40"), new CardManager.Card("Red", new String[] {"Red"}, "20")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Green", new String[] {"Green"}, "35"), new CardManager.Card("Blue", new String[] {"Blue"}, "25")));
        assertFalse(cardManager.canLayCard(new CardManager.Card("Red", new String[] {"Red"}, "20"), new CardManager.Card("Green", new String[] {"Green"}, "Lock")));
    }
}