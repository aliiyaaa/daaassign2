package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long recursiveCalls;
    private long startTime;
    private long endTime;
    private int arraySize;
    private String algorithm;
    
    private static List<PerformanceTracker> history = new ArrayList<>();
    
    public PerformanceTracker(String algorithm) {
        this.algorithm = algorithm;
        reset();
    }
    
    public void reset() {
        comparisons = 0;
        swaps = 0;
        recursiveCalls = 0;
        startTime = 0;
        endTime = 0;
        arraySize = 0;
    }
    
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }
    
    public void setArraySize(int size) {
        this.arraySize = size;
    }
    
    public void addComparison() {
        comparisons++;
    }
    
    public void addSwap() {
        swaps++;
    }
    
    public void addRecursiveCall() {
        recursiveCalls++;
    }
    
    public double getTimeMs() {
        return (endTime - startTime) / 1_000_000.0;
    }
    
    public long getComparisons() {
        return comparisons;
    }
    
    public long getSwaps() {
        return swaps;
    }
    
    public long getRecursiveCalls() {
        return recursiveCalls;
    }
    
    public int getArraySize() {
        return arraySize;
    }
    
    public String getAlgorithm() {
        return algorithm;
    }
    
    public void save() {
        history.add(this);
    }
    
    public static void exportCSV(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Algorithm,ArraySize,Comparisons,Swaps,RecursiveCalls,TimeMs\n");
        
        for (PerformanceTracker tracker : history) {
            writer.write(tracker.getAlgorithm() + ",");
            writer.write(tracker.getArraySize() + ",");
            writer.write(tracker.getComparisons() + ",");
            writer.write(tracker.getSwaps() + ",");
            writer.write(tracker.getRecursiveCalls() + ",");
            writer.write(String.format("%.3f", tracker.getTimeMs()) + "\n");
        }
        
        writer.close();
    }
    
    public static void clearHistory() {
        history.clear();
    }
    
    public void print() {
        System.out.println("Algorithm: " + algorithm);
        System.out.println("Array Size: " + arraySize);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Recursive Calls: " + recursiveCalls);
        System.out.println("Time: " + String.format("%.3f", getTimeMs()) + " ms");
        System.out.println();
    }
}
