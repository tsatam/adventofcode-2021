package adventofcode;

import adventofcode.sonarsweep.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        var input = readInput().stream()
                .map(Integer::parseInt)
                .toList();

        var output = new Solver().solve(input);

        System.out.println(output);
    }

    private static List<String> readInput() {
        var input = new ArrayList<String>();
        try(var scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
        }

        return input;
    }
}
