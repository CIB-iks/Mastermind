import java.util.*;

public class Main {

    public static void main(String[] args) {

        Runner.startMastermind();
    }
}

class Runner {

    public static void startMastermind() {

        System.out.println("""
                Welcome to MasterMind.
                In how many rounds do you want to try and guess the answer?
                Remember: It must be an even number.""");

        try {
            Scanner in = new Scanner(System.in);
            VarStateManager.setNumberOfRounds(in.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("This is not a number buddy. But fine, be this way. I'll give you 4 tries. Your loss.");
        }

        if (checkIfEven(VarStateManager.numberOfRounds)) {
            System.out.println("You'll have " + VarStateManager.numberOfRounds + " guesses.\n" +
                    "I have thought of a Code. Take your guess number " + VarStateManager.getCurrentRound() + "\n");
            AnswerKey.createAnswerHashMap();
        } else {
            System.out.println("That's not an even number. Let's start over.\n");
            startMastermind();
        }
        roundRunner();
    }

    public static void roundRunner() {
        Stones.createStoneObjects();
        AnswerChecker.returnAnswerIndicators();
        Printer.printRound();
        VarStateManager.setCurrentRound(VarStateManager.getCurrentRound() + 1);
        checkIfstillRunning();
    }

    public static void checkIfstillRunning(){

        if (VarStateManager.getCurrentAnswerIndicator().equals(VarStateManager.getGameWon())) {
            System.out.println("Congrats! You won! It took you " + (VarStateManager.getCurrentRound()-1) + " guesses. Let's play again.\n");
            startMastermind();
        }

        if (VarStateManager.getCurrentRound() <= VarStateManager.getNumberOfRounds()) {
            System.out.println("\nNext try! Round: " + VarStateManager.getCurrentRound() + "\n");
            roundRunner();
        }
        else {
            System.out.println("You lost. Let's play another round!.\n");
            startMastermind();
        }
    }

    public static boolean checkIfEven(int numberOfRounds){
        return numberOfRounds % 2 == 0;
    }
}

class Stones {

    public enum StoneColor {
        RED, BLUE, GREEN, YELLOW, PINK, BROWN
    }

    public static void createStoneObjects() {

        HashMap<Integer, Object> guess = new HashMap<>();

        while (guess.size() < 4) {
            System.out.println("""
                    You can choose from the following colours:
                    Red, Green, Blue, Yellow, Pink and Brown.
                    Colours can repeat.""");

            for (int i = 1; i < 5; i++) {
                System.out.println("Input stone " + i + ": ");

                Scanner in = new Scanner(System.in);
                String guessing = in.nextLine().toLowerCase();

                switch (guessing) {
                    case "red":
                        guess.put(i, StoneColor.RED);
                        break;
                    case "blue":
                        guess.put(i, StoneColor.BLUE);
                        break;
                    case "green":
                        guess.put(i, StoneColor.GREEN);
                        break;
                    case "yellow":
                        guess.put(i, StoneColor.YELLOW);
                        break;
                    case "pink":
                        guess.put(i, StoneColor.PINK);
                        break;
                    case "brown":
                        guess.put(i, StoneColor.BROWN);
                        break;
                    default:
                        System.out.println("This is not an available color. Let's start over.\n");
                        guess.clear();
                }
                if (guess.isEmpty()) break;
            }
        }
        VarStateManager.setCurrentGuess(guess);
        VarStateManager.mapOfAllGuesses.put(VarStateManager.getCurrentRound(), guess);
    }
}

    class Printer{

    public static void printRound(){

            for (int i = 1; i <= VarStateManager.getCurrentRound(); i++) {
                System.out.printf("| Round %-2s | %-8s |  %-8s |  %-8s |  %-8s ||| %s\n", i, VarStateManager.getMapOfAllGuesses().get(i).get(1), VarStateManager.getMapOfAllGuesses().get(i).get(2),
                        VarStateManager.getMapOfAllGuesses().get(i).get(3), VarStateManager.getMapOfAllGuesses().get(i).get(4), VarStateManager.getMapOfAllAnswers().get(i));
            }
    }
}

class AnswerChecker {

    public static void returnAnswerIndicators(){

        ArrayList<String> returnAnswer = new ArrayList<>(){};

        HashMap<Integer, Object> answerClone = new HashMap<>(VarStateManager.getFinalAnswerKey());
        HashMap<Integer, Object> guessClone =  new HashMap<>(VarStateManager.getCurrentGuess());

        for (int i= 1; i<5; i++){
            if (guessClone.get(i).equals(answerClone.get(i))){
                returnAnswer.add("Black");
                answerClone.remove(i);
                guessClone.remove(i);
            }
        }

        for (int i = 1; i<5; i++) {
            if (guessClone.containsKey(i)) {
                if (answerClone.containsValue(guessClone.get(i))) {
                    returnAnswer.add("White");
                    Integer answerKey = getKeyByValue(answerClone, guessClone.get(i));
                    answerClone.remove(answerKey);
                }
            }
        }
        VarStateManager.setCurrentAnswerIndicator(returnAnswer);
        VarStateManager.mapOfAllAnswers.put(VarStateManager.getCurrentRound(), returnAnswer);

    }

    public static Integer getKeyByValue(HashMap<Integer, Object> map, Object value) {
        for (HashMap.Entry<Integer, Object> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}

class AnswerKey {
    public static void createAnswerHashMap() {

        HashMap<Integer, Object> answer = new HashMap<>();
        Random rand = new Random();

        for (int i = 1; i < 5; i++) {
            int newAnswer = rand.nextInt(6);

            switch (newAnswer) {
                case 0: answer.put(i, Stones.StoneColor.RED); break;
                case 1: answer.put(i, Stones.StoneColor.BLUE); break;
                case 2: answer.put(i, Stones.StoneColor.GREEN); break;
                case 3: answer.put(i, Stones.StoneColor.YELLOW); break;
                case 4: answer.put(i, Stones.StoneColor.PINK); break;
                case 5: answer.put(i, Stones.StoneColor.BROWN); break;
                default: System.out.println("If you see this message, something went wrong in creating the random answer key");
            }
        }
        VarStateManager.setFinalAnswerKey(answer);
    }
}
