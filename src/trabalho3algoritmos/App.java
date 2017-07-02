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
            int op = 0;
            while (op != 4) {
                System.out.println("\nMenu:\nDigite as seguintes opções:\n"
                        + "1) Para imprimir a árvore\n"
                        + "2) Para pesquisar todas as palavras que começam "
                        + "com determinado prefixo\n"
                        + "3) Para pesquisar todas as palavras que começam com "
                        + "determinado prefixo e seus significados\n"
                        + "4) Para sair do menu\n");
                op = ler.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("\n" + arvore.toString());
                        break;
                    case 2:
                        System.out.print("\nDigite o prefixo no qual você deseja "
                                + "encontrar as palavras que começam com ele: ");
                        String prefixo1 = ler.next();
                        String palavras = arvore.pesquisarPalavrasQueComecamComDeterminadoPrefixo(prefixo1);
                        System.out.println("\nPalavras:\n\n" + palavras);
                        if (!palavras.equals("Não há palavras que comecem com este prefixo.")) {
                            System.out.print("\nEscolha uma palavra dentre essas para ver o seu significado: ");
                            String palavraEscolhida = ler.next();
                            System.out.println("\n" + arvore.imprimeAPalavraESeuSignificado(prefixo1, palavraEscolhida));
                        }
                        break;
                    case 3:
                        System.out.print("\nDigite o prefixo no qual você deseja "
                                + "encontrar as palavras que começam com ele, "
                                + "além dos seus respectivos significados: ");
                        String prefixo2 = ler.next();
                        System.out.println("\n" + arvore.imprimePalavrasESeusSignificados(prefixo2));
                        break;
                    case 4:
                        System.out.println("\nObrigado por usar o programa.\n");
                        System.exit(0);
                        break;
                    default:
                        System.err.println("\nDigite apenas as opções que estão no menu.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
}
