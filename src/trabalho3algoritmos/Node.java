package trabalho3algoritmos;

public class Node {

    public Node father;
    public Node brother;
    public char element;
    public LinkedListOfNodes subtrees;
    public boolean terminouPalavra;

    public Node() {
    }

    public Node(char element) {
        this.father = null;
        this.brother = null;
        this.element = element;
        this.subtrees = new LinkedListOfNodes();
        this.terminouPalavra = false;
    }

    public Node(char element, Node father) {
        this.element = element;
        this.father = father;
        this.subtrees = new LinkedListOfNodes();
        this.terminouPalavra = false;
    }
    
    public void finalizaPalavra(){
        this.terminouPalavra = true;
    }
    
    public boolean getTerminouPalavra(){
        return this.terminouPalavra;
    }
    
    public void addSubtrees(char el) {
        this.subtrees.add(el, this);
    }

    public boolean removeSubtree(Node n) {

        return this.subtrees.remove(n);
    }

    public Node getSubtree(int i) {
        return this.subtrees.get(i);
    }

    public int getSubtreesSize() {
        return this.subtrees.size();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(this.element);
        buffer.append(" { ");
        if(getSubtreesSize() > 0){
            buffer.append(this.subtrees);
        }
        buffer.append(" } ");
        return buffer.toString();
    }

}
