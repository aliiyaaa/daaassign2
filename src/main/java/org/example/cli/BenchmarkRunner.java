package org.example.cli;

import org.example.algorithms.HeapSort;
import org.example.metrics.PerformanceTracker;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class BenchmarkRunner {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }
        
        String command = args[0];
        
        switch (command) {
            case "benchmark":
                runBenchmark(args);
                break;
            case "compare":
                runComparison(args);
                break;
            case "test":
                runTests(args);
                break;
            default:
                System.out.println("Unknown command: " + command);
                printUsage();
        }
    }
    
    private static void printUsage() {
        System.out.println("HeapSort Benchmark Runner");
        System.out.println("Usage:");
        System.out.println("  benchmark <sizes> [output]");
        System.out.println("    sizes: comma-separated array sizes (e.g., 100,1000,5000)");
        System.out.println("    output: optional CSV output filename");
        System.out.println();
        System.out.println("  compare <size> <runs>");
        System.out.println("    size: array size to test");
        System.out.println("    runs: number of runs to average");
        System.out.println();
        System.out.println("  test <type>");
        System.out.println("    type: sorted, reverse, random, duplicates");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  benchmark 100,1000,5000 results.csv");
        System.out.println("  compare 1000 10");
        System.out.println("  test sorted");
    }
    
    private static void runBenchmark(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: benchmark requires sizes parameter");
            return;
        }
        
        String[] sizeStrings = args[1].split(",");
        List<Integer> sizes = new ArrayList<>();
        
        for (String sizeStr : sizeStrings) {
            try {
                sizes.add(Integer.parseInt(sizeStr.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid size '" + sizeStr + "'");
                return;
            }
        }
        
        String outputFile = args.length > 2 ? args[2] : "benchmark_results.csv";
        
        System.out.println("Running benchmark with sizes: " + sizes);
        System.out.println("Output file: " + outputFile);
        System.out.println();
        
        PerformanceTracker.clearHistory();
        
        for (int size : sizes) {
            System.out.println("Testing size: " + size);
            
            int[] arr = generateRandomArray(size);
            PerformanceTracker tracker = new PerformanceTracker("HeapSort");
            
            long startTime = System.nanoTime();
            HeapSort.heapSort(arr, tracker);
            long endTime = System.nanoTime();
            
            if (!HeapSort.isSorted(arr)) {
                System.out.println("ERROR: Array not sorted correctly!");
                return;
            }
            
            tracker.save();
            
            System.out.println("  Comparisons: " + tracker.getComparisons());
            System.out.println("  Swaps: " + tracker.getSwaps());
            System.out.println("  Recursive Calls: " + tracker.getRecursiveCalls());
            System.out.println("  Time: " + String.format("%.3f", tracker.getTimeMs()) + " ms");
            System.out.println();
        }
        
        try {
            PerformanceTracker.exportCSV(outputFile);
            System.out.println("Results saved to: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error saving results: " + e.getMessage());
        }
    }
    
    private static void runComparison(String[] args) {
        if (args.length < 3) {
            System.out.println("Error: compare requires size and runs parameters");
            return;
        }
        
        int size, runs;
        try {
            size = Integer.parseInt(args[1]);
            runs = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid size or runs parameter");
            return;
        }
        
        System.out.println("Running " + runs + " comparisons with size " + size);
        System.out.println();
        
        long totalComparisons = 0;
        long totalSwaps = 0;
        long totalRecursiveCalls = 0;
        double totalTime = 0;
        
        for (int i = 1; i <= runs; i++) {
            System.out.print("Run " + i + "/" + runs + ": ");
            
            int[] arr = generateRandomArray(size);
            PerformanceTracker tracker = new PerformanceTracker("HeapSort");
            
            HeapSort.heapSort(arr, tracker);
            
            if (!HeapSort.isSorted(arr)) {
                System.out.println("ERROR: Array not sorted correctly!");
                return;
            }
            
            totalComparisons += tracker.getComparisons();
            totalSwaps += tracker.getSwaps();
            totalRecursiveCalls += tracker.getRecursiveCalls();
            totalTime += tracker.getTimeMs();
            
            System.out.println(String.format("%.3f", tracker.getTimeMs()) + " ms");
        }
        
        System.out.println();
        System.out.println("Average Results:");
        System.out.println("  Comparisons: " + (totalComparisons / runs));
        System.out.println("  Swaps: " + (totalSwaps / runs));
        System.out.println("  Recursive Calls: " + (totalRecursiveCalls / runs));
        System.out.println("  Time: " + String.format("%.3f", totalTime / runs) + " ms");
    }
    
    private static void runTests(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: test requires type parameter");
            return;
        }
        
        String type = args[1];
        int[] arr;
        
        switch (type) {
            case "sorted":
                arr = generateSortedArray(100);
                System.out.println("Testing with sorted array (100 elements)");
                break;
            case "reverse":
                arr = generateReverseArray(100);
                System.out.println("Testing with reverse sorted array (100 elements)");
                break;
            case "random":
                arr = generateRandomArray(100);
                System.out.println("Testing with random array (100 elements)");
                break;
            case "duplicates":
                arr = generateDuplicateArray(100);
                System.out.println("Testing with array containing duplicates (100 elements)");
                break;
            default:
                System.out.println("Error: Unknown test type '" + type + "'");
                System.out.println("Valid types: sorted, reverse, random, duplicates");
                return;
        }
        
        System.out.println("Before: " + Arrays.toString(Arrays.copyOf(arr, Math.min(10, arr.length))));
        
        PerformanceTracker tracker = new PerformanceTracker("HeapSort");
        HeapSort.heapSort(arr, tracker);
        
        System.out.println("After: " + Arrays.toString(Arrays.copyOf(arr, Math.min(10, arr.length))));
        System.out.println();
        System.out.println("Results:");
        System.out.println("  Sorted: " + HeapSort.isSorted(arr));
        System.out.println("  Comparisons: " + tracker.getComparisons());
        System.out.println("  Swaps: " + tracker.getSwaps());
        System.out.println("  Recursive Calls: " + tracker.getRecursiveCalls());
        System.out.println("  Time: " + String.format("%.3f", tracker.getTimeMs()) + " ms");
    }
    
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }
    
    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
    
    private static int[] generateReverseArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }
    
    private static int[] generateDuplicateArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }
}
