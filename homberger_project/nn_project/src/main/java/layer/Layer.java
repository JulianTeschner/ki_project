package layer;

import activationStrategy.ActivationStrategy;

public interface Layer {
    default public void initRandomWeights(double[][] weights) {
        for (int i = 0; i < weights.length; i++) {
            weights[i][0] = 1;
            for (int j = 0; j < weights[0].length; j++) {
//                weights[i][j] = Math.random()*2-1;
                weights[i][j] = 1;
            }
        }
    }


    public ActivationStrategy getG();

    public double[] getOut();

    public double[] getDelta();

    public double[][] getWeights();

    public int getOffset();


    public void setG(ActivationStrategy g);

    public void setOut(double[] out);

    public void setDelta(double[] delta);

    public void setWeights(double[][] weights);

    public void setOffset(int offset);

    public void calcDelta();

}
