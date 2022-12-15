import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class is a quick solution to Advent of Code 2022: Day 01.
 * Part 01 asks to calculate which Elf is carrying the most calories.
 * Part 02 asks to calculate the sum of the calories being carried by the
 * three Elves who are each carrying the most calories.
 */
public class Main {
    public static void main(String[] args) {
        // Setup.
        File inputFile = new File("src/input.txt");
        ArrayList<Integer> list = new ArrayList<>();
        int temp = 0;
        int tracker = 0;

        // Try to read in the input file.
        try {
            Scanner input = new Scanner(inputFile);
            /* Check each line of the file. If the line is empty, it is a break
             * between Elves. All lines since the last empty line should be summed
             * and the result is the number of calories that Elf is carrying.
             */
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() > 0) {
                    // Has data. Add calorie count to current Elf.
                    temp = Integer.parseInt(line);
                    tracker += temp;
                } else {
                    // Elf has ended. Add this Elf into the Arraylist and
                    // reset the count.
                    list.add(tracker);
                    tracker = 0;
                }
            }
            // Find the top three values by sorting the Arraylist in descending order.
            Collections.sort(list, Collections.reverseOrder());
            System.out.println("Elf 1: " + list.get(0));
            System.out.println("Elf 2: " + list.get(1));
            System.out.println("Elf 3: " + list.get(2));
            int sum = list.get(0) + list.get(1) + list.get(2);
            System.out.println("Sum: " + sum);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
