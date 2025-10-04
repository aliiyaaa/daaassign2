package org.example.algorithms;

import org.example.metrics.PerformanceTracker;

public class HeapSort {
    
    public static void heapSort(int[] arr) {
        heapSort(arr, null);
    }
    
    public static void heapSort(int[] arr, PerformanceTracker tracker) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        if (tracker != null) {
            tracker.reset();
            tracker.setArraySize(arr.length);
            tracker.start();
        }
        
        int n = arr.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, tracker);
        }
        
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i, tracker);
            heapify(arr, i, 0, tracker);
        }
        
        if (tracker != null) {
            tracker.stop();
        }
    }
    
    private static void heapify(int[] arr, int heapSize, int rootIndex) {
        heapify(arr, heapSize, rootIndex, null);
    }
    
    private static void heapify(int[] arr, int heapSize, int rootIndex, PerformanceTracker tracker) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;
        
        if (leftChild < heapSize) {
            if (tracker != null) tracker.addComparison();
            if (arr[leftChild] > arr[largest]) {
                largest = leftChild;
            }
        }
        
        if (rightChild < heapSize) {
            if (tracker != null) tracker.addComparison();
            if (arr[rightChild] > arr[largest]) {
                largest = rightChild;
            }
        }
        
        if (largest != rootIndex) {
            swap(arr, rootIndex, largest, tracker);
            if (tracker != null) tracker.addRecursiveCall();
            heapify(arr, heapSize, largest, tracker);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        swap(arr, i, j, null);
    }
    
    private static void swap(int[] arr, int i, int j, PerformanceTracker tracker) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
        if (tracker != null) {
            tracker.addSwap();
        }
    }
    
    public static void printArray(int[] arr, String message) {
        System.out.print(message + ": ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }
}