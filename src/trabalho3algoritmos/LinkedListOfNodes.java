package trabalho3algoritmos;

public class LinkedListOfNodes {

    private Node head;
    private Node tail;
    private int count;

    public LinkedListOfNodes() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(char element, Node father) {
        Node aux = new Node(element, father);
        if (head == null) {
            head = aux;
            head.father = father;
        } else {
            tail.brother = aux;
        }
        tail = aux;
        tail.father = father;
        count++;
    }

    public Node get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.brother;
            c++;
        }
        return aux;
    }

    public boolean isEmpty() {
        return (head == null);
    }
    
    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Node aux = head;
        while (aux != null) {
            buffer.append(aux.toString());
            aux = aux.brother;
        }
        return buffer.toString();
    }

}
