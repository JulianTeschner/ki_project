package activationStrategy;

public class Sigmoid implements ActivationStrategy {

    @Override
    public double calcActivation(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    @Override
    public double calcDerivedActivation(double x) {
        return calcActivation(x) * (1 - calcActivation(x));
    }


}
