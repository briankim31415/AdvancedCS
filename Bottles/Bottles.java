import java.util.*;
import java.io.*;

public class Bottles {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("bottles.dat"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            int num = kb.nextInt();
            int[] bottles = new int[num];
            for (int j = 0; j < num; j++) {
                bottles[j] = kb.nextInt();
            }

            ValueDP valueDP = new ValueDP(bottles);
            System.out.println(valueDP.getVolume());
            /*ValueR valueR = new ValueR(bottles);
            System.out.println(valueR.getVolume());*/
        }
    }
}

class ValueDP {
    private int[] nums, MCV;
    int length;

    public ValueDP(int[] numsIn) {
        nums = numsIn;
        length = nums.length;
        MCV = new int[length];
        MCV[0] = nums[0];
        MCV[1] = Math.max(nums[0], nums[1]);
        nextVal(2);
    }

    public void nextVal(int index) {
        MCV[index] = Math.max(MCV[index-2] + nums[index], MCV[index-1]);
        if(index < length-1) {
            nextVal(index+1);
        }
    }

    public int getVolume() {
        return MCV[length-1];
    }
}

class ValueR {
    private int[] nums, MCV;

    public ValueR(int[] numsIn) {
        nums = numsIn;
        MCV = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            MCV[i] = -1;
        }
    }

    public int getMax(int bottle) {
        if (MCV[bottle] == -1) {
            if(bottle == 0) {
                MCV[bottle] = nums[0];
            }
            else if(bottle == 1) {
                MCV[bottle] = Math.max(nums[0], nums[1]);
            }
            else MCV[bottle] = Math.max(getMax(bottle-1), getMax(bottle-2) + nums[bottle]);
        }

        return MCV[bottle];
    }

    public int getVolume() {
        return getMax(nums.length-1);
    }
}