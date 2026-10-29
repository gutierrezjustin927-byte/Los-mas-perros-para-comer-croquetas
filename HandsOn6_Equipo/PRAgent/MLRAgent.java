package mlr;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MLRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(">>> Agente MLR iniciado: " + getLocalName());
        addBehaviour(new MLRBehaviour());
    }

    private class MLRBehaviour extends OneShotBehaviour {

        @Override
        public void action() {
            // 4.a: Inicializar dataset
            double[][] X = {
                {1.0, 2.0},
                {2.0, 3.0},
                {3.0, 5.0},
                {4.0, 7.0},
                {5.0, 8.0},
                {6.0, 9.0},
                {7.0, 10.0}
            };
            double[] Y = {55.0, 62.0, 70.0, 78.0, 85.0, 89.0, 95.0};

            // 4.b: Instanciar la clase de regresión y calcular coeficientes
            MultipleLinearRegression mlr = new MultipleLinearRegression(X, Y);
            mlr.fit();

            // 4.c: Imprimir la ecuación de regresión
            double[] betas = mlr.getBetas();
            System.out.println("\n--------------------------------------------------");
            System.out.printf("Ecuacion de Regresion: y = %.4f + %.4f*x1 + %.4f*x2\n", 
                              betas[0], betas[1], betas[2]);
            System.out.println("--------------------------------------------------");

            // 4.d y 4.e: Realizar 3 predicciones e imprimirlas
            double[][] nuevosCasos = {
                {2.5, 4.0},
                {4.5, 6.0},
                {8.0, 10.0}
            };

            System.out.println("\n--- Predicciones realizadas ---");
            for (int i = 0; i < nuevosCasos.length; i++) {
                double pred = mlr.predict(nuevosCasos[i]);
                System.out.printf("Prediccion %d: x1 = %.1f, x2 = %.1f -> y = %.4f\n", 
                                  (i + 1), nuevosCasos[i][0], nuevosCasos[i][1], pred);
            }

            // 4.f: Calcular e imprimir R^2
            double r2 = mlr.calculateR2();
            System.out.println("\n--------------------------------------------------");
            System.out.printf("Coeficiente de Determinacion R^2 = %.4f (%.2f%%)\n", r2, r2 * 100);
            System.out.println("--------------------------------------------------\n");

            // 5: Autodestrucción del agente
            System.out.println(">>> Finalizando agente MLR...");
            myAgent.doDelete();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println(">>> Agente MLR destruido exitosamente.");
    }
}