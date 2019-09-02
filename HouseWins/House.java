import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.io.*;

public class House {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("house.dat"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            int rows = kb.nextInt();
            int[][] pins = new int[rows][rows];

            for (int j = 0; j < rows; j++)
                for (int k = 0; k <= j ; k++)
                    pins[j][k] = kb.nextInt();

            /*ValueMemo valueMemo = new ValueMemo(pins);
            System.out.println(valueMemo.getVolume());*/

            ValueBU valueBU = new ValueBU(pins);
            System.out.println(valueBU.getVolume());
        }
    }
}

class ValueMemo {
    private int[][] nums, MCV;
    private int length;
    private Integer[] array;

    public ValueMemo(int[][] numsIn) {
        nums = numsIn;
        length = nums.length;
        MCV = new int[length][length];
        MCV[0][0] = nums[0][0];
        MCV[1][0] = nums[1][0] + nums[0][0];
        MCV[1][1] = nums[1][1] + nums[0][0];
        System.out.print(MCV[0][0] + "\n" + MCV[1][0] + " " + MCV[1][1] + "\n");
        nextVal(2);
        array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = MCV[length-1][i];
        }
    }

    public void nextVal(int row) {
        if(row < length) {              //check if it's not <=
            for (int i = 0; i <= row; i++) {
                if(i == 0) {
                    MCV[row][i] = MCV[row-1][0] + nums[row][0];
                } else if(i == row) {
                    MCV[i][i] = MCV[i-1][i-1] + nums[i][i];
                } else {
                    MCV[row][i] = Math.max(MCV[row-1][i-1], MCV[row-1][i]) + nums[row][i];
                }
                System.out.print(MCV[row][i] + " ");
            }
            System.out.println();
            nextVal(row+1);
        }
    }

    public int getVolume() {
        return Collections.max(Arrays.asList(array));
    }
}

class ValueBU {
    private int[][] nums, MCV;
    private int length;
    private Integer[] array;

    public ValueBU(int[][] inNums) {
        nums = inNums;
        length = nums.length;
        MCV = new int[length][length];
        for (int j = 0; j < length; j++)
            for (int k = 0; k < length; k++)
                MCV[j][k] = -1;
    }

    public int getMax(int row, int column) {
        if (MCV[row][column] == -1) {
            if (row == 0 && column == 0) {
                MCV[0][0] = nums[0][0];
            } else if (row == 1 && column == 0) {
                MCV[1][0] = nums[0][0] + nums[1][0];
            } else if (row == 1 && column == 1) {
                MCV[1][1] = nums[0][0] + nums[1][1];
            } else if (column == 0) {
                MCV[row][0] = getMax(row - 1, column) + nums[row][0];
            } else if (row == column) {
                MCV[row][column] = getMax(row - 1, column - 1) + nums[row][column];
            } else {
                MCV[row][column] = Math.max(getMax(row - 1, column - 1), getMax(row - 1, column)) + nums[row][column];
            }
        }
        return MCV[row][column];
    }

    public int getVolume() {
        for (int i = 0; i < length; i++) {
            getMax(length-1, i);
        }
        array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = MCV[length-1][i];
        }
        return Collections.max(Arrays.asList(array));
    }
}
