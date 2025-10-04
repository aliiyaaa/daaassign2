# Heap Sort Implementation

A comprehensive, student-friendly implementation of the Heap Sort algorithm with in-place sorting and bottom-up heapify approach.

## 📚 What is Heap Sort?

Heap Sort is a comparison-based sorting algorithm that uses a binary heap data structure. It's particularly efficient because:

- **Time Complexity**: O(n log n) in all cases (best, average, and worst)
- **Space Complexity**: O(1) - in-place sorting
- **Stability**: Not stable (relative order of equal elements may change)

## 🏗️ Project Structure

```
Heap/
├── src/
│   ├── main/java/org/example/
│   │   ├── Main.java          # Demonstration and examples
│   │   └── HeapSort.java      # Core heap sort implementation
│   └── test/java/org/example/
│       └── HeapSortTest.java  # Comprehensive test suite
├── pom.xml                    # Maven configuration
└── README.md                  # This file
```

## 🚀 How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Demonstration
```bash
# Compile and run the main demonstration
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

### Running Tests
```bash
# Run all tests
mvn test

# Run tests with detailed output
mvn test -Dtest=HeapSortTest
```

### Compiling Only
```bash
mvn compile
```

## 📖 Key Features

### 1. In-Place Implementation
- No additional memory allocation needed
- Sorts the array directly in its original location
- Space complexity: O(1)

### 2. Bottom-Up Heapify
- More efficient than top-down approach
- Builds heap from the last non-leaf node upwards
- Reduces the number of comparisons needed

### 3. Comprehensive Testing
- 14 different test scenarios
- Edge cases: empty arrays, single elements, already sorted
- Performance testing with large arrays
- In-place sorting verification

### 4. Student-Friendly Code
- Detailed comments explaining each step
- Clear method names and structure
- Educational examples and demonstrations

## 🔍 Algorithm Explanation

### Step 1: Build Max Heap
```java
// Start from the last non-leaf node and work backwards
for (int i = n / 2 - 1; i >= 0; i--) {
    heapify(arr, n, i);
}
```

### Step 2: Extract Elements
```java
// Move largest element to end and heapify reduced heap
for (int i = n - 1; i > 0; i--) {
    swap(arr, 0, i);           // Move root to end
    heapify(arr, i, 0);        // Heapify reduced heap
}
```

### Step 3: Heapify Process
```java
// Maintain max-heap property
private static void heapify(int[] arr, int heapSize, int rootIndex) {
    int largest = rootIndex;
    int leftChild = 2 * rootIndex + 1;
    int rightChild = 2 * rootIndex + 2;
    
    // Find largest among root and children
    if (leftChild < heapSize && arr[leftChild] > arr[largest]) {
        largest = leftChild;
    }
    if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
        largest = rightChild;
    }
    
    // If largest is not root, swap and continue
    if (largest != rootIndex) {
        swap(arr, rootIndex, largest);
        heapify(arr, heapSize, largest);
    }
}
```

## 🧪 Test Cases Covered

1. **Basic Sorting**: Normal unsorted array
2. **Already Sorted**: Ascending order array
3. **Reverse Sorted**: Descending order array
4. **Duplicates**: Array with repeated elements
5. **Single Element**: Array with one element
6. **Empty Array**: Zero-length array
7. **Two Elements**: Minimal case
8. **Negative Numbers**: Array with negative values
9. **All Same**: Array with identical elements
10. **In-Place Verification**: Confirms no extra memory usage
11. **Large Array**: Performance testing
12. **Null Handling**: Null array safety
13. **Helper Methods**: Testing utility functions
14. **Copy Array**: Testing array copying functionality

## 📊 Performance Characteristics

| Scenario | Time Complexity | Space Complexity |
|----------|----------------|------------------|
| Best Case | O(n log n) | O(1) |
| Average Case | O(n log n) | O(1) |
| Worst Case | O(n log n) | O(1) |

## 🎯 Learning Objectives

After studying this implementation, you should understand:

1. **Heap Data Structure**: How binary heaps work
2. **Heapify Process**: Bottom-up vs top-down approaches
3. **In-Place Sorting**: Memory-efficient algorithms
4. **Algorithm Analysis**: Time and space complexity
5. **Testing Strategies**: Comprehensive test case design
6. **Code Organization**: Clean, maintainable code structure

## 🔧 Customization

### Adding New Test Cases
Add new test methods to `HeapSortTest.java`:

```java
@Test
void testYourNewScenario() {
    int[] arr = {your, test, data};
    HeapSort.heapSort(arr);
    assertTrue(HeapSort.isSorted(arr));
}
```

### Modifying the Algorithm
The core algorithm is in `HeapSort.java`. Key methods to understand:
- `heapSort()`: Main sorting method
- `heapify()`: Maintains heap property
- `swap()`: Utility for element swapping

## 📝 Assignment Ideas

1. **Implement Min-Heap Sort**: Modify to sort in descending order
2. **Add Visualization**: Create a method to print heap structure
3. **Performance Comparison**: Compare with other sorting algorithms
4. **Generic Implementation**: Make it work with any comparable type
5. **Iterative Version**: Rewrite heapify using loops instead of recursion

## 🤝 Contributing

This is a student project, but feel free to:
- Add more test cases
- Improve documentation
- Optimize the algorithm
- Add new features

## 📚 Further Reading

- [Heap Sort on Wikipedia](https://en.wikipedia.org/wiki/Heapsort)
- [Binary Heap Data Structure](https://en.wikipedia.org/wiki/Binary_heap)
- [Introduction to Algorithms by Cormen, Leiserson, Rivest, and Stein](https://mitpress.mit.edu/books/introduction-algorithms)

---

**Happy Learning! 🎓**

*Remember: Understanding the algorithm is more important than memorizing the code. Take time to trace through the examples and understand why each step is necessary.*
