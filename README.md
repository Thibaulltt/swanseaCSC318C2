# Coursework 2

In this folder, you'll find all the necessary components to build and execute the required work of the coursework 2 of CSC318/M18.

## How to build

Just run `make` and everything will automatically be done. 4096 puzzles will be generated, encrypted, written to a file called `puzzles.bin`. After that, the program will read from `puzzles.bin`, decrypt the puzzles and write the decoded ID to a file called `solution.txt`. The program will read this file, and extract the puzzle.

## Dependancies

- `make`
- JDK 9.0

## Known bugs

Apparently, Oracle decided it was a good idea to deprecate a module, and not replace it. So for now, on JDK 9.0, the class `DatatypeConverter` does not exist anymore. It works on Wiktor's machine, but under my JDK on my personal laptop, it doesn't run all the way through.
