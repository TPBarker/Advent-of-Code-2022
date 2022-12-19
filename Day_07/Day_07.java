import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private ArrayList<Integer> smallestValues;

    /**
     * This class represents a Folder in our directory tree.
     */
    public class Folder {
        private Folder parent;
        private String name;
        private int totalSize;
        private HashMap<String, Item> itemContents;
        private HashMap<String, Folder> folderContents;

        // Default constructor
        public Folder(){}

        // Non-default constructor
        public Folder(String name, Folder parent) {
            this.name = name;
            this.parent = parent;
            this.totalSize = -1;
            this.itemContents = new HashMap<>();
            this.folderContents = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getTotalSize() {
            return totalSize;
        }
    }

    /**
     * This class represents a file in our directory tree.
     */
    public class Item {
        private String name;
        private int size;

        // Default constructor.
        public Item(){}

        // Non-default constructor.
        public Item(int size, String name) {
            this.size = size;
            this.name = name;
        }

        // Get item size
        public int getSize() {
            return size;
        }

        // Get item name
        public String getName() {
            return name;
        }
    }

    public Main() {
        smallestValues = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Setup.
        Main main = new Main();

        // Read the input file and populate the directory tree.
        Folder root = main.populateDirectoryTree();

        // Traverse the directory tree and calculate all folder sizes.
        main.calcFolderSizes(root);

        // Debugging info.
        //main.printFolderSizes(root);

        // Part One.
        System.out.println("Part One Solution: " + main.partOne(root));

        // Part Two.
        main.partTwo(root, root.getTotalSize());
        Collections.sort(main.smallestValues);
        System.out.println("Part Two Solution: " + main.smallestValues.get(0));
    }

    /**
     * This method traverses the directory tree and calculates the size of each
     * folder, and sets the totalSize parameter for the Folder. It then explores
     * each child Folder recursively.
     * @param root the Folder to be explored.
     */
    public void calcFolderSizes(Folder root) {
        int folderSize = 0;
        if (!root.itemContents.isEmpty()) {
            for (Item i : root.itemContents.values()) {
                folderSize += i.getSize();
            }
        }

        if (!root.folderContents.isEmpty()) {
            // Other folders in this folder
            for (Folder f : root.folderContents.values()) {
                this.calcFolderSizes(f);
                folderSize += f.getTotalSize();
            }
        }

        root.setTotalSize(folderSize);
    }

    /**
     * This method provides the solution for Part One. We are asked to traverse
     * the directory tree and check each Folder to see if its size is at most 100,000.
     * If so, we add its size to the sum and then report the sum when finished. This
     * method uses recursion to check all folders in the tree.
     * @param root the Folder to be checked.
     * @return the size of the Folder.
     */
    public int partOne(Folder root) {
        int size = 0;

        // If the Folder's size meets the requirement, add it to the sum.
        if (root.getTotalSize() <= 100000) {
            size += root.getTotalSize();
        }

        // Check all child Folders.
        for (Folder f : root.folderContents.values()) {
            size += partOne(f);
        }
        return size;
    }

    /**
     * This method checks the directory structure to find the smallest possible
     * Folder we could delete to free up enough space to fit a system update
     * into our system. It uses the Main.SmallestValues ArrayList to store all
     * the potential candidates for deletion.
     * @param root the Folder to be checked.
     * @param rootSize the size of the top-most Folder of the tree.
     */
    public void partTwo(Folder root, int rootSize) {
        int diskSpace = 70000000;
        int updateSize = 30000000;
        int consumedSpace = rootSize;
        int needToDelete = updateSize - (diskSpace - consumedSpace);

        int smallestFolder = diskSpace;

        /* If this Folder's size is greater than the amount of space we need to
         *  free up, and it's the smallest Folder that meets the requirements so far,
         * tag it as the Folder we are going to delete.
         */
        for (Folder f : root.folderContents.values()) {
            partTwo(f, rootSize);
        }

        if (root.getTotalSize() >= needToDelete) {
            smallestValues.add(root.getTotalSize());
        }
    }


    /**
     * This method opens the input.txt file and constructs the directory tree
     * based on the parsed instructions.
     * @return the root Folder of the new directory tree.
     */
    public Folder populateDirectoryTree() {
        try {
            // Setup.
            File file = new File("src/input.txt");
            Scanner input = new Scanner(file);
            Folder rootDirectory = new Folder("/", null);
            Folder workingDirectory = rootDirectory;

            // While there is data to read in.
            while (input.hasNext()) {
                String line = input.nextLine();
                // If line starts with "$ cd" then change the working directory
                if (line.startsWith("$ cd")) {
                    // Change directory command
                    if (line.endsWith("..")) {
                        // Change into parent
                        workingDirectory = workingDirectory.parent;
                    } else if (line.endsWith(" /")) {
                        // Change into the root directory
                        workingDirectory = rootDirectory;
                    } else {
                        // Change into specified directory
                        workingDirectory = workingDirectory.folderContents.get(line.substring(5));
                    }
                } else if (line.startsWith("$ ls")) {
                    // This command can be ignored
                } else if (line.startsWith("dir")) {
                    // This is a folder sitting within the working directory,
                    // Add it as a child with the current directory as the parent.
                    if (!workingDirectory.folderContents.containsKey(line.substring(4))) {
                        workingDirectory.folderContents.put(line.substring(4),
                                new Folder(line.substring(4), workingDirectory));
                    }
                } else {
                    // This is an item sitting within the working directory.
                    String[] itemDetails = line.split(" ");
                    Item item = new Item(Integer.parseInt(itemDetails[0]), itemDetails[1]);
                    if (!workingDirectory.itemContents.containsKey(item.name)) {
                        workingDirectory.itemContents.put(item.name, item);
                    }
                }
            }
            return rootDirectory;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Folder();
    }

    /**
     * This method uses recursion to print the size of the Folder which is
     * passed in, and then prints the size of all child Folders.
     * @param root the folder to be recursively checked and printed.
     */
    public void printFolderSizes(Folder root) {
        System.out.println("Size: " + root.getTotalSize());
        for (Folder f : root.folderContents.values()) {
            printFolderSizes(f);
        }
    }
}
