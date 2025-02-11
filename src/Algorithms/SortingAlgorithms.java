package Algorithms;
import java.util.Arrays;
import java.util.Random;

@SuppressWarnings({"unchecked", "unused", "ManualArrayCopy"})
public class SortingAlgorithms {
    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length-1; i++)
            for (int j = 0; j < arr.length-i-1; j++)
                if (arr[j].compareTo(arr[j+1]) > 0)
                    swap(arr, j, j+1);
    }


    public static <T extends Comparable<T>> void insertionSort(T[] arr) { insertionSort(arr, arr.length); }
    public static <T extends Comparable<T>> void insertionSort(T[] arr, int n) {
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        // TODO
    }


    public static <T extends Comparable<T>> void shellSort(T[] arr) {
        // TODO
    }


    /* ========== Merge Sort ========== */
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        // TODO (hint: use Arrays.copyOfRange to get an array slice)
    }

    public static <T extends Comparable<T>> void merge(T[] arr, T[] l, T[] r) {
        // TODO
    }


    /* ========== Quick Sort ========== */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot-1);
            quickSort(arr, pivot+1, high);
        }
    }

    public static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i + 1;
    }


    /* ========== Heap Sort ========== */
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int n = arr.length;
        buildMaxHeap(arr);
        for (int i = n-1; i >= 1; i--) {
            swap(arr, 0, i);
            n -= 1;
            maxHeapify(arr, 0, n);
        }
    }

    private static <T extends Comparable<T>> void buildMaxHeap(T[] arr) {
        int n = arr.length;
        for (int i = (n/2) - 1; i >= 0; i--)
            maxHeapify(arr, i, n);
    }

    private static <T extends Comparable<T>> void maxHeapify(T[] arr, int i, int n) {
        // TODO
    }
}
