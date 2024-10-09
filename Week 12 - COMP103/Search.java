
import ecs100.UI;
import java.util.*;
import java.util.Arrays;


public class Search {

    List<String> words;
    List<Integer> numbers;

public void setupGUI(){
    UI.addButton("Binary Search Number", this::searchNumber);
    UI.addButton("Binary Search Strings", this::searchStrings);


}

public void searchNumber(){

    UI.println(numbers);
    int number = UI.askInt("What number are you looking for?");
    Collections.sort(numbers); //what makes the binary search work //numbers are ordered
    int num = recursiveSearch(number,numbers);
    UI.println(num);



}

public int recursiveSearch(int index,List<Integer> numbers){
    return recursiveSearch(index, 0, numbers.size(), numbers);
}

public int recursiveSearch(int index, int start, int end, List<Integer> numbers){
    if(start>end){
        return -1; //base case; finding number failed
    }


    int mid = ( start + end ) / 2;  //position in array
    int midIndex = numbers.get(mid); //actual number in position

    if(midIndex==index){
        return midIndex; //found the number
    }else if(index<midIndex){
        return recursiveSearch(index, start, mid-1, numbers);
    }else{
        return recursiveSearch(index, mid+1, end, numbers);
    }

}

public void searchStrings(){

    UI.println(words);
    Collections.sort(words);
    String word = UI.askString("What word do you want to search?");
    String foundWord = recursiveWordSearch(word,words);
    UI.println(foundWord);

}

public String recursiveWordSearch(String word,List<String> words){
    return recursiveWordSearch(word,0,words.size(),words);
}

public String recursiveWordSearch(String word, int start, int end, List<String> words){

    if(start>end){
        return "word not found"; // failed base case return error
    }

    int mid = (start + end)/2;
    String midWord = words.get(mid);

    String startWord = words.get(start);

    if(startWord.equals(midWord)){
        return midWord; //found the target word, return indicating success
    }else if(word.compareTo(midWord)<0){
          return recursiveWordSearch(word,start,mid-1,words);
    }else{
        return recursiveWordSearch(word,mid+1,end,words);
    }

}

public void loadData(){

    int[] numberItem = {10,39,94,8200,5490,23,45,982,12,98,34,90,1004,1205,2021};

    String[] wordItem = {
            "Apple", "Breeze", "Mountain", "Car", "Elephant", "Rain", "Joy", "Dream", "Candle", "Flower",
            "Ocean", "Sand", "Whisper", "Balloon", "Castle", "Guitar", "River", "Thunder", "Star", "Forest",
            "Shadow", "Butterfly", "Lemon", "Clock", "Window", "Sky", "Path", "Cloud", "Stone", "Flame",
            "Silver", "Ladder", "Pond", "Branch", "Drift", "Stream", "Sun", "Tree", "Mirror", "Glass",
            "Leaf", "Book", "Moon", "Light", "Fire", "Bridge", "Fence", "Garden", "Chair", "Table",
            "Hill", "Wave", "Shell", "Feather", "Sunrise", "Dusk", "Key", "Door", "Basket", "Field",
            "Meadow", "Nest", "Seed", "Bird", "Wind", "Snow", "Fog", "Grass", "Storm", "Night",
            "Tower", "Doorway", "Bench", "Island", "Glove", "Map", "Sail", "Pebble", "Vine", "Forest",
            "Rose", "Horse", "Valley", "Branch", "Cave", "Stone", "Shore", "Echo", "Desert", "Trail",
            "Mountain", "Sunset", "Breeze", "Whisper", "Rock", "River", "Stream", "Cottage", "Wood", "Cloud"
    };

    numbers = new ArrayList<Integer>();

    for(int num : numberItem){
        numbers.add(num);
    }

    words = Arrays.asList(wordItem);

}





    public static void main(String[] args){
    Search s = new Search();
    s.loadData();
     s.setupGUI();
    }
}
