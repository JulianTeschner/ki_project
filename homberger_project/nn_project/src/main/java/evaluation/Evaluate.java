package evaluation;

public interface Evaluate {
    void evaluate();

    double calculatePrecision(double truePositive, double falsePositive);
    double calculateRecall(double truePositive, double falseNegative);
    double calculateF(double truePositive, double falsePositive, double falseNegative);
}
