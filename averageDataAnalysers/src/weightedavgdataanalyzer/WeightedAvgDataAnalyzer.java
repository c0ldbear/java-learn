
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

        while (true){
            try{
                System.out.print("Please enter the file name: ");
                String filename = in.next();

                ArrayList<Double> data = readFile(filename);
                System.out.println("Data input (from file '" + filename+ "'): " + data);

                // The code below might need to be modified so that it prints the correct text.
                System.out.println("Weighted average: " + CalculateWeightedAverage(data));

                break;
            } catch (FileNotFoundException exception) {
                System.out.println("File not found.");
            } catch (NoSuchElementException exception) {
                System.out.println("File contents invalid.");
            } catch (IOException exception) {
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
        } catch (Exception e) {
           System.out.println("ERROR: Something went wrong.");
           System.out.println("\tError message: " + e.getMessage());
        }

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

    private static ArrayList<Double> sortArrayList(ArrayList<Double> data) {
        ArrayList<Double> sortedData = new ArrayList<>();
        ArrayList<Double> searchData = new ArrayList<>(data);
        double minValue = -100000000;
        double value = 0;
        int valueIndex = 0;

        // need to find the smallest values in our list, add to new list, and remove from our search list.
        while (!searchData.isEmpty()) {
            value = searchData.get(0);
            valueIndex = 0;
            for (int i = 0; i < searchData.size(); i++) {
                if (value > searchData.get(i)) {
                    value = searchData.get(i);
                    valueIndex = i;
                }
            }
            System.out.println(value);
            System.out.println(valueIndex);
            sortedData.add(value);
            searchData.remove(valueIndex);
        }

        System.out.println(sortedData);
        return sortedData;
    }

    public static double CalculateWeightedAverage(ArrayList<Double> data) {
        double weightedAverage = 0.;
        int numberOfElementsToDrop = getNumberOfElementsToDrop(data);

        // Selecting the number of elements that is relevant for our sake: i.e. removing element 0 and 1 which are
        // 'weight' and 'numberOfElementsToDrop'
        ArrayList<Double> selectedDataForAverageCalculations = new ArrayList<Double>(data.subList(2, data.size()));
        //System.out.println("My own sorting: " + sortArrayList(selectedDataForAverageCalculations));
        //Collections.sort(selectedDataForAverageCalculations);
        //System.out.println("Collection.sort(): " + selectedDataForAverageCalculations);
        ArrayList<Double> sortedSelectedDataForAverageCalculations = sortArrayList(selectedDataForAverageCalculations);

        // Use the "dropped out" elements by jumping "ahead" of in the ArrayList with the number 'numberOfElementsToDrop'
        for (int i = numberOfElementsToDrop; i < sortedSelectedDataForAverageCalculations.size(); i++) {
            weightedAverage += sortedSelectedDataForAverageCalculations.get(i);
        }

        // this part here is calculating how many elements that is handled in the for-loop without an extra variable.
        // We could have added a variable 'count' to get how many elements where added to 'weightedAverage'
        weightedAverage /= (sortedSelectedDataForAverageCalculations.size() - numberOfElementsToDrop);

        // Lastly, we need to multiply with the weight so we get the weighted average.
        return weightedAverage * getWeight(data);
    }
}