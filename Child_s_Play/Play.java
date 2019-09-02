import java.io.*;
import java.util.*;

public class Play {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner kb = new Scanner(new File("playJudgeCase1.dat"));
        int cases = kb.nextInt(); //kb.nextLine(); needed?
        for (int i = 0; i < cases; i++) {
            int n = kb.nextInt();
            int m = kb.nextInt();
            int l = kb.nextInt();
            int[] connections = new int[m*2];

            for (int j = 0; j < m*2; j++) {
                connections[j] = kb.nextInt();
            }
            int [] knockedOver = new int[l];
            for (int j = 0; j < l; j++) {
                knockedOver[j] = kb.nextInt();
            }
            Graph graph = new Graph(n,knockedOver);
            for (int k = 0; k < m; k++) {
                graph.addEdge(connections[2*k], connections[2*k+1]);
            }
            System.out.println(graph.Falling());
        }
    }
}

class Graph {
    private LinkedList<Integer> adjacent[];
    private int size;
    private int[] knock;

    public Graph(int n, int[] z) {
        this.size = n;
        knock = z;
        adjacent = new LinkedList[size+1];
        for (int i = 1; i < size+1; i++) {
            adjacent[i] = new LinkedList<>();
        }
    }

    public void addEdge(int a, int b) {
        adjacent[a].add(b);
    }

    public int Falling() {
        boolean[] knockedover = new boolean[size+1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int z : knock) {
            knockedover[z] = true;
            queue.add(z);

            while (queue.size() != 0) {
                int current = queue.poll();

                Iterator<Integer> i = adjacent[current].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!knockedover[n]) {
                        knockedover[n] = true;
                        queue.add(n);
                    }
                }
            }
        }

        int out = 0;
        for (boolean isTrue : knockedover) {
            if(isTrue) {
                out++;
            }
        }
        return out;
    }
}