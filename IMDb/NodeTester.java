/**
 * Created by 198495 on 9/25/2017.
 */
public class NodeTester {
    public static void main(String[] args) {

        String a1 = "first";
        String a2 = "second";
        String a3 = "third";
        String a4 = "fourth";
        String a5 = "fifth";
        Actor[] actors = {new Actor(a1), new Actor(a2), new Actor(a3), new Actor(a4), new Actor(a5)};

        Node head = new Node(actors[0]);
        Node current = head;
        for(int i = 1; i < actors.length; i++) {
            head = new Node(actors[i]);
            head.setNextPtr(current);
            current = head;
        }

        while(current != null) {
            System.out.println(current.get());
            current = current.getNextPtr();
        }
    }
}
