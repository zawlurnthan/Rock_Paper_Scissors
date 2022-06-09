import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void printBanner() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("=== Welcome from Rock, Paper, Scissors Game! ===");
        System.out.println("-------------------------------------------------\n");
    }

    public static int getRoundNumber(){
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int round = 0;

        do {
            System.out.println("Enter the number of round you like to play:");

            try {
                String input = scanner.nextLine();
                round = Integer.parseInt(input);
                isValid = true;
            } catch(NumberFormatException | InputMismatchException e){
                System.out.println("\nInput number is invalid!");
            }
        } while(!isValid);

        return round;
    }

    public static String getChoice() {
        boolean isValid = false;
        String choice = "";
        int num = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nEnter the choice from "
                    + "rock -> 1, "
                    + "paper - 2, "
                    + "scissors -> 3:");

            try {
                String input = scanner.nextLine();
                num = Integer.parseInt(input);
                isValid = true;
            } catch(NumberFormatException | InputMismatchException e){
                System.out.println("\nInput choice is invalid!");
            }

            switch (num) {
                case 1 -> choice = "rock";
                case 2 -> choice = "paper";
                case 3 -> choice = "scissors";
                default -> System.out.println("Invalid input!");
            }
        } while (!isValid);

        return choice;
    }

    public static String getRandomChoice() {
        Random rand = new Random();
        String[] choices = {"rock", "paper", "scissors"};
        int index = rand.nextInt(3);
        return choices[index];
    }

    public static int getWinner(String choice, String generate) {
        int score = 0;
        if (choice.equalsIgnoreCase("paper")&& generate.equals("rock")){
            System.out.println("Paper wraps Rock is win!");
            score++;
        }

        else if (choice.equalsIgnoreCase("scissors")&& generate.equals("paper")){
            System.out.println("Scissors cut Paper is win!");
            score++;
        }

        else if (choice.equalsIgnoreCase("rock") && generate.equals("scissors")){
            System.out.println("Rock breaks Scissors is win!");
            score++;
        }
        else if (choice.equalsIgnoreCase(generate)){
            System.out.println("Same weapons! It's tie!");
        }
        else {
            System.out.printf("%s can't beat %s. You lose!%n", choice, generate);
            score--;
        }
        return score;
    }

    public static void getResult(int score) {
        if (score == 0) {
            System.out.printf("\nThere is no winner. Your score is %d!", score);
        }
        else if (score > 0) {
            System.out.printf("\nYou win with score %d!", score);
        }
        else {
            System.out.printf("\nYou lose with score %d!", score);
        }
    }

    public static boolean continueOrEnd(){
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        String input = "";

        while (!isValid){
            System.out.println("\n\nStill want to play? Enter true or false");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                isValid = true;
            }
            else {
                System.out.println("Invalid input!");
            }
        }

        return input.equalsIgnoreCase("y");
    }

    public static void main(String[] args) {
        printBanner();
        int score = 0;
        boolean keepGoing;

        do {
            // get the round number
            int round = getRoundNumber();
            int count = 1;
            // count the game by round number
            while (round >= count) {
                // get user choice
                String choice = getChoice();
                // generate random choices
                String generate = getRandomChoice();
                // printing info
                System.out.println("\nRound   : " + count);
                System.out.println("Choice  : " + choice);
                System.out.println("Generate: " + generate);
                // keep the scores
                score += getWinner(choice, generate);
                System.out.println("Score: " + score);
                // count number of games
                count++;
            }
            // print the winner
            getResult(score);
            // get info to continue or not
            keepGoing = continueOrEnd();
            // reset score to zero
            score = 0;

        } while (keepGoing);

        System.out.println("\nThanks for playing!");
    }
}
