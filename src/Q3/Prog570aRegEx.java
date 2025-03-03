package Q3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Prog570aRegEx {
    private static void printCharSet(boolean[] set, char baseChar) {
        for (int i = 0; i < set.length; i++)
            if (set[i])
                System.out.print((char) (baseChar + i));
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog570a.dat"));

            // Arrays to track common lowercase and uppercase characters
            var commonLower = new boolean[26];
            var commonUpper = new boolean[26];
            boolean isFirstLine = true;  // For initializing the common sets for the first line

            var lowerCasePattern = Pattern.compile("[a-z]");
            var upperCasePattern = Pattern.compile("[A-Z]");
            var nonAlphaPattern  = Pattern.compile("[^a-zA-Z\\s]");  // Excludes whitespace & letters

            while (file.hasNextLine()) {
                String line = file.nextLine();
                System.out.println("Line: " + line);

                var lowerCaseSet = new boolean[26];
                var upperCaseSet = new boolean[26];
                var nonAlphaSet  = new StringBuilder();

                var lcMatcher = lowerCasePattern.matcher(line);
                while (lcMatcher.find()) {
                    char lowerChar = lcMatcher.group().charAt(0);
                    lowerCaseSet[lowerChar - 'a'] = true;
                }

                var ucMatcher = upperCasePattern.matcher(line);
                while (ucMatcher.find()) {
                    char upperChar = ucMatcher.group().charAt(0);
                    upperCaseSet[upperChar - 'A'] = true;
                }

                var naMatcher = nonAlphaPattern.matcher(line);
                while (naMatcher.find()) {
                    nonAlphaSet.append(naMatcher.group());
                }

                // Print line results
                System.out.print("Lowercase: ");
                printCharSet(lowerCaseSet, 'a');
                System.out.print("Uppercase: ");
                printCharSet(upperCaseSet, 'A');
                System.out.println("Odds and Ends: " + nonAlphaSet + "\n");

                if (isFirstLine) {
                    commonLower = lowerCaseSet;
                    commonUpper = upperCaseSet;
                    isFirstLine = false;
                } else {
                    for (int i = 0; i < 26; i++) {
                        commonLower[i] = commonLower[i] && lowerCaseSet[i];
                        commonUpper[i] = commonUpper[i] && upperCaseSet[i];
                    }
                }
            }
            file.close();

            System.out.print("Characters in every line (lowercase): ");
            printCharSet(commonLower, 'a');
            System.out.print("Characters in every line (uppercase): ");
            printCharSet(commonUpper, 'A');

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
