package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    private final List<Character> characters = new ArrayList<>();

    public void addCharacter(Character character) {
        characters.add(character);
        System.out.println("Karakter '" + character.getName() + "' berhasil ditambahkan.");
    }

    public List<Character> getAllCharacters() {
        return characters;
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    public double getAverageLevel() {
        if (characters.isEmpty()) return 0;
        int sum = 0;
        for (Character c : characters) {
            sum += c.getLevel();
        }
        return (double) sum / characters.size();
    }

    public int getHighestLevel() {
        int max = 0;
        for (Character c : characters) {
            if (c.getLevel() > max) {
                max = c.getLevel();
            }
        }
        return max;
    }

    public int countHighLevel(int threshold) {
        int count = 0;
        for (Character c : characters) {
            if (c.getLevel() >= threshold) {
                count++;
            }
        }
        return count;
    }
}