package evaluation;

public interface Evaluate {
    public void evaluate();

    public double calcultePrecision(double truePositive, double falsePositive);
    public double calculteRecall(double truePositive, double falseNegative);
    public double calculteF(double truePositive, double falsePositive, double falseNegative);
}
