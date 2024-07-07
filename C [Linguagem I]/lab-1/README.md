# Lab 1

Let's go to our lab today. You already know a little about C and can solve some simple problems using the language to program solutions to these problems, right? Let's see how to work better with storing variables, data, considering its types, and what scope is.

## Variable scope

Starting backwards, what we saw in the last class will help to define what the scope is. In most languages ​​derived from C, there is an explicit delimitation of a block of code. See the example of the main function, the `main` function:

```
#include <stdio.h>

int main()
{
 return 0;
}
```

Note that the function is a block of code, and that it must be enclosed in braces `{}`. We can call this space of the main function the _local scope_, where what is defined there is not "seen" by those outside the function.

However, consider this other example:

```
#include <stdio.h>

int sum;

int main()
{
 return 0;
}
```

The sum variable is available to be used not only in the `main` function, but in any function in this same code. This means that this variable can be "seen" throughout the code, being considered _global scope_.

Therefore, if a variable is defined outside the functions, it is of global scope, otherwise, it will be of local scope.

**Exercise 1**: Write a code that asks the user for a number, and then displays the message "The number entered was [number]". This number must be stored in a global variable.

**Exercise 2**: Rewrite the program from **exercise 2 of Lab 0**, considering that the variables that represent the numbers received from the user must be of global scope and the final result must be of local scope.

## Data Types

Among the information we can use to solve problems using C, there are five basic types: character, integer, floating point, double precision floating point and no value (_char_, _int_, _float_, _double_ and _void_, respectively).

To test, run the following code:

```
#include <stdio.h>

int main()
{
 char character;
 int integer;
 floatfloatpoint;
 double doubleprecise;

 printf("%s\n", character);
 printf("%d\n", integer);
 printf("%f\n", Floatingpoint);
 printf("%lf\n", doubleprecise);

 return 0;
}
```

You should see output similar to this:

```
(null)
0
0.000000
0.000000
```

Note that the value of `character`, without any size or information assignment, is `null`, that is, null, empty information. The whole number differs from the others because it does not have decimal places. The `float` and `double` types differ in their precision (6 and 10 digits, respectively).

To format a decimal value, we could do the following:

```
#include <stdio.h>

int main()
{
 floatfloatpoint;
 double doubleprecise;

 printf("%.6f\n", Floatingpoint);
 printf("%.10lf\n", doubleprecise);

 return 0;
}
```

And then, you should find output similar to this:

```
0.000000
0.0000000000
```

As for the character, you need to be careful to define its size. For example, if we wanted to collect just one letter from the keyboard, we could solve it as follows:

```
#include <stdio.h>

int main()
{
 char letter[1];

 printf("Enter a letter: ");
 scanf("%c", &letter);
 printf("%s", letter);

 return 0;
}
```

And then, you should find output similar to this (by typing the letter C on your keyboard):

```
Enter a letter: C
W
```

The same `char` also works for names (strings). Just set a slightly larger size, or even no size at all:

```
#include <stdio.h>

int main()
{
 char professorLP1[5] = "Ramon";
 char professorP1[] = "I don't know yet";

 printf("LP1's teacher in the afternoon is: %s", teacherLP1);
 printf("\n");
 printf("P1's teacher in the other class is: %s", teacherP1);
}
```

And then, you should find output similar to this:

```
The LP1 teacher in the afternoon is: Ramon
The P1 teacher in the other class is: I don't know yet
```

**Exercise 3**: Taking a person's name and height as input data, build a program that calculates their ideal weight, using the following formula: (72.7*height) - 58, with the result formatted with 3 digits within a final message with the name provided by the user.

**Challenge 1**: Make a Program that asks for the temperature in degrees Fahrenheit, transforms it and displays the temperature in degrees Celsius. (Format your input and output well!) The formula for conversion is: C = 5 * ((F-32) / 9).

**Challenge 2**: Create a Program that asks how much you earn per hour, the number of hours worked in the month, and the Income Tax and INSS discount percentages.
Calculate and display your total salary for that month in a program that gives us:
- gross salary
- how much you paid to INSS
- how much IR you paid
- and net salary.

Display the result as per the table below (consider that a salary of 1000 reais was provided and IR and INSS discount percentages of 11 and 8 percent, respectively):

```
+ Gross Salary: R$ 1000.00
- IR (11%): R$ 110.00
- INSS (8%): ​​R$ 80.00
= Net Salary: R$ 810.00
```