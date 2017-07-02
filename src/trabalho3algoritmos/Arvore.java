package trabalho3algoritmos;

import java.io.BufferedReader;
import java.io.FileReader;

public class Arvore {

    private Node root;
    private int quantidadeDeNodos;
    private int quantidadeDePalavras;
    private final String arquivo;

    public Arvore(String arquivo) throws Exception {
        quantidadeDePalavras = 0;
        addRoot('#');
        this.arquivo = arquivo;
        lerArquivo();
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

    private void addRoot(char element) {
        Node node = new Node(element);
        root = node;
        quantidadeDeNodos++;
    }

    public void add(String element, String significado) {
        Node nodo = getNode(element);
        if (contains(element)) {
            nodo.finalizaPalavra(significado);
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
        nodo.finalizaPalavra(significado);
        quantidadeDePalavras++;
    }

    public String imprimeAPalavraESeuSignificado(String prefixo, String palavra)
            throws Exception {
        if(!contains(prefixo) || !contains(palavra)){
            throw new Exception("Não achou a palavra.");
        }
        Node nodo = getNode(palavra);
        if (nodo.significado == null || nodo.significado.equals("")) {
            throw new Exception("Não achou a palavra.");
        }
        for (int i = 0; i < prefixo.length(); i++) {
            if (prefixo.charAt(i) != palavra.charAt(i)) {
                throw new Exception("Não achou a palavra.");
            }
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("Significado da palavra pesquisada: ");
        buffer.append(nodo.significado);
        return buffer.toString();
    }

    public String imprimePalavrasESeusSignificados(String prefixo)
            throws Exception {
        String palavras
                = pesquisarPalavrasQueComecamComDeterminadoPrefixo(prefixo);
        String[] array = palavras.split("\n");
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            buffer.append("\nPalavra: ");
            buffer.append(array[i]);
            Node nodo = getNode(array[i]);
            buffer.append("\nSignificado: ");
            buffer.append(nodo.significado);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public String pesquisarPalavrasQueComecamComDeterminadoPrefixo(String prefixo)
            throws Exception {
        StringBuilder buffer = new StringBuilder();
        if (!contains(prefixo)) {
            buffer.append("Não há palavras que comecem com este prefixo.");
        } else {            
            Node nodo = getNode(prefixo);
            LinkedListOfCharHelper lista = new LinkedListOfCharHelper();
            Stack<String> palavras = new Stack();
            int contador = 0;
            buffer.append(prefixo);
            if (nodo.getSubtreesSize() > 0) {
                lista = positionsPre(nodo, new LinkedListOfCharHelper(),
                        contador);
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
                    for (int j = 0; j < aux.getQuantidadeDeNodosFilhos() - 1;
                            j++) {
                        palavras.push(ultimaPalavra);
                    }
                }
            }
        }
        return buffer.toString();
    }

    private LinkedListOfCharHelper positionsPre(Node n,
            LinkedListOfCharHelper res, int contador) {
        if (n == null) {
            return null;
        }
        contador++;
        res.add(n.element, n.getTerminouPalavra(), n.getSubtreesSize());
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

    private void lerArquivo() throws Exception {
        if (this.arquivo == null || "".equals(this.arquivo)) {
            throw new Exception("Arquivo inexistente!");
        }
        try (BufferedReader file = new BufferedReader(new FileReader(arquivo))) {
            String[] linha;
            while (file.ready()) {
                linha = file.readLine().split(";");
                add(linha[0], linha[1]);
            }
        }
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
