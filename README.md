JASM
====

Java assembly like language and interpreter. See rasm also as this project was inspired by it! This is a proof of concept.

Instructions
------------

Declaring a jump point looks like this: !@jumphere

Before every command you must put a . or a !

These are the commands:

* `let,a,b` - creates var a if there is none and sets to b, if there is, sets a to b
* `out,a` - prints out a
* `jmp,@vr` - jumps to @vr
* `jil,a,b,@vr` - jumps to @vr if a is less than b
* `jig,a,b,@vr` - jumps to @vr if a is greater than v
* `jie,a,b,@vr` - jumps to @vr if a is equal to b
* `rtn` - returns to the place last jumped to
* `add,a,b` - sets a to a + b
* `div,a,b` - sets a to a / b
* `mod,a,b` - sets a to a % b
* `sub,a,b` - sets a to a - b
* `mut,a,b` - sets a to a * b
* `get,a`   - sets a to whatever the user inputs next

Example Program
---------------
This program asks the user for a positive integer until the user enters a negative one. When the user enteres a negative integer, it prints the biggest integer the user entered. Everything is recursion instead of looping.
```
!@main
.out,Enter a positive integer
.get,int
.let,max,int
.jig,int,-1,@GetPos
.end

!@GetPos
.out,Enter a new positive integer
.get,int
.jig,int,max,@SetMax
.jig,int,-1,@GetPos
.out,The biggest number you entered is:
.out,max
.end

!@SetMax
let,max,int
.rtn
```

Build and Run
-------------
Make a text file with your set of instructions in it and run:

`java -jar Jasm.jar /path/to/file`
