import java.util.*;
import java.io.*;

public class QuickMafs {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        while(kb.hasNext()) {
            int size = kb.nextInt();
            if(size == 0)
                break;
            PriorityQueue<Integer> nums = new PriorityQueue<>();
            for (int i = 0; i < size; i++)
                nums.add(kb.nextInt());
            int sum = 0;
            for (int i = 0; i < size-1; i++) {
                int cost = nums.poll() + nums.poll();
                sum += cost;
                nums.add(cost);
            }
            System.out.println(sum);
        }
    }
}
