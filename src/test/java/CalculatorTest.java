import TestingLab.Calculator;
import TestingLab.StringUtils;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void shouldThrowException(){

        assertThatThrownBy(()->calculator.divide(4,0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Can't divide by 0");
    }


    @Test
    void shouldDivideDecimals(){
        double result=calculator.divide(1,3);
        assertThat(result).isCloseTo(0.333, Percentage.withPercentage(0.5));
    }

    @Test
    void shouldDivideNega(){
        double result=calculator.divide(-2,4);
        assertThat(result).isEqualTo(-0.5);
    }










}
