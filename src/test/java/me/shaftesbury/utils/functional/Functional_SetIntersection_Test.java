package me.shaftesbury.utils.functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class Functional_SetIntersection_Test {
    public Functional_SetIntersection_Test() {
    }

    @Test
    void setIntersectionTest1() {
        final Set<Integer> input1 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        final Set<Integer> input2 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));

        final Set<Integer> intersection = Functional.set.intersection(input1, input2);
        assertThat(intersection.containsAll(input1)).isTrue();
        assertThat(input1.containsAll(intersection)).isTrue();
    }

    @Test
    void setIntersectionTest2() {
        final Set<Integer> input1 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        final Set<Integer> input2 = new HashSet<Integer>(Arrays.asList(4, 5, 6, 7, 8));
        final Set<Integer> expected = new HashSet<Integer>(Arrays.asList(4, 5));

        final Set<Integer> intersection = Functional.set.intersection(input1, input2);
        assertThat(intersection.containsAll(expected)).isTrue();
        assertThat(expected.containsAll(intersection)).isTrue();
    }

    @Test
    void setIntersectionTest3() {
        final Set<Integer> input1 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        final Set<Integer> input2 = new HashSet<Integer>(Arrays.asList(6, 7, 8));
        final Set<Integer> expected = Collections.emptySet();

        final Set<Integer> intersection = Functional.set.intersection(input1, input2);
        assertThat(intersection.containsAll(expected)).isTrue();
        assertThat(expected.containsAll(intersection)).isTrue();
    }
}