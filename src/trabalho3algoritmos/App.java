package trabalho3algoritmos;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try {
            Scanner ler = new Scanner(System.in);
            String caminho = "src/documentos/";
            System.out.print("Digite o nome do arquivo a ser lido: ");
            String arquivo = ler.next();
            String resposta = caminho + arquivo;
            Arvore arvore = new Arvore(resposta);
            System.out.println(arvore.toString());
            System.out.print("\nDigite o prefixo no qual você deseja "
                    + "encontrar as palavras que começam com ele, "
                    + "além dos seus respectivos significados: ");
            String prefixo = ler.next();            
            System.out.println("\n" + arvore.imprimePalavrasESeusSignificados(prefixo));
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
}
