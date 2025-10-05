Heap Sort Implementation

This is my Java implementation of the Heap Sort algorithm, built for my Design and Analysis of Algorithms course.
I implemented the core algorithm along with a performance tracking system, a CLI tool for benchmarking, and a full set of unit tests to validate correctness and efficiency.

📌 What is Heap Sort?

Heap Sort is a comparison-based sorting algorithm that uses a binary heap data structure to build a max-heap and then repeatedly extracts the maximum element to sort the array.

It’s in-place (doesn’t require extra arrays like Merge Sort)

Has a worst-case time complexity of O(n log n)

Works by:

Building a max-heap from the array

Swapping the first (max) element with the last unsorted element

Reducing the heap size and heapifying again until sorted

Heap Sort is very efficient in terms of time and space, though it’s not stable and usually slower than QuickSort in practice because of more comparisons and swaps.

📂 What’s included

✅ Heap Sort implementation in Java (in-place, bottom-up heapify)

🧪 Comprehensive test suite with edge cases and randomized tests

📈 Benchmarking tools to measure:

Execution time

Number of comparisons

Number of swaps

Number of recursive calls

🖥️ CLI tool to run benchmarks, comparisons, and export performance data as CSV

📊 Example benchmark data included

⚙️ Setup

You’ll need:

Java 17 or higher

Maven 3.6+

Clone the repo and build it:

git clone <repository-url>
cd HeapSort

# Compile the project
mvn compile

# Run tests to check everything
mvn test

🚀 How to Use
Compile
mvn compile

Run Benchmark CLI

You can run the provided CLI tool to benchmark the Heap Sort algorithm on different input sizes.

Benchmark example:

java -cp target/classes org.example.cli.BenchmarkRunner benchmark 100,500,1000 results.csv


Compare performance over multiple runs:

java -cp target/classes org.example.cli.BenchmarkRunner compare 1000 10


Test on different array patterns:

java -cp target/classes org.example.cli.BenchmarkRunner test random
java -cp target/classes org.example.cli.BenchmarkRunner test sorted
java -cp target/classes org.example.cli.BenchmarkRunner test reverse
java -cp target/classes org.example.cli.BenchmarkRunner test duplicates

🧠 Algorithm Details
Time Complexity
Case	Complexity
Best	O(n log n)
Average	O(n log n)
Worst	O(n log n)

Heap Sort performs the same number of operations regardless of input distribution, making it more predictable than algorithms like QuickSort.

Space Complexity

O(1) auxiliary space (in-place sorting)

The heap is built directly in the input array

📊 Performance Results

I ran some benchmarks on my machine using random arrays of various sizes.
Here are the sample results (from benchmark_results.csv):

Algorithm	Array Size	Comparisons	Swaps	Recursive Calls	Time (ms)
HeapSort	100	1,024	581	482	0.123
HeapSort	500	7,461	4,073	3,574	0.123
HeapSort	1000	16,850	9,070	8,071	0.226

Multiple run averages (multiple_runs.csv):

Algorithm	Array Size	Comparisons	Swaps	Recursive Calls	Time (ms)
HeapSort	10	35	21	12	0.003
HeapSort	50	379	207	158	0.004
HeapSort	100	944	516	417	0.007

These numbers may vary depending on your CPU and JVM configuration.

🧪 Testing

All functionality is covered by JUnit 5 test cases:

# Run all tests
mvn test


The tests cover:

✅ Empty arrays, single elements, duplicates, negatives, mixed inputs

✅ Sorted and reverse-sorted arrays

✅ Performance tracking (comparisons, swaps, time)

✅ CSV export validation

✅ Copy and helper methods

📁 Project Structure
HeapSort/
├── src/main/java/org/example/
│   ├── algorithms/
│   │   └── HeapSort.java              # Core sorting algorithm
│   ├── cli/
│   │   └── BenchmarkRunner.java      # CLI for benchmarks & tests
│   └── metrics/
│       └── PerformanceTracker.java   # Tracks comparisons, swaps, etc.
├── src/test/java/org/example/algorithms/
│   └── HeapSortTest.java             # Comprehensive test suite
├── pom.xml                           # Maven config
└── README.md                         # This file

📚 What I Learned

While working on this project, I learned:

How Heap Sort works internally with heapify operations

How to benchmark algorithms properly (time, swaps, comparisons)

How to build CLI tools for algorithm testing

How to structure and write JUnit tests for algorithms

How to export and analyze performance data in CSV format

⚠️ Issues & Limitations

Not stable (equal elements may change order)

For very large arrays (>1M elements), performance depends on JVM optimizations

Currently supports only integer arrays

🌟 Future Improvements

 Add Min-Heap Sort version

 Add support for generic types

 Add parallel Heap Sort for large datasets

 Create visualizations for heap structure during sorting

 Improve CLI with more configuration options

📝 References

Heap Sort - Wikipedia

CLRS - Introduction to Algorithms

This project was part of my coursework and helped me better understand both algorithm design and performance measurement. 🧠💻
