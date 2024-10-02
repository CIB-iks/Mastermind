import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class VarStateManager {
    //HashMap Current guess
   private static HashMap<Integer, Object> currentGuess = new HashMap<>();;

    //Hash Map all Guesses
    public static HashMap<Integer, HashMap<Integer,Object>> mapOfAllGuesses = new HashMap<>();

    //HashMap Final Answer
    static HashMap<Integer, Object> finalAnswerKey = new HashMap<>();;

    //Hashmap Current Answer Indicator
    private static ArrayList<String> currentAnswerIndicator = new ArrayList<>();;

    //Hashmap all Answer Indicators
    public static HashMap<Integer, ArrayList<String>> mapOfAllAnswers = new HashMap<>();;

    //Game Won List
    static final List<String> gameWon = Arrays.asList("Black", "Black", "Black", "Black");

    //Current Round
    static int currentRound = 1;

    //Total Number of Rounds
    static int numberOfRounds = 4;



    public static HashMap<Integer, Object> getFinalAnswerKey() {
        return finalAnswerKey;
    }

    public static List<String> getGameWon() {
        return gameWon;
    }

    public static HashMap<Integer, Object> getCurrentGuess() {
        return currentGuess;
    }

    public static HashMap<Integer, HashMap<Integer, Object>> getMapOfAllGuesses() {
        return mapOfAllGuesses;
    }

    public static int getCurrentRound() {
        return currentRound;
    }

    public static int getNumberOfRounds() {
        return numberOfRounds;
    }

    public static ArrayList<String> getCurrentAnswerIndicator() {
        return currentAnswerIndicator;
    }

    public static HashMap<Integer, ArrayList<String>> getMapOfAllAnswers() {
        return mapOfAllAnswers;
    }




    public static void setFinalAnswerKey(HashMap<Integer, Object> finalAnswerKey) {
        VarStateManager.finalAnswerKey = finalAnswerKey;
    }

    public static void setCurrentGuess(HashMap<Integer, Object> currentGuess) {
        VarStateManager.currentGuess = currentGuess;
    }

    public static void setCurrentRound(int currentRound) {
        VarStateManager.currentRound = currentRound;
    }

    public static void setNumberOfRounds(int numberOfRounds) {
        VarStateManager.numberOfRounds = numberOfRounds;
    }

    public static void setCurrentAnswerIndicator(ArrayList<String> currentAnswerIndicator) {
        VarStateManager.currentAnswerIndicator = currentAnswerIndicator;
    }
}
