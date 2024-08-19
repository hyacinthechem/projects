import java.util.*;
//import ecs100;
import ecs100.UI;


public class FindingMode {

    private long findModeTime;
    private long findModeStepsTime;
    private long findModeMapTime;

    public static void main(String[] args) {

        FindingMode findingMode = new FindingMode();
        findingMode.setupGUI();

    }

    public void setupGUI() {

        UI.addButton("Enter", this::enter);

    }

    public void enter() {

        List<Integer> numbers = new ArrayList<>(Arrays.asList(23, 22, 49, 25, 43, 23, 5, 31, 43, 27, 21, 45, 43, 16, 5, 21, 18, 27, 39, 18, 21, 7, 42, 28, 21, 19));
        findMode(numbers);

        Collections.sort(numbers);   // o(n log(n)) steps

        findModeSteps(numbers);

        findModeMap(numbers);

        processTimes();
    }

    public int findMode(List<Integer> numbers) {
        long start = System.currentTimeMillis();
        int mode = numbers.get(0);     // 0(1)
        int modeCount = 1;
        for (int value : numbers) {
            int count = 0;
            for (int other : numbers) {      //nested loop to compute values is n x n therefor 0(n^2)
                if (value == other) {
                    count++;
                }
            }
            if (count > modeCount) {

                mode = value;
                modeCount = count;
            }

        }
        UI.println(mode);
        long endTime = System.currentTimeMillis();


        findModeTime = endTime - start;


        return mode;


    }

    public void findModeSteps(List<Integer> numbers) {

        UI.println(numbers);

        long start = System.currentTimeMillis();

        int mode = numbers.get(0);
        int modeCount = 1;
        int count = 1;

        for (int i = 1; i < numbers.size(); i++) {

            if (numbers.get(i).equals(numbers.get(i - 1))) {     // o(n)

                count++;

            } else {

                if (count > modeCount) {

                    mode = numbers.get(i - 1);
                    modeCount = count;

                }

                count = 1;
            }
        }

        if (count > modeCount) {

            mode = numbers.get(numbers.size() - 1);
            modeCount = count;

        }

        UI.println("Mode: " + mode);
        long endTime = System.currentTimeMillis();
        findModeStepsTime = endTime - start;
    }

    public void findModeMap(List<Integer> numbers) {
        long start = System.currentTimeMillis();
        Map<Integer, Integer> numberCounts = new HashMap<>();

        for (int values : numbers) {
            numberCounts.put(values, numberCounts.getOrDefault(values, 0) + 1); //.put() methods are o(1)
        }

        int mode = numbers.get(0);
        int modeCount = 1;

        for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {

            int num = entry.getKey();     // o(n)
            int count = entry.getValue();   // o(n)

            if (count > modeCount) {

                mode = num;   //o(1)
                modeCount = count;  //o(1)
            }
        }

        UI.println("Mode: " + mode);
        long endTime = System.currentTimeMillis();
        findModeMapTime = endTime - start;
    }

    public void processTimes(){

        UI.println("Time taken for n^2 steps:  " + findModeTime);
        UI.println("Time taken for o(nlogn) steps:  " + findModeStepsTime);
        UI.println("Time taken for o(n) steps:  " + findModeMapTime);

    }

}

