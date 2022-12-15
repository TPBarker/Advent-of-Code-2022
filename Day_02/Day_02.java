import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Notes:
 * A = Rock (1)
 * B = Paper (2)
 * C = Scissors (3)
 * X = Loss (0)
 * Y = Draw (3)
 * Z = Win (6)
 */

/**
 * This class is the solution for Advent of Code 2022 - Day 02. The problem involves
 * reading a coded text input from a file and then determining which actions should be
 * taken to achieve certain results in a simulated game of Rock Paper Scissors.
 */
public class Main {
    public static void main(String[] args) {
        // Setup.
        File file = new File("src/input.txt");
        int points = 0;
        
        // Try reading the file.
        try {
            Scanner fileInput = new Scanner(file);
            
            // Loop through each line of the file.
            while (fileInput.hasNextLine()) {
                String opponent = fileInput.next();
                String player = fileInput.next();
                switch (player) {
                    case "X":
                        // This indicates we need to lose this game.
                        switch (opponent) {
                            case "A":
                                // Opponent picked rock, need to pick scissors.
                                // 3 points for scissors.
                                points += 3;
                                break;
                            case "B":
                                // Opponent picked paper, need to pick rock.
                                // 1 point for rock.
                                points += 1;
                                break;
                            case "C":
                                // Opponent picked scissors, need to pick paper.
                                // 2 points for paper.
                                points += 2;
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Y":
                        // This indicates we need to draw this game. We will therefore
                        // receive 3 points for drawing.
                        points += 3;
                        switch (opponent) {
                            case "A":
                                // Opponent picked rock, need to pick rock.
                                // 1 point for rock.
                                points += 1;
                                break;
                            case "B":
                                // Opponent picked paper, need to pick paper.
                                // 2 points for paper.
                                points += 2;
                                break;
                            case "C":
                                // Opponent picked scissors, need to pick scissors.
                                // 3 points for scissors.
                                points += 3;
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Z":
                        // This indicates we need to win this game. We will
                        // receive 6 points for winning.
                        points += 6;
                        switch (opponent) {
                            case "A":
                                // Opponent picked rock, need to pick paper.
                                // 2 points for paper.
                                points += 2;
                                break;
                            case "B":
                                // Opponent picked paper, need to pick scissors.
                                // 3 points for scissors.
                                points += 3;
                                break;
                            case "C":
                                // Opponent picked scissors, need to pick rock.
                                // 1 point for rock.
                                points += 1;
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Total points: " + points);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
