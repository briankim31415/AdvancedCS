import java.util.*;
import java.io.*;

public class doPave {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            int price = kb.nextInt();
            int buildings = kb.nextInt();
            int streets = kb.nextInt();
            int roads[][] = new int[buildings+1][buildings+1];

            for (int j = 0; j < streets; j++) {
                int a = kb.nextInt();
                int b = kb.nextInt();
                int cost = kb.nextInt();
                roads[a][b] = cost;
                roads[b][a] = cost;
            }
            MST mst = new MST(roads);
            System.out.println(price * mst.getSum());
        }
    }
}

class MST {
    private int[][] graph;
    private int sum = 0;

    public MST(int graph[][]) {
        this.graph = graph;
        int parent[] = new int[graph.length];
        int key[] = new int[graph.length];
        boolean mstSet[] = new boolean[graph.length];

        for (int i = 1; i < graph.length; i++) {
            key[i] = Integer.MAX_VALUE;
        }

        key[1] = 0;
        parent[1] = -1;

        for (int i = 1; i < graph.length-1; i++) {
            int u = minIndex(key, mstSet);
            mstSet[u] = true;

            for (int j = 1; j < graph.length-1; j++) {
                if(graph[u][j] != 0 && !mstSet[j] && graph[u][j] < key[j]) {
                    parent[j] = u;
                    key[j] = graph[u][j];
                }
            }
        }
        for (int i : key) {
            if(i != Integer.MAX_VALUE) {
                sum += i;
            }
        }
    }

    public int getSum() {
        return sum;
    }

    public int minIndex(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 1; i < graph.length; i++) {
            if(!mstSet[i] && key[i] < min) {
                min = key[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
