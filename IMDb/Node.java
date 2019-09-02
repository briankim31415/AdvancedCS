/**
 * Created by 198495 on 9/29/2017.
 */
public class Node {
    Object data;
    Node next;

    public Node() {
        data = null;
        next = null;
    }

    public Node(Object s) {
        this.data = s;
        next = null;
    }

    public void set(Object s) { this.data = s; }

    public Object get() { return data; }

    public void setNextPtr(Node a) {
        this.next = a;
    }

    public Node getNextPtr() {
        return this.next;
    }

    public String toString() {
        return data + " ";
    }
}
