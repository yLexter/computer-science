# Lab 5

In this script, we will specialize in the use of other repetition structures, controlled or not by predefined conditions, that is, which can have a determined end based on a count.

## Reviewing the Use of Repetition Structures

Repetition structures allow a set of instructions to be executed until a certain condition occurs or until a certain condition is no longer satisfied. The first structure we saw, `while` (which means "while"), works very similarly to the `if` block, whereas, with `if`, what is inside the block is executed only once; in the `while` block, what is inside the block can be executed more than once.

```
 if (expression)
 {
 command; // executed once
 }

 while (expression)
 {
 command; // executed more than once
 }
```

As it is not known how many times the condition will be satisfied, we say that the `while` repetition structure has an open ending.

If we want to create a program that prints a count from 1 to 5, we will have the following solution:

```
#include <stdio.h>

int main() {
 int counter = 1; // initialization

 while (counter <= 5) { // stop/continuity condition
 printf("%d ", counter);
 ++counter; // increment
 }
 puts("");

 return 0;
}
```

Note that we need to update the counter at the end of the `while` loop, always with a variable of type `int`. Floating point values ​​can be approximate, so controlling counting loops with floating point variables can result in inaccurate counter values ​​and inaccurate termination tests. For this reason, you should always control count loops with integer values.

**Exercise 1**: Make a program that reads 5 numbers and reports the sum of the numbers.

**Exercise 2**: Write a program that calculates the arithmetic mean of a given number of numbers, for example:

```
Average of how many numbers? 3
Enter a number: 6
Enter a number: 9
Enter a number: 7
The average of the values ​​is 7.
```

## Repetition Structures: for

In a solution involving counting, it is possible to use the `for` loop, with a less verbose syntax, as follows:

```
for(initialization; stop/continue condition; increment) {
 command;
}
```

With this loop, therefore, the counting printing solution would look like this:

```
#include <stdio.h>

int main() {
 // initialization; stop/continuity condition; increment
 for (int counter = 1; counter <= 5; ++counter) {
 printf("%d ", counter);
 }

 puts("");

 return 0;
}
```

The increment part can be rewritten in several ways, including variations to allow increments of two by two, three by three, etc.

```
counter = counter + 1
counter += 1
++accountant
counter++
```

**Exercise 3**: Rewrite the solution to exercise 2 using `for`.

**Exercise 4**: Write a program that calculates the factorial of an integer provided by the user. Example: 5! = 5.4.3.2.1 = 120.

## Repetition Structures: do-while

Again we will use `while`, but with a small difference: requiring that the content of the repetition block be executed at least once. This is because, unlike `for` and `while` loops, which test the loop condition at the beginning, `do-while` tests the condition at the end of the loop.

```
of {
 command;
} while (condition);

```

So, if we need to rewrite the solution from the first example, we will be left with the following:

```
#include <stdio.h>

int main() {
 int counter = 1; // initialization

 of {
 printf("%d ", counter);
 } while (++counter <= 5);
 puts("");

 return 0;
}
```

It is possible to merge the counter update with the condition evaluation as well, as in the previous example.

**Exercise 5**: Rewrite the solution to exercise 2 using `do-while`.

## Forcing a Loop to Stop

As with the `switch` structure, it is also possible to use the reserved word `break` to brake the execution of a repetition. For example:

```
#include <stdio.h>

int main() {
 // initialization; stop/continuity condition; increment
 for (int counter = 1; counter <= 5; ++counter) {
 printf("%d ", counter);

 if (counter == 3)
 break;
 }

 puts("");

 return 0;
}
```

An operation contrary to the brake would be to allow the loop to continue forcibly, or to skip a step. To do this, we can use the reserved word `continue`:

```
#include <stdio.h>

int main() {
 // initialization; stop/continuity condition; increment
 for (int counter = 1; counter <= 5; ++counter) {
 if (counter == 3)
 continues;

 printf("%d ", counter);

 }

 puts("");

 return 0;
}
```

**Exercise 6**: Replicate the two previous codes by reversing the order of the `printf` function and the `if` block to evaluate the effect of the words `break` and `continue`.

**Challenge 1**: Write a program that asks for two numbers, base and exponent, calculates and displays the first number mere raised to the second number. Provide several solutions using the three loops you learned, but do not use the language's power function.