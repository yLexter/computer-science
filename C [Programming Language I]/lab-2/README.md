# Lab 2

Deepening our knowledge of the C language, we will see the power of operators and the increase in functionality of our programs with the addition of conditional flows.

## First of all, the types

Without forgetting that we are working with a strongly typed language, C has the following types:

Type | Size (approx. in bits) | Value range (minimum)
-- | -- | --
char | 8 | -127 to 127
unsigned char | 8 | 0 to 255
signed char | 8 | -127 to 127
int | 16 | -32,767 to 32,767
unsigned int | 16 | 0 to 65,535
signed int | 16 | Same as `int`
short int | 16 | Same as `int`
unsigned short int | 16 | 0 to 65,535
signed short int | 16 | Same as `short int`
long int | 32 | -2,147,483,647 to 2,147,483,647
signed long int | 32 | Same as `long int`
unsigned long int | 32 | 0 to 4,294,967,295
float | 32 | Six digits of precision
double | 64 | Ten digits of precision
long double | 80 | Ten digits of precision

Knowing this, we can deal with our first operator, the assignment operator.

## Assignment Operators

The assignment operator `=` can be used within any valid expression in C: `variable_name = expression;`, where the expression can be as simple as a constant or as complex as your need. See the following examples:

```
int main() {
 int age = 29;

 char initialName = "R";

 float weight = 74.5;
 float height = 1.80;
 float imc = calculateImc(weight, height);
 return 0;
}

float calculateImc(float weight, float height) {
 // BMI calculation
 return
}
```

The destination, or left part, of the assignment must be a variable or a pointer (we will see what this means later).

In an assignment statement, the type conversion rule is simple: the value on the right side of an assignment is converted to the type on the left side. To better understand this operation, let's look at the following code:

```
intx;
char ch;
float f;

int main()
{
  ch = x; /* line 1 */
  x = f; /* line 2 */
  f = ch; /* line 3 */
  f = x; /* line 4 */
 return 0;
}
```
In line 1, the most significant bits of the integer `x` are ignored, leaving `ch` with the 8 least significant bits. If `x` is between 256 and 0, `ch` and `x` have the same value; otherwise, `ch` reflects only the least significant bits of `x`.

In line 2, `x` receives only the integer part of `f`.

On line 3, `f` converts the 8-bit integer value of `ch` to the same floating point value.

On line 4, `f` converts the 16-bit integer value to floating point format.

See some type conversions into assignments in the following table:

Destination type | Expression type | Possible lost information
-- | -- | --
signed char | char | If value > 127, the target is negative
char | short int | The 8 most significant bits
char | int | The 8 most significant bits
char | long int | The 24 most significant bits
int | long int | The 16 most significant bits
int | float | The fractional part, at least
float | double | Precision, the result is rounded
double | long double | Precision, the result is rounded

Finally, in C it is possible to perform multiple assignments, as in the following example:

```
int main() {
 int x, y, z;
  x = y = z = 0;
 return 0;
}
```

In addition to the `=` operator, we have the possibility of combining it with mathematical operators (`+` for addition, `-` for subtraction, `*` for multiplication, `/` for division and `%` for remainder of division) .

```
int main() {
 int c = 3, d = 5, e = 4, f = 6, g = 12;

 c += 7; /* even if c = c + 7, it results in c = 10 */
 d -= 4; /* even if d = d - 4, results in d = 1 */
 and *= 5; /* even if e = e * 5, results in e = 20 */
 f /= 3; /* even if f = f / 3, results in f = 2 */
 g %= 9; /* even if g = g % 9, results in g = 3 */

 return 0;
}
```

**Exercise 1**: Write a program that receives a negative integer and a positive decimal number. Then convert the first number to a decimal and the decimal to an integer, testing at least two different types of conversions. Also perform a combined operation with assignment with each number after conversion.

## Increment and Decrement Operators

