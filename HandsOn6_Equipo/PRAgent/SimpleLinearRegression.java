
package examples.handson6;

public class SimpleLinearRegression {
    private final double[] x = {1, 2, 3, 4, 5};
    private final double[] y = {3, 5, 7, 9, 11};

    private double beta0;
    private double beta1;

    public void calcularModelo() {
        int n = x.length;
        double sumaX = 0, sumaY = 0;
        double sumaXY = 0, sumaX2 = 0;

        for (int i = 0; i < n; i++) {
            sumaX += x[i];
            sumaY += y[i];
            sumaXY += x[i] * y[i];
            sumaX2 += x[i] * x[i];
        }

        beta1 = (n * sumaXY - sumaX * sumaY)
              / (n * sumaX2 - sumaX * sumaX);

        beta0 = (sumaY - beta1 * sumaX) / n;
    }

    public double predecir(double valorX) {
        return beta0 + beta1 * valorX;
    }

    public double calcularR2() {
        double mediaY = 0;
        for (double valor : y) {
            mediaY += valor;
        }
        mediaY /= y.length;

        double sst = 0;
        double sse = 0;

        for (int i = 0; i < y.length; i++) {
            double estimado = predecir(x[i]);
            sst += Math.pow(y[i] - mediaY, 2);
            sse += Math.pow(y[i] - estimado, 2);
        }

        return sst == 0 ? (sse == 0 ? 1 : 0) : 1 - sse / sst;
    }

    public double getBeta0() {
        return beta0;
    }

    public double getBeta1() {
        return beta1;
    }
}
