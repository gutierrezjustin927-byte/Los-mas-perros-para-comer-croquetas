public class PolynomialRegression {
    private double beta0, beta1, beta2;
    private final double[] x, y;

    public PolynomialRegression(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        fit();
    }

    private void fit() {
        int n = x.length;
        // Matriz de diseño X: [1, x, x^2]
        double[][] X = new double[n][3];
        for (int i = 0; i < n; i++) {
            X[i][0] = 1.0;
            X[i][1] = x[i];
            X[i][2] = x[i] * x[i];
        }

        // B = (X^T * X)^(-1) * X^T * y
        double[][] Xt = MatrixUtils.transpose(X);
        double[][] XtX = MatrixUtils.multiply(Xt, X);
        double[][] invXtX = MatrixUtils.invert3x3(XtX);
        double[][] XtX_inv_Xt = MatrixUtils.multiply(invXtX, Xt);
        double[] betas = MatrixUtils.multiplyMatrixVector(XtX_inv_Xt, y);

        this.beta0 = betas[0];
        this.beta1 = betas[1];
        this.beta2 = betas[2];
    }

    public double predict(double xVal) {
        return beta0 + (beta1 * xVal) + (beta2 * Math.pow(xVal, 2));
    }

    public double calculateRSquared() {
        double yMean = 0.0;
        for (double val : y) yMean += val;
        yMean /= y.length;

        double ssTot = 0.0, ssRes = 0.0;
        for (int i = 0; i < y.length; i++) {
            double yPred = predict(x[i]);
            ssTot += Math.pow(y[i] - yMean, 2);
            ssRes += Math.pow(y[i] - yPred, 2);
        }
        return 1.0 - (ssRes / ssTot);
    }

    public double getBeta0() { return beta0; }
    public double getBeta1() { return beta1; }
    public double getBeta2() { return beta2; }
}