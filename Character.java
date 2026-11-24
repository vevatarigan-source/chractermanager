package com.unpri.charactermanager.model;

public class Character {
    private String name;
    private CharacterType type;
    private int level;
    private int hp;
    private int atk;

    public Character(String name, CharacterType type, int level, int hp, int atk) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hp = hp;
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }
}