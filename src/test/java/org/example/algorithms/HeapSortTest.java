package org.example.algorithms;

import org.example.metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HeapSortTest {
    
    private PerformanceTracker tracker;
    
    @BeforeEach
    void setUp() {
        tracker = new PerformanceTracker("HeapSort");
    }
    
    @Test
    void testSortEmptyArray() {
        int[] arr = {};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
    }
    
    @Test
    void testSortSingleElement() {
        int[] arr = {42};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(42, arr[0]);
    }
    
    @Test
    void testSortTwoElements() {
        int[] arr = {5, 3};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(3, arr[0]);
        assertEquals(5, arr[1]);
    }
    
    @Test
    void testSortAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(1, arr[0]);
        assertEquals(5, arr[4]);
    }
    
    @Test
    void testSortReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(1, arr[0]);
        assertEquals(5, arr[4]);
    }
    
    @Test
    void testSortWithDuplicates() {
        int[] arr = {3, 1, 3, 2, 1};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(1, arr[0]);
        assertEquals(3, arr[4]);
    }
    
    @Test
    void testSortLargeArray() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = 100 - i;
        }
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(1, arr[0]);
        assertEquals(100, arr[99]);
    }
    
    @Test
    void testSortAllSameElements() {
        int[] arr = {7, 7, 7, 7, 7};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        for (int i = 0; i < arr.length; i++) {
            assertEquals(7, arr[i]);
        }
    }
    
    @Test
    void testSortNegativeNumbers() {
        int[] arr = {-5, -1, -3, -2, -4};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(-5, arr[0]);
        assertEquals(-1, arr[4]);
    }
    
    @Test
    void testSortMixedPositiveNegative() {
        int[] arr = {5, -3, 0, -1, 2};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(-3, arr[0]);
        assertEquals(5, arr[4]);
    }
    
    @Test
    void testSortWithZero() {
        int[] arr = {5, 0, 3, 1, 0};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(0, arr[0]);
        assertEquals(5, arr[4]);
    }
    
    @Test
    void testNullArray() {
        int[] arr = null;
        HeapSort.heapSort(arr, tracker);
        assertNull(arr);
    }
    
    @Test
    void testPerformanceTracking() {
        int[] arr = {5, 2, 8, 1, 9};
        HeapSort.heapSort(arr, tracker);
        
        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);
        assertTrue(tracker.getTimeMs() >= 0);
        assertEquals(5, tracker.getArraySize());
        assertEquals("HeapSort", tracker.getAlgorithm());
    }
    
    @Test
    void testCopyArray() {
        int[] original = {5, 3, 1, 4, 2};
        int[] copy = HeapSort.copyArray(original);
        
        assertEquals(original.length, copy.length);
        for (int i = 0; i < original.length; i++) {
            assertEquals(original[i], copy[i]);
        }
        
        copy[0] = 999;
        assertEquals(5, original[0]);
        assertEquals(999, copy[0]);
    }
    
    @Test
    void testIsSorted() {
        int[] sorted = {1, 2, 3, 4, 5};
        int[] notSorted = {1, 3, 2, 4, 5};
        
        assertTrue(HeapSort.isSorted(sorted));
        assertFalse(HeapSort.isSorted(notSorted));
    }
    
    @Test
    void testSortRandomArray() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(11, arr[0]);
        assertEquals(90, arr[6]);
    }
    
    @Test
    void testSortEdgeCaseTwo() {
        int[] arr = {2, 1};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
    }
    
    @Test
    void testSortWithMaxInt() {
        int[] arr = {Integer.MAX_VALUE, 1, Integer.MIN_VALUE};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(Integer.MIN_VALUE, arr[0]);
        assertEquals(Integer.MAX_VALUE, arr[2]);
    }
    
    @Test
    void testSortPerformanceConsistency() {
        int[] arr1 = {5, 2, 8, 1, 9};
        int[] arr2 = {5, 2, 8, 1, 9};
        
        PerformanceTracker tracker1 = new PerformanceTracker("HeapSort");
        PerformanceTracker tracker2 = new PerformanceTracker("HeapSort");
        
        HeapSort.heapSort(arr1, tracker1);
        HeapSort.heapSort(arr2, tracker2);
        
        assertTrue(HeapSort.isSorted(arr1));
        assertTrue(HeapSort.isSorted(arr2));
        
        assertEquals(tracker1.getComparisons(), tracker2.getComparisons());
        assertEquals(tracker1.getSwaps(), tracker2.getSwaps());
    }
    
    @Test
    void testSortWithoutTracker() {
        int[] arr = {5, 3, 1, 4, 2};
        HeapSort.heapSort(arr);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(1, arr[0]);
        assertEquals(5, arr[4]);
    }
    
    @Test
    void testSortVeryLargeArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertTrue(tracker.getComparisons() > 0);
    }
    
    @Test
    void testSortWithRepeatedPattern() {
        int[] arr = {1, 2, 1, 2, 1, 2};
        HeapSort.heapSort(arr, tracker);
        assertTrue(HeapSort.isSorted(arr));
        assertEquals(1, arr[0]);
        assertEquals(2, arr[5]);
    }
    
    @Test
    void testPrintArray() {
        int[] arr = {1, 2, 3};
        HeapSort.printArray(arr, "Test");
    }
}
