import ecs100.UI;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;



public class SortingAlgorithm {


    private double[] numbers = new double[12];
    private Set<Double> numberSet = new HashSet<Double>();
    private int num;



    public void setupGUI() {
        UI.addButton("Choose Sorting Algorithm", this::selection);
        UI.addButton("Merge Sort", this::mergeSort);
        UI.addButton("Quick Sort", this::quickSort);
        UI.addButton("Insertion Sort", this::insertionSort);
    }

    public double[] getNumbers() {

         int size = 12;
         double[] ans = new double[size];
         Random rand = new Random();
        int randomInt = rand.nextInt();

        for(int i = 0; i<size; i++){
           ans[i] = rand.nextInt(100);
        }

        UI.println(ans);

        return ans;


    }


    public void selection() {



    }

    public void mergeSort() {


      for(int i = 0; i<numberSet.size(); i++){




      }

    }


    public void insertionSort() {
       long start = System.currentTimeMillis();

         double[] insertionArray = getNumbers();


        for(int i = 1 ; i < insertionArray.length; i++){

            double key = insertionArray[i];
            int j = i - 1;
            while(j >= 0 && key < insertionArray[j]){

                insertionArray[j+1] = insertionArray[j];
                j = j - 1;
            }
              insertionArray[j+1] = key;
        }
        long end = System.currentTimeMillis();
        UI.println(insertionArray);

        long elapsed = end - start;
        UI.println(elapsed);
    }


    public void quickSort() {


    }


    public static void main(String[] args) {

        SortingAlgorithm sortingAlgorithm = new SortingAlgorithm();
        sortingAlgorithm.setupGUI();
        sortingAlgorithm.getNumbers();

    }
}

