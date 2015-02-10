# WAM
Implementation of the Warren Abstract Machine.

This project contains an implementation of the Warren Abstract Machine. In its current status it is mainly useful for
enthusiasts that want to learn more about the inner workings of Prolog. The software provides step-by-step instruction
execution with an interface to view the current state of the machine.

To understand the WAM I strongly suggest visiting this page: http://wambook.sourceforge.net/

Package overview:

    compiler: This contains all the code to tokenize files/strings and compile the tokens to WAM instructions.
    gui: Here are the GUI files.
    instructions: Here are the various WAM instruction classes stored.
    main: The main interface to the WAM plus a test main class. Start here.
    parser: I use ANTLR to parse Prolog files. The resulting parsetrees are used by the compiler classes.
    runtime: The execution environment of the WAM. Here are all instructions executed and is the code stored.
    

I welcome anybody to improve the source. 
