package com.hadouin.rpg;

import com.hadouin.utils.InputAsker;
import java.util.ArrayList;
import java.util.List;

public class Mage extends SpellCaster {
    List<Spell> learnedSpells = new ArrayList<>();
    public Mage(){
        this.setClassName("Mage");
        this.lifePoints = 15;
        this.baseAttack = 2;
        this.manaPoints = 50;

    }
    public void attack(List<Enemy> enemies){
        Spell selectedSpell = selectSpell();
        if (selectedSpell.manaCost <= manaPoints) {
            manaPoints -= selectedSpell.manaCost;
            selectedSpell.cast(enemies, this);
        }

    }

    private Spell selectSpell() {
        System.out.println("You have " + manaPoints + " mana");
        InputAsker inputAsker = new InputAsker(System.in, System.out);

        List<String> spellStringList = new ArrayList<>();
        for (Spell spell : learnedSpells) {
            spellStringList.add(spell.name + " manaCost: " + spell.manaCost);
        }
        String[] spellStrings = new String[spellStringList.size()];
        spellStringList.toArray(spellStrings);

        int spellChoiceIndex = inputAsker.getStringsChoiceIndex("What spell do you want to cast", spellStrings);
        return learnedSpells.get(spellChoiceIndex);
    }

}
