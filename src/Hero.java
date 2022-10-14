public abstract class Hero {
    int lifePoints;
    int armor;
    int baseAttack;
    String name;
    String className;

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

    public void attack(Enemy enemy) {
        enemy.defend(baseAttack);
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
