import java.util.*;
import java.io.*;

public class PrettyPrint{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("PrettyPrint.txt"));
        while(kb.hasNext()) {
            double[] doubleValues = Arrays.stream(kb.nextLine().split(", ")).mapToDouble(Double::parseDouble).toArray();
            BinarySearchTree bst = new BinarySearchTree();
            for(double num: doubleValues)
                bst.insertValue(num);

            bst.traverse();
            System.out.printf("%s%nIn order: %s%n", bst.prettyString, bst.inOrderString);
        }
    }
}
