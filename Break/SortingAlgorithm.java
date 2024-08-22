import ecs100.UI;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithm {

    private double[] numbers;

    public void setupGUI() {
        UI.addButton("Merge Sort", this::mergeSort);
        UI.addButton("Quick Sort", this::quickSort);
        UI.addButton("Insertion Sort", this::insertionSort);
    }

    public double[] getNumbers() {
        int size = 12;
        double[] ans = new double[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            ans[i] = rand.nextInt(100); // Generate whole numbers between 0 and 99
        }

        UI.println("Generated numbers: " + Arrays.toString(ans));
        numbers = ans;
        return ans;
    }

    public void mergeSort() {
        if (numbers == null) {
            getNumbers(); // Initialize numbers if not already set
        }
        mergeSort(numbers);
        UI.println("Sorted array: " + Arrays.toString(numbers));
    }

    private void mergeSort(double[] numbers) {
        if (numbers.length < 2) {
            return; // Base case: Array is already sorted
        }

        int half = numbers.length / 2;
        double[] left = Arrays.copyOfRange(numbers, 0, half);
        double[] right = Arrays.copyOfRange(numbers, half, numbers.length);

        mergeSort(left);
        mergeSort(right);

        mergeBack(numbers, left, right);
    }

    private void mergeBack(double[] numbers, double[] left, double[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int numberIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                numbers[numberIndex++] = left[leftIndex++];
            } else {
                numbers[numberIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            numbers[numberIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            numbers[numberIndex++] = right[rightIndex++];
        }
    }

    public void insertionSort() {
        long start = System.currentTimeMillis();

        if (numbers == null) {
            getNumbers(); // Initialize numbers if not already set
        }
        double[] insertionArray = numbers.clone(); // Work with a copy to avoid modifying original

        for (int i = 1; i < insertionArray.length; i++) {
            double key = insertionArray[i];
            int j = i - 1;
            while (j >= 0 && key < insertionArray[j]) {
                insertionArray[j + 1] = insertionArray[j];
                j = j - 1;
            }
            insertionArray[j + 1] = key;
        }

        long end = System.currentTimeMillis();
        UI.println("Sorted array: " + Arrays.toString(insertionArray));

        long elapsed = end - start;
        UI.println("Time elapsed: " + elapsed + " ms");
    }

    public void quickSort() {
        // Placeholder for quick sort implementation
        // You can implement quick sort similarly and add functionality to use 'numbers'
    }

    public static void main(String[] args) {
        SortingAlgorithm sortingAlgorithm = new SortingAlgorithm();
        sortingAlgorithm.setupGUI();
        sortingAlgorithm.getNumbers();
    }
}


