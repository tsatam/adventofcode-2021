package adventofcode;

import adventofcode.annotations.Day;
import adventofcode.annotations.Part;
import adventofcode.day1.SonarSweep;
import adventofcode.day10.SyntaxScoring;
import adventofcode.day2.Dive;
import adventofcode.day3.BinaryDiagnostic;
import adventofcode.day4.GiantSquid;
import adventofcode.day5.HydrothermalVenture;
import adventofcode.day6.LanternfishSolver;
import adventofcode.day7.TheTreacheryOfWhales;
import adventofcode.day8.SevenSegmentSearch;
import adventofcode.day9.SmokeBasin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.util.Map.entry;

public class App {
    public static void main(String[] args) throws ReflectiveOperationException {
        if (args.length < 2) {
            System.err.println(Arrays.toString(args));
            System.err.println("Please provide the advent day and part as the first two arguments, e.g. `1 1`. ");
            System.exit(1);
        }
        var day = Integer.parseInt(args[0]);
        var part = Integer.parseInt(args[1]);

        var solver = loadSolver(day, part);


        var input = readInput();

        var output = solver.solve(input);

        System.out.println(output);
    }

    private static Solver loadSolver(int day, int part) throws ReflectiveOperationException {
        var reflections = new Reflections("adventofcode");
        var clazz = reflections.getSubTypesOf(Solver.class)
            .stream()
            .filter(c -> c.isAnnotationPresent(Day.class))
            .filter(c -> c.isAnnotationPresent(Part.class))
            .filter(c -> c.getAnnotation(Day.class).value() == day)
            .filter(c -> c.getAnnotation(Part.class).value() == part)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No solver found for day [%d] part [%d]. ".formatted(day, part)));

        System.out.printf("Using class [%s]%n", clazz.getName());

        return clazz.getConstructor().newInstance();
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
