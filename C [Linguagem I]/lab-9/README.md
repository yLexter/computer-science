# Lab 9

## Pointers

Pointers are variables whose values ​​are **memory addresses**. Typically, a variable directly contains a specific value. A pointer, however, contains the address of another variable that contains a specific value. The pointer points to this variable. In this sense, a variable name directly references a value and a pointer indirectly references a value, as in the following image:

![](pointer_example.png)

Referencing a value through a pointer is called **indirection**.

### Declaring a Pointer

Pointers, like all variables, must be defined before they can be used. The following statement sets the variable `countPtr` to an `int *` — a pointer to an integer:

```
int *countPtr;
```

This definition is read from right to left: “countPtr is a pointer to int” or “countPtr points to an object or variable of type int”. The `*` sign indicates that the variable is a pointer.

By convention it is recommended to end the name of each pointer variable with `Ptr` to indicate that the variable is a pointer and should be treated accordingly. Other common naming conventions include starting the variable name with `p` (e.g. `pCount`) or `p_` (e.g. `p_count`).

The `*` sign is not reused with inline definitions, as it is with normal types, for example:

```
// both variables are of type int
int count1, count2;

// both variables are of type int, but only the first is a pointer
int *countPtr, count;

// the above definition can also be written this way:
int *countPtr;
int count;
```

### Operators for Pointers

The first operator available in this tutorial is the address operator `&`. It returns the address of a variable and cannot be applied to literal values ​​or expressions. For example:

```
int y = 5; // y contains the value 5

int *yPtr = &y; // yPtr contains the address value of y
```

![](pointer_example_2.png)

The second operator available is the indirection operator `*`, also called the dereferencing operator. It is used to get the value of the object pointed to by the pointer. For example:

```
printf("%d", *yPtr); // gets the value of the variable pointed to by yPtr, which is y = 5
```

However, it is necessary to observe, when using the `*` operator, whether the pointer was initialized or assigned to the address of another variable in memory. This can generate errors during program execution, bring incorrect results or violate system security.

Let's see, then, the two operators used in practice:

```
#include <stdio.h>

int main() {
 int a = 7;
 int *aPtr = &a;

 printf("The address of a is %p \nThe value of aPtr is %p\n", &a, aPtr);
 printf("The value of a is %d \nThe value of aPtr is %d\n", a, *aPtr);
 printf("&*aPtr = %p\n*&aPtr = %p\n", &*aPtr, *&aPtr);

 return 0;
}
```

When executing the previous code, you will encounter a result similar to the following (it will not be exactly the same as memory addresses are dynamically assigned):

```
The address of a is 0x7ffda3501604
The value of aPtr is 0x7ffda3501604
The value of a is 7
The value of aPtr is 7
&*aPtr = 0x7ffda3501604
*&aPtr = 0x7ffda3501604
```

**Exercise 1**: Create a program that receives two integer values ​​`n1` and `n2` and, using pointers `p1` and `p2`, calculates the following expressions:

- *p1 - *p2
- **&p1
- 3* - *p1/(*p2)+7

### Using pointers in functions

So far we have created functions that contain parameters linked directly to the types of variables. This is what we call **pass by value**. See the following example:

```
#include <stdio.h>

intValueAoCube(int n);

int main() {
 int a = 5;

 printf("The value of a cubed is %d \n", valorAoCube(a));

 return 0;
}

intValueAoCubo(int n) {
 return n * n * n;
}

```
The `valorAoCubo` function receives a value in the parameter `n`, which, with local scope, is used to calculate the cube of this parameter, without changing the original value of `a`.

With pointers, we will expand the options for using parameters in the functions we are creating. We will use a technique we call **pass by reference**. In this case, we will use the operators seen previously to implement the use of pointers, or use the values ​​indirectly.

