package com.hadouin.rpg;

import java.util.List;

public abstract class Spell {
    int manaCost;
    String name;
    String description;

    public abstract void attack(List<Enemy> enemies, Hero attacker);
}
