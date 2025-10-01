package mate.academy;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortAction extends RecursiveAction {

    private static final int THRESHOLD = 10;
    private final int[] array;

    public MergeSortAction(int[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {
        if (array.length <= THRESHOLD) {
            Arrays.sort(array);
            return;
        }

        int mid = array.length / 2;

        int[] leftPart = new int[mid];
        int[] rightPart = new int[array.length - mid];

        System.arraycopy(array, 0, leftPart, 0, mid);
        System.arraycopy(array, mid, rightPart, 0, array.length - mid);

        MergeSortAction leftTask = new MergeSortAction(leftPart);
        MergeSortAction rightTask = new MergeSortAction(rightPart);

        invokeAll(leftTask, rightTask);

        merge(leftPart, rightPart, array);
    }

    private void merge(int[] left, int[] right, int[] dest) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                dest[k++] = left[i++];
            } else {
                dest[k++] = right[j++];
            }
        }

        while (i < left.length) {
            dest[k++] = left[i++];
        }

        while (j < right.length) {
            dest[k++] = right[j++];
        }
    }
}
