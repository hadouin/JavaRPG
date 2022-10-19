package com.hadouin.rpg;

import java.util.List;

public abstract class Spell {
    int manaCost;
    String name;
    String description;

    public abstract void cast(List<Enemy> enemies, Hero attacker);
}
