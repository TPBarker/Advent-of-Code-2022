import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public Main() {
    }
    public static void main(String[] args) {
        // Setup
        File file = new File("src/input.txt");
        Main main = new Main();
        Scanner input = null;

        // Try reading in the file.
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Check to see how big the array needs to be.
        int lineLength = 0;
        int lineCount = 0;
        while (input.hasNext()) {
            lineLength = input.nextLine().length();
            lineCount++;
        }

        // Initialise an array of appropriate size.
        int[][] array = new int[lineCount][lineLength];
        lineCount = 0;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Build array of values from the file.
        while (input.hasNext()) {
            String line = input.nextLine();
            for (int j = 0; j < line.length(); j++) {
                array[lineCount][j] = Integer.parseInt(line.substring(j, j + 1));
            }
            lineCount++;
        }

        // Print array to double-check
        //main.printArray(lineCount, lineLength, array);

        // Part One.
        main.checkArray(lineCount, lineLength, array);

        // Part Two.
        main.scenicScore(lineCount, lineLength, array);
    }

    /**
     * This method solves for Part One of the problem. It checks the array to see how
     * many trees are visible from 'outside' the array looking in.
     * @param lineCount an Integer containing the number of lines in the array.
     * @param lineLength an Integer containing the number of characters in each line.
     * @param array an array of Integers containing the 'trees' in the array.
     */
    public void checkArray(int lineCount, int lineLength, int[][] array) {
        int visibleNum = 0;

        // For each value
        // i goes top to bottom, j goes left to right
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < lineLength; j++) {
                boolean visibleTop = true;
                boolean visibleBottom = true;
                boolean visibleLeft = true;
                boolean visibleRight = true;

                // If the value is on the 'edge' then it is visible
                if (i == 0 || j == 0 || i == lineCount - 1 || j == lineLength - 1) {
                    // Value is on 'the edge' and is visible
                } else {
                    // If the value is not 'on the edge' then we should check
                    // above, below, left and right to see if it is still visible
                    // Check above
                    for (int a = 0; a < i; a++) {
                        if (array[a][j] >= array[i][j]) {
                            visibleTop = false;
                            break;
                        }
                    }

                    // Check below
                    for (int b = lineCount - 1; b > i; b--) {
                        if (array[b][j] >= array[i][j]) {
                            visibleBottom = false;
                            break;
                        }
                    }

                    // Check left
                    for (int l = 0; l < j; l++) {
                        if (array[i][l] >= array[i][j]) {
                            visibleLeft = false;
                            break;
                        }
                    }

                    // Check right
                    for (int r = lineLength - 1; r > j; r--) {
                        if (array[i][r] >= array[i][j]) {
                            visibleRight = false;
                            break;
                        }
                    }
                }

                if (visibleTop || visibleBottom || visibleLeft || visibleRight) {
                    visibleNum++;
                }
            }
        }
        System.out.println("Number of visible trees: " + visibleNum);
    }

    /**
     * This method is used for debugging. It prints the entire array to the screen.
     * @param lineCount  an Integer containing the number of lines in the input text file.
     * @param lineLength an Integer containing the number of characters in each line.
     * @param array an Integer array containing the values from the text file.
     */
    public void printArray(int lineCount, int lineLength, int[][] array) {
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < lineLength; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method solves for Part Two of the problem. It calculates the 'Scenic Score' of
     * each tree in the array, by checking how many trees can be seen up, down, left and right
     * from each tree and multiplying these numbers together.
     * @param lineCount an Integer containing the number of lines in the array.
     * @param lineLength an Integer containing the number of characters in each line.
     * @param array the array of 'Trees' populated from the text file.
     */
    public void scenicScore(int lineCount, int lineLength, int[][] array) {
        // Setup.
        int topScore = -1;

        // For each tree in the array.
        // i goes top to bottom, j goes left to right.
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < lineLength; j++) {
                // Get score for up.
                ArrayList<Integer> upValues = new ArrayList<>();
                for (int u = i - 1; u >= 0; u--) {
                    upValues.add(array[u][j]);
                }
                // Get score for down.
                ArrayList<Integer> downValues = new ArrayList<>();
                for (int d = i + 1; d < lineCount; d++) {
                    downValues.add(array[d][j]);
                }
                // Get score for left.
                ArrayList<Integer> leftValues = new ArrayList<>();
                for (int l = j - 1; l >= 0; l--) {
                    leftValues.add(array[i][l]);
                }
                // Get score for right.
                ArrayList<Integer> rightValues = new ArrayList<>();
                for (int r = j + 1; r < lineLength; r++) {
                    rightValues.add(array[i][r]);
                }

                // Calculate scenic score for this tree.
                int up =scoreTree(array[i][j], upValues);
                int down = scoreTree(array[i][j], downValues);
                int left = scoreTree(array[i][j], leftValues);
                int right = scoreTree(array[i][j], rightValues);
                int scenicScore = up * down * left * right;

                if (scenicScore > topScore) {
                    topScore = scenicScore;
                }
            }
        }

        System.out.println("Best tree score: " + topScore);
    }

    /**
     * This method calculates the scenic score for a given tree. It does so by calculating
     * how many trees we can see in a given direction (in our ArrayList of 'Trees').
     * @param treeHeight the height of the tree we are checking from.
     * @param trees an ArrayList containing every tree in the given direction.
     * @return an Integer containing how many trees we can see in this direction.
     */
    public int scoreTree(int treeHeight, ArrayList<Integer> trees) {
        // Setup.
        int treeCount = 0;

        for (int i : trees) {
            if (i < treeHeight) {
                // Tree is smaller than tree.
                treeCount++;
            }
            if (i >= treeHeight) {
                // Tree blocks view - it is the last tree we can see.
                treeCount++;
                return treeCount;
            }
        }
        return treeCount;
    }
}
