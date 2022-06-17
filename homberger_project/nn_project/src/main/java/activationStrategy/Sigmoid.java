package activationStrategy;

public class Sigmoid implements ActivationStrategy {

    @Override
    public double calcActivation(double x) {
        return (1.0 / (1.0 + Math.pow(Math.E, -x)));
    }

    @Override
    public double calcDerivedActivation(double x) {
        return calcActivation(x) * (1.0 - calcActivation(x));
    }


}
