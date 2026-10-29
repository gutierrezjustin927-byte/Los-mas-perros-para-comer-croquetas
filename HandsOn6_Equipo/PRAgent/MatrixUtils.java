public class MatrixUtils {
    public static double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        double[][] result = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static double[] multiplyMatrixVector(double[][] a, double[] v) {
        int rows = a.length;
        int cols = a[0].length;
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i] += a[i][j] * v[j];
            }
        }
        return result;
    }

    public static double[][] invert3x3(double[][] a) {
        double det = a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1])
                   - a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0])
                   + a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);

        if (Math.abs(det) < 1e-9) {
            throw new ArithmeticException("Determinante cero, matriz no invertible.");
        }

        double invDet = 1.0 / det;
        double[][] inv = new double[3][3];

        inv[0][0] = (a[1][1] * a[2][2] - a[1][2] * a[2][1]) * invDet;
        inv[0][1] = (a[0][2] * a[2][1] - a[0][1] * a[2][2]) * invDet;
        inv[0][2] = (a[0][1] * a[1][2] - a[0][2] * a[1][1]) * invDet;

        inv[1][0] = (a[1][2] * a[2][0] - a[1][0] * a[2][2]) * invDet;
        inv[1][1] = (a[0][0] * a[2][2] - a[0][2] * a[2][0]) * invDet;
        inv[1][2] = (a[0][2] * a[1][0] - a[0][0] * a[1][2]) * invDet;

        inv[2][0] = (a[1][0] * a[2][1] - a[1][1] * a[2][0]) * invDet;
        inv[2][1] = (a[0][1] * a[2][0] - a[0][0] * a[2][1]) * invDet;
        inv[2][2] = (a[0][0] * a[1][1] - a[0][1] * a[1][0]) * invDet;

        return inv;
    }
}