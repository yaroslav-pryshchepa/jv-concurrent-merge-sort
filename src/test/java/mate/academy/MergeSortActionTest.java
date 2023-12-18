package mate.academy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;

class MergeSortActionTest {

    @Test
    void compute_ArrayUnsorted_SortsArray() {
        // given
        int[] unsortedArray = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        MergeSortAction action = new MergeSortAction(unsortedArray);
        // In the context of JUnit tests, we are using `new ForkJoinPool()` to create
        // a dedicated thread pool for specific test scenarios.
        // This approach allows us to ensure that the thread pool used in one set of tests
        // doesn't interfere with or influence the behavior of other tests in each suite.
        ForkJoinPool pool = new ForkJoinPool();

        // when
        pool.invoke(action);

        // then
        assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9}, unsortedArray);
    }

    @Test
    void compute_ArrayEmpty_RemainsEmpty() {
        // given
        int[] emptyArray = {};
        MergeSortAction action = new MergeSortAction(emptyArray);
        ForkJoinPool pool = new ForkJoinPool();

        // when
        pool.invoke(action);

        // then
        assertArrayEquals(new int[]{}, emptyArray);
    }

    @Test
    void compute_ArraySingleElement_RemainsUnchanged() {
        // given
        int[] singleElementArray = {42};
        MergeSortAction action = new MergeSortAction(singleElementArray);
        ForkJoinPool pool = new ForkJoinPool();

        // when
        pool.invoke(action);

        // then
        assertArrayEquals(new int[]{42}, singleElementArray);
    }

    @Test
    void compute_ArrayWithDuplicates_SortsArray() {
        // given
        int[] arrayWithDuplicates = {5, 3, 5, 3, 5};
        MergeSortAction action = new MergeSortAction(arrayWithDuplicates);
        ForkJoinPool pool = new ForkJoinPool();

        // when
        pool.invoke(action);

        // then
        assertArrayEquals(new int[]{3, 3, 5, 5, 5}, arrayWithDuplicates);
    }

    @Test
    void compute_AlreadySortedArray_RemainsSorted() {
        // given
        int[] sortedArray = {1, 2, 3, 4, 5};
        MergeSortAction action = new MergeSortAction(sortedArray);
        ForkJoinPool pool = new ForkJoinPool();

        // when
        pool.invoke(action);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedArray);
    }
}
