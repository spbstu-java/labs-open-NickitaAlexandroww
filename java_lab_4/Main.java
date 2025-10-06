package lab_4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        List<Integer> numbers = Arrays.asList(11, 25, 64, 28, 99);
        System.out.println("List of elements: " + numbers);
        System.out.println("Average: " + averageOfList(numbers).orElse(0.0));

        System.out.println("========================================");
        List<String> strings = Arrays.asList("box", "barrel", "village");
        System.out.println("List of elements: " + strings);
        System.out.println("Transformed elements: " + transformStrings(strings));

        System.out.println("========================================");
        List<Integer> duplicateNumbers = Arrays.asList(33, 9, 9, 11, 8, 8, 22);
        System.out.println("List of elements: " + duplicateNumbers);
        System.out.println("Unique elements in square: " + uniqueSquares(duplicateNumbers));

        System.out.println("========================================");
        List<String> moreStrings = Arrays.asList("word1st", "word2nd", "word3rd");
        System.out.println("List of elements: " + moreStrings);
        System.out.println("Last element: " + getLastElement(moreStrings));
        System.out.println("----------------------------------------");
        List<String> moreStringsEmpty = List.of();
        System.out.println("List of elements: " + moreStringsEmpty);
        try {
            System.out.println("Last element: " + getLastElement(moreStringsEmpty));
        } catch (Exception e) {
            System.out.println("Exception raised: " + e.getMessage());
        }

        System.out.println("========================================");
        int[] numArray = {2, 2, 2, 6, 5, 3};
        System.out.println("List of elements: " + Arrays.toString(numArray));
        System.out.println("Even sum: " + sumOfEvenNumbers(numArray));
        System.out.println("----------------------------------------");
        int[] numArrayWithoutEven = {33, 23, 11, 17, 109, 121};
        System.out.println("List of elements: " + Arrays.toString(numArrayWithoutEven));
        System.out.println("Even sum: " + sumOfEvenNumbers(numArrayWithoutEven));

        System.out.println("========================================");
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        System.out.println("List of elements: " + stringList);
        System.out.println("Converted: " + convertToMap(stringList));

        System.out.println("========================================");
    }

    public static OptionalDouble averageOfList(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average();
    }

    public static List<String> transformStrings(List<String> strings) {
        return strings.stream()
                .map(s -> "_ALT_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Integer> uniqueSquares(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> Collections.frequency(numbers, n) == 1)
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Empty list!"));
    }

    public static int sumOfEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    public static Map<Character, String> convertToMap(List<String> strings) {
        return strings.stream()
                .filter(s -> s.length() > 0)
                .collect(Collectors.toMap(s -> s.charAt(0), s -> s.substring(1)));
    }
}