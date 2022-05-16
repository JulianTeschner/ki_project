package layer;

public interface Layer {
    public void forwardPass(double[] in);

    public void backwardPass();

}
