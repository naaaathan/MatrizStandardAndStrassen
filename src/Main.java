import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Informe a dimensão da matriz quadrada : ");
        Scanner matrixTam = new Scanner(System.in);

        int n = matrixTam.nextInt();

        long tempoMultPadraoInicio;
        long tempoMultPadraoFim;
        long tempoMultStrassenInicio;
        long tempoMultStrassenFim;

        int[][] matrizX = new int[n][n];
        int[][] matrizY = new int[n][n];
        int[][] produtoMultPadrao;
        int[][] produtoMultStrassen;

        System.out.println("\n------ Preenche matrizX -----\n");
        preencheMatriz(matrizX);
        System.out.println("\n------ Preenche matrizY -----\n");
        preencheMatriz(matrizY);

        System.out.println("\n\nMultiplica matrizX e matrizY normal = \n");
        MultiplicacaoMatriz multiplicacaoMatriz = new MultiplicacaoMatriz(matrizX, matrizY);
        tempoMultPadraoInicio = System.currentTimeMillis();
        produtoMultPadrao = multiplicacaoMatriz.produtoMultiplicacao();
        tempoMultPadraoFim = System.currentTimeMillis();
        //printarMatriz(produtoMultPadrao);

        System.out.println("\n\nMultiplica matrizX e matrizY Strassen = \n");
        MultiplicacaoStrassen multiplicacaoStrassen = new MultiplicacaoStrassen();
        tempoMultStrassenInicio = System.currentTimeMillis();
        produtoMultStrassen = multiplicacaoStrassen.multiplicaStrassen(matrizX,matrizY);
        tempoMultStrassenFim = System.currentTimeMillis();

        //printarMatriz(produtoMultStrassen);

        System.out.println("\nTempo execução multiplicação de matrizes método convencional : " + (tempoMultPadraoFim - tempoMultPadraoInicio));

        System.out.println("\nTempo execução multiplicação de matrizes método divisão e conquista Strassen : " + (tempoMultStrassenFim - tempoMultStrassenInicio));


    }

    private static void printarMatriz(int[][] produtoMult) {

        int tamanho = produtoMult.length;

        for (int i = 0; i < tamanho; i++) {
            System.out.print("\n");
            for (int j = 0; j < tamanho; j++) {
                System.out.print(produtoMult[i][j] + "   ");
            }
        }
    }

    private static void preencheMatriz(int[][] matriz) {

        int linhas = matriz.length;
        int colunas = matriz[0].length;
        Random valorAleatorio = new Random();

        for (int i = 0; i < linhas; i++) {
            System.out.print("\n");
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = valorAleatorio.nextInt(50);
                //System.out.print(matriz[i][j] + "    ");
            }
        }
    }
}
