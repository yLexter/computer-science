# Lab 11

Archives are used for permanent retention of large amounts of data. Computers store files on secondary storage devices such as solid-state drives, flash drives, and hard drives.

In this script we will work with files, understanding a form of persistent storage for our program data.

## Dealing with files in C language

C views each file as a sequential stream of bytes (stream). Streams provide communication channels between files and programs.

When a file is opened, a stream is associated with the file. Three streams are automatically opened when program execution begins — standard input, standard output, and standard error. The standard input stream allows a program to read data from the keyboard, and the standard output stream allows a program to print data to the screen.

Opening a file returns a pointer to a FILE structure (defined in <stdio.h>) that contains the information used to process the file. This structure includes a file descriptor - an index into an operating system array called the open file table. Each element of the array contains a file control block that the operating system uses to manage a particular file.

```
#include <stdio.h>

int main (void) {

 FILE *file;

 // path to the file including the name
 // opening mode: read, write or append
 file = fopen("path/to/the/file/my_file.txt", "w");

 return 0;
}
```

As in the example above, a new data type, the FILE type, is needed to access the file, through a pointer that points to this data.

Note that opening a file means assigning the `fopen` function call to the pointer, which has two parameters: the path to the file (in Windows, you must separate the folders with two slashes and, in Linux, ...) containing , including the file name, and the opening mode, which can be reading (r - read), writing (w - write) or addition (a - append). In this case, we are assigning the write function, where a new file will be created.

If we do not specify the full path, the file will be created in the same directory as our program.

```
#include <stdio.h>

int main (void) {

 FILE *file;

 // path to the file including the name
 // opening mode: read, write or append
 file = fopen("my_file.txt", "w");

 return 0;
}
```

## Saving information in files

To save information in a file, simply use the same principle as printing information on the terminal. That's right, we will use the `printf` function, but with a difference.

```
#include <stdio.h>

int main (void) {

 FILE *file;

 // path to the file including the name
 // opening mode: read, write or append
 file = fopen("my_file.txt", "w");

 fprintf(file, "C Programming Class");

 return 0;
}
```

The `fprintf` function receives the file that will be written and the information to be saved, as a parameter.

To close this example, remember to do what we are used to in a text editor, to save the changes when we click to close. In the C language, this is equivalent to calling the `fclose` function passing our pointer as a parameter.

```
#include <stdio.h>

int main (void) {

 FILE *file;

 // path to the file including the name
 // opening mode: read, write or append
 file = fopen("my_file.txt", "w");

 fprintf(file, "C Programming Class");

 fclose(file);

 return 0;
}
```

This is just to be safe. If you don't invoke this function, things happen normally.

**Exercise 1**: Replicate the example above by writing the following sentence in a file called "lab11.txt": "I'm learning to write to files using C."

**Exercise 2**: Reusing the same code, change the opening mode to `append` and write another sentence and observe the result. Then return the open mode to `write` again and write a third sentence to the file, observing the result.

**Challenge 1**: Research a different way of including information in files using the `fputs` and `fputc` functions and produce a different version of the programs made in the two previous exercises.

## Recovering information from files

Just as we use a function related to `printf` to "print" information in files, we will use a function related to `scanf` to read information from them, just as we read from the keyboard.

```
#include <stdio.h>

int main (void) {

 FILE *file;

 // path to the file including the name
 // read, write or append
 file = fopen("my_file.txt", "r");

 int x, y, z;

 // pointer to file
 // format in which the information is written
 // information destination variables
 fscanf(file, "%d %d %d", &x, &y, &z);

 printf("%d %d %d", x, y, z);

 fclose(file);

 return 0;
}
```

The `fscanf` function searches the file pointed to by the pointer passed in the first pparameter, in the format specified in the second parameter, and stores this information in the specified variables. Very similar to `scanf`, right? In our example above, we have a file that stores three integers, separated by space.

But what if the file doesn't exist? How to make? The program will display other information, but not exactly what would be in the file. In fact, any other information previously stored at the memory address used is displayed. That is, the `fscanf` function was not called.

It is necessary to ensure that the `file` pointer points to a valid file. One way out is to inform the user what happened:

```
#include <stdio.h>

int main (void) {

 FILE *file;
 char fileName [] = "my_file2.txt";

 // path to file up to name
 // read, write or append
 file = fopen(fileName, "r");
 if (file == NULL) {
 printf("File %s cannot be opened\n", fileName);
 return 0;
 }

 int x, y, z;

 // pointer to file
 // format in which the information is written
 // information destination variables
 fscanf(file, "%d %d %d", &x, &y, &z);

 printf("%d %d %d", x, y, z);

 fclose(file);

 return 0;
}
```

**Exercise 3**: Using the `fscanf` function, try to read 5 decimal numbers in the file called "notas.txt" and print the average of these grades in the terminal.

There's one more thing, what if the file is not formatted exactly as specified in the second parameter of the `fscanf` function? Firstly, the data previously stored in the memory addresses used would be collected again.

Secondly, we need a function with more processing possibilities and fewer limitations. To understand this, look at the next example:

```
#include <stdio.h>
#include <stdlib.h>

int main (void) {

 FILE *file;
 char fileName [] = "frases.txt";

 file = fopen(fileName, "r");
 if (file == NULL) {
 printf("File %s cannot be opened\n", fileName);
 return 0;
 }

 char phrase[100];
 while(fgets(sentence, 100, file) != NULL) {
 printf("%s", sentence);
 }

 fclose(file);

 return 0;
}
```

The function `fgets` used in the loop reads a line of up to 100 characters, which is the number specified in the second parameter, and stores in the character array of the same size the string contained in the file pointed to by the third parameter pointer.

**Exercise 4**: Write a program that reads only the first line of a file in two versions: the first, using the `fgets` function and the second using `fscanf` (use just a %s to format the collection in file). Note the difference between the approaches.

**Challenge 2**: Create a program that has a function to copy the contents of one file to another. Tip: use the `fputs` function researched previously.