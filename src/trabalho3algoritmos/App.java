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
            arvore.add("premeditado");
            arvore.add("pararaio");
            arvore.add("giles");
            arvore.add("parada");
            arvore.add("para");
            System.out.println(arvore.toString());
            System.out.println("\nPrimeira pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadaString("g"));
            System.out.println("\nSegunda pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadaString("a"));
            System.out.println("\nTerceira pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadaString("pre"));
            System.out.println("\nQuarta pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadaString("p"));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}