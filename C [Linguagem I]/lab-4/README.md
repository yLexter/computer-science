# Lab 4

In this roadmap, we will review the use of conditions in our C program solutions, as well as begin using repetitions as flow control structures.

## Reviewing the Use of Conditional Structures

In the "Lab 2" script we see the first way to control the flow of outputs in a program, the `if-else` structure, which basically tests whether an expression is true or false (starting from the reserved word `if`), may or may not have an escape valve (the contents of the `else` block).

The switch command successively tests the value of an expression against a list of integer or character constants. When the value matches, the associated commands are executed. A switch can have at least 257 case statements (C ANSI).

Note that, in both structures, there is a block of code that will be associated with a condition being true. For `if-else` the construction is more dynamic, and can be replaced by the ternary operator `?`; In `switch`, it is a little more restricted to the evaluation of clauses (cases) with constant values.

To recap, see the example of solving the problem "Good morning, good afternoon or good evening" using the structures we have studied so far:

```
#include <stdio.h>

int main()
{
 int shift = 0;
 printf("Enter 1-Morning, 2-Evening and 3-Evening: ");
 scanf("%d", &turn);

 // resolution using if-else
 if (shift == 1)
 printf("Good morning!");
 else if (turn == 2)
 printf("Good afternoon!");
 else if (turn == 3)
 printf("Good evening!");
 else
 printf("Invalid Value!");

 // resolution using ternary operator
 shift == 1 ? printf("Good morning!") : shift == 2 ? printf("Good afternoon!") : shift == 3 ? printf("Good evening!") : printf("Invalid Value!");

 // resolution using switch cases
 switch (shift) {
 case 1:
 printf("Good morning!");
 break;
 case 2:
 printf("Good afternoon!");
 break;
 case 3:
 printf("Good evening!");
 break;
 default:
 printf("Invalid Value!");
 break;
 }

 return 0;
}
```

**Exercise 1**: Make a program that asks for the name and price of two products and tells you which product you should buy, knowing that the decision is always for the cheapest.

**Exercise 2**: Improve the solution from the previous program by asking the price of three products and then informing which product is the most expensive.

**Challenge 1**: Make a Program that asks for the value of the 3 sides of a triangle. The program should inform you whether the values ​​can be a triangle. Indicate, if the sides form a triangle, whether it is: equilateral, isosceles or scalene. Three sides form a triangle when the sum of any two sides is greater than the third. Tips:
- Equilateral Triangle: three equal sides;
- Isosceles Triangle: any two equal sides;
- Scalene Triangle: three different sides

## Repetition Structures: while

Repetition structures allow a set of instructions to be executed until a certain condition occurs or until a certain condition is no longer satisfied. The first structure we will work on, `while` (which means "while"), works very similarly to the `if` block: the difference is that, in `if`, what is inside the block is only executed once. In the `while` block, what is inside the block can be executed more than once.

```
 if (expression)
 {
 command;
 }

 while (expression)
 {
 command;
 }
```

As it is not known how many times the condition will be satisfied, we say that the `while` repetition structure has an open ending.

Let's look at a first example: a number counter. Suppose we want to count how many numbers the user types on the keyboard. Implementing this solution without knowing exactly how many times the user will type something is complicated, right?

```
#include <stdio.h>

int main()
{
 int counter = 0;
 int number = 0;

 // how many times do I need to ask the user to type?
 printf("Enter a number: ");
 scanf("%d", &number);
 counter++;

 printf("Enter a number: ");
 scanf("%d", &number);
 counter++;

 printf("Enter a number: ");
 scanf("%d", &number);
 counter++;

 printf("Enter a number: ");
 scanf("%d", &number);
 counter++;

 // ...

 printf("You entered %d numbers", counter);

 return 0;
}
```

See that we have some repeated code snippets. These sections will inevitably be suppressed as one within a `while` block:

```
#include <stdio.h>

int main()
{
 int counter = 0;
 int number = 0;

 printf("Enter a number: ");
 scanf("%d", &number);

 while () {
 counter++;
 printf("Enter a number: ");
 scanf("%d", &number);
 }

 printf("You entered %d numbers", counter);

 return 0;
}
```

Much better, right? What remains to complete our program is to establish the stopping condition (or continuity, depending on your point of view). We can think about the following: when the user no longer enters a number, the count must end. Therefore, our condition for the program to continue is: as long as the user types a number.

In C, we have the value `EOF` (it can mean Ctrl + Z, ^Z or even -1) to determine the absence of data, that is, when the user simply presses Enter. So, with the help of this value, our program would look like this:

```
#include <stdio.h>

int main()
{
 int counter = 0;
 printf("Enter a number: ");
 scanf("%d", &number);

 while (number != EOF) {
 counter++;
 printf("Enter a number: ");
 scanf("%d", &number);
 }

 printf("You entered %d numbers", counter);

 return 0;
}
```

~~**Exercise 3**: Write a program that reads a username and password and does not accept the password that is the same as the username, showing an error message and asking for the information again.~~

**Challenge 2**: Make an uppercase and lowercase vowel counter program. Consonants and symbols must be ignored, and, at the end of the program, show the number of uppercase and lowercase vowels that the user entered. _Tip: use switch case._