import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class reads input from the text file as a sequence of operations for a crane
 * on a cargo dock, moving crates between stacks of crates. Solution One involves moving
 * crates from one stack to another one at a time, and Solution Two involves the crane
 * picking up multiple crates at once, which alters the behaviour of the pop/push
 * operations of the stacks.
 */
public class Main {
    private Stack<Character> column1;
    private Stack<Character> column2;
    private Stack<Character> column3;
    private Stack<Character> column4;
    private Stack<Character> column5;
    private Stack<Character> column6;
    private Stack<Character> column7;
    private Stack<Character> column8;
    private Stack<Character> column9;

    public Main() {
        this.column1 = new Stack<>();
        this.column2 = new Stack<>();
        this.column3 = new Stack<>();
        this.column4 = new Stack<>();
        this.column5 = new Stack<>();
        this.column6 = new Stack<>();
        this.column7 = new Stack<>();
        this.column8 = new Stack<>();
        this.column9 = new Stack<>();
    }

    // Accessor methods.
    public Stack<Character> getColumn1() {
        return column1;
    }

    public Stack<Character> getColumn2() {
        return column2;
    }

    public Stack<Character> getColumn3() {
        return column3;
    }

    public Stack<Character> getColumn4() {
        return column4;
    }

    public Stack<Character> getColumn5() {
        return column5;
    }

    public Stack<Character> getColumn6() {
        return column6;
    }

    public Stack<Character> getColumn7() {
        return column7;
    }

    public Stack<Character> getColumn8() {
        return column8;
    }

    public Stack<Character> getColumn9() {
        return column9;
    }

    private ArrayList<Stack<Character>> bundleStacks() {
        ArrayList<Stack<Character>> bundle = new ArrayList<>();
        bundle.add(column1);
        bundle.add(column2);
        bundle.add(column3);
        bundle.add(column4);
        bundle.add(column5);
        bundle.add(column6);
        bundle.add(column7);
        bundle.add(column8);
        bundle.add(column9);
        return bundle;
    }

    public static void main(String[] args) {
        // Setup. 
        Main main = new Main();
        File positions = new File("src/positions.txt");
        File instructions = new File("src/instructions.txt");

        // Read matrix positions from the file and add data into the columns.
        main.populateMatrix(positions);

        // Add the column stacks into the ArrayList.
        ArrayList<Stack<Character>> allStacks = main.bundleStacks();

        // Reverse positions.
        ArrayList<Stack<Character>> newStacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            newStacks.add(main.reverseStack(allStacks.get(i)));
        }

        // For Solution 1.
        ArrayList<Stack<Character>> finalStacks = main.operateInstructions(instructions, newStacks);
        for (int j = 0; j < 9; j++) {
            main.printStack(finalStacks.get(j));
        }

