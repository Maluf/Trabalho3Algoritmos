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

    public void add(Node n) {
        if (head == null) {
            head = n;
            head.father = n.father;
        } else {
            tail.brother = n;
        }
        tail = n;
        tail.father = n.father;
        count++;
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

    public Node head() {
        return head;
    }

    public void add(int index, char element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node n = new Node(element);
        if (index == 0) { //insere no inicio
            n.brother = head;
            head = n;
            if (tail == null) {
                tail = n;
            }
        } else if (index == count) { // insere no final
            tail.brother = n;
            tail = n;
        } else { // insere no meio
            Node aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.brother;
            }
            n.brother = aux.brother;
            aux.brother = n;
        }
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

    public char set(int index, char element) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.brother;
        }
        char tmp = aux.element;
        aux.element = element;
        return tmp;

    }

    public boolean remove(Node n) {

        if (n == null) {
            return false;
        }
        if (count == 0) {
            return false;
        }

        if (head.equals(n)) { // remocao do primeiro
            head = head.brother;
            if (count == 1) { // se havia so um elemento na lista
                tail = null;
            }
            count--;
            return true;
        }

        Node ant = head;
        Node aux = head.brother;

        for (int i = 1; i < count; i++) {
            if (aux.equals(n)) {
                if (aux == tail) { // remocao do ultimo
                    tail = ant;
                    tail.brother = null;
                } else { // remocao do meio
                    ant.brother = aux.brother;
                }
                count--;
                return true;
            }
            ant = ant.brother;
            aux = aux.brother;
        }

        return false;

    }

    public boolean remove(char element) {
        if (contains(element)) {
            return false;
        }
        if (count == 0) {
            return false;
        }

        if (head.element == (element)) { // remocao do primeiro
            head = head.brother;
            if (count == 1) { // se havia so um elemento na lista
                tail = null;
            }
            count--;
            return true;
        }

        Node ant = head;
        Node aux = head.brother;

        for (int i = 1; i < count; i++) {
            if (aux.element == (element)) {
                if (aux == tail) { // remocao do ultimo
                    tail = ant;
                    tail.brother = null;
                } else { // remocao do meio
                    ant.brother = aux.brother;
                }
                count--;
                return true;
            }
            ant = ant.brother;
            aux = aux.brother;
        }

        return false;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista
     *
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public char removeByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        Node aux = head;
        if (index == 0) {
            if (tail == head) // se tiver apenas um elemento
            {
                tail = null;
            }
            head = head.brother;
            count--;
            return aux.element;
        }
        int c = 0;
        while (c < index - 1) {
            aux = aux.brother;
            c++;
        }
        char element = aux.brother.element;
        if (tail == aux.brother) {
            tail = aux;
        }
        aux.brother = aux.brother.brother;
        count--;
        return element;
    }

    public int indexOf(char element) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element == (element)) {
                return (index);
            }
            aux = aux.brother;
            index++;
        }
        return -1;
    }

    public boolean contains(char element) {
        Node aux = head;
        while (aux != null) {
            if (aux.element == (element)) {
                return (true);
            }
            aux = aux.brother;
        }
        return false;
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
