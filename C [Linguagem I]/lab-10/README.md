# Lab 10

## To recap: use of pointers

Pointers are used to access the memory address of variables, providing lower-level control. For example:

```
#include <stdio.h>

int main(void) {

 int x = 10;
 double y = 20.50;
 char z = 'a';

 int *xPtr = &x;
 double *yPtr = &y;
 char *zPtr = &z;

 printf("Address of x = %p - Value of x = %d\n", xPtr, *xPtr);
 printf("Address of y = %p - Value of y = %.2lf\n", yPtr, *yPtr);
 printf("Address of z = %p - Value of z = %c\n", zPtr, *zPtr);

 return 0;
}
```

An example output for the above code is this:

```
Address of x = 0x7ffdd971f84c - Value of x = 10
y-address = 0x7ffdd971f840 - y-value = 20.50
Address of z = 0x7ffdd971f83f - Value of z = 97
```

Unlike declaring variables, with pointers you need to pay attention to the signs used to indicate pointing and dereferencing:

```
#include <stdio.h>

int main(void) {
 // variable declaration 1
 int x = 10;

 // variable declaration 2
 intx;
 x = 10;

 // pointer declaration 1
 int *xPtr;
 xPtr = &x;

 // pointer declaration 2
 int *xPtr = &x;

 return 0;
}
```

Operations can be done between values ​​using pointers, for example:

```
#include <stdio.h>

int main(void) {

 int x = 10;
 double y = 20.50;

 int *xPtr = &x;
 double *yPtr = &y;

 double sum = *xPtr + *yPtr;

 printf("Sum = %.2lf\n", sum);

 return 0;
}
```

The result of this code is:
```
Sum = 30.50
```
What happened for this result is that we have access to the values ​​at the memory addresses indicated by the `xPtr` and `yPtr` pointers. The `*` sign accesses the value from the pointer.

## Incrementing pointers with the const qualifier

The const qualifier allows you to tell the compiler that the value of a specific variable should not be modified, thus enforcing the principle of least privilege. This can reduce debugging time and avoid unintended side effects, making a program more robust and easier to modify and maintain. If an attempt is made to modify a value declared const, the compiler detects it and issues an error.

### Using a non-constant pointer on non-constant data

The function example in the challenge in the last tutorial exemplifies the risk of side effects when working with information without this qualifier. In the following situation, we see that the highest level of data access is granted by a non-constant pointer to non-constant data.

```
#include <stdio.h>
#include <ctype.h>

void convertToCapital(char *sPtr);

int main(void) {
 char word[] = "programming laboratory class";

 printf("Before conversion: %s\n", word);
 convertToCapital(word);
 printf("After conversion: %s\n", word);

 return 0;
}

void convertToCapital(char *sPtr) {
 while (*sPtr != '\0') {
 *sPtr = toupper(*sPtr);
 ++sPtr;
 }
}
```

Output of the previous example:

```
Before conversion: program lab class
After conversion: PROGRAMMING LABORATORY CLASS
```

Data can be modified through the dereferenced pointer, and the pointer can be modified to point to other data items. A function can use this pointer to receive a string argument and then process (and possibly modify) each character in the string.

The convertToCapital function above declares its parameter, a non-constant pointer to non-constant data called `sPtr`. The function processes the array string (pointed to by `sPtr`) one character at a time. The C standard library function `toupper` of the `<ctype.h>` header converts each character to its corresponding uppercase letter.

If the original character is not a letter or is already capitalized, toupper returns the original character. At the end of the loop the pointer is incremented to point to the next character in the string. Chapter 8 introduces many string and character processing functions from the C standard library.

### Using a non-constant pointer in constant data

A non-constant pointer to constant data can be modified to point to any data item of the appropriate type, but the data it points to cannot be modified. A function can receive such a pointer to process the elements of an array argument without modifying them. For example, observe the use of the following function:

```
#include <stdio.h>

void printCharacters(const char *sPtr);

int main(void) {
 char word[] = "programming laboratory class";

 puts("The String is: ");
 printCharacters(word);

 return 0;
}

void printCharacters(const char *sPtr) {
 while (*sPtr != '\0') {
 printf("%c", *sPtr);
 ++sPtr;
 }
}
```

The `imprimeCaracteres` function declares the `sPtr` parameter as being of type `const char *`. The declaration reads from right to left as “sPtr is a pointer to a character constant”. The repeat structure tion contained in the function displays each character until it encounters a null character. After displaying each character, the loop increments the spPtr pointer to point to the next character in the string.

**Exercise 1**: Change the core of the `imprimeCaracteres` function to some instruction that modifies each character, using the dereferencing operator, and note in the code, using comments, which error appeared.

### Trying to modify a constant pointer for non-constant data

A constant pointer to non-constant data always points to the same memory location, but the data in that location can be modified through the pointer.

```
#include <stdio.h>

int main(void) {
 int x = 0;
 int y = 0;

 int *const ptr = &x;

 *ptr = 7; // is allowed because constant is not the value, but the pointer
 printf("%d", x);
 // ptr = &y; // error: pointer is constant, cannot be pointed to another memory address

 return 0;
}
```

The pointer `ptr` is defined in the previous example as being of type `int *const`, which is read from right to left as “ptr is a constant pointer to an integer”. After is initialized (line 11) with the address of the integer variable x. The program tries to assign the address of y to ptr (line 14), but the compiler generates an error.

**Exercise 2**: Create a function analogous to the first example, which prints all lowercase letters, using a pointer like `char *const` and write down what happens with comments.

### Trying to modify a constant pointer to constant data

The least access privilege is granted by a constant pointer to constant data. This pointer always points to the same memory location, and data in that memory location cannot be modified.

This is how, for example, an array must be passed to a function that just looks at the elements of the array using array index notation and does not modify the elements.

To understand better, see the following example:

```
#include <stdio.h>

int main(void) {
 int x = 0;
 int y = 0;

 const int *const ptr = &x;

 //*ptr = 7; // error: not allowed because the value is not constant
 printf("%d", x);
 // ptr = &y; // error: not allowed because the pointer is not constant either

 return 0;
}
```

**Exercise 3**: Modify the function from the previous exercise, which prints all lowercase letters, to, this time, use a pointer like `const char *const` and write down with comments what happens.

## Arrays and Pointers

The use of pointers for arrays is a little different, as it is not necessary to use the `&` operator or copy the size of the array to the pointer. Observe the following example:

```
#include <stdio.h>

int main(void) {

 int vectorExample[3] = {1,2,3};

 int *vectorPtr = vectorExample;
 // the previous line of code is the same
 // put int *vetorPtr = &vetorExample[0]
 // but it is not necessary to specify it this way

 printf("%d\n", *vectorPtr);
 printf("%p", vectorPtr);

 return 0;
}
```

If you noticed, it is not necessary to specify which value in the array we will be pointing to, as the first index is automatically pointed to.

If it is necessary to specifically point to another item in the array, you can do the following:

```
#include <stdio.h>

int main(void) {

 int vectorExample[3] = {1,2,3};
 int *vetorPtr = &vetorExample[1];

 printf("%d\n", *vetorPtr);
 printf("%p", vectorPtr); // Another memory address, unlike the previous example

 return 0;
}
```

It is possible to traverse an array in the same way as we did with strings, and access the values ​​by incrementing the pointer.

**Exercise 4**: Based on the previous example and what we discussed about strings, create a program that loops through arrays of the following types: float, double and char. Using comments, answer: what can you notice when we access the memory address at each iteration of each of these arrays?