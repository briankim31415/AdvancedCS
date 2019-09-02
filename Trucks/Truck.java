import java.util.*;
import java.io.*;

public class Truck {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("truck.dat"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            int capacity = kb.nextInt();
            int itemNum = kb.nextInt();
            int[][] items = new int[itemNum+1][2];

            for (int j = 1; j < items.length; j++)
                for (int k = 0; k < 2; k++)
                    items[j][k] = kb.nextInt();

            /*KnapsackBU truck = new KnapsackBU(items, capacity);
            truck.findMax();*/
            KnapsackR truckR = new KnapsackR(items, capacity);
            truckR.run();
        }
    }
}


class KnapsackBU {
    private int[][] items, vals;
    public KnapsackBU(int[][] inItems, int inCapacity) {
        items = inItems;
        vals = new int[items.length][inCapacity+1];
    }

    public void findMax() {
        for (int i = 1; i < items.length; i++) {
            vals[i][items[i][0]] = items[i][1];
            for (int j = 1; j < vals[i].length; j++) {
                if(j < items[i][0]) {
                    vals[i][j] = vals[i-1][j];
                } else {
                    vals[i][j] = Math.max(items[i][1] + vals[i][j - items[i][0]], vals[i - 1][j]);
                }
            }
        }
        System.out.println(vals[vals.length-1][vals[0].length - 1]);
    }
}

class KnapsackR {
    private int[][] items, vals;
    int cap;
    public KnapsackR(int[][] inItems, int inCapacity) {
        items = inItems;
        cap = inCapacity;
        vals = new int[items.length][cap+1];
    }

    public void run() {
        initArray();
        System.out.println(findMax(items.length - 1, cap));
    }

    public void initArray() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < vals[i].length; j++) {
                vals[i][j] = -1;
            }
        }
        for (int i = 1; i < items.length; i++) {
            for (int j = 1; j < items[i][0]; j++) {
                vals[i][j] = vals[i-1][j];
            }
            vals[i][items[i][0]] = Math.max(items[i][1], vals[i-1][items[i][0]]);
        }

    }

    public int findMax(int i, int b) {
        if(vals[i][b] == -1) {
            if(b - items[i][0] < 0 || i == 0) {
                return 0;
            } else {
                vals[i][b] = Math.max(items[i][1] + findMax(i, b - items[i][0]), findMax(i - 1, b));
            }
        }

        printArr();
        return vals[i][b];
    }

    public void printArr() {
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[i].length; j++) {
                if(vals[i][j] !=-1 && vals[i][j] < 10) {
                    System.out.print(" " + vals[i][j] + " ");
                } else {
                    System.out.print(vals[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }
}