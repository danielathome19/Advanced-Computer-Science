package Algorithms;

@SuppressWarnings({"unused"})
public class SearchAlgorithms {
    public static <T extends Comparable<T>> int linearSearch(T[] arr, T target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(T[] arr, T target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) == 0)
                return mid;
            else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearchRecursive(T[] arr, T target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (arr[mid].compareTo(target) == 0)
            return mid;
        else if (arr[mid].compareTo(target) < 0)
            return binarySearchRecursive(arr, target, mid + 1, high);
        else
            return binarySearchRecursive(arr, target, low, mid - 1);
    }
}