import java.util.List;

public abstract class Hero {
    int lifePoints;
    int armor;
    int baseAttack;
    int XP;
    int level;
    String name;
    String className;

    public void addXP(int amount) {
        this.XP += amount;
        checkLevelUp();
    }

    private void checkLevelUp() {

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Hero(){
        InputAsker inputAsker = new InputAsker(System.in, System.out);
        name = inputAsker.promptWord("What will your name be ?");

    }

    public void attack(List<Enemy> enemies) {
        Enemy enemy = enemies.get(0);
        // TODO: choose which enemy to attack:
        System.out.println("You attack: " + enemy);
        enemy.defend(baseAttack, this);
    }

    public void defend(int attackDamage) {
        if (attackDamage - armor < 0) {
            System.out.println(name + " is so strong it did nothing");
            return;
        }
        lifePoints -= attackDamage - armor;
        System.out.println("Ouch " + name + " Took " + (attackDamage - armor) + " damage");
    }
}
