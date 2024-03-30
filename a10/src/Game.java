import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the game.
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
@SuppressWarnings("FieldMayBeFinal")
public class Game {
    private static final int DEFAULT_NUM_BEINGS = 8;
    private Being[] computerPlayers; //a Being array holding the non-player Being objects - either Human or Vampire objects
    private ArrayList<Vampire> allVampires; // all the vampires (player + computer vampire players)
    private int numBeings; // number of filled spots in the computerPlayer array
    private Vampire player; // the program user
    private BFF bff;
    private Random rand;
    private NameGenerator ng;

    /**
     * This constructor creates a game (calling the other constructor) with the default number of beings
     */
    public Game() {

        this(DEFAULT_NUM_BEINGS);
    }

    /**
     * This constructor creates a game with a specified number of beings & sets all the (unset) instance vars
     *
     * @param num the number of beings to be added to the game
     */
    public Game(int num) { //complete
        bff = new BFF();
        rand = new Random();
        ng = new NameGenerator();
        allVampires = new ArrayList<>();
        computerPlayers = new Being[num];
        numBeings = num;
        displayWelcome();
        player = createVampire();
        allVampires.add(player);
        generateComputerPlayers();
    }

    /**
     * Starts the whole program
     *
     * @param args command line arguments
     */
    public static void main(String[] args) { //complete
        new Game().play();
    }

    private void generateComputerPlayers() {
        for (int i = 0; i < computerPlayers.length; i++) {
            Being being;
            String name = ng.getRandomName();
            // 60% probability of making a human, else make a vampire
            // random starting quarts 3-10
            // humans should get a random BloodType from the enum
            // vampires will get a random form.

            // get a random number between 0 and 99
            int probability = getRandomProbability();

            // 60% chance of human
            if (probability < 60) {
                // get random bloodtype
                BloodType type = BloodType.getRandom();
                being = new Human(name, rand.nextInt(8) + 3, type);
            } else {
                // create a vampire
                VampireForm form = VampireForm.getRandom();
                BloodType type1 = BloodType.getRandom();
                BloodType type2 = BloodType.getRandom();
                being = new Vampire(name, rand.nextInt(8) + 3, form, type1, type2);

                // vampires must be added to allVampires
                // note we need to cast Being to Vampire to add it to the list
                allVampires.add((Vampire) being);
            }

            // add the being to the computerPlayers array
            computerPlayers[i] = being;

            // Make sure to add vampire to the computerPlayers array AND the allVampires list

        }
    }

    /**
     * Asks the user to specify vampire name, quarts of blood, starting form, and 2 favorite blood types
     * and creates a new Vampire object using those inputs
     *
     * @return Vampire instance that was created
     */
    private Vampire createVampire() { //complete
        String name = bff.input("Player, what is your vampire name?");
        int quarts = bff.inputInt("How many quarts of blood do you have (3-10)?", 3, 10);
        String formStr = bff.input("What form are you to start? (Vampire, Vampire_Bat, Vampire_Wolf)", "Vampire", "Vampire_Bat", "Vampire_Wolf");
        String type = bff.input("What blood type is your favorite?", BloodType.getBloodTypeStringsAsArray());
        String type2 = bff.input("What blood type is your second favorite?", BloodType.getBloodTypeStringsAsArray());
        // this assumes the player doesn't enter the same blood type.
        VampireForm form = VampireForm.valueOf(formStr.toUpperCase());
        return new Vampire(name, quarts, form, BloodType.matchString(type), BloodType.matchString(type2));
    }

    /**
     * Prints a nice stringified version of the computer players that were
     * created in the generateComputerPlayers method
     */
    private void printComputerPlayers() { //complete
        bff.printRed("Your opponents: ");
        for (int i = 0; i < numBeings; i++) {
            bff.print(i + ": " + computerPlayers[i]);
        }
        int numHumans = getNumberOfHumans();
        bff.printRed("There are " + numHumans + " humans and " + (numBeings - numHumans) + " computer vampires");
    }

    /**
     * Runs an infinite loop that is "broken" when the game is over (i.e., over=true)
     * to repeatedly run rounds of the game; the player can input commands each round
     * and the computer players will take their turns.
     */
    public void play() { //complete
        printComputerPlayers();
        bff.input("Hit return to continue");
        displayInstructions();

        int roundNum = 1;
        boolean over = false;
        boolean quit = false;
        while (!over) {
            String command = bff.input("Round #" + roundNum + ": " + player.getName() + " Enter command >", "Rest", "Attack", "Change", "Quit");
            switch (command.toLowerCase()) {
                case "rest":
                    rest();
                    break;
                case "attack":
                    attack();
                    break;
                case "change":
                    change();
                    break;
                case "quit":
                    quit = true;
                    over = true;
                    break;
            }
            if (!quit) {
                npcActions();
                mysteryEvent();
                over = checkGameStatus();
                if (bff.inputYesNo("Want to print opponents stats (it costs 1 point?)")) {
                    printComputerPlayers();
                    player.losePoints(1);
                }
            }
            roundNum++;
        }
        displayGameOver(quit, roundNum - 1);
    }

