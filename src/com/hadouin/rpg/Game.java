package com.hadouin.rpg;

import com.hadouin.utils.InputAsker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {
    List<Hero> heroes = new ArrayList<>();
    int playerTurn;
    int partySize;
    final InputAsker inputAsker = new InputAsker(System.in, System.out);
    final  String[] heroClasses = new String[]{"Warrior", "Mage"};
    boolean gameRunning = false;

    public void start(){

        initGame();
        gameLoop();
    }

    private void gameLoop() {
        // idee faire des vagues d'ennemis de plus en plus forts avec tous les 5 un boss stocker
        int baseEnemies = 0;
        Random rand = new Random();

        while (gameRunning) {

            baseEnemies++;

            // create enemies
            List<Enemy> enemiesPool = new ArrayList<>();
            for (int i = 0; i < baseEnemies + rand.nextInt(3); i++ ) {
                enemiesPool.add(new Enemy());
            }

            launchBattle(enemiesPool);


        }

    }

    private void launchBattle(List<Enemy> enemies) {
        List<Enemy> remainingEnemies = new ArrayList<>(enemies);

        while (! remainingEnemies.isEmpty() && ! heroes.isEmpty()) {
            System.out.println("It's " + heroes.get(playerTurn).name + "'s turn to play. HP=" + heroes.get(playerTurn).lifePoints);
            System.out.println(remainingEnemies);

            String[] actionChoices = new String[]{"attack", "skip"};
            int choiceIndex = inputAsker.getStringsChoiceIndex("What will you do ?", actionChoices);
            switch (actionChoices[choiceIndex]) {
                case "attack":
                    heroes.get(playerTurn).attack(remainingEnemies);
                    break;
                case "skip":
                    break;
                default:
                    break;
            }

            // Test enemy death
            deathReaper(remainingEnemies);

            //Next player's turn
            if (playerTurn == heroes.size() -1) {
                enemiesTurn(remainingEnemies);
                playerTurn = 0;
            } else {
                playerTurn++;
            }

            //Test Heroes Death
            testHeroesDeath(heroes);
        }

        System.out.println("no remaining enemies for this battle");

        // Upgrade heroes
    }

    private void deathReaper(List<Enemy> enemies) {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next(); // must be called before you can call i.remove()
            if (enemy.lifePoints <= 0) {
                enemy.die();
                i.remove();
            }
        }
    }
    private void testHeroesDeath(List<Hero> heroes) {
        heroes.removeIf(hero -> hero.lifePoints <= 0);
        if (heroes.isEmpty()) {
            gameRunning = false;
        }

    }

    private void enemiesTurn(List<Enemy> remainingEnemies) {
        for (Enemy enemy : remainingEnemies) {
            enemy.attack(heroes);
        }
    }

    private void initGame() {
        choosePartySize();
        createHeroes();
        gameRunning = true;
    }
    private void createHeroes() {
        // For each player create a com.hadouin.rpg.Hero
        for (int heroID = 0; heroID < partySize; heroID++) {

            int classChoiceIndex = inputAsker.getStringsChoiceIndex("Player " + heroID + " choose your class: ", heroClasses);
            switch (heroClasses[classChoiceIndex]) {
                case "Warrior" -> heroes.add(new Warrior());
                case "Mage" -> {
                    Mage mage = new Mage();
                    mage.learnedSpells.add(new Fireball());
                    heroes.add(mage);
                }
                default -> throw new RuntimeException("Class does not exist");
            }
        }
    }
    private void choosePartySize() {

        // Choose party size
        do {
            System.out.println("Party size has to be between 1 and 4");
            partySize = inputAsker.promptInt("How many people are in your party ?:", 0);
        } while (partySize > 4 || partySize < 1 );

    }
}
