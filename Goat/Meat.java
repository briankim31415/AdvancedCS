import java.util.*;
import java.io.*;

public class Meat {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = kb.nextInt();
            Heap goatHeap = new Heap(n);

            for (int j = 0; j < n; j++) {
                int goatCycle = kb.nextInt();
                int[] cycle = new int[goatCycle];
                for (int k = 0; k < goatCycle; k++)
                    cycle[k] = kb.nextInt();
                goatHeap.addGoat(new Goat(cycle));
            }

            int totalDays = 0;
            int daysLeft = 0;

            while(daysLeft < goatHeap.cycle()) {
                if(goatHeap.goatKilled())
                    daysLeft = 0;
                else
                    daysLeft++;
                totalDays++;
                goatHeap.nextDay();
            }

            totalDays -= goatHeap.cycle();
            System.out.printf("%d %d%n", goatHeap.size, totalDays);
        }
    }
}
