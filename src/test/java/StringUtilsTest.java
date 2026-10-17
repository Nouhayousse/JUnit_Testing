import TestingLab.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    @Test
    void shouldReverseAWord(){
        assertThat(StringUtils.reverse("java")).isEqualTo("avaj");
    }

    @Test
    void shouldReverseEmpty(){
        assertThat(StringUtils.reverse("")).isEqualTo("");
    }

    @Test
    void shouldReverseOneChar(){
        assertThat(StringUtils.reverse("j")).isEqualTo("j");
    }

    @Test
    void shouldReverseTextWithSpace(){
        assertThat(StringUtils.reverse("j  a v   a")).isEqualTo("a   v a  j");
    }

    @Test
    void shouldRecognizePalindromeIgnoringCaseAndSpaces(){
        assertThat(StringUtils.isPalindrome("A man a plan a canal Panama")).isTrue();
    }
    @Test
    void shouldRecognizePalindromeWithPonctuation(){
        assertThat(StringUtils.isPalindrome("ra,:da!r.")).isTrue();
    }

    @Test
    void shouldNotfindVowels(){
        assertThat(StringUtils.countVowels("rhythm")).isEqualTo(0);
    }

    @Test
    void shouldRecognizeMajusculeVowels(){
        assertThat(StringUtils.countVowels("sAlAmI kOrami Ewa")).isEqualTo(8);
    }

    @Test
    void shouldSplitPhrasetoCorrectN(){
        assertThat(StringUtils.splitWords("so, i sign just to know i'm alive and cry all the tears i couldn't cry !")).hasSize(17);
    }

    @Test
    void shouldContainsExactWords(){
        assertThat(StringUtils.splitWords("Salam ana Nouhaila")).containsExactly("Salam","ana","Nouhaila");
    }






}
