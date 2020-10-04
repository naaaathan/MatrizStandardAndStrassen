public class MultiplicacaoMatriz {

    private final int[][] matrizX;
    private final int[][] matrizY;

    public MultiplicacaoMatriz(int[][] matrizX, int[][] matrizY) {
        this.matrizX = matrizX;
        this.matrizY = matrizY;

    }

    public int[][] produtoMultiplicacao() {

        int n = matrizX.length;


        int[][] z = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                z[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    z[i][j] += matrizX[i][k] * matrizY[k][j];
                }
            }
        }
        return z;
    }


}
