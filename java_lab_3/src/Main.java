package java_lab_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

class TXTreading extends Exception {
    public TXTreading(String message) {
        super(message);
    }
}

class ReadingErr extends Exception {
    public ReadingErr(String message) {
        super(message);
    }
}

public class Main {
    private static final String DICTIONARY_FILE_PATH_IN_RESOURCES = "/dictionary.txt";

    public static void main(String[] args) {
        Dictionary translator = null;
        try {
            translator = new Dictionary(DICTIONARY_FILE_PATH_IN_RESOURCES);
        } catch (ReadingErr | TXTreading e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("<<<Dictionary now LOADED>>>");
        System.out.println(" ");
        System.out.println("Enter ENGLISH word:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(" ");
        System.out.println("RUSSIAN translation of the word:");
        System.out.println(translator.translateText(input));
    }
}

class Dictionary {
    private final Map<String, String> dictionary = new HashMap<>();

    public Dictionary(String resourcePath) throws ReadingErr, TXTreading {
        URL resource = getClass().getResource(resourcePath);
        if (resource == null) {
            throw new TXTreading("ERROR: No resource file " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new ReadingErr("ERROR: File not TXT" + line);
                }
                dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim().toLowerCase());
            }
        } catch (IOException e) {
            throw new TXTreading("ERROR: Reading file" + e.getMessage());
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
