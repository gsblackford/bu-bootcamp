import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GradeAnalyzer {
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    scores.add(-1); // Add -1 for empty lines
                    continue;
                }

                System.out.println("Read line: " + line);
                try {
                    int number = Integer.parseInt(line.trim());
                    scores.add(number);
                } catch (NumberFormatException e) {
                    scores.add(-1); // Add -1 for invalid scores
                    System.out.println("Invalid score (not a number): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        double average = 0.0;
        if (scores.size() > 0) {
            double sum = 0;
            int trueSize = 0;

            for (int score : scores) {
                if (score != -1) { // Ignore invalid scores
                    sum += score;
                    trueSize++;
                }
            }
            average = sum / trueSize;
        }

        return average;
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
            double avg, int high, int low,
            String outputFile) {

        int invalidCount = scores.stream().filter(score -> score == -1).toArray().length;
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80 && score <= 89) {
                countB++;
            } else if (score >= 70 && score <= 79) {
                countC++;
            } else if (score >= 60 && score <= 69) {
                countD++;
            } else {
                countF++;
            }
        }

        try (java.io.PrintWriter writer = new java.io.PrintWriter(outputFile)) {
            if (scores.size() == invalidCount || scores.isEmpty()) {
                writer.println("No valid scores to analyze.");
                System.out.println("No valid scores to analyze.");
            } else {
                writer.println("=== Grade Analysis Report ===");
                writer.printf("Total scores processed: %d%n", scores.size());
                writer.printf("Invalid scores: %d%n", invalidCount);
                writer.println();

                writer.printf("Average score: %.2f%n", avg);

                if (high == Integer.MIN_VALUE) {
                    writer.println("Highest score: N/A");
                } else {
                    writer.printf("Highest score: %d%n", high);
                }

                if (low == Integer.MAX_VALUE) {
                    writer.println("Lowest score: N/A");
                } else {
                    writer.printf("Lowest score: %d%n", low);
                }
                writer.println();

                writer.println("Grade Distribution:");
                writer.printf("  A (90-100): %d%n", countA);
                writer.printf("  B (80-89): %d%n", countB);
                writer.printf("  C (70-79): %d%n", countC);
                writer.printf("  D (60-69): %d%n", countD);
                writer.printf("  F (below 60): %d%n", countF);

                System.out.println("Report written to " + outputFile);
            }
        } catch (IOException e) {
            System.out.println("Could not write to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java GradeAnalyzer <filename>");
            return;
        }

        String filename = args[0]; // Get filename from command line argument

        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores(filename);
        System.out.println("Scores read from file: " + scores);

        // Step 2: calculate statistics
        double average = calculateAverage(scores);
        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > high) {
                high = score;
            }
            if (score < low && score != -1) { // Ignore invalid scores for low
                low = score;
            }
        }

        // Step 3: write and print report
        String outputFile = "report.txt";
        writeReport(scores, average, high, low, outputFile);
    }

}
