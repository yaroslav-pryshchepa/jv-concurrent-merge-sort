package mate.academy;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.RecursiveAction;

public class MergeSortAction extends RecursiveAction {

    private static final int THRESHOLD = 2;
    private final int[] array;

    public MergeSortAction(int[] array) {
        this.array = Objects.requireNonNull(array, "array must not be null");
    }

    @Override
    protected void compute() {
        if (array.length <= THRESHOLD) {
            Arrays.sort(array);
            return;
        }

        int mid = array.length / 2;

        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        MergeSortAction leftTask = new MergeSortAction(left);
        MergeSortAction rightTask = new MergeSortAction(right);

        leftTask.fork();
        rightTask.compute();
        leftTask.join();

        merge(left, right, array);
    }

    private void merge(int[] left, int[] right, int[] result) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
