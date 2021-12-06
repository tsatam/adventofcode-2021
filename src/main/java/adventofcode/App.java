package adventofcode;

import adventofcode.day1.SonarSweep;
import adventofcode.day2.Dive;
import adventofcode.day3.BinaryDiagnostic;
import adventofcode.day4.GiantSquid;
import adventofcode.day5.HydrothermalVenture;
import adventofcode.day6.LanternfishSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.util.Map.entry;

public class App {
    private static final Map<String, Supplier<Solver>> solvers = Map.ofEntries(
            entry("1-1", SonarSweep.PartOne::new),
            entry("1-2", SonarSweep.PartTwo::new),
            entry("2-1", Dive.PartOne::new),
            entry("2-2", Dive.PartTwo::new),
            entry("3-1", BinaryDiagnostic.PartOne::new),
            entry("3-2", BinaryDiagnostic.PartTwo::new),
            entry("4-1", GiantSquid.PartOne::new),
            entry("4-2", GiantSquid.PartTwo::new),
            entry("5-1", HydrothermalVenture.PartOne::new),
            entry("5-2", HydrothermalVenture.PartTwo::new),
            entry("6-1", LanternfishSolver.PartOne::new),
            entry("6-2", LanternfishSolver.PartTwo::new)
    );

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Please provide the advent day and part as the first argument, e.g. `1-1`. ");
            System.exit(1);
        }

        var solver = solvers.get(args[0]).get();

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
