import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Rake {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner kb = new Scanner(new File("rake.dat"));
        int root = kb.nextInt();
        int count = 1;
        while(root != -1) {
            ArrayList<Integer> piles = new ArrayList<>();
            HashMap<Integer, Integer> pileValue = new HashMap();

            Node tree = new Node(root, kb, 0, piles, pileValue);
            Collections.sort(piles);
            System.out.println("Case " + count + ":");
            for (int i = 0; i < piles.size(); i++) {
                int value = tree.traverse(piles.get(i), 0);
                System.out.print(((i == piles.size() - 1) ? value + "\n" : value + " "));
            }

            root = kb.nextInt();
            count++;
            System.out.println();
        }
    }
}

class Node {
    Node leftChild, rightChild;
    int position, value;

    public Node(int val, Scanner kb, int pos, ArrayList p, HashMap map) {
        value = val;
        int next = kb.nextInt();
        position = pos;

        if (!map.containsKey(pos)) {
            map.put(pos, 0);
            p.add(pos);
        }

        if (next != -1) {
            rightChild = new Node(next, kb, position - 1, p, map);
        }
        next = kb.nextInt();
        if (next != -1) {
            leftChild = new Node(next, kb, position + 1, p, map);
        }
    }
    public int traverse (int pileNumber, int returnValue) {
        if (position == pileNumber) {
            returnValue += value;
        }
        if (rightChild != null) {
            returnValue = rightChild.traverse(pileNumber, returnValue);
        }
        if (leftChild != null) {
            returnValue = leftChild.traverse(pileNumber, returnValue);
        }
        return returnValue;

    }

}