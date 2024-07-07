# Lab 3

We will learn about some of the flow control structures in the C language, understanding all the alternatives to evaluate conditions and produce different outputs for our programs.

## Reviewing the most basic decision structure: if-else

To decide between two possible solutions to a problem, you need to compare values ​​and decide what to do. In C, we use `if` followed by an expression to denote that from then on, a new block of code will be executed given that a condition is met.

The word `else` means otherwise, that is, if the condition of the `if` expression is not satisfied, then what will be inside the else block will be executed.

Only the code associated with `if` or the code associated with `else` will be executed, never both.

The general form of the **if-else** sentence is as follows:

```
 if (expression)
 {
 command;
 }
 else
 {
 command;
 }
```

Attention: `else` is not always necessary, that is, it is optional.

**Exercise 1**: Write a Program that asks for a value and shows on the screen whether the value is a multiple of 3, 4 or 5. Tip: see the even or odd example from the previous lab.

## Ternary Operator: ?

There is an economical way to write an if-else where only one expression is evaluated, using the ternary operator `?`.

The generic way to write this operator is as in the following example:

```
expression? command: otherCommand;
```

For example, the odd or even number problem could be solved in a single line, as follows:

```
#include <stdio.h>

int main() {
 int num;

 printf("Enter a number: ");
 scanf("%d", &num);

 num % 2 == 0 ? printf("The number is even!") : printf("The number is odd!");

 return 0;
}
```

It is worth remembering that it is possible to write several nested expressions with ternary operators, instead of one command.

```
exp1 ? command: exp2? command: otherCommand;
```

**Exercise 2**: Rewrite the solution to challenge 2 from the previous lab using the ternary operator. Replace the possible entries with numbers (1-Morning, 2-Evening and 3-Night).

## Nested Conditions

An `if` statement can be the object of another `if` or `else`. Nested conditions are common in programming.

In C, `else` always refers to the nearest `if`, which is within the same block as `else` and is not associated with another `if`. To understand this concept, consider the following generic example.

```
if(exp1)
{
 if(exp2) command 1;
 if(exp3) command 2; // this if is associated
 else command 3; // to this else
} else command 4; // this one is associated with if(exp1)
```

The ANSI C standard specifies that at least 15 levels of nesting must be supported. In practice, most compilers allow more than this.

**Exercise 3**: Make a program to read two partial notes from a student. The program must calculate the average achieved per student and present:
The message "Approved", if the average achieved is greater than or equal to seven;
The message "Failed" if the average is less than seven;
The message "Approved with Distinction", if the average is ten.

## A more formal decision structure: switch

The switch command successively tests the value of an expression against a list of integer or character constants. When the value matches, the associated commands are executed. A switch can have at least 257 case statements (C ANSI).

Its basic structure has the following commands:

```
switch (expr) {
 case const1:
  sequence of commands;
  break;
 case const2:
  sequence of commands;
  break;
  ...
 case constn:
  sequence of commands;
  break;
 default:
  sequence of commands;
  break;
}
```

The expression in this case can be a variable of type `int` or a `char`, which can be received from the keyboard or not.

The clauses (cases) `const1`, `const2`, ..., `constn` are types of `if` that evaluate whether the expression is equal to the value of `case`. If any of the values ​​match, then the block will be executed.

The `default` clause must always be included, otherwise values ​​not explicitly tested in a `switch` will be ignored. And, finally, in each clause it is necessary to use the reserved word `break`.

To understand better, see the following example. To solve the shift problem, we could create a `switch`, starting with the standard case, where no valid letter is identified.

```
#include <stdio.h>

int main()
{
 int shift = 0;
 printf("Enter 1-Morning, 2-Evening and 3-Evening: ");
 scanf("%d", &turn);

 switch (shift) {

 default:
 printf("Invalid Value!");
 break;
 }

 return 0;
}
```

Let's include the first clause: when recognizing the value 1, "Good morning!" is printed;

```
#include <stdio.h>

int main()
{
 int shift = 0;
 printf("Enter 1-Morning, 2-Evening and 3-Evening: ");
 scanf("%d", &turn);

 switch (shift) {
 case 1:
 printf("Good morning!");
 break;
 default:
 printf("Invalid Value!");
 break;
 }

 return 0;
}

```

Likewise, the other clauses can be included:

```
#include <stdio.h>

int main()
{
 int shift = 0;
 printf("Enter 1-Morning, 2-Evening and 3-Evening: ");
 scanf("%d", &turn);

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

**Exercise 4**: Simulate, with the help of a switch, a telephone service at a bank. There are five options: check your balance, take out a loan, check your credit card statement, request a new card or speak directly to an attendant, the latter being the default option.

**Challenge 1**: (Use `if` and `switch` in your solution) Write a program that reads the two partial grades obtained by a student in a subject over the course of a semester, and calculates their average. The attribution of concepts follows the table below:

```
Average Utilization Concept
 Between 9.0 and 10.0 A
 Between 7.5 and 9.0 B
 Between 6.0 and 7.5 C
 Between 4.0 and 6.0 D
 Between 4.0 and zero E
```

The algorithm must show on the screen the grades, the average, the corresponding concept and the message “PASSED” if the concept is A, B or C or “FAIL” if the concept is D or E.