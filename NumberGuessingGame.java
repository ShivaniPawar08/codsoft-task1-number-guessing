
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int highScore = 0;

        boolean playAgain = true;

        System.out.println(" Welcome to the Advanced Number Guessing Game!");

        while (playAgain) {

            
            int maxNumber = 100;
            int maxAttempts = 7;

            System.out.println("\nChoose Difficulty Level:");
            System.out.println("1. Easy   (1–50, 10 attempts)");
            System.out.println("2. Medium (1–100, 7 attempts)");
            System.out.println("3. Hard   (1–500, 5 attempts)");

            int choice;
            while (true) {
                System.out.print("Enter choice (1/2/3): ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice == 1 || choice == 2 || choice == 3) {
                        break;
                    }
                } else {
                    sc.next();
                }
                System.out.println("Invalid choice!");
            }

            if (choice == 1) {
                maxNumber = 50;
                maxAttempts = 10;
            } else if (choice == 2) {
                maxNumber = 100;
                maxAttempts = 7;
            } else {
                maxNumber = 500;
                maxAttempts = 5;
            }

            int numberToGuess = random.nextInt(maxNumber) + 1;

            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have chosen a number between 1 and " + maxNumber);
            System.out.println("You have " + maxAttempts + " attempts.");

            
            while (!guessedCorrectly && attemptsUsed < maxAttempts) {

                System.out.print("\nEnter your guess: ");

                if (!sc.hasNextInt()) {
                    System.out.println(" Enter a valid number!");
                    sc.next();
                    continue;
                }

                int guess = sc.nextInt();

                if (guess < 1 || guess > maxNumber) {
                    System.out.println("Guess must be between 1 and " + maxNumber);
                    continue;
                }

                attemptsUsed++;

                if (guess == numberToGuess) {

                    System.out.println(" Correct! You guessed it in " + attemptsUsed + " attempts.");

                    int roundScore = (maxAttempts - attemptsUsed + 1) * 10;
                    totalScore += roundScore;

                    System.out.println(" Round Score: " + roundScore);
                    System.out.println(" Total Score: " + totalScore);

                    if (roundScore > highScore) {
                        highScore = roundScore;
                    }

                    guessedCorrectly = true;
                } 
                else if (guess > numberToGuess) {
                    System.out.println("⬆ Too high!");
                } 
                else {
                    System.out.println("⬇ Too low!");
                }

                if (!guessedCorrectly && attemptsUsed == 2) {
                    if (numberToGuess % 2 == 0)
                        System.out.println(" Hint: The number is EVEN.");
                    else
                        System.out.println(" Hint: The number is ODD.");
                }

                if (!guessedCorrectly && attemptsUsed == 4) {
                    int low = Math.max(1, numberToGuess - 10);
                    int high = Math.min(maxNumber, numberToGuess + 10);
                    System.out.println(" Hint: The number is between " + low + " and " + high);
                }

                System.out.println("Attempts left: " + (maxAttempts - attemptsUsed));
            }

            if (!guessedCorrectly) {
                System.out.println("\n Out of attempts!");
                System.out.println("The correct number was: " + numberToGuess);
            }

    
            System.out.println("\n Current High Score: " + highScore);

        
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String again = sc.next();

            if (!again.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\n Game Over!");
        System.out.println("Final Score: " + totalScore);
        System.out.println("Highest Single-Round Score: " + highScore);

        sc.close();
    }
}