```
#include <stdio.h>

void valorAoCuboComPonteiro(int *nPtr);

int main() {
 int a = 5;

 valorAoCuboComPonteiro(&a);
 printf("The value of a cubed using pointers is %d \n", a);

 return 0;
}

void valorAoCuboComPonteiro(int *nPtr) {
 *nPtr = *nPtr * *nPtr * *nPtr;
}

```

In the previous example, notice that, to receive the indirect value of the variable `a`, through the pointer that points to this value, in the function definition, we use the dereferencing operator `*`. When passing the value, that is, when calling the function, we use the address operator `&`.

I.e:

```
#include <stdio.h>

intValueAoCube(int n);
void valorAoCuboComPonteiro(int *nPtr);

int main() {
 int a = 5;

 // Use of the value directly stored in the variable a
 a = valueAoCube(a);
 printf("The value of a cubed is %d \n", a);

 a = 5; // example reset

 // Use of the pointer that points to the value stored in variable a
 valorAoCuboComPonteiro(&a);
 printf("The value of a cubed using pointers is %d \n", a);

 return 0;
}

intValueAoCubo(int n) {
 // Use of the value directly passed as a parameter
 return n * n * n;
}

void valorAoCuboComPonteiro(int *nPtr) {
 // Use of the value indirectly passed as a parameter
 *nPtr = *nPtr * *nPtr * *nPtr;
}

```

Did you remember something similar that we’ve already used? That's right, the `scanf` function from the standard package. Whenever we receive a value from the keyboard, this value is passed through the reference to the address of the variable that will store the value.

**_Note_**: With strings, it is not necessary to use the address operator `&`

**Exercise 2**: Using the same logic as the previous example, using passing by reference, create a function that, receiving the radius value from the user, calculates the perimeter (2\*pi*radius) and the area (pi\ *radius²) of a circle. Consider pi = 3.14.

### Using pointers with strings

Consider below two statements in C. What is the difference between the two?

```
char word[] = "programming";
char *word = "programming";
```

The variable `char Palavra[] = "programacao"` creates an array of characters that is like any other matrix, and on this variable we can perform all matrix operations. The only special thing about this array is that even though we initialized it with 11 elements, its size is 12 (the compiler automatically adds '\0').

```
#include <stdio.h>
int main()
{
 char word[] = "programming";
 printf("%lu\n", sizeof(word));
 word[0] = 'P';
 printf("%s", word);
 return 0;
}
```

The output of the previous code is:

```
12
Schedule
```

The `char *palavra = "programacao"` instruction creates a literal string. A string literal is stored in the read-only part of memory by most compilers. The C and C++ standards say that string literals have static storage duration, any attempt to modify them generates undefined behavior.

Therefore, `word` is just a pointer and, like any other pointer, stores the address of the literal string.

```
#include <stdio.h>
int main()
{
 char *word = "programming";
 printf("%lu\n", sizeof(word));

 // The lines below may generate an error or no effect
 // word[0] = 'P';
 // printf("%s", word);

 return 0;
}
```

The output of the previous code is:

```
8
```

In short:

- The first is an array, the second is a pointer;
- The size of the first is given by the number of characters; that of the second, by the number of bytes in memory;
- `word` and `&palavra` are the same thing, but `word` and `*word` are not;
- The value `programacao` in the first case is stored in a contiguous section of memory; in the second case, in a section of memory code;
- It is not possible to change the `word` array to a value larger than the one defined, whereas, in the pointer, it is allowed;
- It is not valid to use the increment or decrement operator in the `word` array, while it is valid in the pointer;
- It is allowed to reset the size in the array declaration, while the pointer size is read-only.

**Challenge 1**: Based on the following example, create a function that receives a string and returns how many vowels and consonants it has. Remember to use parameter passing as a reference.

```
#include <stdio.h>
#include <ctype.h>

void convertToCapital(char *sPtr);

int main(void) {
 char word[] = "programming laboratory class";

 printf("Before conversion: %s\n", word);
 convertToCapital(word);
 printf("After conversion: %s\n", word);
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