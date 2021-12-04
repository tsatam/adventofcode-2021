package adventofcode;

import adventofcode.day4.giantsquid.PartTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Solver solver = new PartTwo();

    public static void main(String[] args) {
        var input = readInput();

        var output = solver.solve(input);

        System.out.println(output);
    }

    private static List<String> readInput() {
        var input = new ArrayList<String>();
        try (var scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
        }

        return input;
    }
}
