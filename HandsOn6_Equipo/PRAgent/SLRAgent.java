
package examples.handson6;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class SLRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("SLRAgent iniciado");

        addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                SimpleLinearRegression modelo =
                    new SimpleLinearRegression();

                modelo.calcularModelo();

                System.out.println("=== REGRESION LINEAL SIMPLE ===");

                System.out.println("Beta 0: " + modelo.getBeta0());
                System.out.println("Beta 1: " + modelo.getBeta1());

                System.out.println("Ecuacion: y = "
                    + modelo.getBeta0() + " + "
                    + modelo.getBeta1() + "x");

                double[] valores = {6, 7, 8};

                for (double x : valores) {
                    System.out.println("Prediccion x=" + x
                        + ": y=" + modelo.predecir(x));
                }

                System.out.println("R^2 = " + modelo.calcularR2());

                System.out.println("SLRAgent finalizado");
                myAgent.doDelete();
            }
        });
    }
}
