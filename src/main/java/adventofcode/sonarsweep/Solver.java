package adventofcode.sonarsweep;

import java.util.List;

public class Solver {
    public int solve(List<Integer> input) {
        int counter = 0;
        for(int i = 1; i < input.size(); i++) {
            if(input.get(i) > input.get(i - 1)) {
                counter++;
            }
        }
        return counter;
    }
}
