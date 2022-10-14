import java.util.ArrayList;
import java.util.List;

public class Game {
    static List<Hero> heroes = new ArrayList<Hero>();
    static int playerTurn;
    static int partySize;
    static final InputAsker inputAsker = new InputAsker(System.in, System.out);

    public static void main(String[] args){

        initGame();

    }
    private static void initGame() {
        choosePartySize();
        for (int i = 0; i < partySize ; i++){
            createHeroes();
        }

    }
    private static void createHeroes() {
        // For each player create a Hero
        for (int heroID = 0; heroID < partySize; heroID++) {
            String classChoice = inputAsker.chooseStringWithIndex("Player " + heroID + " choose your class: ", new String[]{"Warrior", "Mage"});
            switch (classChoice) {
                case "Warrior":
                    heroes.add(new Warrior());
                    break;
                case "Mage":
                    heroes.add(new Mage());
                default:
                    throw new RuntimeException("Class does not exist");
            }
        }
    }
    private static void choosePartySize() {

        // Choose party size
        do {
            System.out.println("Party size has to be between 1 and 4");
            partySize = inputAsker.promptInt("How many people are in your party ?:", 0);
        } while (partySize > 4 || partySize < 1 );

    }
}
