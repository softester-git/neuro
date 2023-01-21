package at.neuro;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.Arrays;

/**
 * This sample shows how to create, train, save and load simple Perceptron neural network
 */
public class TestFramework {

    public static void debugRun() {

// create new perceptron network
        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
        DataSet trainingSet = new DataSet(2, 1);

        trainingSet.add(new DataSetRow (new double[]{0, 0}, new double[]{0}));
        trainingSet.add(new DataSetRow (new double[]{0, 1}, new double[]{0}));
        trainingSet.add(new DataSetRow (new double[]{1, 0}, new double[]{0}));
        trainingSet.add(new DataSetRow (new double[]{1, 1}, new double[]{1}));

        neuralNetwork.learn(trainingSet);

        neuralNetwork.save("first_perceptron.nnet");

        System.out.println("Testing trained perceptron");

        for (DataSetRow dataRow : trainingSet.getRows()) {
            neuralNetwork.setInput(dataRow.getInput());
            neuralNetwork.calculate();
            double[ ] networkOutput = neuralNetwork.getOutput();
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
        }

        //testNeuralNetwork(neuralNetwork, trainingSet);
//
//        // save trained perceptron
//        myPerceptron.save("mySamplePerceptron.nnet");
//
//        // load saved neural network
//        NeuralNetwork loadedPerceptron = NeuralNetwork.load("mySamplePerceptron.nnet");
//
//        // test loaded neural network
//        System.out.println("Testing loaded perceptron");
//        testNeuralNetwork(loadedPerceptron, trainingSet);
//
    }

//    public static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {
//
//        for(DataSetRow dataRow : testSet.getRows()) {
//            nnet.setInput(dataRow.getInput());
//            nnet.calculate();
//            double[] networkOutput = nnet.getOutput();
//            System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
//            System.out.println(" Output: " + Arrays.toString(networkOutput));
//        }
//
//    }

}