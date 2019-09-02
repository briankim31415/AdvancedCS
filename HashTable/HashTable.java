import java.util.LinkedList;

public class HashTable {
    Node[] nodes;
    int size = 0;
    int collisions = 0;
    long time = 0;

    public HashTable() {
        nodes = new Node[101];
    }

    public HashTable(int initCap) {
        nodes = new Node[initCap];
    }

    public Object put(Object key, Object value) {
        long start = System.currentTimeMillis();
        int collisionCounter = 0;
        int index = key.hashCode() % nodes.length;
        Object returnValue = null;
        int removedIter = -1;
        boolean foundRemoved = false;
        int countIter = 0;
        double offset = 0;
        int base = index;

        while (nodes[index] != null) {
            if (nodes[index].key == key) {
                returnValue = nodes[index].value;
                nodes[index] = new Node(key, value);
                size++;
                return returnValue;
            }
            if (nodes[index].removed && !foundRemoved) {
                removedIter = index;
                foundRemoved = true;
                collisionCounter--;
            }
            if(countIter == nodes.length) {
                break;
            }
            //index = (index+1) % nodes.length;                               //Linear probing
            index = (base + (int) Math.pow(offset, 2)) % nodes.length;    //Quadratic probing
            collisionCounter++;
            countIter++;
        }
        if (foundRemoved) {
            nodes[removedIter] = new Node(key, value);
        }
        else {
            nodes[index] = new Node(key, value);
        }

        /*if (nodes[index] == null) {
            nodes[index] = new Node(key, value);
        } else {
            Node last = nodes[index];
            while(last.getNextPtr() != null) {
                last = last.getNextPtr();
                collisionCounter++;
            }
            last.setNextPtr(new Node(key, value));
        }*/

        long end = System.currentTimeMillis();
        size++;
        collisions = collisionCounter;
        time = end-start;
        return returnValue;
    }

    public int getCollisionCounter() {
        return collisions;
    }

    public long getTime() {
        return time;
    }

    public Object get(Object key) {
        int index = key.hashCode()%nodes.length;
        int offset = 0;
        int base = index;
        if(nodes[index] != null && nodes[index].key.equals(key)) {
            return nodes[index];
        } else if(nodes[index] != null && !nodes[index].key.equals(key)) {
            boolean collision = true;
            while(collision) {
                //index = (index+1) % nodes.length;                               //Linear probing
                index = (base + (int) Math.pow(offset, 2)) % nodes.length;    //Quadratic probing
                if(nodes[index] != null && !nodes[index].removed && nodes[index].key.equals(key)) {
                    return nodes[index].value;
                } else if(nodes[index] == null) {
                    collision = false;
                }
                offset++;
            }
        }
        return null;
    }

    public Object remove(Object key) {
        int index = key.hashCode()%nodes.length;
        if(nodes[index] != null && nodes[index].key.equals(key)) {
            nodes[index].removed = true;
            size--;
            return nodes[index].value;
        } else if(nodes[index] != null && !nodes[index].key.equals(key)) {
            boolean collision = true;
            while(collision) {
                if(index == nodes.length-1) {
                    index = 0;
                } else {
                    index++;
                }
                if(nodes[index] != null && !nodes[index].removed && nodes[index].key.equals(key)) {
                    nodes[index].removed = true;
                    size--;
                    return nodes[index].value;
                } else if(nodes[index] == null) {
                    collision = false;
                }
            }
        }
        return null;
    }

    public String toString() {
        String s = "";
        for(Node node : nodes) {
            if(node != null) {
                s = s + node.toString() + "\n";
            } else if(node != null && node.removed) {
                s = s + "<dummy>\n";
            } else if(node == null) {
                //s = s + "<null>";
            }
        }
        return s;
    }

    public int getLength() {
        return nodes.length;
    }

    private class Node {
        public Object key;
        public Object value;
        public boolean removed;
        Node next;

        public Node() {
            key = null;
            value = null;
            removed = false;
            next = null;
        }

        public Node(Object inKey, Object inValue) {
            key = inKey;
            value = inValue;
            removed = false;
            next = null;
        }

        public void setNextPtr(Node a) {
            this.next = a;
        }

        public Node getNextPtr() {
            return this.next;
        }

        public String toString() {
            String s = "<" + key.toString() +", " + value.toString() + ">";
            return s;
        }
    }
}