jasm
====

Java assembly like language and interpreter... See rasm also! This is a proof of concept.

instructions
============

let,a,b - creates var a if there is none and sets to b, if there is, sets a to b
out,a   - prints out a
jmp,@vr - jumps to @vr
jil,a,b,@vr - jumps to @vr if a is less than b
jig,a,b,@vr - jumps to @vr if a is greater than v
jie,a,b,@vr - jumps to @vr if a is equal to b
rtn     - returns to the place last jumped to
add,a,b - sets a to a + b
div,a,b - sets a to a / b
mod,a,b - sets a to a % b
sub,a,b - sets a to a - b
mut,a,b - sets a to a * b
