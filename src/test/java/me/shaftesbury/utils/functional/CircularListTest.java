package me.shaftesbury.utils.functional;

import io.vavr.collection.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class CircularListTest {
    @Nested
    class Initialise {
        @Test
        void circularArrayListInitialiseTest1() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output)
                    .containsSequence(1, 2, 3, 4)
                    .containsSequence(2, 3, 4, 1)
                    .containsSequence(3, 4, 1, 2)
                    .containsSequence(4, 1, 2, 3);
        }
    }

    @Nested
    class PrependOrAppend {
        @Test
        void prependAnElement() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output.prepend(0))
                    .containsSequence(0, 1, 2, 3, 4)
                    .containsSequence(1, 2, 3, 4, 0)
                    .containsSequence(2, 3, 4, 0, 1)
                    .containsSequence(3, 4, 0, 1, 2)
                    .containsSequence(4, 0, 1, 2, 3);
        }

        @Test
        void appendAnElement() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output.append(0))
                    .containsSequence(1, 2, 3, 4, 0)
                    .containsSequence(2, 3, 4, 0, 1)
                    .containsSequence(3, 4, 0, 1, 2)
                    .containsSequence(4, 0, 1, 2, 3)
                    .containsSequence(0, 1, 2, 3, 4);
        }
    }

    @Nested
    class Length {
        @Disabled("What does length mean for an infinite sequence?")
        @Test
        void hasSize() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            assertThat(output).hasSize(5);
        }
    }

    @Nested
    class Take {
        @Test
        void takeFewer() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            assertThat(output.take(3))
                    .isInstanceOf(List.class)
                    .containsSequence(1, 2, 3)
                    .doesNotContain(4, 5);
        }

        @Test
        void takeMore() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            assertThat(output.take(6))
                    .isInstanceOf(List.class)
                    .containsSequence(1, 2, 3, 4, 5, 1);
        }
    }

    @Nested
    class Filter {
        @Test
        void filter() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output.filter(i -> i % 2 == 0))
                    .containsSequence(2, 4)
                    // It's not possible to check this in general because doesNotContain()
                    // is a terminating node and requires the entire sequence
//                    .doesNotContain(1, 3)
                    .isExactlyInstanceOf(CircularList.class);
        }

        @Test
        void filterAndTake() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output.filter(i -> i % 2 == 0).take(4))
                    .containsSequence(2, 4, 2, 4)
                    .doesNotContain(1, 3)
                    .isInstanceOf(List.class);
        }
    }

    @Nested
    class Fold {
        @Test
        void foldAndTake1() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            org.assertj.core.api.Assertions.assertThat(output.take(5).fold(100, Integer::sum)).isEqualTo(115);
        }

        @Test
        void foldAndTake2() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            org.assertj.core.api.Assertions.assertThat(output.take(6).fold(100, Integer::sum)).isEqualTo(116);
        }
    }

    @Nested
    class Map {
        @Test
        void map() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output.map(Object::toString))
                    .isInstanceOf(CircularList.class);
        }

        @Test
        void mapAndTake() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4));
            assertThat(output.map(Object::toString).take(4))
                    .containsSequence("1", "2", "3", "4")
                    .isInstanceOf(List.class);
        }
    }

    @Nested
    class FlatMap {
        @Test
        void flatMap() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            assertThat(output.flatMap(List::of))
                    .isInstanceOf(CircularList.class);
        }

        @Test
        void flatMapAndTake() {
            final CircularList<Integer> output = CircularList.of(List.of(1, 2, 3, 4, 5));
            assertThat(output.flatMap(List::of).take(5))
                    .containsSequence(1, 2, 3, 4, 5)
                    .isInstanceOf(List.class);
        }
    }
}
