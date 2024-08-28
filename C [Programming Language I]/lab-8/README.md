# Lab 8

In this script we will learn how to deal with Arrays to manipulate sets of numbers and characters.

## Value Sets: Arrays

An array is a group of elements of the same type stored contiguously in memory. The following image shows an array of integers called `c`, containing five elements:

![](array_example.png)

Each element is accessible by its position, like a kind of key-lock scheme. When accessing key 2, for example, you have access to element 0; when accessing key 3, we have element 72, and so on.

These keys are always enclosed in `[]` brackets, and become variables that can be manipulated like any other. For example:

```
#include <stdio.h>

int main()
{
 int s[5];

 // Accessing the first element and saving value
 s[0] = 1;
 // Accessing the second element and saving value
 s[1] = 2;
 // Accessing the third element and saving value from the first and second
 s[2] = s[0] + s[1];
 printf("%d\n", s[2]);
}
```

Note that, initially, an array must have a defined size.

### Initializing an array

It is possible to initialize the values ​​of an array in at least three different ways. The first, through a loop, allows you to automatically place values ​​in up to all items in the array:

```
#include <stdio.h>

int main()
{
 int s[5];

 for (size_t j = 0; j < 5; j++) {
 s[j] = 0;
 }

 printf("%s%8s\n", "Element", "Value");

 for (size_t j = 0; j < 5; j++) {
 printf("%7zu%8d\n", j, s[j]);
 }
}
```

Running the code above, we have:

```
Value Element
 0 0
 1 0
 2 0
 3 0
 4 0
```

A second way is to say, directly in the assignment, what the elements of the array are:

```
#include <stdio.h>

int main()
{
 int s[5] = {1, 2, 3, 4, 5};

 for (size_t j = 0; j < 5; j++) {
 printf("%7zu%8d\n", j, s[j]);
 }
}
```
Running the code above, we have:

```
Value Element
 0 1
 1 2
 2 3
 3 4
 4 5
```

Something we can notice in these first two code examples is the informal definition of size, which can be resolved by creating a constant with the help of a `#define` directive:

```
#include <stdio.h>

#define SIZE 5

int main()
{
 int s[SIZE] = {0}; // initialize the array with "zero" elements

 printf("%s%8s\n", "Element", "Value");

 for (size_t j = 0; j < SIZE; j++) {
 printf("%7zu%8d\n", j, s[j]);
 }
}
```

Taking advantage, the code above exemplifies the simplest initialization, replicating just one element, inferring its quantity from the size of the array definition (it is not linked to SIZE, it could be any other informal number as well).

**Exercise 1**: Write a Program that reads 20 numbers, which must be inserted into two arrays with 10 elements each. Generate a third vector of 20 elements, whose values ​​must be composed of the interspersed elements of the two other arrays.

**Exercise 2**: Make a program that simulates a dice roll. Roll the die 100 times and store the results in an array. Then, show how many times each value was achieved.

**Challenge 1**: Using lists, make a program that asks a person 5 questions about a crime (1 for YES and 0 for NO). The questions are:
- "Did you call the victim?"
- "Were you at the scene of the crime?"
- "Do you live near the victim?"
- "Should it be for the victim?"
- "Have you ever worked with the victim?"

The program must ultimately issue a classification regarding the person's participation in the crime. If the person responds positively to 2 questions they must be classified as "Suspect", between 3 and 4 as "Accomplice" and 5 as "Murderer". Otherwise, he will be classified as "Innocent".

**Challenge 2**: Write a program that reads an indeterminate number of values, corresponding to notes, ending data entry when a value equal to -1 is entered (which should not be stored). After this data entry:
- Show the number of values ​​that were read;
- Display all values ​​in the order they were entered, one next to the other;
- Display all values ​​in the reverse order to which they were entered, one below the other;
- Calculate and show the sum of values;
- Calculate and show the average of the values;
- Calculate and display the number of values ​​above the calculated average;
- Calculate and show the number of values ​​below seven.

## Manipulating Strings with Arrays

Arrays can contain data of any type, although all elements in a given array must have the same type. Now we will discuss storing strings in character arrays. So far, the only string processing capability we have is outputting a string with printf. A string like "hello" is really an array of individual characters, plus one more item.

```
#include <stdio.h>
#define SIZE 20

int main()
{
 char string0[] = "hello"; // implicitly reserves 6 characters
 char string1[] = "string literal"; // implicitly reserves 15 characters
 char string2[SIZE]; // reserve 20 characters

 return 0;
}
```

Looking at the comments above, it may seem strange that the arrays `string0` and `string1` have an extra character in their size. This is due to the occurrence of the character `\0`, present, implicitly, as the compiler determined the size of the array `string0` and `string1` based on the length of the string. The string "hello" contains five characters plus a string-terminating null character. It's as if it was initialized like this:

```
char string0[] = {'h', 'e', ​​'l', 'l', 'o', '\0'};
```

To manipulate these arrays, we can use the input and output functions that we already know. Pay attention to one detail: when dealing with the character array, the formatting operator is `%s` and the letters will be enclosed in double quotes, whereas, when dealing with one letter at a time, the formatting operator is ` %c`.

```
#include <stdio.h>
#define SIZE 20

int main()
{
 char string0[] = "hello"; // implicitly reserves 6 characters
 char string1[] = "literal string"; // implicitly reserves 15 characters
 char string2[SIZE]; // reserve 20 characters

 printf("%s", "Enter a word in string (maximum 19 characters): ");
 scanf("%19s", string2);

 printf("string1 is: %s\nstring2 is: %s\n", string1, string2);
 puts("string1 with spaces between characters:");

 for (size_t i = 0; i < SIZE && string2[i] != '\0'; ++i) {
 printf("%c ", string2[i]);
 }

 return 0;
}
```

Note also that we can format the number of letters that will be read, just as we did with the decimal places of a `float`, for example. Also note the lack of the `&` operator in the input function for a character array.

**Exercise 3**: Name vertically. Make a program that requests the user's name and prints it vertically.

```
F
U
L
A
N
O
```

**Challenge 3**: Modify the previous program to display the name in the form of stairs (normal and inverted).

```
F
FU
FUL
FULA
FULAN
SO-and-so
FULAN
FULA
FUL
FU
F
```