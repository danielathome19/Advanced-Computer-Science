package Q3;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Prog570aRegEx {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog570a.dat"));

            // Arrays to track common lowercase and uppercase characters
            var commonLower = new boolean[26];
            var commonUpper = new boolean[26];
            boolean isFirstLine = true; // For initializing the common sets for the first line

            // Regular expressions for lowercase, uppercase, and non-alphabetic characters
            var lowerCasePattern = Pattern.compile("[a-z]");
            var upperCasePattern = Pattern.compile("[A-Z]");
            var nonAlphaPattern = Pattern.compile("[^a-zA-Z\\s]"); // Excludes whitespace and letters

            // Loop through each line of the file
            while (file.hasNextLine()) {
                String line = file.nextLine();
                System.out.println("Line: " + line);

                // Arrays to track the current line's lowercase and uppercase letters
                var lowerCaseSet = new boolean[26];
                var upperCaseSet = new boolean[26];
                StringBuilder nonAlphaSet = new StringBuilder();

                // Match lowercase letters
                var lowerCaseMatcher = lowerCasePattern.matcher(line);
                while (lowerCaseMatcher.find()) {
                    char lowerChar = lowerCaseMatcher.group().charAt(0);
                    lowerCaseSet[lowerChar - 'a'] = true;
                }

                // Match uppercase letters
                var upperCaseMatcher = upperCasePattern.matcher(line);
                while (upperCaseMatcher.find()) {
                    char upperChar = upperCaseMatcher.group().charAt(0);
                    upperCaseSet[upperChar - 'A'] = true;
                }

                // Match non-alphabetic characters
                var nonAlphaMatcher = nonAlphaPattern.matcher(line);
                while (nonAlphaMatcher.find()) {
                    nonAlphaSet.append(nonAlphaMatcher.group());
                }

                // Print the results for this line
                System.out.print("Lower case: ");
                printCharacterSet(lowerCaseSet, 'a');
                System.out.print("Upper case: ");
                printCharacterSet(upperCaseSet, 'A');
                System.out.println("Odds and Ends: " + nonAlphaSet);

                // Initialize common sets for the first line
                if (isFirstLine) {
                    for (int i = 0; i < 26; i++) {
                        commonLower[i] = lowerCaseSet[i];
                        commonUpper[i] = upperCaseSet[i];
                    }
                    isFirstLine = false;
                } else {
                    // For each subsequent line, update the common character arrays
                    for (int i = 0; i < 26; i++) {
                        commonLower[i] = commonLower[i] && lowerCaseSet[i];
                        commonUpper[i] = commonUpper[i] && upperCaseSet[i];
                    }
                }
            }

            // Final results: characters appearing in every line
            System.out.print("Characters in every line (Lower case): ");
            printCharacterSet(commonLower, 'a');
            System.out.print("Characters in every line (Upper case): ");
            printCharacterSet(commonUpper, 'A');

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper function to print the set of characters in boolean array
    private static void printCharacterSet(boolean[] set, char baseChar) {
        for (int i = 0; i < set.length; i++) {
            if (set[i]) {
                System.out.print((char) (baseChar + i));
            }
        }
        System.out.println();
    }
}
