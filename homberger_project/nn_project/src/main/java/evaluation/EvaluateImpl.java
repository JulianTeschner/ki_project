package evaluation;

import layer.Layer;

public class EvaluateImpl implements Evaluate {
    public double[] y;
    public Layer outputLayer;
    public int epoch;

    public EvaluateImpl(double[] y, Layer outputLayer) {
       this.y = y;
       this.outputLayer = outputLayer;
       epoch = 0;
    }

    public void evaluate() {
        double trueNegative = 0;
        double falsePositive = 0;
        double falseNegative = 0;
        double truePositive = 0;
        for (int i = 0; i < y.length; i++) {

            if (y[i] == 1) {
                if (outputLayer.getOut()[i] >= 0.5) {
                    truePositive++;
                } else {
                    falseNegative++;
                }
            } else {
                if (outputLayer.getOut()[i] <= 0.5) {
                    trueNegative++;
                } else {
                    falsePositive++;
                }
            }
            epoch++;
            System.out.printf("Epoch: %d\n\t1\t0\n1\t%.0f\t%.0f\n0\t%.0f\t%.0f\n", epoch, truePositive, falseNegative, falsePositive, trueNegative);
            System.out.printf("Precision: %f \n", calculatePrecision(truePositive, falsePositive));
            System.out.printf("Recall: %f \n", calculateRecall(truePositive, falseNegative));
            System.out.printf("F-Value: %f \n", calculateF(truePositive, falsePositive, falseNegative));
        }
    }

    public double calculatePrecision(double truePositive, double falsePositive) {
        return truePositive/(truePositive+falsePositive);
    }
    public double calculateRecall(double truePositive, double falseNegative) {
        return truePositive/(truePositive+falseNegative);
    }
    public double calculateF(double truePositive, double falsePositive, double falseNegative) {
        double precision = calculatePrecision(truePositive, falsePositive);
        double recall= calculateRecall(truePositive, falseNegative);
        return (2*precision*recall)/(precision + recall);
    }
}
