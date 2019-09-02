import java.util.*;
import java.io.*;

public class Change {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("change.dat"));
        int num = kb.nextInt();
        for (int i = 0; i < num; i++) {
            int n = kb.nextInt();
            int[] coins = new int[n];
            for (int j = 0; j < n; j++) {
                coins[j] = kb.nextInt();
            }
            int change = kb.nextInt();

            ChangeBU changeBU = new ChangeBU(coins, change);
            changeBU.findMin();

            /*ChangeMemo changeMemo = new ChangeMemo(coins, change);
            changeMemo.runMemo();*/
        }
    }
}

class ChangeBU {
    int change;
    int[] coins;
    int[][] cVals;
    public ChangeBU(int[] inArray, int inChange) {
        coins = inArray;
        change = inChange;
        cVals = new int[coins.length][inChange+1];
    }

    public void findMin() {
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < change+1; j++) {
                if(i==0 && j - coins[i] < 0) {
                    cVals[i][j] = 0;
                } else if(i==0) {
                    cVals[i][j] = cVals[i][j - coins[i]]+1;
                } else if (j - coins[i] < 0) {
                    cVals[i][j] = cVals[i-1][j];
                } else {
                    cVals[i][j] = Math.min(cVals[i][j - coins[i]] + 1, cVals[i - 1][j]);
                }
            }
        }
        System.out.print(cVals[coins.length-1][change]);
        findChange();
    }

    public void findChange() {
        int[] returnVals = new int[coins.length];
        int index = change;
        int coin = coins.length-1;
        while(true) {
            if(coin > 0) {
                if(cVals[coin-1][index] > cVals[coin][index]) {
                    returnVals[coin]++;
                    index = index-coins[coin];
                } else {
                    coin = coin-1;
                }
            } else {
                returnVals[coin] += index;
                break;
            }
        }

        for (int num : returnVals) {
            System.out.print(" " + num);
        }
        System.out.println();
    }
}

class ChangeMemo {
    int change;
    int[] coins, coinNums;
    int[][] cVals;
    public ChangeMemo(int[] inArray, int inChange) {
        coins = inArray;
        change = inChange;
        coinNums = new int[coins.length];
        cVals = new int[coins.length][inChange+1];
    }

    public void runMemo() {
        for (int i = 0; i < cVals.length; i++) {
            for (int j = 0; j < cVals[0].length; j++) {
                cVals[i][j] = -1;
            }
        }
        System.out.println(recurse(cVals.length-1, cVals[0].length-1) + " ");
        trace(cVals.length-1, cVals[0].length-1);
        for (int i = 0; i < coins.length; i++) {
            System.out.print(coinNums[i] + " ");
        }
        System.out.println();
    }

    public int recurse(int a, int b)
    {
        if(cVals[a][b] == -1) {
            if(a == 0) {
                if(b % coins[0] != 0)
                    cVals[a][b] = Integer.MAX_VALUE;
                else
                    cVals[a][b] = b / coins[0];
            } else {
                if(b < coins[a])
                    cVals[a][b] = recurse(a-1, b);
                else
                    cVals[a][b] = Math.min(recurse(a, b-coins[a]) + 1, recurse(a-1, b));
            }
        }
        return cVals[a][b];
    }

    public void trace(int c, int d)
    {
        if(c == 0) {
            if(cVals[c][d] != 0) {
                coinNums[c] += cVals[c][d];
            }
        }
        else if(cVals[c-1][d] == cVals[c][d]) {
            trace(c-1, d);
        } else {
            coinNums[c]++;
            trace(c, d - coins[c]);
        }
    }
}

