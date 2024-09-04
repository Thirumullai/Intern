import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int min = 1;
        int max = 100;
        int maxAttempts = 5;
        int rounds = 0;
        int score = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            rounds++;
            int numberToGuess = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean isGuessed = false;

            System.out.println("\nRound " + rounds + ": Guess a number between " + min + " and " + max);

            while (attempts < maxAttempts && !isGuessed) {
                attempts++;
                System.out.print("Attempt " + attempts + ": Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    isGuessed = true;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!isGuessed) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\nGame over! Your final score is " + score + " after " + rounds + " round(s).");
        scanner.close();
    }
}
