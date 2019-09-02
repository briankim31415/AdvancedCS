/**
 * Created by 198495 on 9/27/2017.
 */
public class LinkedList {
    Node head;
    int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    public void add(Object data) {
        Node n = new Node(data);
        n.setNextPtr(head);
        head = n;
        count++;
    }

    public Node get(int index) {
        if(index > count) {
            return null;
        } else {
            Node atIndex = head;
            for(int i = 0; i < index; i ++) {
                atIndex = atIndex.getNextPtr();
            }
            return atIndex;
        }
    }

    public int size() {
        return count;
    }

    public String toString() {
        String out = "";
        Node current = head;
        while(current != null) {
            out += current;
            current = current.getNextPtr();
        }
        return out;
    }
}