import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to calculate the solutions for Day 4. It involves parsing
 * the input text file for Strings containing "number ranges" (e.g. 43-68) and then
 * running checks to see which ranges overlap, or are duplicated within other ranges.
 */
public class Main {

    public Main(){}
    public static void main(String[] args) {
        // Setup.
        File file = new File("src/input.txt");
        Main main = new Main();
        int partOne = 0;
        int partTwo = 0;
        
        // Try reading the file.
        try {
            Scanner input = new Scanner(file);
            
            // Read each line of the file.
            while (input.hasNext()) {
                /* Split the line into two ranges by finding the comma.
                 * In hindsight, probably easier to use the String.split() method
                 * and work with the String[] instead.
                 */
                String line = input.nextLine();
                int commaNum = line.indexOf(",");
                String partA = line.substring(0,commaNum);
                String partB = line.substring(commaNum + 1);
                // To calculate part one:
                partOne += main.isItWithin(partA, partB);

                // To calculate part two:
                partTwo += main.doesItOverlap(partA, partB);
            }
            System.out.println("Answer to Part One: " + partOne);
            System.out.println("Answer to Part Two: " + partTwo);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method takes two Strings which contain number ranges (e.g. "32-56") and then checks
     * to see if one range is completely contained within the other.
     * @param partA a String containing the first number range.
     * @param partB a String containing the second number range.
     * @return a true/false Integer - 1 if the condition is satisfied, 0 if not.
     */
    public int isItWithin(String partA, String partB) {
        // Break Strings into integers
        int dashA = partA.indexOf("-");
        int dashB = partB.indexOf("-");
        int A1 = Integer.parseInt(partA.substring(0, dashA));
        int A2 = Integer.parseInt(partA.substring(dashA + 1));
        int B1 = Integer.parseInt(partB.substring(0, dashB));
        int B2 = Integer.parseInt(partB.substring(dashB + 1));

        // Is the set of A1:A2 completely in B1:B2? Vice Versa?
        if ((A1 <= B1 && A2 >= B2) || (B1 <= A1 && B2 >= A2)) {
            // Yes
            return 1;
        }
        return 0;
    }

    /**
     * This method takes two Strings which contain number ranges (e.g. "32-56") and then checks
     * to see if the number ranges overlap at all.
     * @param partA a String containing the first number range.
     * @param partB a String containing the second number range.
     * @return a true/false Integer - 1 if the condition is satisfied, 0 if not.
     */
    public int doesItOverlap(String partA, String partB) {
        // Break Strings into integers
        int dashA = partA.indexOf("-");
        int dashB = partB.indexOf("-");
        int A1 = Integer.parseInt(partA.substring(0, dashA));
        int A2 = Integer.parseInt(partA.substring(dashA + 1));
        int B1 = Integer.parseInt(partB.substring(0, dashB));
        int B2 = Integer.parseInt(partB.substring(dashB + 1));

        // Does the set of A1:A2 have any overlap with B1:B2?
        if ((A1 >= B1 && A1 <= B2) || (A2 >= B1 && A2 <= B2) ||
                (B1 >= A1 && B1 <= A2) || (B2 >= A1 && B2 <= A2)) {
            return 1;
        }
        return 0;
    }

}
