package trabalho3algoritmos;

public class App {

    public static void main(String[] args) {
        try {
            Arvore arvore = new Arvore();
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
            arvore.add("para");
            arvore.add("parada");
            
            System.out.println(arvore.toString());
            System.out.println("\nPrimeira pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadoPrefixo("g"));
            System.out.println("\nSegunda pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadoPrefixo("a"));
            System.out.println("\nTerceira pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadoPrefixo("pre"));
            System.out.println("\nQuarta pesquisa:\n" + arvore.pesquisarPalavrasQueComecamComDeterminadoPrefixo("p"));
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
}
