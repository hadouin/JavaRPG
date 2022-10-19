import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    int lifePoints = 5;
    int attackDamage = 2;
    int armor;
    int xpGiven;
    List<Hero> involvedHeroes = new ArrayList<Hero>();

    public void attack(List<Hero> heroesToAttack) {
        Random rand = new Random();
        Hero targetHero = heroesToAttack.get(rand.nextInt(heroesToAttack.size()));
        targetHero.defend(attackDamage);
    }

    public void defend(int attackDamage, Hero attacker) {
        if (attackDamage - armor < 0) {
            System.out.println("The enemy is so strong it did nothing");
            return;
        }
        if (!involvedHeroes.contains(attacker)) {
            involvedHeroes.add(attacker);
        }
        lifePoints -= attackDamage - armor;
        System.out.println("Ouch the enemy Took " + (attackDamage - armor) + " damage");
        TestDeath();
    }

    private void TestDeath() {
        if (lifePoints <= 0) {
            die();
        }
    }

    public void die() {
        System.out.println("The enemy dies");
        giveExp(involvedHeroes);
    }
    private void giveExp(List<Hero> heroes) {
        System.out.print("Heroes who receive XP");
        System.out.println(heroes);
        for (Hero hero: heroes) {
            hero.addXP(xpGiven);
        }
    }
}
