import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReverseWords {

    private static final Logger logger = Logger.getLogger(ReverseWords.class.getName());

    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        // Test cases
        testReverseWord();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            logger.info("Processing file...");
            String line;
            while ((line = reader.readLine()) != null) {
                String reversedLine = reverseWords(line);
                writer.write(reversedLine);
                writer.newLine();
            }
            logger.info("File processed successfully.");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File processing failed.", e);
        }
    }

    public static String reverseWords(String line) {
        String[] words = line.split(" ");
        StringBuilder reversedLine = new StringBuilder();

        for (String word : words) {
            String reversedWord = reverseWord(word);
            reversedLine.append(reversedWord).append(" ");
        }

        return reversedLine.toString().trim();
    }

    public static String reverseWord(String word) {
        StringBuilder letters = new StringBuilder();
        StringBuilder punctuation = new StringBuilder();

        // Separate letters and punctuation
        for (char c : word.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                letters.append(c);
            } else {
                punctuation.append(c);
            }
        }

        // Reverse the letters
        letters.reverse();

        // Append the punctuation to the end of the reversed letters
        return letters.append(punctuation).toString();
    }

    // Simple test cases for reverseWord method
    public static void testReverseWord() {
        logger.info("Running test cases...");
        assert reverseWord("Yogesh.").equals("hsegoY.") : "Test case 1 failed";
        assert reverseWord("Great!").equals("taerG!") : "Test case 2 failed";
        assert reverseWord("Hello,").equals("olleH,") : "Test case 3 failed";
        assert reverseWord("World!").equals("dlroW!") : "Test case 4 failed";
        assert reverseWord("this").equals("siht") : "Test case 5 failed";
        logger.info("All test cases passed.");
    }
}
