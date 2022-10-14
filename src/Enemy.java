import java.util.List;
import java.util.Random;

public class Enemy {
    int lifePoints;
    int attackDamage;
    int armor;

    public void attack(List<Hero> heroesToAttack) {
        Random rand = new Random();
        Hero targetHero = heroesToAttack.get(rand.nextInt(heroesToAttack.size()));
        targetHero.defend(attackDamage);
    }

    public void defend(int attackDamage) {

    }
}
