import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class PRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(">>> Inicializando " + getLocalName() + "...");
        addBehaviour(new PRBehaviour());
    }

    private class PRBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            // 4.a Inicializar dataset
            DataSet ds = new DataSet();

            // 4.b Instanciar modelo y calcular betas optimos
            PolynomialRegression pr = new PolynomialRegression(ds.getX(), ds.getY());
            double b0 = pr.getBeta0();
            double b1 = pr.getBeta1();
            double b2 = pr.getBeta2();

            // 4.c Imprimir ecuacion en terminal
            System.out.println("\n-------------------------------------------");
            System.out.println("ECUACION POLINOMIAL CALCULADA:");
            System.out.printf("y = %.4f + (%.4f)x + (%.4f)x^2\n", b0, b1, b2);
            System.out.println("-------------------------------------------");

            // 4.d y 4.e Realizar tres predicciones e imprimirlas
            double[] valoresPrueba = {18.0, 28.0, 42.0};
            System.out.println("PREDICCIONES:");
            for (double xVal : valoresPrueba) {
                double yEst = pr.predict(xVal);
                System.out.printf("Para Batch Size x = %.1f -> Efficiency y = %.4f\n", xVal, yEst);
            }

            // 4.f Calcular e imprimir R^2
            double r2 = pr.calculateRSquared();
            System.out.printf("\nCoeficiente de Determinacion R^2: %.4f\n", r2);
            System.out.println("-------------------------------------------");

            // 5. El agente muere al concluir
            System.out.println(">>> Tarea completada. Matando agente...");
            myAgent.doDelete();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println(">>> Agente " + getLocalName() + " terminado y destruido.");
    }
}