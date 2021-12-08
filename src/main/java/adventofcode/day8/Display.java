package adventofcode.day8;

record Display(String[] inputs, String[] outputs) {
    public static Display fromInputLine(String inputLine) {
        var splitInputLine = inputLine.replace('|', ':').split(" : ");
        var inputs = splitInputLine[0].split(" ");
        var outputs = splitInputLine[1].split(" ");
        return new Display(inputs, outputs);
    }
}
