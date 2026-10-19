import TestingLab.PasswordValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {
    private final PasswordValidator passwordValidator=new PasswordValidator();

    @Test
    @DisplayName("A password respecting all rulles is Valid !")
    void shouldAcceptPasswordRespectingAlLRules(){
        assertThat(passwordValidator.isValid("Jano@mi678$")).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldRejectNullAndEmpty(String password){
        assertThat(passwordValidator.isValid(password)).isFalse();

    }

    @Test
    void shouldRejectShortPasswords(){
        assertThat(passwordValidator.isValid("Ka@5n")).isFalse();
    }

    @Test
    void shouldRejectNullPassword(){
        assertThat(passwordValidator.isValid(null)).isFalse();
    }

    @Test
    void shouldRejectEmptyPassword(){
        assertThat(passwordValidator.isValid("")).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "Kari@ma*&, False",
            "sari%m23*, False",
            "Sar45imat, False",
            "Hach @s23!, False",
            "Jak@arta2E, True",
            "saej, False"
    })
    void  shouldRejectPasswordNotRespectingRules(String password,boolean valid){
        assertThat(passwordValidator.isValid(password)).isEqualTo(valid);

    }


    @Test
    void shouldReturnNoViolationsForValidPassword(){
        assertThat(passwordValidator.validate("Kuka$ta7%")).isEmpty();
    }


    @Test
    void shouldReportMissingUppercase() {
        assertThat(passwordValidator.validate("abcdefg1!"))
                .containsExactly(
                        "password must contain an UpperCase"
                );
    }


    @Test
    void shouldReportAllViolations() {
        assertThat(passwordValidator.validate("akste"))
                .containsExactly(
                        "password size can't be less than 8",
                        "password must contain an UpperCase",
                        "password must contain a number",
                        "password must contain a special char"
                );
    }
}
