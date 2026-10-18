import TestingLab.GradeCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GradeCalculatorTest {
    private final GradeCalculator calculator=new GradeCalculator();

    @ParameterizedTest
    @CsvSource({
            "0, Echec",
            "9.99, Echec",
            "10, Passable",
            "11.99, Passable",
            "12, Assez bien",
            "13.99, Assez bien",
            "14, Bien",
            "15.99, Bien",
            "16, Tres bien",
            "20, Tres bien"
    }
    )
    void shouldReturnExpectedMention(double score, String expected){
        assertThat(calculator.mention(score)).isEqualTo(expected);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-0.1,20.1,-5,25})
    void shouldRejectInvalidScores(double score){
        assertThatThrownBy(()->calculator.mention(score)).isInstanceOf(IllegalArgumentException.class);
    }
}
