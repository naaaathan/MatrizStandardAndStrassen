

public class MultiplicacaoStrassen {

    private static final int VALOR_ALTERNA = 128;


    public int[][] multiplicaStrassen(int[][] A, int[][] B) {

        int n = A.length;
        int r = redimensionaStrassen(n);
        int[][] Aaux = new int[r][r];
        int[][] Baux = new int[r][r];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Aaux[i][j] = A[i][j];
                Baux[i][j] = B[i][j];
            }
        }

        int[][] CAux = strassenRecursivo(Aaux, Baux);
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(CAux[i], 0, C[i], 0, n);
        }
        return C;
    }

    private static int[][] strassenRecursivo(int[][] A, int[][] B) {

        int n = A.length;

        MultiplicacaoMatriz multiplicacaoMatriz = new MultiplicacaoMatriz(A,B);

        if (n <= VALOR_ALTERNA  ) {
            return multiplicacaoMatriz.produtoMultiplicacao();
        } else {

            int tam = n / 2;
            int[][] a11 = new int[tam][tam];
            int[][] a12 = new int[tam][tam];
            int[][] a21 = new int[tam][tam];
            int[][] a22 = new int[tam][tam];

            int[][] b11 = new int[tam][tam];
            int[][] b12 = new int[tam][tam];
            int[][] b21 = new int[tam][tam];
            int[][] b22 = new int[tam][tam];

            int[][] aResult;
            int[][] bResult;


            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    a11[i][j] = A[i][j]; // TOPO ESQUERDA
                    a12[i][j] = A[i][j + tam]; // TOPO DIREITA
                    a21[i][j] = A[i + tam][j]; // EMBAIXO ESQUERDA
                    a22[i][j] = A[i + tam][j + tam]; // EMBAIXO DIREITA

                    b11[i][j] = B[i][j]; // TOPO ESQUERDA
                    b12[i][j] = B[i][j + tam]; // TOPO DIREITA
                    b21[i][j] = B[i + tam][j]; //EMBAIXO ESQUERDA
                    b22[i][j] = B[i + tam][j + tam]; //  EMBAIXO DIREITA
                }
            }


            aResult = soma(a11, a22);
            bResult = soma(b11, b22);
            int[][] p1 = strassenRecursivo(aResult, bResult);


            aResult = soma(a21, a22);
            int[][] p2 = strassenRecursivo(aResult, b11);

            bResult = subtrai(b12, b22);
            int[][] p3 = strassenRecursivo(a11, bResult);


            bResult = subtrai(b21, b11);
            int[][] p4 = strassenRecursivo(a22, bResult);


            aResult = soma(a11, a12);
            int[][] p5 = strassenRecursivo(aResult, b22);


            aResult = subtrai(a21, a11);
            bResult = soma(b11, b12);
            int[][] p6 = strassenRecursivo(aResult, bResult);


            aResult = subtrai(a12, a22);
            bResult = soma(b21, b22);
            int[][] p7 = strassenRecursivo(aResult, bResult);



            int[][] c12 = soma(p3, p5);
            int[][] c21 = soma(p2, p4);

            aResult = soma(p1, p4);
            bResult = soma(aResult, p7);
            int[][] c11 = subtrai(bResult, p5);


            aResult = soma(p1, p3);
            bResult = soma(aResult, p6);
            int[][] c22 = subtrai(bResult, p2);


            int[][] C = new int[n][n];
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    C[i][j] = c11[i][j];
                    C[i][j + tam] = c12[i][j];
                    C[i + tam][j] = c21[i][j];
                    C[i + tam][j + tam] = c22[i][j];
                }
            }
            return C;
        }
    }


    private static int redimensionaStrassen(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }


    private static int[][] subtrai(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }


    private static int[][] soma(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

}