        // For Solution 2.
        ArrayList<Stack<Character>> veryFinalStacks = main.operatePart2(instructions, newStacks);
        for (int j = 0; j < 9; j++) {
            main.printStack(veryFinalStacks.get(j));
        }
    }

    /**
     * This method solves for part one. It involves reading instructions from the
     * text file and moving one crate at a time between Stacks.
     * @param input the instructions text file to be read from.
     * @param stacks an ArrayList of Stacks of Characters to be operated on.
     * @return  an ArrayList of Stacks of Characters after the operations have been
     *          completed.
     */
    public ArrayList<Stack<Character>> operateInstructions(File input, ArrayList<Stack<Character>> stacks) {
        try {
            Scanner instructions = new Scanner(input);
            while (instructions.hasNext()) {
                String line = instructions.nextLine();
                int space1 = line.indexOf(" ");
                int space2 = line.indexOf(" ", space1 + 1);
                int space3 = line.indexOf(" ", space2 + 1);;
                int space4 = line.indexOf(" ", space3 + 1);;
                int space5 = line.lastIndexOf(" ");

                int quantity = Integer.parseInt(line.substring(space1 + 1, space2));
                int from = Integer.parseInt(line.substring(space3 + 1, space4)) - 1;
                int to = Integer.parseInt(line.substring(space5 + 1, line.length())) - 1;

                for (int z = 0; z < quantity; z++) {
                    stacks.get(to).push(stacks.get(from).pop());
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stacks;
    }

    /**
     * This method solves for part two. It involves reading instructions from the
     * instructions text file and moving multiple crates at once between the stacks.
     * @param input the instructions text file to be read.
     * @param stacks an ArrayList containing the Stacks of Characters to be operated on.
     * @return  a new ArrayList of Stacks of Characters, after all the operations
     *          have been completed.
     */
    public ArrayList<Stack<Character>> operatePart2(File input, ArrayList<Stack<Character>> stacks) {
        try {
            Scanner instructions = new Scanner(input);
            while (instructions.hasNext()) {
                String line = instructions.nextLine();
                int space1 = line.indexOf(" ");
                int space2 = line.indexOf(" ", space1 + 1);
                int space3 = line.indexOf(" ", space2 + 1);;
                int space4 = line.indexOf(" ", space3 + 1);;
                int space5 = line.lastIndexOf(" ");

                Stack<Character> temp = new Stack<>();
                int quantity = Integer.parseInt(line.substring(space1 + 1, space2));
                int from = Integer.parseInt(line.substring(space3 + 1, space4)) - 1;
                int to = Integer.parseInt(line.substring(space5 + 1, line.length())) - 1;

                // Push crates into temp stack
                for (int z = 0; z < quantity; z++) {
                    temp.push(stacks.get(from).pop());
                }

                // Push crates back into place from temp stack, to maintain order
                int tempSize = temp.size();
                for (int q = 0; q < tempSize; q++) {
                    stacks.get(to).push(temp.pop());
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stacks;
    }

    /**
     * This method reads positions from the text file and uses them to populate
     * several Stacks with initial position data for the crates.
     * @param input the text file containing the initial crate positions.
     */
    public void populateMatrix(File input) {

        // Initialise starting positions
        try {
            Scanner matrix = new Scanner(input);
            while (matrix.hasNext()) {
                String line = matrix.nextLine();

                for (int i = 0; i < line.length(); i += 4) {
                    if (line.charAt(i) == '[') {
                        // Push this item to the appropriate stack
                        switch (i) {
                            case 0:
                                column1.push(line.charAt(i + 1));
                                break;
                            case 4:
                                column2.push(line.charAt(i + 1));
                                break;
                            case 8:
                                column3.push(line.charAt(i + 1));
                                break;
                            case 12:
                                column4.push(line.charAt(i + 1));
                                break;
                            case 16:
                                column5.push(line.charAt(i + 1));
                                break;
                            case 20:
                                column6.push(line.charAt(i + 1));
                                break;
                            case 24:
                                column7.push(line.charAt(i + 1));
                                break;
                            case 28:
                                column8.push(line.charAt(i + 1));
                                break;
                            case 32:
                                column9.push(line.charAt(i + 1));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method simply prints all Characters in a Stack to the terminal.
     * @param stack the Stack of Characters to be printed.
     */
    public void printStack(Stack<Character> stack) {
        for (Character c : stack.stream().toList()) {
            System.out.print(c);
        }
        System.out.println();
    }

    /**
     * This method takes a Stack and reverses the position of the elements
     * within the Stack.
     * @param stack the Stack of Characters to be reversed.
     * @return a new Stack of Characters, with the positions reversed.
     */
    public Stack<Character> reverseStack(Stack<Character> stack) {
        Stack<Character> temp = new Stack<>();
        int originalSize = stack.size();
        for (int i = 0; i < originalSize; i++) {
            temp.push(stack.pop());
        }
        return temp;
    }

    // Mutator methods.
    public void setColumn1(Stack<Character> column1) {
        this.column1 = column1;
    }

    public void setColumn2(Stack<Character> column2) {
        this.column2 = column2;
    }

    public void setColumn3(Stack<Character> column3) {
        this.column3 = column3;
    }

    public void setColumn4(Stack<Character> column4) {
        this.column4 = column4;
    }

    public void setColumn5(Stack<Character> column5) {
        this.column5 = column5;
    }

    public void setColumn6(Stack<Character> column6) {
        this.column6 = column6;
    }

    public void setColumn7(Stack<Character> column7) {
        this.column7 = column7;
    }

    public void setColumn8(Stack<Character> column8) {
        this.column8 = column8;
    }

    public void setColumn9(Stack<Character> column9) {
        this.column9 = column9;
    }
}
