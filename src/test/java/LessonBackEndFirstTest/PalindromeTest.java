package LessonBackEndFirstTest;

import LessonBackEndFirst.Palindrome;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class PalindromeTest {


    // A roza upala na Lapu Azora
    // 1) abba
    // 2) aba
    // 3) a  aab a aa
    // 4) a  aab a a
    // 5) abbc
    // 6) Ab bB a

    @Test
    @DisplayName("Слово палиндром")
    void isPalindrome() {
        assertThat(Palindrome.isPalindrome("A roza upala na Lapu Azora")).isTrue();
    }

    @Test
    @DisplayName("Слово не палиндром")
    void isNotPalindrome () {
        assertThat(Palindrome.isPalindrome("12354124hsgdjs")).isFalse();
    }

    @Test
    @DisplayName("Не чувствительность к регистру")
    void palindromeRegister () {
        assertThat(Palindrome.isPalindrome("AbBA")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("wordsPalindrome")
    @DisplayName("Список палендромов")
    void listOfWordsIsPalindrome(String s) {
        Assertions.assertThat(Palindrome.isPalindrome(s)).isTrue();
    }

    private static Stream<Arguments> wordsPalindrome() {
        return Stream.of(
                Arguments.of("A roza upala na Lapu Azora"),
                Arguments.of("abba"),
                Arguments.of("aba"),
                Arguments.of("a  aab a aa"),
                Arguments.of("Ab bB a"));
    }

    @ParameterizedTest
    @MethodSource("wordsNotPalindrome")
    @DisplayName("Список не палендромов")
    void listOfWordsIsNotPalindrome(String s) {
        Assertions.assertThat(Palindrome.isPalindrome(s)).isFalse();
    }

    private static Stream<Arguments> wordsNotPalindrome() {
        return Stream.of(
                Arguments.of("i propadaet v millionah navek"),
                Arguments.of("kogda-to samyj dorogoj chelovek"),
                Arguments.of("pravda slishkom glubokaya rana"),
                Arguments.of("zabyvat' drug druga pora nam"),
                Arguments.of("abbc"));
    }

}
