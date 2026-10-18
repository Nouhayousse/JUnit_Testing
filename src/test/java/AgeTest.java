import TestingLab.Age;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AgeTest {

    @Test
    void shouldRejectNegativeAge(){
        assertThatThrownBy(()->new Age(-7)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalide");
    }

    @Test
    void shouldConsider18AsAdult(){
        assertThat(new Age(18).isAdult()).isTrue();
    }
}
