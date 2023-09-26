import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        // Define game settings
        int minNumber = 1;
        int maxNumber = 100;
        int maxAttempts = 10;
        int score = 0;

        // Create a random number generator
        Random random = new Random();
        int targetNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;

        JOptionPane.showMessageDialog(null, "Welcome to Guess the Number Game!\nTry to guess the number between " + minNumber + " and " + maxNumber);

        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            String guessStr = JOptionPane.showInputDialog("Attempt " + attempts + "/" + maxAttempts + "\nEnter your guess:");
            int guess = Integer.parseInt(guessStr);

            if (guess == targetNumber) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number in " + attempts + " attempts.");
                score = maxAttempts - attempts + 1;
                break;
            } else if (guess < targetNumber) {
                JOptionPane.showMessageDialog(null, "Try a higher number.");
            } else {
                JOptionPane.showMessageDialog(null, "Try a lower number.");
            }

            if (attempts == maxAttempts) {
                JOptionPane.showMessageDialog(null, "Sorry, you've used all your attempts. The correct number was " + targetNumber);
            }
        }

        JOptionPane.showMessageDialog(null, "Game Over\nYour Score: " + score);
    }
}