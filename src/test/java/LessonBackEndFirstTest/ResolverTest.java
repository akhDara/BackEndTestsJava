package LessonBackEndFirstTest;

import LessonBackEndFirst.Resolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResolverTest {

        private static Resolver resolver;

        @BeforeAll
        static void beforeAll(){
          resolver = new Resolver ();
        }

        @Test
        @DisplayName("Базовый сценарий")
        void baseTest(){
                int min = resolver.getMin(new int[]{4, 2, 1, 5, 2, 6});
                assertThat(min).isEqualTo(1);
        }

        @Test
        @DisplayName("Пустая ссылка")
        void badDataTest () {
                assertThatThrownBy(() -> resolver.getMin(new int[]{}))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Array could not be empty");
        }

        @ParameterizedTest(name = "[{index}] expected{1}")
        @MethodSource("testData")
        void testWithParams (int[] array, int expected) {
                assertThat(resolver.getMin(array))
                        .isEqualTo(expected);
        }

        Stream<Arguments> testData() {
                int caseSize = 10;
                List<Arguments> arguments = new ArrayList<>();
                for (int i = 0; i < caseSize; i++) {
                        int arraySize = (int) (Math.random() * 10) + 1;
                        int [] array = new int[arraySize];
                        for (int b = 0; b < arraySize; b++) {
                                array[b] = (int) (Math.random() * 10000000);
                        }
                        int expected = IntStream.of(array)
                                .min()
                                .orElse(0);
                        arguments.add(Arguments.of(array, expected));
                }
                return arguments.stream();
        }
}
