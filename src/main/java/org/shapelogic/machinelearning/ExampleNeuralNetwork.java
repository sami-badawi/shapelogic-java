package org.shapelogic.machinelearning;

/** ExampleNeuralNetwork for use in default neural network.<br />
 *
 * @author Sami Badawi
 */
public class ExampleNeuralNetwork {

    public static double[][] makeSmallerThanNeuralNetwork(double limit) {
        return new double[][] {
            {
                limit - 0.5, //Bias first hidden layer

                -1.
            }
        };
    }

    public static double[][] makeGreaterThanNeuralNetwork(double limit) {
        return new double[][] {
            {
                -limit + 0.5, //Bias first hidden layer

                1.
            }
        };
    }

}
