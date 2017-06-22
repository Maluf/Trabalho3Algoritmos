package trabalho3algoritmos;

public class Arvore {

    private Node root;
    private int count;

    public Arvore() {
        root = null;
        count = 0;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public char getRoot() {
        if (isEmpty()) {
            throw new NullPointerException("Árvore vazia.");
        }
        return root.element;
    }

    public void setRoot(char element) {
        if (isEmpty()) {
            throw new NullPointerException("Árvore vazia.");
        }
        root.element = element;
    }

    public String getParent(String element) {
        // Implementar
        return null;
    }

    public boolean addRoot(char element) {
        //Implementar
        if (root != null) {
            return false;
        }

        Node node = new Node(element);
        root = node;
        count++;
        return true;
    }

    public void add(String element) {
        if (contains(element)) {
            return;
        }
        Node nodo = getNode(element);
        String resto = getString(element);
        char variavel = 0;
        for (int i = 0; i < resto.length(); i++) {
            variavel = resto.charAt(i);
            nodo.addSubtrees(variavel);
            nodo = nodo.getSubtree(nodo.getSubtreesSize() - 1);
        }
        nodo.finalizaPalavra();
        count++;
    }

    public void add(char element, Node father) {
        Node nodo = searchNodeRef(father, root);
        nodo.addSubtrees(element);
        nodo = nodo.getSubtree(nodo.getSubtreesSize() - 1);
        nodo.finalizaPalavra();
        count++;
    }

    public LinkedListOfNodes positionsWidth() {
        LinkedListOfNodes li = new LinkedListOfNodes();
        Queue<Node> fila = new Queue<>();
        Node aux = null;
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {

                aux = fila.dequeue();

                if (aux.subtrees != null) {
                    for (int i = 0; i < aux.getSubtreesSize(); i++) {
                        fila.enqueue(aux.getSubtree(i));
                    }
                }
                li.add(aux.element);
            }
        }
        return li;
    }

    public Node getNode(String element) {
        if (isEmpty()) {
            throw new NullPointerException("Árvore vazia.");
        }
        Node nodo = root;
        boolean flag = false;
        for (int i = 0; i < element.length(); i++) {
            flag = false;
            char variavel = element.charAt(i);
            if (nodo.getSubtreesSize() > 0) {
                int tamanho = nodo.getSubtreesSize();
                for (int j = 0; j < tamanho; j++) {
                    if (nodo.getSubtree(j).element == variavel) {
                        nodo = nodo.getSubtree(j);
                        j = tamanho;
                        flag = true;
                    }
                }
            }
            if (!flag) {
                i = element.length();
            }
        }
        return nodo;
    }

    public String getString(String element) {
        if (isEmpty()) {
            throw new NullPointerException("Árvore vazia.");
        }
        Node nodo = root;
        String resp = "";
        boolean flag = false;
        for (int i = 0; i < element.length(); i++) {
            char variavel = element.charAt(i);
            if (nodo != null) {
                if (nodo.getSubtreesSize() > 0) {
                    int tamanho = nodo.getSubtreesSize();
                    for (int j = 0; j < tamanho; j++) {
                        if (nodo.getSubtree(j).element == variavel) {
                            nodo = nodo.getSubtree(j);
                            j = tamanho;
                            flag = true;
                        }
                    }
                }
                if (nodo.element != variavel || !flag) {
                    resp += variavel;
                    nodo = null;
                }
                flag = false;
            } else {
                resp += variavel;
            }
        }
        return resp;
    }

    public boolean contains(String element) {
        Node nodo = root;
        boolean flag = false;
        for (int i = 0; i < element.length(); i++) {
            char variavel = element.charAt(i);
            if (nodo.getSubtreesSize() > 0) {
                int tamanho = nodo.getSubtreesSize();
                for (int j = 0; j < tamanho; j++) {
                    if (nodo.getSubtree(j).element == variavel) {
                        nodo = nodo.getSubtree(j);
                        j = tamanho;
                        flag = true;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            flag = false;
        }
        return true;
    }

    private Node searchNodeRef(Node element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.equals(element)) {
                res = target;
            } else {
                if (target.getSubtreesSize() > 0) {
                    for (int i = 0; i < target.getSubtreesSize(); i++) {
                        res = searchNodeRef(element, target.getSubtree(i));
                    }
                }
            }
        }
        return res;
    }

    private Node searchNodeRef(String element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.equals(element)) {
                res = target;
            } else {
                if (target.getSubtreesSize() > 0) {
                    for (int i = 0; i < target.getSubtreesSize(); i++) {
                        res = searchNodeRef(element, target.getSubtree(i));
                    }
                }
            }
        }
        return res;
    }

    private Node searchNodeRef(char element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.equals(element)) {
                res = target;
            } else {
                if (target.getSubtreesSize() > 0) {
                    for (int i = 0; i < target.getSubtreesSize(); i++) {
                        res = searchNodeRef(element, target.getSubtree(i));
                    }
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            throw new NullPointerException("Árvore vazia.");
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("Árvore:");
        buffer.append("\n");
        buffer.append(this.root.toString());
        return buffer.toString();
    }
}
