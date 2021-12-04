package adventofcode.day2;

sealed interface Position<P extends Position<P>> {
    P applyCommand(Command nextCommand);
    P sum(P other);
    int product();

    record PartOne(int horizontal, int depth) implements Position<PartOne> {

        public PartOne applyCommand(Command nextCommand) {
            return switch (nextCommand.direction()) {
                case forward -> forward(nextCommand.distance());
                case down -> down(nextCommand.distance());
                case up -> up(nextCommand.distance());
            };
        }

        public PartOne sum(PartOne other) {
            return new PartOne(horizontal + other.horizontal, depth + other.depth);
        }

        public int product() {
            return horizontal * depth;
        }

        private PartOne forward(int distance) {
            return new PartOne(horizontal + distance, depth);
        }

        private PartOne up(int distance) {
            return new PartOne(horizontal, depth - distance);
        }

        private PartOne down(int distance) {
            return new PartOne(horizontal, depth + distance);
        }
    }

    record PartTwo(int horizontal, int depth, int aim) implements Position<PartTwo> {

        public PartTwo applyCommand(Command nextCommand) {
            return switch (nextCommand.direction()) {
                case forward -> forward(nextCommand.distance());
                case down -> down(nextCommand.distance());
                case up -> up(nextCommand.distance());
            };
        }

        public PartTwo sum(PartTwo other) {
            return new PartTwo(horizontal + other.horizontal, depth + other.depth, aim + other.aim);
        }

        public int product() {
            return horizontal * depth;
        }

        private PartTwo forward(int distance) {
            return new PartTwo(horizontal + distance, depth + (aim * distance), aim);
        }

        private PartTwo up(int distance) {
            return new PartTwo(horizontal, depth, aim - distance);
        }

        private PartTwo down(int distance) {
            return new PartTwo(horizontal, depth, aim + distance);
        }
    }
}
