package com.unpri.charactermanager.model;

public enum CharacterType {
    WARRIOR("Warrior: jarak dekat, kuat | HP 150, ATK 35"),
    MAGE("Mage: sihir jarak jauh | HP 130, ATK 25"),
    ARCHER("Archer: jarak jauh, seimbang | HP 140, ATK 30"),
    HEALER("Healer: penyembuh tim | HP 120, ATK 20");

    private final String description;

    CharacterType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}