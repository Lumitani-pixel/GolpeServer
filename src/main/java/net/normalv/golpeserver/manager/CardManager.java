package net.normalv.golpeserver.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.normalv.golpeserver.MainApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
    /**
     * Creates a reader with the json card deck.
     * This is then parsed to a JsonArray as the cards a saved as such.
     * Then we iterate through the array checking if each element is an object and add it to the final Cards list.
     * @param shuffled if this is true after we did the previous steps we will shuffle the list.
     * @return Lastly we return the full deck of cards
     */
    public List<Card> getCardsFromJson(boolean shuffled) {
        List<Card> cards = new ArrayList<>();

        Reader reader;
        try {
            reader = new FileReader(MainApplication.class.getResource("deckConfig.json").getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Error in getting deck config: "+e.getMessage());
            throw new RuntimeException(e);
        }

        JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray) {
            if(jsonElement.isJsonObject()) {
                cards.addAll(getAsCards(jsonElement.getAsJsonObject()));
            }
        }

        if(shuffled) Collections.shuffle(cards);

        return cards;
    }

    /**
     * Gets Cards out of a JsonObject
     * @param cardDetails The Cards as a JsonObject
     * @return The list of cards converted to Java Objects
     */
    private List<Card> getAsCards(JsonObject cardDetails) {
        List<Card> cards = new ArrayList<>();

        for(int i = 1; i<= cardDetails.get("Count").getAsInt(); i++) {

            cards.add(new Card(cardDetails.get("Color").getAsString(),
                    getColors(cardDetails.get("NextColors").getAsJsonArray()),
                    cardDetails.get("Value").getAsString())
            );
        }
        return cards;
    }

    /**
     * Converts JsonArray to String array
     * @param colors JsonArray
     * @return String Array
     */
    private String[] getColors(JsonArray colors) {
        String[] stringColors = new String[colors.size()];

        for(int i = 0; i<colors.size(); i++) {
            stringColors[i] = colors.get(i).getAsString();
        }
        return stringColors;
    }

    public record Card(String color, String[] nextColors, String value) {
    }
}