    /**
     * This method shows the game over message, including interesting stats about all of the players
     *
     * @param playerQuits whether the player quit the game before it ended
     * @param rounds      the number of rounds that were played
     */
    private void displayGameOver(boolean playerQuits, int rounds) { //complete
        bff.print("Game over after " + rounds + " rounds");
        String winner = "";
        if (playerQuits) {
            bff.print("Player quit... but here are the ending stats:\n");
        }
        bff.print("Here is how each vampire scored:");
        rankVampiresInList();
        for (Vampire v : allVampires) {
            if (v == player) {
                bff.printRed("\tYOU: " + v);
            }
            bff.print("\t" + v);
        }
        if (getNumberOfHumans() == 0) {
            bff.print("The VAMPIRES win, no humans remain\n");
            bff.printRed("Congratulations to top scoring Vampire: " + allVampires.get(0));
        } else if (player.getPoints() == 0) {
            bff.print("The HUMANS win.\n");
        } else {
            bff.print(getNumberOfHumans() + " Humans remain...Congratulations to top scoring Vampire(s):");
            int index = 1;
            String top = allVampires.get(0).toString();
            while (allVampires.get(index).getPoints() == allVampires.get(0).getPoints() && index < allVampires.size()) {
                top += "\n and " + allVampires.get(index).toString();
                index++;
            }
            bff.printRed(top);
        }
    }

    /**
     * This completed method uses a fancy expression to sort the vampire list by points.
     */
    private void rankVampiresInList() {
        allVampires.sort((Vampire v1, Vampire v2) -> v2.getPoints() - v1.getPoints());
    }

