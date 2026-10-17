import TestingLab.Calculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private final Calculator calculator=new Calculator();
    @Test
    void shouldAddTwoPositives(){
        int result=calculator.add(2,4);
        assertThat(result).isEqualTo(6);
    }

    @Test
    void shouldAddTwoDiffSigns(){
        int result=calculator.add(-3,6);
        assertThat(result).isEqualTo(3);
    }

    @Test
    void shouldAddTwoNega(){
        int result=calculator.add(-7,-9);
        assertThat(result).isEqualTo(-16);
    }

    @Test
    void shouldSubTwoNega(){
        int result=calculator.subtract(-8,-4);
        assertThat(result).isEqualTo(-4);
    }

    @Test
    void shouldSubTwoDiff(){
        int result=calculator.subtract(5,-9);
        assertThat(result).isEqualTo(14);
    }


}
