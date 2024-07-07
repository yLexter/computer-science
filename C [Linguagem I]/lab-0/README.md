# Lab 0

Hello. Welcome to the Programming Laboratory 1 subject. We will be practicing the C language, a general purpose and high-level language (which also provides low-level resources).

Developed in 1972 by American scientist Dennis Ritchie within Bell Laboratories (the company owned by the telephone's inventor, Graham Bell, now owned by Nokia), designed for the creation of compilers and operating systems, the main one being Unix (its characteristics are present on MacOS, Linux and Android systems).

It is considered the "mother" of many other high-level languages ​​known today, such as C++, C#, Dart, Go, Java, PHP, Rust, Swift, among others, but it is still current and is still widely used in the areas of its original purpose and also in virtual machines.

It is a very fast processing language, compared to other languages, such as Java and Python, and is very versatile, as it will even help you learn about other programming languages. That's why it's so popular.

## Our first C program: Hello World

To create our first program, see the steps and code syntax:

```
#include <stdio.h>

int main()
{
 printf("Hello, World!");
 return 0;
}
```

In the first line, we have the inclusion of the standard data input and output library (very important when solving problems), Standard I/O.

Like most programming languages, C is made up of many functions, denoted whenever there is a word followed by parentheses `()`. In this case, we are invoking the main function, `main`, which is responsible for executing the code. There you can store and manipulate input and output data.

Note that the function is a block of code, and that it must be enclosed in braces `{}`. And one more observation: the `main` function always returns something, which is why we have the word `return` in the last line. It signals that a value will be returned, which in this case is an integer. That's why a reserved word `int` appears before the name of the function, which corresponds to the type of information that will be returned at the end of the function.

Oh, don't forget that every end of line must contain the semicolon `;`!

Finally, see the core of our function: "printing" the phrase "Hello, World!" on the screen. To do so, we will use the `printf` function from the `stdio.h` library to perform this task for us.

This code can be written on your machine by installing a compiler, such as CGC, for example, and using a text editor, such as Sublime. The file must have the extension `.c`.

Tip: Use an IDE, such as CodeBlocks or Visual Studio Code, to make your coding task more productive, or use online environments, always prioritizing those integrated with `GitHub`, such as `repl.it`.

**Exercise 1:** What would a program look like to print two sentences, like a greeting and your name?

## A second program: adding two numbers

Since we know how to output data, let's see how to insert information into the program, that is, work with data entry.

Let's consider the scenario of adding two numbers. To do so, it is necessary to create the space where we will store the two numbers we want to add, as follows:

```
#include <stdio.h>

int main()
{
 int integer1 = 0;
 int integer2 = 0;
}
```

We have just created two _variables_, spaces in the computer's memory to store information necessary to solve the problem. As our problem is solving a sum of two numbers, I need to memorize which two numbers I will add (obvious, right)?

Tip: be careful with the name of the variables: integer1 and INTEGER1 are not the same thing. C is _case sensitive_.

After creating the variables, we will insert the way to collect the information, which, in this case, are the two numbers. We will use the `scanf` function:

```
#include <stdio.h>

int main()
{
 int integer1 = 0;
 int integer2 = 0;

 scanf("%d", &integer1);
 scanf("%d", &integer2);
}
```

Okay, now I can save what I type on the keyboard! But wait, there are some things you need to know: the `scanf` function takes two _parameters_: the first will format what you enter from the keyboard - text, but which is called a `string`, to a `decimal integer` (for that's the letter d). The second parameter is easier to understand: it is the destination of the information (the name of the variable). In this case, we need to inform the program that memory space will be allocated to store the information, hence the use of the `&` symbol. Without it, our program would result in a "segmentation fault" or "access violation" error.

Finally, we need to store the sum. To do so, we will use another variable that will store the result of the arithmetic operation:

```
#include <stdio.h>

int main()
{
 int integer1 = 0;
 int integer2 = 0;

 scanf("%d", &integer1);
 scanf("%d", &integer2);

 int sum = 0;
 sum = integer1 + integer2;
}
```

Okay, we can run our program. But there's still something missing... printing the result on the screen! Let's use the `printf` function again:

```
#include <stdio.h>

int main()
{
 int integer1 = 0;
 int integer2 = 0;

 scanf("%d", &integer1);
 scanf("%d", &integer2);

 int sum = 0;
 sum = integer1 + integer2;

 printf("%d", sum);
}
```

Okay, our program works. Prints the sum correctly. But if we look from outside the code, as just users and not programmers, what exactly is this program doing? It could be a subtraction, a multiplication, a division, or any other operation.

To understand what a program does, it is necessary to guide the user, and this also needs to be programmed.

Firstly, it is necessary to inform what data the user will enter, before asking for it on the keyboard:

```
#include <stdio.h>

int main()
{
 int integer1 = 0;
 int integer2 = 0;

 printf("Sum of two numbers\n");

 printf("Enter the first number: ");
 scanf("%d", &integer1);
 printf("Enter the second number: ");
 scanf("%d", &integer2);

 int sum = 0;
 sum = integer1 + integer2;

 printf("%d", sum);
}
```

Furthermore, in the last line, when we inform the result, we can print a message indicating that that information is the sum of the two numbers:

```
#include <stdio.h>

int main()
{
 int integer1 = 0;
 int integer2 = 0;

 printf("Sum of two numbers\n");

 printf("Enter the first number: ");
 scanf("%d", &integer1);
 printf("Enter the second number: ");
 scanf("%d", &integer2);

 int sum = 0;
 sum = integer1 + integer2;

 printf("The sum is %d\n", sum);
}
```

Note that we used the same formatting symbol for `decimal integer` as we did in `scanf`. This is due to this **f** in the scan and print functions. It is possible to format information, inserting as many as necessary.

And the symbol `\n` was placed to skip a line. See without it what the result of your program would be.

**Exercise 2**: Create a program to subtract two numbers and multiply by a third.

**Challenge**: Create a program to calculate power of base 2, with the base information (2) always being a _constant_, and the exponent informed on the keyboard by the user.