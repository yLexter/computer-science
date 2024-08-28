# Search Tree Analysis

In this project, we perform a comparative analysis of search tree implementations, including the Binary Search Tree, AVL Tree, Black-Red Tree and Splay Tree. The goal is to study the performance of these trees under various conditions.

## Requirements

1. **Obtaining Implementations**: Initially, we look for the implementations of these search trees in some repository. Preferably, we obtained the implementations from the same repository to ensure consistency.

2. **Rotation Count**: In the source codes of each implementation, we modified them to record the number of rotations performed by each algorithm during tree balancing maintenance operations. We count each rotation in isolation, especially when multiple rotations are needed to correct the balance.

3. **Diverse Tests**: We run the algorithms with various masses of tests, including jumbled values, sorted values ​​in ascending order, sorted values ​​in descending order, and partially sorted values ​​in increasing order (90%) with jumbled data at the end of the sample . We perform these tests with 1,000,000, 2,000,000 and 3,000,000 non-repeating numbers.

4. **Tree Height**: After entering all the values, we inform the height of each tree and the number of rotations necessary to build the binary trees, when applicable.

5. **Tests with Repeated Numbers**: We prepared two test masses, one with non-repeating random numbers and the other with repeated numbers. For the second batch of tests, we generated random numbers in the range 0 to 300,000 to ensure repetitions.

6. **Search Operations**: We perform search operations on each algorithm and its resulting trees using the two test masses mentioned above. We compute the total time required to fetch all searched values.

7. **Analysis Report**: Finally, we present a report analyzing the heights of the trees obtained, the number of rotations required and the time required to search each tree generated. This is done based on the respective test masses from item 3, when the values ​​of the test masses from item 5 are researched.

## Conclusion

This analysis provides valuable insights into the performance and efficiency of search trees in different scenarios and can be a useful reference for making decisions regarding the choice of search algorithms in future projects.