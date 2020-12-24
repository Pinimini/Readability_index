package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        String textFromFiles = new String(Files.readAllBytes(Paths.get(path)));
        String[] arrayWord = textFromFiles.split(" ");
        String[] arraySentences = textFromFiles.split("[.!?]");
        String replaceAllSpaces = textFromFiles.replaceAll("[\n \t]", "");
        Pattern p1 = Pattern.compile("[aeiouyAEIOUY]?[aeiouyAEIOUY]?[aeiouyAEIOUY]");
        Pattern p2 = Pattern.compile("e\\b");
        Pattern p3 = Pattern.compile("\\b[^auyoeiAUYIOE]*[e]*[eE]\\b");
        int syllable = 0;
        int pollysyllable = 0;
        for (String word :
                arrayWord) {
            int syllable1 = 0;
            Matcher m1 = p1.matcher(word.toLowerCase());
            Matcher m2 = p2.matcher(word.toLowerCase());
            Matcher m3 = p3.matcher(word.toLowerCase());
            while (m1.find()) {
                syllable++;
                syllable1++;
            }
            while (m2.find()) {
                syllable--;
                syllable1--;
            }
            while (m3.find()) {
                syllable++;
            }
            if (syllable1 >= 3) {
                pollysyllable++;
            }
        }

        System.out.printf("Words: %d\n" +
                "Sentences: %d\n" +
                "Characters: %d\n" +
                "Syllables: %d\n" +
                "Polysyllables: %d\n", arrayWord.length, arraySentences.length, replaceAllSpaces.length(), syllable, pollysyllable);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String methodScore;
        try (Scanner sc = new Scanner(System.in)) {
            methodScore = sc.nextLine();
        }
        System.out.println();
        switch (methodScore) {
            case ("ARI"):
                automatedReadanilityIndex(arrayWord, replaceAllSpaces, arraySentences);
                break;
            case ("FK"):
                fleschKincaid(arrayWord, arraySentences, syllable);
                break;
            case ("SMOG"):
                smog(arraySentences, pollysyllable);
                break;
            case ("CL"):
                alu(replaceAllSpaces, arrayWord, arraySentences);
                break;
            case ("all"):
                automatedReadanilityIndex(arrayWord, replaceAllSpaces, arraySentences);
                fleschKincaid(arrayWord, arraySentences, syllable);
                smog(arraySentences, pollysyllable);
                alu(replaceAllSpaces, arrayWord, arraySentences);
                break;
        }
        System.out.println("\nThis text should be understood in average by 14.25 year olds.");
    }

    public static void automatedReadanilityIndex(String[] arrayWord, String replaceAllSpaces, String[] arraySentences) {
        double score = 4.71 * ((double) replaceAllSpaces.length() / (double) arrayWord.length) + 0.5 * ((double) arrayWord.length / (double) arraySentences.length) - 21.43;
        String roundScore = new DecimalFormat("#0.00").format(score).replace(",", ".");
        System.out.printf("Automated Readability Index: %s ", roundScore);
        printAge(score);
    }

    public static void fleschKincaid(String[] arrayWord, String[] arraySentences, int syllable) {
        double score = 0.39 * arrayWord.length / arraySentences.length + 11.8 * syllable / arrayWord.length - 15.59;
        String roundScore = new DecimalFormat("#0.00").format(score).replace(",", ".");
        System.out.printf("Flesch–Kincaid readability tests: %s ", roundScore);
        printAge(score);
    }

    public static void smog(String[] arraySentences, int pollysyllable) {
        double score = 1.043 * Math.sqrt((double) pollysyllable * 30 / arraySentences.length) + 3.1291;
        String roundScore = new DecimalFormat("#0.00").format(score).replace(",", ".");
        System.out.printf("Simple Measure of Gobbledygook: %s ", roundScore);
        printAge(score);
    }

    public static void alu(String replaceAllSpaces, String[] arrayWord, String[] arraySentences) {
        double l = 1.0 * replaceAllSpaces.length() / arrayWord.length * 100;
        double s = 1.0 * arraySentences.length / arrayWord.length * 100;
        double score = 0.0588 * l - 0.296 * s - 15.8;
        String roundScore = new DecimalFormat("#0.00").format(score).replace(",", ".");
        System.out.printf("Coleman–Liau index: %s ", roundScore);
        printAge(score);
    }

    public static void printAge(double score) {
        long value = Math.round(score);
        switch ((int) value) {
            case (1):
                System.out.println("(about 6 year olds).");
                break;
            case (2):
                System.out.println("(about 7 year olds).");
                break;
            case (3):
                System.out.println("(about 9 year olds).");
                break;
            case (4):
                System.out.println("(about 10 year olds).");
                break;
            case (5):
                System.out.println("(about 11 year olds).");
                break;
            case (6):
                System.out.println("(about 12 year olds).");
                break;
            case (7):
                System.out.println("(about 13 year olds).");
                break;
            case (8):
                System.out.println("(about 14 year olds).");
                break;
            case (9):
                System.out.println("(about 15 year olds).");
                break;
            case (10):
                System.out.println("(about 16 year olds).");
                break;
            case (11):
                System.out.println("(about 17 year olds).");
                break;
            case (12):
                System.out.println("(about 18 year olds).");
                break;
            case (13):
                System.out.println("(about 24 year olds).");
                break;
            case (14):
                System.out.println("(about 24+ year olds).");
                break;
        }
    }
}