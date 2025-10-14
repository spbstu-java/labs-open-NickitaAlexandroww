package lab_4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void printInfo () {
        System.out.println("========================================");
        System.out.println("Info:");
        System.out.println("========================================");
        System.out.println("This program uses Stream API to get info from lists.");
        System.out.println("Separate list elements with comma.");
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        if (Arrays.stream(args).toList().contains("--info")) {
            printInfo();
            System.exit(0);
        }

        if (args.length < 6) {
            System.out.println("You must specify all the arrays");
            System.exit(1);
        }


        System.out.println("========================================");
        List<Integer> numbers;
        try {
            numbers = Arrays.stream(args[0].split("\\s*,\\s*")).map(Integer::parseInt).toList();
        } catch (NumberFormatException e) {
            System.out.println("Failed to convert string \"" + args[0] + "\" to list of integers!");
            System.exit(1);
            return;
        }
        System.out.println("List of elements: " + numbers);
        System.out.println("Average: " + averageOfList(numbers).orElse(0.0));

        System.out.println("========================================");
        List<String> strings = Arrays.stream(args[1].split("\\s*,\\s*")).toList();
        System.out.println("List of elements: " + strings);
        System.out.println("Transformed elements: " + transformStrings(strings));

        System.out.println("========================================");
        List<Integer> duplicateNumbers;
        try {
            duplicateNumbers = Arrays.stream(args[2].split("\\s*,\\s*")).map(Integer::parseInt).toList();
        } catch (NumberFormatException e) {
            System.out.println("Failed to convert string \"" + args[2] + "\" to list of integers!");
            System.exit(1);
            return;
        }
        System.out.println("List of elements: " + duplicateNumbers);
        System.out.println("Unique elements in square: " + uniqueSquares(duplicateNumbers));

        System.out.println("========================================");
        List<String> moreStrings = Arrays.stream(args[3].split("\\s*,\\s*")).toList();
        System.out.println("List of elements: " + moreStrings);
        System.out.println("Last element: " + getLastElement(moreStrings));

        System.out.println("========================================");
        int[] numArray;
        try {
            numArray = Arrays.stream(args[4].split("\\s*,\\s*")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            System.out.println("Failed to convert string \"" + args[4] + "\" to array of integers!");
            System.exit(1);
            return;
        }
        System.out.println("List of elements: " + Arrays.toString(numArray));
        System.out.println("Even sum: " + sumOfEvenNumbers(numArray));

        System.out.println("========================================");
        List<String> stringList = Arrays.stream(args[5].split("\\s*,\\s*")).toList();
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
                .orElseThrow(() -> new NoSuchElementException("Collection is empty"));
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