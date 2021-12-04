package adventofcode.day3.binarydiagnostic;

import adventofcode.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo implements Solver {
    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }

        var oxygenGeneratorRating = findOxygenGeneratorRating(input);
        var cO2ScrubberRating = findCO2ScrubberRating(input);

        return Integer.toString(oxygenGeneratorRating * cO2ScrubberRating);
    }

    private int findOxygenGeneratorRating(List<String> input) {
        var size = input.get(0).length();

        var filteredList = input;

        for (int i = 0; filteredList.size() > 1; i++) {
            int bitToCheck = i % size;
            var mostCommon = Math.round(filteredList.stream()
                    .map(s -> s.charAt(bitToCheck))
                    .mapToInt(c -> Integer.parseUnsignedInt(c.toString(), 2))
                    .average()
                    .orElse(0));
            filteredList = filteredList.stream()
                    .filter(s -> Character.toString(s.charAt(bitToCheck)).equals(Long.toString(mostCommon, 2)))
                    .toList();

        }

        return Integer.parseUnsignedInt(filteredList.get(0), 2);
    }

    private int findCO2ScrubberRating(List<String> input) {
        var size = input.get(0).length();

        var filteredList = input;

        for (int i = 0; filteredList.size() > 1; i++) {
            int bitToCheck = i % size;
            var mostCommon = 1 - Math.round(filteredList.stream()
                    .map(s -> s.charAt(bitToCheck))
                    .mapToInt(c -> Integer.parseUnsignedInt(c.toString(), 2))
                    .average()
                    .orElse(0));
            filteredList = filteredList.stream()
                    .filter(s -> Character.toString(s.charAt(bitToCheck)).equals(Long.toString(mostCommon, 2)))
                    .toList();
        }

        return Integer.parseUnsignedInt(filteredList.get(0), 2);
    }
}
