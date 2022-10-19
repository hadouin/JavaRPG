import java.util.ArrayList;
import java.util.List;

public class Mage extends SpellCaster {
    List<Spell> learnedSpells = new ArrayList<Spell>();
    public Mage(){
        this.setClassName("Mage");
    }
    public void attack(List<Enemy> enemies){
        Spell selectedSpell = selectSpell();
        selectedSpell.attack(enemies, this);
    }

    private Spell selectSpell() {
        System.out.println("You have " + manaPoints + " mana");
        InputAsker inputAsker = new InputAsker(System.in, System.out);

        List<String> spellStringList = new ArrayList<String>();
        for (Spell spell : learnedSpells) {
            spellStringList.add(spell.name + "mana: " + spell.manaCost);
        }
        String[] spellStrings = new String[spellStringList.size()];
        spellStringList.toArray(spellStrings);

        int spellChoiceIndex = inputAsker.getStringsChoiceIndex("What spell do you want to cast", spellStrings);
        return learnedSpells.get(spellChoiceIndex);
    }

}
