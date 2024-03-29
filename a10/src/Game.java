import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the game.
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class Game {
    private Being[] computerPlayers; //a Being array holding the non-player Being objects - either Human or Vampire objects
    private ArrayList<Vampire> allVampires; // all the vampires (player + computer vampire players)
    private int numBeings; // number of filled spots in the computerPlayer array
    private Vampire player; // the program user
    private BFF bff;
    private Random rand;
    private NameGenerator ng;
    private static final int DEFAULT_NUM_BEINGS = 8;

    /**
     * This constructor creates a game (calling the other constructor) with the default number of beings
     */
    public Game(){

        this(DEFAULT_NUM_BEINGS);
    }

    /**
     * This constructor creates a game with a specified number of beings & sets all the instance vars
     * @param num the number of beings to be added to the game
     */
    public Game(int num){ //complete
        bff = new BFF();
        rand = new Random();
        ng  = new NameGenerator();
        allVampires = new ArrayList<>();
        computerPlayers = new Being[num];
        numBeings = num;
        displayWelcome();
        player = createVampire();
        allVampires.add(player);
        generateComputerPlayers();
    }

    private void generateComputerPlayers(){
        for(int i = 0; i < computerPlayers.length; i++) {
            Being being = null;
            String name = ng.getRandomName();
            //TODO: finish. 60% probability of making a human, else make a vampire
            // random starting quarts 3-10
            // humans should get a random BloodType from the enum
            // vampires will get a random form.

            //TODO: Make sure to add vampire to the computerPlayers array AND the allVampires list

        }
    }

    /**
     * Asks the user to specify vampire name, quarts of blood, starting form, and 2 favorite blood types
     * and creates a new Vampire object using those inputs
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
    private void printComputerPlayers(){ //complete
        bff.printRed("Your opponents: ");
        for(int i = 0; i < numBeings; i++){
            bff.print(i + ": " + computerPlayers[i]);
        }
        int numHumans = getNumberOfHumans();
        bff.printRed("There are " + numHumans + " humans and " +  (numBeings - numHumans) + " computer vampires" );
    }

    /**
     * Runs an infinite loop that is "broken" when the game is over (i.e., over=true)
     * to repeatedly run rounds of the game; the player can input commands each round
     * and the computer players will take their turns.
     */
    public void play(){ //complete
        printComputerPlayers();
        bff.input("Hit return to continue");
        displayInstructions();

        int roundNum = 1;
        boolean over = false;
        boolean quit = false;
        while(!over){
            String command =  bff.input("Round #" +  roundNum + ": " + player.getName() + " Enter command >", "Rest", "Attack", "Change", "Quit");
            switch(command.toLowerCase()){
                case "rest": rest(); break;
                case "attack": attack(); break;
                case "change": change(); break;
                case "quit": quit = true; over = true; break;
            }
            if(!quit) {
                npcActions();
                mysteryEvent();
                over=checkGameStatus();
                if(bff.inputYesNo("Want to print opponents stats (it costs 1 point?")){
                    printComputerPlayers();
                    player.losePoints(1);
                }
            }
            roundNum++;
        }
        displayGameOver(quit, roundNum -1);
    }

    /**
     * This method shows the game over message, including interesting stats about all of the players
     * @param playerQuits whether the player quit the game before it ended
     * @param rounds the number of rounds that were played
     */
    private void displayGameOver(boolean playerQuits, int rounds) { //complete
        bff.print("Game over after " + rounds + " rounds");
        String winner = "";
        if(playerQuits){
            bff.print("Player quit... but here are the ending stats:\n");
        }
        bff.print("Here is how each vampire scored:");
        rankVampiresInList();
        for(Vampire v: allVampires){
            if(v == player){
                bff.printRed("\tYOU: " + v );
            }
            bff.print("\t" + v);
        }
        if(getNumberOfHumans() == 0){
            bff.print("The VAMPIRES win, no humans remain\n");
            bff.printRed("Congratulations to top scoring Vampire: " + allVampires.get(0));
        }
        else if(player.getPoints() == 0){
            bff.print("The HUMANS win.\n");
        }
        else{
            bff.print(getNumberOfHumans() + " Humans remain...Congratulations to top scoring Vampire(s):");
            int index = 1;
            String top = allVampires.get(0).toString();
            while(allVampires.get(index).getPoints() == allVampires.get(0).getPoints() && index < allVampires.size()){
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

    private int getNumberOfHumans(){
        //TODO
        bff.printRed("getNumberOfHumans is not implemented yet.");
        return 0;
    }


    /**
     * Checks if the game has ended (either all humans are gone or the player has no points)
     * @return whether the game has ended according to the defined criteria
     */
    private boolean checkGameStatus() { //complete
        boolean gameOver = false;
        if(getNumberOfHumans() == 0){
            gameOver = true;
        }
        if(player.getPoints() == 0){
            gameOver = true;
        }
        return gameOver;
    }

    private void npcActions() { //Non player characters perform actions.
        int numHumans = getNumberOfHumans();
        bff.printFancy("Computer beings (the humans and vampires) take their turns.\n There are " + numHumans +
                " humans and " +  (numBeings - numHumans) + " computer vampires" );

        //TODO: Finish this method. Loop through all the computerPlayers, Use random number to make game more fun
        //if npc is human, there's a 3/10 chance that they can increase blood quarts by 1.
        // if npc is a vampire, there is a 50% chance that the vampire attacks another player and a
        // 50% chance that they change form.

    }

    private void mysteryEvent() { //complete
        int probability = rand.nextInt(100);
        if (probability > 70)  { //30% change
            int num = rand.nextInt(numBeings);
            bff.printRed("❓Mystery event causes a computer player to disappear... Goodbye " + computerPlayers[num].getName());
            removeBeingFromArray(num);
        }
        else {
            int damage = 0;
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
    private void humanAttackVampires(String item){  //complete
        int numberOfAttacks = rand.nextInt(2) + 1; // attacks 1, 2, or 3 vampires.
        boolean playerAttacked = false;
        for(int i = 0; i < numberOfAttacks; i++) {
            Vampire v = allVampires.get(rand.nextInt(allVampires.size()));
            if(playerAttacked && v == player){
                // player was already attacked, don't double hit the same player
            }
            else {
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
                }
                else{
                    bff.print(v.getDisplay() + " took damage of " + damage);
                }
            }
        }
    }
    private void change() {
        //TODO - player chooses a different vampire form to take.
    }

    private void rest(){
        //TODO 50% chance to rest and get a point, 50% to not sleep. (no Points)
        //  bff.print("You take a rest in your coffin ⚰️ and rejuvenate. +1 point");
        // bff.print("It is too noisy to sleep");
    }
    private void attack() { //complete
        bff.print("You have chosen to attack... \n\tYou start to search for your victim");
        attackBy(player);
    }

    private void attackBy(Vampire hunter) {
        //TODO: choose a random computerPlayer (equal odds)
        // if NPC is a Vampire, do vampireFaceOff
        // if NPC is a Human, print the victim's info
        // then the hunter will suck their blood -- use the suck(Vampire, Human, int) method
        // 1 quart if it is a non-preferred blood type
        // 2 quarts for favBlood2 type
        // 3 quarts for favBlood1 type
        //TODO: next figure out if the human is still alive
        // if not, removeBeingFromArray and award the hunter 3 points.

        //TODO: Uncomment this at the end to have a nice print message for when the player is the hunter
        /*
            if(hunter == player){
                String s = h.toString(); // the human to string
                if(!alive) {
                    s = "You drained the life from " + h.getName() + " and they have been removed from the game";
                }
                bff.printRed("🩸 Wasn't that human delicious? You drank 🩸 " + amt + " quarts from human: " +  s);
              }
            else{
                bff.print(hunter.getIcon() + " " + hunter.getName() + " drinks 🩸 " + amt + " quarts from human " + h);
            }
        }*/
    }

    private int suck(Vampire hunter, Human human, int numQuarts) {
        int count = 0;
        //TODO: the vampire tries to drink up to numQuarts, 1 quart at a time.
        // Loop numQuarts times. If suckBlood call on the human returns true, count it and call vampire's suck blood
        return count; //number of quarts successfully drained from human
    }

    private void vampireFaceOff(Vampire attacker, Vampire other) {
        String display = ("It's a VAMPIRE face-off\n" + attacker.getName() + " the " + attacker.getIcon() + " versus " + other.getName() + " the " + other.getIcon());

        //TODO: finish this based on rock-paper-scissor rules (Comparing forms)
        // NOTICE: there is a helper function in VampireForm that will get winning form.
        //if attacker and other are the same, it's a tie, and both lose 1 point.
        //otherwise winner gets 2 points, loser loses 2 points

        //if attacker or other is  the player,  print  message from the point of player
        //otherwise a message is optional.
    }


    private void removeBeingFromArray(int num) {

        Being goner = computerPlayers[num];
        //TODO: remove being from the array. Array can stay the same size, but numBeings will be 1 less
        // and all Being objects get shifted to the left to not leave a gap
        // if the goner is a Vampire, also delete from the list of allVampires.
    }

    private boolean assessIfAlive(Human h) {
        //TODO: return true if human is alive
        // otherwise print human's name with a message that they have been sucked dry AND return false;
        boolean alive = true;
        return alive;
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







    /**
     * Starts the whole program
     * @param args
     */
    public static void main(String[] args) { //complete
        new Game().play();
    }
}
