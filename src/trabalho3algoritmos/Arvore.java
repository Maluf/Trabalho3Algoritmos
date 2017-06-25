package trabalho3algoritmos;

public class Arvore {

    private Node root;
    private int quantidadeDeNodos;
    private int quantidadeDePalavras;

    public Arvore() {
        quantidadeDePalavras = 0;
        addRoot('#');
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int getQuantidadeDeNodos() {
        return quantidadeDeNodos;
    }
    
    public int getQuantidadeDePalavras() {
        return quantidadeDePalavras;
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

    public char getParent(char element) {
        Node nodo = searchNodeRef(element, root);
        return nodo.father.element;
    }

    private void addRoot(char element) {
        Node node = new Node(element);
        root = node;
        quantidadeDeNodos++;
    }

    public void add(String element) {
        Node nodo = getNode(element);
        if (contains(element)) {
            nodo.finalizaPalavra();
            quantidadeDePalavras++;
            return;
        }
        String resto = getString(element);
        char variavel = 0;
        for (int i = 0; i < resto.length(); i++) {
            variavel = resto.charAt(i);
            nodo.addSubtrees(variavel);
            quantidadeDeNodos++;
            nodo = nodo.getSubtree(nodo.getSubtreesSize() - 1);
        }
        nodo.finalizaPalavra();        
        quantidadeDePalavras++;
    }

    public void add(char element, Node father) {
        Node nodo = searchNodeRef(father, root);
        nodo.addSubtrees(element);
        nodo = nodo.getSubtree(nodo.getSubtreesSize() - 1);
        nodo.finalizaPalavra();
        quantidadeDeNodos++;
        quantidadeDePalavras++;
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
                li.add(aux);
            }
        }
        return li;
    }

    public String pesquisarPalavrasQueComecamComDeterminadoPrefixo(String prefixo) throws Exception {
        StringBuilder buffer = new StringBuilder();
        Node nodo = getNode(prefixo);
        LinkedListOfCharHelper lista = new LinkedListOfCharHelper();
        Stack<String> palavras = new Stack();
        int contador = 0;
        if (nodo.equals(this.root)) {
            buffer.append("Não há palavras que comecem com este prefixo.");
        } else {
            buffer.append(prefixo);
            if (nodo.getTerminouPalavra()) {
                buffer.append("\n");
            }
            if (nodo.getSubtreesSize() > 0) {
                lista = positionsPre(nodo, new LinkedListOfCharHelper(), contador);
            }
            for (int i = 1; i < lista.size(); i++) {
                LinkedListOfCharHelper.Node aux = lista.getNode(i);
                buffer.append(aux.element);
                if (aux.getTerminouPalavra()) {
                    if (aux.getQuantidadeDeNodosFilhos() > 0) {
                        String[] array = buffer.toString().split("\n");
                        String ultimaPalavra = array[array.length - 1];
                        buffer.append("\n");
                        if (!palavras.isEmpty()) {
                            buffer.append(palavras.pop());
                        } else {
                            buffer.append(ultimaPalavra);
                        }
                    } else if (i < lista.size() - 1) {
                        buffer.append("\n");
                        if (!palavras.isEmpty()) {
                            buffer.append(palavras.pop());
                        } else {
                            buffer.append(prefixo);
                        }
                    }
                }
                if (aux.getQuantidadeDeNodosFilhos() > 1) {
                    String[] array = buffer.toString().split("\n");
                    String ultimaPalavra = array[array.length - 1];
                    for (int j = 0; j < aux.getQuantidadeDeNodosFilhos() - 1; j++) {
                        palavras.push(ultimaPalavra);
                    }
                }
            }
        }
        return buffer.toString();
    }

    private LinkedListOfCharHelper positionsPre(Node n, LinkedListOfCharHelper res, int contador) {
        if (n == null) {
            return null;
        }
        contador++;
        res.add(n.element, n.getTerminouPalavra(), n.getSubtreesSize(),
                (n.father.getSubtreesSize() - 1));
        Node aux = n;
        if (aux.getSubtreesSize() > 0) {
            positionsPre(aux.getSubtree(0), res, contador);
        }
        if (aux.brother != null && contador > 1) {
            positionsPre(aux.brother, res, contador);
        }
        return res;
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

    private Node searchNodeRef(char element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.element == (element)) {
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
