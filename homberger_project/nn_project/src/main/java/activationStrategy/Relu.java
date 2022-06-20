package activationStrategy;

public class Relu implements ActivationStrategy{

    @Override
    public double calcActivation(double x) {
        return Math.max(0.0001, x);
    }

    @Override
    public double calcDerivedActivation(double x) {
        if (x > 0) {
            return 1;
        } else {
            return 0.0001;
        }
    }
}
