package mlr;

public class MultipleLinearRegression {
    private double[][] X;
    private double[] Y;
    private double[] betas;
    private int n;
    private int k;

    public MultipleLinearRegression(double[][] features, double[] target) {
        this.n = features.length;
        this.k = features[0].length + 1; // +1 por el intercepto beta_0
        this.Y = target;

        // Construir la matriz de diseño X con la primera columna de unos
        this.X = new double[n][k];
        for (int i = 0; i < n; i++) {
            this.X[i][0] = 1.0;
            for (int j = 0; j < features[i].length; j++) {
                this.X[i][j + 1] = features[i][j];
            }
        }
    }

    // Calcula betas = (X^T * X)^(-1) * X^T * Y
    public void fit() {
        double[][] Xt = transpose(X);
        double[][] XtX = multiply(Xt, X);
        double[][] XtX_inv = invert(XtX);
        double[][] XtX_inv_Xt = multiply(XtX_inv, Xt);
        this.betas = multiply(XtX_inv_Xt, Y);
    }

    public double predict(double[] x) {
        double yPred = betas[0];
        for (int i = 0; i < x.length; i++) {
            yPred += betas[i + 1] * x[i];
        }
        return yPred;
    }

    public double calculateR2() {
        double yMean = 0.0;
        for (double val : Y) yMean += val;
        yMean /= n;

        double ssTot = 0.0;
        double ssRes = 0.0;
        for (int i = 0; i < n; i++) {
            double[] rowFeatures = new double[k - 1];
            for (int j = 1; j < k; j++) {
                rowFeatures[j - 1] = X[i][j];
            }
            double pred = predict(rowFeatures);
            ssRes += Math.pow(Y[i] - pred, 2);
            ssTot += Math.pow(Y[i] - yMean, 2);
        }
        return 1.0 - (ssRes / ssTot);
    }

    public double[] getBetas() {
        return betas;
    }

    private double[][] transpose(double[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        double[][] t = new double[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                t[j][i] = matrix[i][j];
            }
        }
        return t;
    }

    private double[][] multiply(double[][] A, double[][] B) {
        int rA = A.length, cA = A[0].length, cB = B[0].length;
        double[][] C = new double[rA][cB];
        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cB; j++) {
                for (int m = 0; m < cA; m++) {
                    C[i][j] += A[i][m] * B[m][j];
                }
            }
        }
        return C;
    }

    private double[] multiply(double[][] A, double[] B) {
        int rA = A.length, cA = A[0].length;
        double[] C = new double[rA];
        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cA; j++) {
                C[i] += A[i][j] * B[j];
            }
        }
        return C;
    }

    private double[][] invert(double[][] matrix) {
        int len = matrix.length;
        double[][] augmented = new double[len][len * 2];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) augmented[i][j] = matrix[i][j];
            augmented[i][len + i] = 1.0;
        }

        for (int i = 0; i < len; i++) {
            double pivot = augmented[i][i];
            for (int j = 0; j < len * 2; j++) augmented[i][j] /= pivot;

            for (int kIdx = 0; kIdx < len; kIdx++) {
                if (kIdx != i) {
                    double factor = augmented[kIdx][i];
                    for (int j = 0; j < len * 2; j++) {
                        augmented[kIdx][j] -= factor * augmented[i][j];
                    }
                }
            }
        }

        double[][] inv = new double[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                inv[i][j] = augmented[i][len + j];
            }
        }
        return inv;
    }
}