
package weightedavgdataanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.*;

public class WeightedAvgDataAnalyzer {

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);



        boolean done = false;
        while (!done){
            try{
                System.out.print("Please enter the file name: ");
                String filename = in.next();
                //System.out.println();

                ArrayList<Double> data = readFile(filename);

                // The code below might need to be modified so that it prints the correct text.
                System.out.println("Weighted average: " + CalculateWeightedAverage(data));

                done = true;
            }

            catch (FileNotFoundException exception){
                System.out.println("File not found.");
                return;
            }

            catch (NoSuchElementException exception){
                System.out.println("File contents invalid.");
            }

            catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public static ArrayList<Double> readFile(String filename) throws IOException{
        File inFile = new File(filename);
        try(Scanner in = new Scanner(inFile)) {
            return readData(in);
        }
    }

    public static ArrayList<Double> readData(Scanner in) throws IOException{
        String[] nameOfStringArray;
        ArrayList<Double> data = new ArrayList<>();

        // grab all the data from the file
        try {
            if (in.hasNextLine()) {
                nameOfStringArray = in.nextLine().split(" ");
                for (String stringElement : nameOfStringArray) {
                    data.add(Double.parseDouble(stringElement));
                }
            }
            // No handling if something goes wrong - so we might need add some handling here.
        } catch (Exception e) {}

        return data;
    }

    private static double getWeight(ArrayList<Double> data) {
        // Get the 'weight' value from the input 'data'
        return data.get(0);
    }

    private static int getNumberOfElementsToDrop(ArrayList<Double> data) {
        // Get the 'numberOfElementsToDrop' from the input 'data' which we know is the second element in the file.
        return data.get(1).intValue();
    }

    public static double CalculateWeightedAverage(ArrayList<Double> data) {
        double weightedAverage = 0.;
        int numberOfElementsToDrop = getNumberOfElementsToDrop(data);

        // Selecting the number of elements that is relevant for our sake: i.e. removing element 0 and 1 which are
        // 'weight' and 'numberOfElementsToDrop'
        ArrayList<Double> selectedDataForAverageCalculations = new ArrayList<Double>(data.subList(2, data.size()));
        Collections.sort(selectedDataForAverageCalculations);

        for (int i = numberOfElementsToDrop; i < selectedDataForAverageCalculations.size(); i++) {
            weightedAverage += selectedDataForAverageCalculations.get(i);
        }

        // this part here is calculating how many elements that is handled in the for-loop without an extra variable.
        // We could have added a variable 'count' to get how many elements where added to 'weightedAverage'
        weightedAverage /= (selectedDataForAverageCalculations.size() - numberOfElementsToDrop);

        // Lastly, we need to multiply with the weight so we get the weighted average.
        return weightedAverage * getWeight(data);
    }
}