    /**
     * Returns the number of humans in computerPlayers
     *
     * @return the number of humans in computerPlayers
     */
    private int getNumberOfHumans() {
        int counter = 0;

        for (Being player : computerPlayers) {
            // just increase the counter if the player is a human
            if (player instanceof Human) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Checks if the game has ended (either all humans are gone or the player has no points)
     *
     * @return whether the game has ended according to the defined criteria
     */
    private boolean checkGameStatus() { //complete
        boolean gameOver = getNumberOfHumans() == 0;
        if (player.getPoints() == 0) {
            gameOver = true;
        }
        return gameOver;
    }

    private void npcActions() { //Non player characters perform actions.
        int numHumans = getNumberOfHumans();
        bff.printFancy("Computer beings (the humans and vampires) take their turns.\n There are " + numHumans +
                " humans and " + (numBeings - numHumans) + " computer vampires");

        // Finish this method. Loop through all the computerPlayers, Use random number to make game more fun
        // if npc is human, there's a 3/10 chance that they can increase blood quarts by 1.
        // if npc is a vampire, there is a 50% chance that the vampire attacks another player and a
        // 50% chance that they change form.

        for (Being player : computerPlayers) {
            // generate random number for probability
            // I'm assuming this is what the instructions mean by "use random number to make game more fun"???
            int probability = getRandomProbability();

            if (player instanceof Human) {
                if (probability < 30) {
                    player.increaseQuarts(1);
                }
            } else if (player instanceof Vampire) {
//                (i'm assuming the two scenarios listed above are mutually exclusive, since it doesn't say)
                if (probability < 50) {
                    attackBy((Vampire) player);
                } else {
//                    need to cast to Vampire to call changeForm
                    ((Vampire) player).changeForm();
                }
            }
        }
    }

    /**
     * MYSTERY EVENT!!
     * With a 30% chance, the mystery event causes a random computer player to eisappear
     * With a 20% chance, the human attacks vampires with garlic
     * With a 20% chance, the human attacks vampires with a cross
     * With a 20% chance, the human attacks vampires with sunlight
     * With a 10% chance, the human gets 3 points
     * In any of the last 4 events, the player is told how many points they have
     */
    private void mysteryEvent() { //complete
        int probability = getRandomProbability();
        if (probability > 70) { //30% change
            int num = rand.nextInt(numBeings);
            bff.printRed("❓Mystery event causes a computer player to disappear... Goodbye " + computerPlayers[num].getName());
            removeBeingFromArray(num);
        } else {
            if (probability >= 0 & probability < 20) {
                humanAttackVampires("garlic");
            } else if (probability >= 20 & probability < 40) {
                humanAttackVampires("cross");
            } else if (probability >= 40 & probability < 60) {
                humanAttackVampires("sunlight");
            } else if (probability >= 60 & probability < 70) {
                bff.printRed("Mystery Event: 🩸🩸🩸 You find a blood bank.🩸🩸🩸🩸. You gain 3 points");
                player.increaseQuarts(3);
                player.earnPoints(3);
            }
            bff.printRed("You are a " + player.getIcon() + " with " + player.getPoints() + " points");
        }
    }

    /**
     * This method allows human to attack a random number of random vampires (1-3) with garlic, a cross, or sunlight
     * The amount of damage caused depends on the type of the vampire(s) attacked
     * The amount of damage taken is printed
     *
     * @param item the item used to attack the vampires (garlic, cross, sunlight)
     */
    private void humanAttackVampires(String item) {  //complete
        int numberOfAttacks = rand.nextInt(2) + 1; // attacks 1, 2, or 3 vampires.
        boolean playerAttacked = false;
        for (int i = 0; i < numberOfAttacks; i++) {
            Vampire v = allVampires.get(rand.nextInt(allVampires.size()));
//            if the player was already attacked, we don't hit them again
            if (!playerAttacked || v != player) {
                int damage = v.getForm().getDamage(item);
                v.losePoints(damage);
                if (v == player) { //only print attacks to the player.
                    playerAttacked = true;
                    if (item.equalsIgnoreCase("garlic"))
                        bff.printRed("Mystery Event: 🧄 A Human throws garlic at you 🧄. You lose " + damage + " points.");
                    else if (item.equalsIgnoreCase("cross"))
                        bff.printRed("Mystery Event: ✝️ A Human yields a cross at you ✝️. You lose " + damage + " points.");
                    else if (item.equalsIgnoreCase("sunlight"))
                        bff.printRed("Mystery Event: ☀️️☀️☀️ The sun hits you ️️☀️☀️☀️. You lose " + damage + " points.");
                } else {
                    bff.print(v.getDisplay() + " took damage of " + damage);
                }
            } else {
                // player was already attacked, don't double hit the same player
            }
        }
    }

    private void change() {
        // ask the player what form they want to chang eto
        String desiredForm =  bff.input("What form do you want to change to? (Vampire, Vampire_Bat, Vampire_Wolf)", "Vampire", "Vampire_Bat", "Vampire_Wolf");

        // change the form depending on the user input
        player.changeForm(VampireForm.valueOf(desiredForm.toUpperCase()));
    }

    private void rest() {
        int probability = getRandomProbability();
        // 50% chance to rest and get a point, 50% to not sleep. (no Points)

        if (probability < 50) {
            player.earnPoints(1);
            bff.print("You take a rest in your coffin ⚰️ and rejuvenate. +1 point");
        } else {
            bff.print("You can't sleep :(");
        }
    }

    /**
     * when the player is a vampire, they can use this method to attack
     */
    private void attack() { //complete
        bff.print("You have chosen to attack... \n\tYou start to search for your victim");
        attackBy(player);
    }

    @SuppressWarnings("PatternVariableCanBeUsed")
    private void attackBy(Vampire hunter) {
        // choose a random computerPlayer (equal odds)
        // if NPC is a Vampire, do vampireFaceOff
        // if NPC is a Human, print the victim's info
        // then the hunter will suck their blood -- use the suck(Vampire, Human, int) method
        // 1 quart if it is a non-preferred blood type
        // 2 quarts for favBlood2 type
        // 3 quarts for favBlood1 type
        // next figure out if the human is still alive
        // if not, removeBeingFromArray and award the hunter 3 points.

        int victimIndex = rand.nextInt(numBeings); // gets an index of a random player
        Being victim = computerPlayers[victimIndex]; // gets the player at that index

        if (victim instanceof Vampire) {
            Vampire vampireVictim = (Vampire) victim;
            vampireFaceOff(vampireVictim, hunter);
        } else if (victim instanceof Human) {
            int amount;
            Human humanVictim = (Human) victim;
            if (humanVictim.getBloodType().equals(hunter.getFavBlood1())) {
                amount = 3;
            } else if (humanVictim.getBloodType().equals(hunter.getFavBlood2())) {
                amount = 2;
            } else {
                amount = 1;
            }

            suck(hunter, humanVictim, amount);

            // check if human is still alive
            boolean alive = assessIfAlive(humanVictim);
            if (!alive) {
                removeBeingFromArray(victimIndex);
                hunter.earnPoints(3);
            }

            if (hunter == player) {
                String s = humanVictim.toString(); // the human to string
                if (!alive) {
                    s = "You drained the life from " + humanVictim.getName() + " and they have been removed from the game";
                }
                bff.printRed("🩸 Wasn't that human delicious? You drank 🩸 " + amount + " quarts from human: " + s);
            } else {
                bff.print(hunter.getIcon() + " " + hunter.getName() + " drinks 🩸 " + amount + " quarts from human " + humanVictim);
            }

        }
    }

    private int suck(Vampire hunter, Human human, int numQuarts) {
        int count = 0;
        // the vampire tries to drink up to numQuarts, 1 quart at a time.
        // Loop numQuarts times. If suckBlood call on the human returns true, count it and call vampire's suck blood

        for (int i = 0; i < numQuarts; i++) {
            if (human.suckBlood()) {
                hunter.suckBlood();
                count++;
            }
        }

        return count; //number of quarts successfully drained from human
    }

    private void vampireFaceOff(Vampire attacker, Vampire other) {
        String display = ("It's a VAMPIRE face-off\n" + attacker.getName() + " the " + attacker.getIcon() + " versus " + other.getName() + " the " + other.getIcon());

        bff.print(display);
        String playerMessage = "";

        if (attacker.getForm() == other.getForm()) {
//            tie case
            attacker.losePoints(1);
            other.losePoints(1);
            playerMessage = "It's a tie, both lose 1 point!";
        } else if (VampireForm.getWinner(attacker.getForm(), other.getForm()) == attacker.getForm()) {
            //            attacker wins
            attacker.earnPoints(2);
            other.losePoints(2);
            playerMessage = attacker.getName() + " wins! +2 points for " + attacker.getName();
        } else if (VampireForm.getWinner(attacker.getForm(), other.getForm()) == other.getForm()) {
            //            other wins
            attacker.losePoints(2);
            other.earnPoints(2);
            playerMessage = other.getName() + " wins! +2 points for " + other.getName();
        }
        // finish this based on rock-paper-scissor rules (Comparing forms)
        // NOTICE: there is a helper function in VampireForm that will get winning form.
        //if attacker and other are the same, it's a tie, and both lose 1 point.
        //otherwise winner gets 2 points, loser loses 2 points

        //if attacker or other is  the player,  print  message from the point of player
        //otherwise a message is optional.

        //        if the attacker or other are the player, we print a message; otherwise it's optioanl
        if (attacker == player || other == player) {
            bff.printRed(playerMessage);
        }
    }

    private void removeBeingFromArray(int num) {
        Being goner = computerPlayers[num];
        // remove being from the array. Array can stay the same size, but numBeings will be 1 less
        // and all Being objects get shifted to the left to not leave a gap
        // if the goner is a Vampire, also delete from the list of allVampires.
        if (goner instanceof Vampire) {
            allVampires.remove(goner);
        }

        // drop goner
        computerPlayers[num] = null;


//        this shifts everything to the left starting fromt he index of the goner
        for (int i = num + 1; i < numBeings - 1; i++) {
            computerPlayers[i - 1] = computerPlayers[i];
        }

        // decrement numBeings
        numBeings--;

    }

    private boolean assessIfAlive(Human h) {
        // return true if human is alive
        // otherwise print human's name with a message that they have been sucked dry AND return false;
        boolean alive = true;

        if (h.getQuarts() <= 0) {
            System.out.println(h.getName() + " has been sucked dry.");
            alive = false;
        }
        return alive;
    }

    private int getRandomProbability() {
        return rand.nextInt(100);
    }

    /**
     * Method that prints welcome to the game
     */
    private void displayWelcome() { //complete
        bff.printFancy("🧛🏼 🦇 🐺 Welcome to the Vampire Attack Game 🧛🏼 🦇 🐺\n"
                + "\n\tIn this game it's humans versus vampires. Vampires can change forms to traditional, bat, or wolf"
                + "\n\tThe vampires find humans and suck their blood 🩸. If they find a human with one of their "
                + "\n\tfavorite bloodTypes, they feast more. Humans aren't great at finding vampires, but when "
                + "\n\tthey do, they may attack with garlic 🧄, sunlight ☀️ , or branding a cross ✝️,"
                + "\n\t which affects the Vampire's health points, depending on the Vampire's current form "
                + "\n\n\tVampires sometimes face-off in a battle (Rock-Paper-Scissors style) based on their form"
                + "\n\t Original Vampire 🧛🏼 beats Vampire-Bat 🦇. Vampire-Bat 🦇 beats Vampire-Wolf 🐺. Vampire-Wolf 🐺 beats Original Vampire.🧛🏼"
                + "\n\nYou are a vampire, and it is time for you to start your adventure!");
    }

    /**
     * Method that prints instructions for the game
     */
    private void displayInstructions() { //complete
        bff.print("This game runs by string commands: you can type the following: "
                + "\n\tRest (which might increase health points)" + "\n\tAttack (Randomly attack a computer player)" + "\n\tChange (to switch form)" + "\n\tQuit (to end game)");
    }
}
