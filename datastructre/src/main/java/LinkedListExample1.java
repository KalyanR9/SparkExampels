public class LinkedListExample1 {
    private Node first;
    private Node last;
    private int nodeCount;

    public void add(int data) {
        if (first == null) {
            first = new Node(data);
            last = first;
        } else {
            Node newNode = new Node(data);
            last.setNextNode(newNode);
            last = newNode;
        }
        nodeCount++;
    }

    public void insert(int position, int data) {
        if (size() < position) {
            throw new IllegalArgumentException("The LinkedList is empty and there are no items to get");
        } else {
            Node currentnode = first;
            //traverse till inserting node
            for (int i = 0; i < position && currentnode != null; i++) {
                currentnode = currentnode.getNextNode(); // previous node of inserting node
            }
            Node node = new Node(data); // inserting node
            node.setNextNode(currentnode.getNextNode());
            currentnode.setNextNode(node);
            nodeCount++;
        }
    }

    public int remove() {
        if (first == null) {
            throw new IllegalArgumentException("The LinkedList is empty and there are no items to remove");
        }
        int data = first.getData();
        first = first.getNextNode();
        --nodeCount;
        return data;
    }

    public int remove(int position) {
        if (first == null || size() < position) {
            throw new IllegalArgumentException("The LinkedList is empty and there are no items to remove");
        }

        Node currentNode = first;
        Node prevNode = first;
        for (int i = 0; i < position && currentNode != null; i++) {
            prevNode = currentNode;
            currentNode = currentNode.getNextNode(); //deleting node
        }
        int x = currentNode.getData();
        prevNode.setNextNode(currentNode);
        nodeCount--;
        return x;
    }

    public int get(int position) {
        if (size() < position) {
            throw new IllegalArgumentException("");
        }
        Node currentNode = first;
        for (int i = 0; i < size() && currentNode != null; i++) {
            if (i == position) {
                return currentNode.getData();
            }
            currentNode = currentNode.getNextNode();
        }
        return 0;
    }

    public int size() {
        return nodeCount;
    }
}

class Node {
    Node nextNode;
    int data;

    Node(int data) {
        this.data = data;
        this.nextNode = null;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

}

