import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Simple class that reads a file of names and has a method to return a random name
 *
 * @author Kendra Walther
 * Fall 2023, ITP 265, My Section
 * Email: kwalther@usc.edu
 * Date Created: 10/30/23
 */
public class NameGenerator {
    private static final String FILENAME_SHORT = "names.txt";
    private static final String FILENAME_LONG = "/Users/kendra/Documents/code/itp265/answers/A08-userLogin/AssignmentSolutions/A09-VampireGame/src/names.txt";
    private final ArrayList<String> allNames;
    private final Random r;

    public NameGenerator() {
        allNames = new ArrayList<>(4945);
        initializeNames();
        r = new Random();
    }

    public String getRandomName() {
        return allNames.get(r.nextInt(allNames.size()));
    }

    public ArrayList<String> getAllNames() {
        return allNames;
    }

    private void initializeNames() {
        String fileName = FILENAME_SHORT;
        boolean read = true, triedAlt = false;
        do {
            try (FileInputStream fis = new FileInputStream(fileName); Scanner fs = new Scanner(fis)) {
                while (fs.hasNextLine()) {
                    String name = fs.nextLine();
                    allNames.add(name);

                }
                read = true;
            } catch (IOException e) {
                System.err.println("Name File not found, trying long version");
                read = false; //to force a second try
                if (!triedAlt) {
                    fileName = FILENAME_LONG;
                    triedAlt = true;
                } else {
                    System.err.println("Neither name file found, creating some fake data");
                    allNames.add("Trina");
                    allNames.add("Sinan");
                    allNames.add("Nitin");
                    allNames.add("Reza");
                    allNames.add("Rob");
                    allNames.add("Sanjay");
                    allNames.add("Matt");
                    allNames.add("Kendra");
                }
            }
        } while (!read);
    }
}
