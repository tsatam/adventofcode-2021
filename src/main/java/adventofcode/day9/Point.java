package adventofcode.day9;

record Point(int x, int y) {
    public Point south() {
        return new Point(x, y + 1);
    }
    public Point north() {
        return new Point(x, y - 1);
    }
    public Point east() {
        return new Point(x + 1, y);
    }
    public Point west() {
        return new Point(x - 1, y);
    }
}
