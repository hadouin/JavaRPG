import java.util.List;

public class Fireball extends Spell{
    int damage;

    public Fireball() {
        this.name = "fireball";
        this.damage = 10;
        this.manaCost = 5;
        this.description = "A simple spell that does damage to one enemy at the expense of mana";
    }

    @Override
    public void attack(List<Enemy> enemies, Hero attacker) {
        Enemy enemy = enemies.get(0);
        enemy.defend(damage, attacker);
    }
}
