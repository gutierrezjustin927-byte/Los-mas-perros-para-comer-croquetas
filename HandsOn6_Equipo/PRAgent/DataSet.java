public class DataSet {
    private final double[] x;
    private final double[] y;

    public DataSet() {
        // Datos del caso Six Sigma Material: Batch Size (x) vs Machine Efficiency (y)
        this.x = new double[]{10.0, 15.0, 20.0, 25.0, 30.0, 35.0, 40.0, 45.0, 50.0};
        this.y = new double[]{73.0, 78.0, 85.0, 90.0, 91.0, 89.0, 84.0, 76.0, 68.0};
    }

    public double[] getX() { return x; }
    public double[] getY() { return y; }
}