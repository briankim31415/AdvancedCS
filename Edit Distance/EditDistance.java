import java.util.*;
import java.io.*;

public class EditDistance {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        String source = kb.nextLine();
        String target = kb.nextLine();
        int n = source.length();
        int m = target.length();
        FindDistance findDistance = new FindDistance();
        System.out.println(findDistance.distance(source, target, n, m));
        findDistance.trace(source, target);
    }

}

class FindDistance {
    public int[][] costs;

    public int distance(String s, String t, int n, int m) {
        if(n == 0)
            return m;
        if(m == 0)
            return n;
        costs = new int[n+1][m+1];
        int cost;
        for (int i = 0; i <= n; i++) {
            costs[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            costs[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(s.charAt(i-1) != t.charAt(j-1)) {
                    cost = 1;
                } else {
                    cost = 0;
                }
                costs[i][j] = Math.min(Math.min(costs[i-1][j]+1, costs[i][j-1]+1), costs[i-1][j-1]+cost);
            }
        }
        return costs[n][m];
    }

    public void trace(String first, String second){
        ArrayDeque<String> output = new ArrayDeque<>();
        output.push(first);
        int x = costs[0].length-1;
        int y = costs.length-1;
        while (costs[y][x] != 0) {
            String peek = output.peek();
            if (x > 0 && y > 0) {
                if (costs[y-1][x] == costs[y][x] - 1){
                    output.push(peek.substring(0, y-1) + peek.substring(y, peek.length()));
                    y--;
                } else if (costs[y-1][x-1] == costs[y][x] - 1){
                    output.push(peek.substring(0, y-1) + Character.toString(second.charAt(x-1)) + peek.substring(y, peek.length()));
                    x--;
                    y--;
                } else if (costs[y][x-1] == costs[y][x] - 1){
                    output.push(peek.substring(0, y-1) + Character.toString(second.charAt(x-1)) + peek.substring(y-1, peek.length()));
                    x--;
                } else {
                    x--;
                    y--;
                }
            } else if (y == 0 && x > 0){
                output.push(peek.substring(0, 0) + Character.toString(second.charAt(x-1)) + peek.substring(0, peek.length()));
                x--;
            } else if (x == 0 && y > 0){
                output.push(peek.substring(0, y-1) + peek.substring(y, peek.length()));
                y--;
            }
        }

        Iterator it = output.descendingIterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
