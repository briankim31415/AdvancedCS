import java.util.*;
import java.io.*;

public class LabRats {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("rats.dat"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            int total = kb.nextInt();
            int exit = kb.nextInt();
            int time = kb.nextInt();
            int connections = kb.nextInt();
            Graph graph = new Graph(total, exit, time);
            for (int j = 0; j < connections; j++) {
                int a = kb.nextInt();
                int b = kb.nextInt();
                int weight = kb.nextInt();
                graph.addEdge(a, b, weight);
            }

            System.out.println(graph.getMice() + "\n");
        }
    }
}

class Graph {
    int total, exit, time;
    HashMap<Integer,Vertex> vertices = new HashMap<>();

    public Graph(int total, int exit, int time) {
        this.total = total;
        this.exit = exit;
        this.time = time;
        for (int i = 1; i <= total; i++) {
            vertices.put(i, new Vertex(i));
        }
    }

    public void addEdge(int a, int b, int weight) {
        Vertex start = vertices.get(a);
        Vertex end = vertices.get(b);
        start.neighbors.put(end, weight);
    }

    public int getMice() {
        int mice = 0;
        for (int i = 1; i <= total; i++) {
            int weight = Dijkstra(i, exit);
            if(weight <= time && weight != -1)
                mice++;
        }
        return mice;
    }

    public int Dijkstra(int start, int end) {
        Vertex source = vertices.get(start);
        Vertex exit = vertices.get(end);

        for(Map.Entry<Integer, Vertex> vertexEntry : vertices.entrySet()) {
            vertexEntry.getValue().cost = Integer.MAX_VALUE;
        }
        source.cost = 0;

        source.cost = 0;

        boolean[] visited = new boolean[total];
        PriorityQueue<Vertex> check = new PriorityQueue<>();
        check.add(source);
        while(!check.isEmpty()) {
            Vertex current = check.poll();
            visited[current.cellNum-1] = true;
            for(Vertex vertex : current.neighbors.keySet()) {
                if(!visited[vertex.cellNum-1] && vertex.cost > current.cost + current.neighbors.get(vertex)) {
                    vertex.cost = current.cost + current.neighbors.get(vertex);
                    check.add(vertex);
                }
            }
        }
        if(exit.cost == Integer.MAX_VALUE) {
            return -1;
        }
        return exit.cost;
    }

}

class Vertex implements Comparable<Vertex> {
    HashMap<Vertex, Integer> neighbors = new HashMap<>();
    int cellNum, cost;
    public Vertex(int id) {
        this.cellNum = id;
        cost = Integer.MAX_VALUE;
    }

    public int compareTo(Vertex vertex) {
        return this.cost - vertex.cost;
    }
}