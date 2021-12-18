package adventofcode.day18;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReducerTest {


    @Test
    void getEndOfSimplePair_returnsNegative1IfStartIndexIsNotStartOfPair() {
        assertThat(Reducer.getEndOfSimplePair("[[[1,1],1],1]", 3)).isEqualTo(-1);
    }
    @Test
    void getEndOfSimplePair_returnsNegative1IfStartIndexIsStartOfComplexPair() {
        assertThat(Reducer.getEndOfSimplePair("[[[1,1],1],1]", 1)).isEqualTo(-1);
    }
    @Test
    void getEndOfSimplePair_returnsIndexOfEndOfPair() {
        assertThat(Reducer.getEndOfSimplePair("[[[1,1],1],1]", 2)).isEqualTo(6);
    }
    @Test
    void getEndOfSimplePair_handlesDoubleDigitNumbers() {
        assertThat(Reducer.getEndOfSimplePair("[[[11,1],1],1]", 2)).isEqualTo(7);
    }
}
