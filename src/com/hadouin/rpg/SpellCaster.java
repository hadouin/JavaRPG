package com.hadouin.rpg;

public abstract class SpellCaster extends Hero {
    int manaPoints;
    public boolean hasEnoughMana(Spell spell) {
        return spell.manaCost > manaPoints;
    }

}
