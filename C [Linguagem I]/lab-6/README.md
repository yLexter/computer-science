# Lab 6

Most computer programs that solve real-world problems are much larger than those presented in the first few chapters. Experience has shown that the best way to develop and maintain a program is to build it from smaller parts, each easier to maintain than the original program. This technique is called divide and conquer. We will describe some key features of C for designing, implementing, operating, and maintaining large programs.

## How known functions work

In C, you use functions to modularize programs by combining new functions you write with prepackaged C standard library functions. The `stdio.h` C standard library provides a rich collection of functions to perform common mathematical calculations, string manipulations, character manipulations, input/output, and many other useful operations. Pre-packaged functions make your job easier because they provide many of the features you need.

Functions are invoked by a function call, which specifies the name of the function and provides information (such as arguments) that the function needs to perform its designated task. A common analogy for this is the hierarchical form of management. A boss (the calling role or caller) asks a worker (the calling role) to perform a task and report when it is complete.

For example, a function that displays data on the screen calls the printf worker function to perform this task. The printf function displays the data and reports - or returns - to the caller when it completes its task. The boss role does not know how the worker role performs its assigned task. The worker can call other worker functions, and the boss will not know about it.

The following diagram shows a boss role communicating hierarchically with multiple worker roles:

![image](boss-workers.png)

The printf, scanf and pow functions that we used in the previous scripts are from the standard library. The `main()` code snippet is also a function, responsible for executing the program.

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

It is interesting to know the rich collection of functions in the C standard library to help reduce program development time. When possible, use standard functions rather than writing new ones. The C standard library functions are written by experts, well tested and efficient. Additionally, using functions from the C standard library helps make programs more portable.

Not only the functions from the standard library, but also from the `math.h` library, which help us perform mathematical calculations.

```
#include <stdio.h>
#include <math.h>

int main()
{
 int number;

 printf("Enter an integer: ");
 scanf("%d", &number);
 printf("%d", sqrt(number)); // square root
 printf("%d", log(number)); // natural logarithm
 printf("%d", log10(number)); // base 10 logarithm
 printf("%d", pow(number, 2)); // power

 return 0;
}
```

**Exercise 1**: Research mathematical trigonometry functions and create a code with examples of their use.

## Defining the first functions

You can define functions to perform specific tasks that can be used at multiple points in a program. The instructions that define the function are written once and are hidden from other functions. As we will see, this hiding is crucial for good coding practice in Software Engineering.

The format of a function generally follows the following scheme:

```
returntypefunctionname(parameters) {
 // code
 //return;
}
```

Any and all code can be redefined in a function, with the aim of reusing solutions to complement others, in a divide and conquer strategy: our solution to an entire problem must be composed of smaller (and reusable) solutions.

Let's look at a first example. In exercise 3 of Lab 1, the following was requested: Taking a person's name and height as input data, build a program that calculates their ideal weight, using the following formula: (72.7*height) - 58, being the result formatted with 3 digits within a final message with the name provided by the user.

Well, your solution was slightly similar or the same as this:

```
#include <stdio.h>


int main()
{
 float height = 0.0;
 printf("Enter your height: ");
 scanf("%f", &height);

 floatIdealweight = (72.7*height) - 58;
 printf("Your ideal weight is %.3f", Idealweight);

 return 0;
}
```

Dispensing with the scope comment (for now), our entire solution was coded inside the main function. However, what if we needed this functionality more than once? What would our code look like? Is repeating the entire code a solution?

```
#include <stdio.h>

int number = 0;

int main()
{
 float height = 0.0;
 printf("Enter your height: ");
 scanf("%f", &height);

 floatIdealweight = (72.7*height) - 58;
 printf("Your ideal weight is %.3f", Idealweight);

 float height = 0.0;
 printf("Enter your height: ");
 scanf("%f", &height);

 floatIdealweight = (72.7*height) - 58;
 printf("Your ideal weight is %.3f", Idealweight);

 // ... ?
 return 0;
}
```

Yes, but not the best. Even because, if this functionality were invoked several times in loops or conditionals, for example, it would become even more difficult to maintain and control any changes to the code.

Let's then create a function to inform anyone's ideal weight. Initially, we can extract all the code involved in the solution to a definition in a separate block:

```
#include <stdio.h>

int number = 0;

float calculateIdealPeso(); // prototype

int main()
{
 float weight = calculatePesoIdeal(); // call

 return 0;
}

float calculateIdealPesto() { // definition
 float height = 0.0;
 printf("Enter your height: ");
 scanf("%f", &height);

 floatIdealweight = (72.7*height) - 58;
 printf("Your ideal weight is %.3f", Idealweight);

 returnIdealweight;
}
```

Note that, in addition to defining the function, as we are used to in simpler languages, we define a `prototype`, that is, a kind of "skeleton" preparing the compiler to understand what our function will be like. This is done before the main function, where we call our function `calculaPesoIdeal()`.

It solves our problem. However, if we want the function to just perform the calculations to obtain the ideal weight, without asking for values ​​from the user?

```
#include <stdio.h>

float calculateIdealPeso(); // prototype

int main()
{
 float weight = calculatePesoIdeal(); // call

 return 0;
}

float calculateIdealPesto() { // definition
 floatIdealweight = (72.7*height) - 58; // height?
 printf("Your ideal weight is %.3f", Idealweight);

 returnIdealweight;
}
```

The function must receive the height before performing the calculation, as an argument or parameter.

```
#include <stdio.h>

float calculateIdealPesto(float height); // prototype

int main()
{
 float height = 0.0;
 printf("Enter your height: ");
 scanf("%f", &height);

 float PesoIdeal = calculatePesoIdeal(height); // call
 printf("Your ideal weight is %.3f", Idealweight);

 return 0;
}

float calculateIdealPeso(float height) { // definition
 floatIdealweight = (72.7*height) - 58;
 returnIdealweight;
}
```

Note that we "hide" the calculation of the ideal weight of the main function. If there is any internal change, it will be encapsulated in the function, thus complying with good code practices.

**Exercise 2**: Define a function that returns the largest of 4 integers passed as a parameter.

**Exercise 3**: Modify the example weight calculation function to insert conditions between ideal weight for women and men, applying the following formulas - women: (62.1\*height) - 44.7 and men: (72.7*height) - 58.

**Challenge 1**: Write a program that converts from 24-hour notation to 12-hour notation. For example, the program must convert 2:25 p.m. to 2:25 p.m. The input is given as two integers. Your program must have two functions: one to perform the conversion and one to output. Record the A.M./P.M. information. as a value 'A' for A.M. and 'P' for P.M. Thus, the function to perform the conversions will have a formal parameter to record whether it is A.M. or P.M. Include a loop that allows the user to repeat this calculation for new input values ​​every as many times as you wish.

**Challenge 2**: Research the function to generate random numbers and set up a "game" where you try to guess the number generated by the computer.

**Challenge 3**: Build a function that receives a date in the format DD/MM/YYYY and returns a string in the format D of monthByExtenso of YYYY. Optionally, validate the date and return "NULL" if the date is invalid. You can use functions from the <time.h> library.