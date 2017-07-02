
package trabalho3algoritmos;


public class LinkedListOfCharHelper {

    public class Node {

        public char element;
        public Node next;
        public boolean terminouPalavra;
        public int quantidadeDeNodosFilhos;
        
        public Node(char element) {
            this.element = element;
            next = null;
        }
        
        public boolean getTerminouPalavra(){
            return this.terminouPalavra;
        }
        
        public int getQuantidadeDeNodosFilhos(){
            return this.quantidadeDeNodosFilhos;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public LinkedListOfCharHelper() {
        head = null;
        tail = null;
        count = 0;
    }
    
    public void add(char element, boolean terminou, int quantidadeFilhos) {
        Node aux = new Node(element);
        aux.terminouPalavra = terminou;
        aux.quantidadeDeNodosFilhos = quantidadeFilhos;
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }
    
    public Node getNode(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux);
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
}