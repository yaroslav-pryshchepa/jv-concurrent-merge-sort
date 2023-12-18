package mate.academy;

import java.util.concurrent.ForkJoinPool;

public class ConcurrentMergeSortExample {

    public static void sort(int[] array) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new MergeSortAction(array));
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 0, 13, 5, 6, 7, -1};
        System.out.println("Original array: ");
        for (int value : array) {
            System.out.print(value + " ");
        }

        sort(array);

        System.out.println("\nSorted array: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}
