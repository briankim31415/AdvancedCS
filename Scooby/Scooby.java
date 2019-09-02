import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by 198495 on 3/20/2018.
 */
public class Scooby {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("input.txt"));
        int iter = kb.nextInt(); kb.nextLine();
        for (int i = 0; i < iter; i++) {
            HashMap<Character, Integer> rooms = new HashMap<>();
            String line = kb.nextLine();
            String[] connections = line.split(" ");
            char[] letters = line.toCharArray();
            int index = 0;

            for(char c : letters) {
                if(!rooms.containsKey(c)) {
                    rooms.put(c, index);
                    index++;
                }
            }

            Graph graph = new Graph(rooms);
            for(String s : connections) {
                graph.addEdge(rooms.get(s.charAt(0)), rooms.get(s.charAt(1)));
            }
            String check = kb.nextLine();
            System.out.println(rooms.containsKey(check.charAt(0)) && rooms.containsKey(check.charAt(1)) && graph.isLink(rooms.get(check.charAt(0)), rooms.get(check.charAt(1))) ? "yes" : "no");
        }
    }
}

class Graph {
    private HashMap<Character, Integer> rooms;
    private LinkedList<Integer> adjacent[];

    public Graph(HashMap<Character, Integer> inMap) {
        this.rooms = inMap;
        adjacent = new LinkedList[inMap.size()];
        for (int i = 0; i < rooms.size(); i++) {
            adjacent[i] = new LinkedList<>();
        }
    }

    public void addEdge(int a, int b) {
        adjacent[a].add(b);
        adjacent[b].add(a);
    }

    public boolean isLink(int source, int destination) {
        boolean visited[] = new boolean[rooms.size()];
        LinkedList<Integer> queue = new LinkedList();
        visited[source] = true;
        queue.add(source);

        while(queue.size() != 0) {
            int current = queue.poll();
            if(current == destination) {
                return true;
            }

            Iterator<Integer> i = adjacent[current].listIterator();
            while(i.hasNext()) {
                int n = i.next();
                if(!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return false;
    }
}
