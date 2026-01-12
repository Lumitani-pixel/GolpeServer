package net.normalv.golpeserver.manager;

public class CardManager {
    public record Card(String color, String[] nextColors, String value) {
    }
}