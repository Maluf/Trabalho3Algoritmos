package trabalho3algoritmos;

public class Stack<E> {

    private int count;
    private Node head;
    private Node tail;

    private class Node {

        private E element;
        private Node next;

        public Node() {
        }

        public Node(E element) {
            this.element = element;
        }
    }

    public Stack() {
        clear();
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public void push(E element) {
        Node nodo = new Node(element);
        if (count == 0) {
            head = nodo;
        } else {
            tail.next = nodo;
        }
        tail = nodo;
        count++;
    }

    public E pop() throws Exception {
        if (count == 0) {
            throw new Exception("Underflow");
        }
        Node nodo = tail;
        Node prev = head;
        Node atual = null;
        if (count == 1) {
            head = null;
            tail = null;
        } else {
            atual = head.next;
            while (atual != null) {
                if (atual.equals(tail)) {
                    tail = prev;
                    prev.next = prev.next.next;
                    atual = null;
                } else {
                    prev = prev.next;
                    atual = atual.next;
                }
            }
        }
        count--;
        return nodo.element;
    }

    public E top() throws Exception {
        if (count == 0) {
            throw new Exception("Empty Stack");
        }
        return tail.element;
    }

}