For this type of operation, it is only possible to work with addition and subtraction, in two different orders in relation to the variable. Observe the following table:

Operator | Example | Meaning
-- | -- | --
++ | ++a | Increment `a` by 1, then use the new value of `a` in the expression where `a` resides.
++ | a++ | Uses the current value of `a` in the expression where `a` resides, then increments `a` by 1.
-- | --b | Decrease `b` by 1 and then use the new value of `b` in the expression where `b` resides.
-- | b-- | Uses the current value of `b` in the expression where `b` resides, then decrements `b` by 1.

To make it clearer, look at the following example:

```
#include <stdio.h>

int main() {
 int c = 5;
 printf("%d", c); // print the value of c before post-increment
 printf("%d", c++); // print 5 and then increment
 printf("%d", c); // print ime 6

 int c = 5;
 printf("%d", c); // print the value of c before pre-increment
 printf("%d", ++c); // increment c and then print 6
 printf("%d", c); // print 6

 return 0;
}
```

**Exercise 2**: Check the result, given two numbers `a` and `b` provided by the user, of the following operations:
1) a = 5; b = a * (a++);
2) a = 5; b = a * ++a;
3) a = 5; b = a * a++;

## Comparison Operators

Before we start enhancing our programs with conditional flows, it is important to know the operators involved in the comparison, firstly, understanding what the equality operators are:

Operator | Example | Condition evaluated
-- | -- | --
== | x == y | `x` is equal to `y`
!= | x != y | `x` is different from `y`

And then, the relative operators:

Operator | Example | Condition evaluated
-- | -- | --
Operator > | x > y | `x` is greater than `y`
Operator < | x < y | `x` is smaller than `y`
Operator >= | x >= y | `x` is greater than or equal to `y`
Operator <= | x <= y | `x` is less than or equal to `y`

Operators return the entire result, with the result being 0 when the expression is false and 1 when true. To test, let's print as in the example:

```
#include <stdio.h>

int main() {
 int x = 5;
 int y = 10;

 printf("%d\n", x < y); // print 1
 printf("%d\n", x == y); // print 0

 return 0;
}
```

**Exercise 3**: Write a program that receives two integer values ​​and compares them, printing the results of the comparisons.

**Challenge 1**: Research how to print "true" or "false" instead of 0 or 1 using the ternary operator `?`.

These comparisons will help us make decisions in our program. Let's start with the simplest structure, the **if-else**.

## Decision Structure: if-else

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

See the following example: To check whether a number is even or odd, we use the `%` operator: if the remainder of the division is 0, the number is even, otherwise, it will be odd.

One way to resolve this problem is as follows:

```
#include <stdio.h>

int main() {
 int num;

 printf("Enter a number: ");
 scanf("%d", &num);

 if (num % 2 == 0)
 {
 printf("The number is even!");
 }
 else
 {
 printf("The number is odd!");
 }

 return 0;
}
```

See that, when evaluating the expression `num % 2 == 0` (the remainder of division by 2 is equal to zero), I open two blocks of code. One for if the condition is true and the other if it is false.

As each block only has one line, we could give up the keys (only in this case).

```
#include <stdio.h>

int main() {
 int num;

 printf("Enter a number: ");
 scanf("%d", &num);

 if (num % 2 == 0) printf("The number is even!");
 else printf("The number is odd!");

 return 0;
}
```

And there is one more way: as `else` is optional, we can do without it too.

```
#include <stdio.h>

int main() {
 int num;

 printf("Enter a number: ");
 scanf("%d", &num);

 if (num % 2 == 0) printf("The number is even!");
 printf("The number is odd!");

 return 0;
}
```

**Exercise 4**: Make a Program that asks for a value and shows on the screen whether the value is positive or negative.

**Challenge 2**: Make a Program that asks which shift you study. Ask to enter 1-morning or 2-evening or 3-evening. Print the message "Good Morning!", "Good Afternoon!" or good night!" or "Invalid Value!", as applicable.
