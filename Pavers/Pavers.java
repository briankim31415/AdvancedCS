import java.util.*;
import java.io.*;

public class Pavers {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        int cases = kb.nextInt();
        for (int i = 0; i < cases; i++) {
            HashMap<String, Integer> roads = new HashMap<>();
            int price = kb.nextInt();
            int buildings = kb.nextInt();
            int streets = kb.nextInt();
            Graph graph = new Graph(buildings);

            for (int j = 0; j < streets; j++) {
                int a = kb.nextInt();
                int b = kb.nextInt();
                int cost = kb.nextInt();
                graph.addEdge(a,b);
                roads.put(a + " " + b, cost);
            }
            graph.getRoads(roads);

            System.out.println(price * graph.totalCost());
        }
    }
}

class Graph {
    private LinkedList<Vertex> adjacent[];
    private int size;
    private HashMap<String, Integer> roads = new HashMap<>();
    private boolean visited[] = new boolean[size+1];

    public Graph(int size) {
        this.size = size;
        adjacent = new LinkedList[size+1];
        for (int i = 0; i < size+1; i++) {
            adjacent[i] = new LinkedList<>();
        }
    }

    public void getRoads(HashMap<String, Integer> inRoads) {
        this.roads = inRoads;
    }

    public void addEdge(int a, int b) {
        adjacent[a].add(new Vertex(b));
        adjacent[b].add(new Vertex(a));
    }

    public int totalCost() {
        int sum = 0;
        int index = 1;
        Vertex current = adjacent[index].get(0);
        current.cost = 0;
        while(!allVisited()) {
            update(index, current);
        }

        return sum;
    }

    public boolean allVisited() {
        for (boolean visited : visited) {
            if(!visited) {
                return false;
            }
        }
        return true;
    }

    public void update(int building, Vertex inParent) {
        int index = 1;
        for (Vertex vertex : adjacent[building]) {
            if(vertex.cost > roads.get(building + " " + vertex.id)) {
                vertex.cost = roads.get(building + " " + vertex.id);
                vertex.parent = inParent;
            }
        }
    }
}

class Vertex {
    public Vertex parent;
    public int cost, id;

    public Vertex(int id) {
        this.id = id;
        this.cost = 1001;
        this.parent = null;
    }
}
