package adventofcode.day18;

import java.util.Set;
import java.util.stream.Collectors;

public class Reducer {
    private static final boolean LOG_REDUCE = true;

    static String reducePair(String pair) {
        Set<Character> digits = "0123456789".chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        int depth = 0;

        for (int i = 0; i < pair.length(); i++) {
            switch (pair.charAt(i)) {
                case '[' -> depth++;
                case ']' -> depth--;
            }

            if (pair.charAt(i) == '[' && depth > 4) {
                var endOfPair = getEndOfSimplePair(pair, i);
                var isSimplePair = endOfPair != -1;

                if (isSimplePair) {
                    var b = new StringBuilder(pair);

                    var indexOfPairStart = i;
                    var indexOfLeftNumber = i + 1;
                    var indexOfComma = b.indexOf(",", i);
                    var indexOfRightNumber = indexOfComma + 1;
                    var indexOfPairEnd = b.indexOf("]", indexOfRightNumber);
                    var pairLength = (indexOfPairEnd - indexOfPairStart) + 1;

                    var innerLeftNumber = Integer.parseInt(b.substring(indexOfLeftNumber, indexOfComma));
                    var innerRightNumber = Integer.parseInt(b.substring(indexOfRightNumber, indexOfPairEnd));

                    var indexOfLeftNumberToAddTo = -1;
                    var lengthOfLeftNumberToAddTo = 0;
                    var indexOfRightNumberToAddTo = -1;
                    var lengthOfRightNumberToAddTo = 0;
                    for (int j = indexOfPairStart; j > 0; j--) {
                        if (digits.contains(b.charAt(j))) {
                            for (int k = j; k > 0; k--) {
                                if (!digits.contains(b.charAt(k))) {
                                    break;
                                }
                                indexOfLeftNumberToAddTo = k;
                                lengthOfLeftNumberToAddTo++;
                            }
                            break;
                        }
                    }
                    for (int j = indexOfPairEnd; j < pair.length(); j++) {
                        if (digits.contains(b.charAt(j))) {
                            indexOfRightNumberToAddTo = j;
                            for (int k = j; k < pair.length(); k++) {
                                if (!digits.contains(b.charAt(k))) {
                                    break;
                                }
                                lengthOfRightNumberToAddTo++;
                            }
                            break;
                        }
                    }
                    var replacementString = "$".repeat(pairLength);
                    b.replace(indexOfPairStart, indexOfPairEnd + 1, replacementString);
                    if (indexOfRightNumberToAddTo > 0) {
                        var rightNumber = Integer.parseInt(b.substring(indexOfRightNumberToAddTo, indexOfRightNumberToAddTo + lengthOfRightNumberToAddTo)) + innerRightNumber;
                        b.replace(indexOfRightNumberToAddTo, indexOfRightNumberToAddTo + lengthOfRightNumberToAddTo, Integer.toString(rightNumber));
                    }
                    if (lengthOfLeftNumberToAddTo > 0) {
                        var leftNumber = Integer.parseInt(b.substring(indexOfLeftNumberToAddTo, indexOfLeftNumberToAddTo + lengthOfLeftNumberToAddTo)) + innerLeftNumber;
                        b.replace(indexOfLeftNumberToAddTo, indexOfLeftNumberToAddTo + lengthOfLeftNumberToAddTo, Integer.toString(leftNumber));
                    }
                    var result = b.toString().replace(replacementString, "0");
                    if (LOG_REDUCE) System.out.printf("    => %s via explosion%n", result);
                    return result;
                }
            }
        }
        for (int i = 0; i < pair.length(); i++) {
            if (digits.contains(pair.charAt(i)) && digits.contains(pair.charAt(i + 1))) {
                var sequence = pair.charAt(i) + "" + pair.charAt(i + 1);
                var number = Integer.parseInt(sequence);
                var left = (int) Math.floor(number / 2.);
                var right = (int) Math.ceil(number / 2.);
                var result = pair.replace(
                        sequence,
                        "[%d,%d]".formatted(left, right)
                );
                if (LOG_REDUCE) System.out.printf("    => %s via split%n", result);
                return result;
            }

        }
        return pair;

    }

    static int getEndOfSimplePair(String pair, int startIndex) {
        if (pair.charAt(startIndex) != '[') return -1;

        for (int i = startIndex + 1; i < pair.length(); i++) {
            if (pair.charAt(i) == '[') return -1;
            if (pair.charAt(i) == ']') return i;
        }
        return -1;
    }
}
