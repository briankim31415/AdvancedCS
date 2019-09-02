public class BinarySearchTree {
    Node root;
    String inOrderString, prettyString;

    public BinarySearchTree() {
        inOrderString = prettyString = "";
    }

    public void insertValue(double val) {
        if(root == null) {
            root = new Node(val, 0);
            return;
        }
        Node parent = findParent(val, root);
        if(val <= parent.value)
            parent.left = new Node(val, parent.depth+1);
        else
            parent.right = new Node(val, parent.depth+1);
    }

    public Node findParent(double val, Node node) {
        if(val <= node.value && node.left == null)
            return node;
        else if(val > node.value && node.right == null)
            return node;
        else {
            if(val <= node.value)
                return findParent(val, node.left);
            else
                return findParent(val, node.right);
        }
    }

    public void traverse() {
        search(root);
        inOrderString = inOrderString.substring(0, inOrderString.length()-2);
    }

    public void search(Node node) {
        if(node != null) {
            search(node.left);
            newLayer(node);
            search(node.right);
        }
    }

    public void newLayer(Node node) {
        int val = (int) node.value;
        inOrderString += val + ", ";
        String nextLayer = "";
        for (int i = 0; i < node.depth*2; i++) {
            nextLayer += " ";
        }
        nextLayer += val + "\n";
        prettyString = nextLayer + prettyString;
    }
}

class Node {
    double value;
    int depth;
    Node left, right;

    public Node(double value, int depth) {
        this.value = value;
        this.depth = depth;
    }
}


