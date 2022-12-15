import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is my solution for Day 6. It involves parsing input from a text file
 * which contains a very long String of characters, and then checking to find the
 * earliest point at which it contains a set of completely unique characters of
 * a certain length (part one: 4 unique characters, part two: 14 unique
 * characters).
 */
public class Main {

    public Main(){}

    public static void main(String[] args) {
        File file = new File("src/input.txt");
        Main main = new Main();

        System.out.println("Solution to Part One: " + main.findMatch(file));
        System.out.println("Solution to Part Two: " + main.findMatchTake2(file));
    }

    /**
     * This method calculates the solution for part two. It reads the String in
     * from the text file and reads along it, looking for the first set of fourteen
     * unique characters. Compared to part one, it uses a much smarter solution. For
     * each set of 14 characters, it counts each character it encounters in a HashMap.
     * Then, if the HashMap contains 14 entries, we know we have encountered 14
     * unique characters.
     * @param file the text file containing the String.
     * @return an Integer containing the index number of the last character in the
     * unique fourteen character set.
     */
    public int findMatchTake2(File file) {
        try {
            // Setup.
            Scanner input = new Scanner(file);
            String line = input.nextLine();
            int i = 13;
            
            // Loop through each character in the String.
            while (i < line.length()) {
                // Build the fourteen character set.
                StringBuilder builder = new StringBuilder();
                builder.append(line.charAt(i - 13));
                builder.append(line.charAt(i - 12));
                builder.append(line.charAt(i - 11));
                builder.append(line.charAt(i - 10));
                builder.append(line.charAt(i - 9));
                builder.append(line.charAt(i - 8));
                builder.append(line.charAt(i - 7));
                builder.append(line.charAt(i - 6));
                builder.append(line.charAt(i - 5));
                builder.append(line.charAt(i - 4));
                builder.append(line.charAt(i - 3));
                builder.append(line.charAt(i - 2));
                builder.append(line.charAt(i - 1));
                builder.append(line.charAt(i));

                // For each character in the set, add its count to a HashMap.
                HashMap<Character, Integer> map = new HashMap<>();
                for (char c : builder.toString().toCharArray()) {
                    if (map.containsKey(c)) {
                        map.put(c, (map.get(c) + 1));
                    } else {
                        map.put(c, 1);
                    }
                }
                /* If the HashMap contains 14 entries, each character in the
                 * fourteen-character set must be unique. Return the index of
                 * the appropriate character.
                 */
                if (map.size() == 14) {
                    return i + 1;
                }
                i++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This method calculates the solution for part one. It reads the String in
     * from the text file and reads along it, looking for the first set of four
     * unique characters. It uses a very 'brute force' approach of a complicated
     * conditional statement to check the four characters.
     * @param file the text file containing the String.
     * @return an Integer containing the index of the last character in the
     * four unique character set.
     */
    public int findMatch (File file) {
        try {
            // Setup.
            Scanner input = new Scanner(file);
            StringBuffer buffer = new StringBuffer();
            String line = input.nextLine();
            
            // Loop through every character in the String.
            for (int i = 0; i < line.length(); i++) {
                // If this character is in the first four, setup the first four
                // character set.
                if (buffer.length() < 4) {
                    buffer.append(line.charAt(i));
                } else {
                    // Check to see if this 4 character set is unique. If so, we can terminate.
                    if (buffer.charAt(0) == buffer.charAt(1) ||buffer.charAt(0) ==
                            buffer.charAt(2) || buffer.charAt(0) == buffer.charAt(3) ||
                            buffer.charAt(1) == buffer.charAt(2) || buffer.charAt(1) ==
                            buffer.charAt(3) || buffer.charAt(2) == buffer.charAt(3)) {
                        // Duplicate characters found, loop again.
                    } else {
                        // Unique substring found, can terminate.
                        return i;
                    }
                    // Load the next character into the set.
                    buffer.deleteCharAt(0);
                    buffer.append(line.charAt(i));
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
