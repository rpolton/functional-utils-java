package me.shaftesbury.utils.functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static me.shaftesbury.utils.functional.FunctionalTest.doublingGenerator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.fail;

public class Functional_Append_Test {
    @Test
    void appendTest1() {
        final int i = 1;
        final Collection<Integer> l = Functional.init(doublingGenerator, 5);
        final Iterable<Integer> output = Functional.append(i, l);
        final List<Integer> expected = Arrays.asList(1, 2, 4, 6, 8, 10);

        assertThat(output).containsExactlyElementsOf(expected);
    }

    @Test
    void tryToRemoveFromAnIteratorTest1() {
        final int i = 1;
        final Collection<Integer> l = Functional.init(doublingGenerator, 5);
        final Iterable<Integer> output = Functional.append(i, l);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> output.iterator().remove());
    }

    @Test
    void appendIterableCanOnlyHaveOneIterator() {
        final int i = 1;
        final Collection<Integer> l = Functional.init(doublingGenerator, 5);
        final Iterable<Integer> output = Functional.append(i, l);
        try {
            output.iterator();
        } catch (final UnsupportedOperationException e) {
            fail("Should not reach this point");
        }
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(output::iterator);
    }
}