import java.util.*;
import java.io.*;

public class GoPlantATree {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        int testCases = kb.nextInt();
        kb.nextLine();
        kb.nextLine();
        for(int i = 0; i < testCases; i++) {
            int totalTrees = 0;
            HashMap<String,Integer> trees = new HashMap<>();
            ArrayList<String> allSpecies = new ArrayList<>();
            while(kb.hasNext()) {
                String newTree = kb.nextLine();
                if(newTree.isEmpty()) {
                    break;
                }
                if(!trees.containsKey(newTree)) {
                    trees.put(newTree,1);
                    allSpecies.add(newTree);
                } else {
                    trees.replace(newTree, trees.get(newTree)+1);
                }
                totalTrees++;
            }
            Collections.sort(allSpecies);
            for(String speciesName: allSpecies) {
                System.out.printf("%s %.4f%n", speciesName, 100*((double)trees.get(speciesName)/totalTrees));
            }
            System.out.println();
        }
    }
}