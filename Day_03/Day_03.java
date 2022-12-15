import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class calculates the solution to both parts of Day 3.
 * The solution I have written is a little 'brute force' and could definitely
 * be improved.
 */
public class Main {

    public Main(){}
    
    public static void main(String[] args) {
        File file = new File("src/input.txt");
        Main main = new Main();
        try {
            Scanner input = new Scanner(file);
            int priority = 0;
            int priorities = 0;
            while (input.hasNext()) {
                String partA = input.nextLine();
                String partB = input.nextLine();
                String partC = input.nextLine();

                priorities += main.getTriplicates(partA, partB, partC);
            }
            System.out.println(priorities);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to calculate the answer to Part 2 of the problem.
     * @param a one String to be compared.
     * @param b the second String to be compared.
     * @param c the third String to be compared.
     * @return  an Integer containing the 'priority' (ASCII number) of the character
     *          which is common to all three Strings.
     */
    public int getTriplicates(String a, String b, String c) {
        // Find the char which is common to all 3 Strings
        for (char x : a.toCharArray()) {
            for (char y : b.toCharArray()) {
                for (char z : c.toCharArray()) {
                    if (x == y && y == z) {
                        // Match found
                        if ((int)z < 97) {
                            // Letter is uppercase
                            return ((int)z - 38);
                        } else {
                            // Letter is lowercase
                            return ((int)z - 96);
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * This method is used to calculate the answer for Part One of the problem.
     * @param a one of the Strings to be compared.
     * @param b the other String to be compared.
     * @return  an Integer containing the 'priority' (ASCII number) of the character
     *          which is common to both String a and String b.
     */
    public int getDuplicates(String a, String b) {
        for (char c : a.toCharArray()) {
            for (char z : b.toCharArray()) {
                if (z == c) {
                    // Match found
                    if ((int)z < 97) {
                        // Letter is uppercase
                        return ((int)z - 38);
                    } else {
                        // Letter is lowercase
                        return ((int)z - 96);
                    }
                }
            }
        }
        return 0;
    }

}
