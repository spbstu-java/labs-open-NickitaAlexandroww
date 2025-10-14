package lab_3;

import lab_3.exceptions.FileReadException;
import lab_3.exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private final Map<String, String> dictionary = new HashMap<>();

    public void loadTranslationsFromFile(String filePath) throws InvalidFileFormatException, FileReadException {

        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(filePath).toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("ERROR: File not TXT " + line);
                }

                dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim().toLowerCase());
            }
        } catch (IOException e) {
            throw new FileReadException("ERROR: Reading file " + e.getMessage());
        }
    }

    public String translateText(String input) {
        String[] words = input.split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId].toLowerCase();

            String bestMatch = findBestMatch(word);
            if (bestMatch != null) {

                result.append(dictionary.get(bestMatch));
            } else {

                result.append(words[wordId]);
            }

            if (wordId < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    private String findBestMatch(String input) {

        return dictionary.keySet().stream()
                .filter(input::startsWith)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }
}
