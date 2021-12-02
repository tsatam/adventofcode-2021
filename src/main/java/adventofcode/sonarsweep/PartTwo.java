package adventofcode.sonarsweep;

import java.util.List;

public class PartTwo {
    public int solve(List<Integer> input) {
        if (input.size() < 4) {
            return 0;
        }

        int counter = 0;
        for (int i = 3; i < input.size(); i++) {
            if (input.get(i) > input.get(i - 3)) {
                counter++;
            }
        }
        return counter;
    }
}
