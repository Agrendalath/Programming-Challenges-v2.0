package agrendalath.sorting;

import java.util.Random;

class Algorithms {
    private static <T> void swap(T[] array, int x, int y) {
        T t = array[y];
        array[y] = array[x];
        array[x] = t;
    }

    private static <T> T[] shuffleArray(T[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; --i) {
            int index = random.nextInt(i + 1);
            swap(array, i, index);
        }
        return array;
    }

    static <T extends Number & Comparable<T>> T[] shuffleSort(T[] array) {
        boolean sorted;
        while (true) {
            sorted = true;
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i + 1].compareTo(array[i]) == -1) {
                    sorted = false;
                    shuffleArray(array);
                    break;
                }
            }
            if (sorted)
                break;
        }
        return array;
    }

    static <T extends Number & Comparable<T>> T[] stupidSort(T[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].compareTo(array[i + 1]) == 1) {
                swap(array, i, i + 1);
                i = -1;
            }
        }
        return array;
    }

    static <T extends Number & Comparable<T>> T[] bubbleSort(T[] array) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i].compareTo(array[i + 1]) == 1) {
                    swap(array, i, i + 1);
                    changed = true;
                }
            }
        }
        return array;
    }

    private static <T extends Number & Comparable<T>> void doTheQuickSort(T[] array, int low, int high) {
        int half = (low + high) / 2;
        T pivot = array[half];
        array[half] = array[high];

        int j;
        for (int i = j = low; i < high; ++i)
            if (array[i].compareTo(pivot) == -1) {
                swap(array, i, j);
                ++j;
            }

        array[high] = array[j];
        array[j] = pivot;
        if (low < j - 1)
            doTheQuickSort(array, low, j - 1);
        if (j + 1 < high)
            doTheQuickSort(array, j + 1, high);
    }

    static <T extends Number & Comparable<T>> T[] quicksort(T[] array) {
        if (array.length != 0)
            doTheQuickSort(array, 0, array.length - 1);
        return array;
    }

    private static <T extends Number & Comparable<T>> void doTheMergeSort(T[] array, T[] helper, int low, int high) {
        int half = (low + high + 1) / 2;
        int left = low;
        int right = half;

        if (half - low > 1)
            doTheMergeSort(array, helper, low, half - 1);
        if (high - half > 0)
            doTheMergeSort(array, helper, half, high);

        for (int i = low; i <= high; i++)
            helper[i] = ((left == half) || ((right <= high) && (array[left].compareTo(array[right]) == 1))) ? array[right++] : array[left++];

        System.arraycopy(helper, low, array, low, high + 1 - low);
    }

    static <T extends Number & Comparable<T>> T[] mergeSort(T[] array) {
        T[] helper = array.clone();
        doTheMergeSort(array, helper, 0, array.length - 1);
        return array;
    }
}

