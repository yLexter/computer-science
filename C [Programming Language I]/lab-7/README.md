# Lab 7

Continuing what we saw in the previous itinerary, we will deepen our knowledge to formulate elegant solutions using functions in the C language.

## Reviewing the use of functions

We use functions to modularize programs by combining new functions you write with functions from standard C libraries, such as the `stdio.h` standard library, with functions to handle data input and output.

Note that, in the C language, in addition to defining a function, modularizing its solution with or without the use of parameters, as we are used to in simpler languages, we define a `prototype`, that is, a kind of "skeleton" preparing the compiler to understand what our function will be like.

To remember these concepts, see the example of a function to define the largest of 3 numbers:

```
#include <stdio.h>

int maximum(int x, int y, int z);

int main(void)
{
 int n1 = 0, n2 = 0, n3 = 0;
 printf("%s", "Enter three integers: ");
 scanf("%d%d%d", &n1, &n2, &n3);

 printf("Maximum is: %d\n", maximum(n1, n2, n3));
 return 0;
}

int maximum(int x, int y, int z)
{
 int max = x;

 if (y > max)
 max = y;
 if (z > max)
 max = z;

 returnmax;
}
```

**Exercise 1**: Write a function to check whether a year is a leap year or not. Use the following rule: a leap year is divisible by 4, but not by 100, or it is divisible by 400.
Example: 1988 is a leap year because it is divisible by 4 and not by 100; 2000 is a leap year because it is divisible by 400.

**Exercise 2**: Write a function called `imprimePotencias` that takes three parameters: you will provide the initial limiting value, the final limiting value and what the exponent will be (in that order). For example, when receiving the numbers 2, 5 and 2, 4, 9, 16 and 25 will be printed.

**Challenge 1**: Build a function that takes an integer as a parameter and returns 1 if it is prime and 0 otherwise. Tip: To assess whether a number is prime, use the Sieve of Erastosthenes.

## Recursion

Recursion or recursion is the use of a function that calls itself. Yes, this is possible, a function can invoke itself!
But how does recursion work?

![](recursion.jpg)

In a recursive function, with each call a new occurrence of the function is created in memory with commands and variables “isolated” from previous occurrences.
The function runs until all occurrences have been resolved. _In other words, it would be like saying: to solve a problem, I turn to myself to solve the slightly smaller problem in the same way, and, for this slightly smaller problem, I do the same..._

However, a problem that arises when using recursion is _how to make it stop_. If the programmer is not careful, it is easy to fall into an infinite recursive loop which can even exhaust memory...

Every recursion is composed of a **base case** and **recursive calls**. The base case is the simplest case. A condition is used in which the problem is easily solved. Recursive calls, in turn, seek to simplify the problem in such a way that they converge to the base case.

### Advantages of recursion

It makes writing code simpler and more elegant, making it easy to understand and maintain.

### Disadvantages of recursion

When the recursive loop is very large, a lot of memory is consumed in calls to different levels of recursion, as each recursive call allocates memory for parameters, local and control variables. For this reason, in many cases, an iterative solution (using loops) is recommended, as it uses less memory and is more efficient in terms of performance than using recursion.

To understand better, let's turn to the most famous example of recursion, the factorial calculation.

## Example of Recursion: Factorial

The calculation of a factorial number, represented by the exclamation mark `!`, occurs with the successive multiplication of its predecessors until reaching the number 1. For example, to calculate the factorial of 5, we have: 5! = 5\*4\*3\*2\*1 = 120.

To express this solution using an iterative approach, we would have the following:

```
//Factorial calculation with iterative function
#include <stdio.h>

//prototype of the factorial function
double factorial(int n);

int main(void)
{
 int number;
 double f;

 printf("Enter the number you want to calculate the factorial: ");
 scanf("%d",&number);

 // call the factorial function
 f = factorial(number);

 printf("Factorial of %d = %.0lf",number,f);

 return 0;
}

//Iterative function that calculates the factorial
//of an integer n
double factorial(int n)
{
 double vfat = 1;
 for (; n >= 1; n--) {
 vfat *= n;
 }

 return vfat;
}
```

Note that, when expressing the solution in natural language, we mention the two important parts of a recursive function: the recursive calls (successive multiplication of predecessors) and the base case (arriving at number 1). Understanding this, we can modify our iterative solution to a recursive one: ```
// Factorial calculation with recursive function
#include <stdio.h>

// Factorial function prototype
double factorial(int n);

int main(void)
{
 int number;
 double f;

 printf("Enter the number you want to calculate the factorial: ");
 scanf("%d",&number);

 // Call the factorial function
 f = factorial(number);

 printf("Factorial of %d = %.0lf",number,f);

 return 0;
}

// Recursive function that calculates the factorial of an integer n
double factorial(int n)
{
 double vfat = 1;

 if ( n <= 1 ) // Base case: factorial of n <= 1 returns 1
 return 1;
 else
 {
 // Recursive call
 vfat = n * factorial(n - 1);
 return vfat;
 }
}
```

The execution of this code, in an illustrated way, can be understood in the following figure:

![](fibonacci.png)

Note that the base case looks like the stop condition of a loop, having the same function: preventing the program from entering an infinite loop, overflowing memory. Therefore, whenever you have difficulty solving a recursive function, an alternative is to think about iterative implementation.

**Exercise 3**: Write a recursive function to calculate the sum of all numbers from 1 to a given number n passed as a parameter.

**Challenge 2**: Write a recursive function that solves the Fibonacci sequence problem, passing as a parameter the number of the sequence you want to obtain.

**Challenge 3**: Expand the code from Challenge 1 of this script to evaluate how many prime numbers there are between 1 and 100.