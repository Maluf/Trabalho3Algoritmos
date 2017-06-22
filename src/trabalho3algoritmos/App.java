package trabalho3algoritmos;

public class App {

    public static void main(String[] args) {
        try {
            Arvore arvore = new Arvore();
            arvore.addRoot('0');
            arvore.add("guilherme");
            arvore.add("gilberto");
            arvore.add("amor");
            arvore.add("arvore");
            arvore.add("prefixo");
            arvore.add("preludio");
            arvore.add("parafuso");
            System.out.println(arvore.toString());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
