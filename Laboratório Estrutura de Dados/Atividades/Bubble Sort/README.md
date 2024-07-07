# Performance Analysis of Sorting Algorithms

In this performance analysis, we examine the execution time and number of comparisons for two Bubble Sort algorithms: "Classic" and "Optimized".

# Computer Specifications
- Intel Core i5 2410M processor
- 8GB RAM

## For a vector of 1,000 elements

### Disordered Vectors

- Algorithm: Classic
 - Execution Time: 16ms
 - Total Comparisons: 999,000

- Algorithm: Optimized
 - Execution Time: 12ms
 - Total Comparisons: 499,175

### Ordered Vectors

- Algorithm: Classic
 - Execution Time: 3ms
 - Total Comparisons: 999,000

- Algorithm: Optimized
 - Execution Time: 0ms
 - Total Comparisons: 999

## For a vector of 10,000 elements

### Disordered Vectors

- Algorithm: Classic
 - Execution Time: 385ms
 - Total Comparisons: 99,990,000

- Algorithm: Optimized
 - Execution Time: 322ms
 - Total Comparisons: 49,992,515

### Ordered Vectors

- Algorithm: Classic
 - Execution Time: 238ms
 - Total Comparisons: 99,990,000

- Algorithm: Optimized
 - Execution Time: 1ms
 - Total Comparisons: 9,999

## For a vector with 100,000 elements

### Disordered Vectors

- Algorithm: Classic
 - Execution Time: 30,559ms
 - Total Comparisons: 1,409,965,408

- Algorithm: Optimized
 - Execution Time: 22,839ms
 - Total Comparisons: 704,867,744

### Ordered Vectors

- Algorithm: Classic
 - Execution Time: 18,017ms
 - Total Comparisons: 1,409,965,408

- Algorithm: Optimized
 - Execution Time: 1ms
 - Total Comparisons: 99,999

## Complexities

- **Optimized**
 - Ordered: O(n)
 - Unordered: O(≅ n²/2)

- **Traditional**
 - Ordered: O(n²)
 - Unordered: O(n²)