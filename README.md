#Styla - a Prolog in Scala,

### The name STYLA stands tentatively for "Scala Terms Yield Logic Agents" but hints

toward the fact that style (seen as "elegance" of implementation) has been a major
design principle behind it.

Styla is a fairly complete Prolog interpreter written in Scala, derived from
Kernel Prolog (see Fluents: A Refactoring of Prolog for Uniform Reflection
and Interoperation with External Objects CL'2000).

### Install

The code has been developped between 2011 and 2015 and unless a porting
to the latest Cursor-base scala is voluntieered by someone, it requires
you to install:

[The Scala 2.12.20 system](https://www.scala-lang.org/download/2.12.20.html)
while making sure to put scala and scalac in your path.

Just type "styla.sh” to run the system from its precompiled
"styla.jar" file.

If you do not have Scala installed, and just want to embed Styla in
a Java application, the self-contained
JavaCallsStyla, will allow
to customize JavaMain.java as needed. the same directory contains also
the script "jstyla.sh” that can run the interactive
Styla toplevel, even if you do not have Scala installed.

### Use the scripts

* "compile.sh” to recompile the system with scalac
* * to_jar.sh creates a jar file, enabling styla.sh
* "styla.sh” to run it with scala and
* go.sh does all those steps

### Settings

* The file "prolog/fluents/Lib.scala" embeds Prolog code that is present at
  start-up. New Prolog code that you want part of the default libraries
  can be added there.

### Programs and benchmarks

* Sample programs are in directory "progs".

Try "bm.sh”, "bm1.sh”, "bm2.sh” shell scripts for benchmarking.

Among the features not found in most Prologs, [first class Logic Engines](https://arxiv.org/abs/1102.1178) -
and a generic view of everything
as [Fluents](https://www.researchgate.net/profile/Paul-Tarau/publication/221655012_Fluents_A_Refactoring_of_Prolog_for_Uniform_Reflection_an_Interoperation_with_External_Objects/links/0c960517f2f3485c8e000000/Fluents-A-Refactoring-of-Prolog-for-Uniform-Reflection-an-Interoperation-with-External-Objects.pdf)
that abstract away iteration over various data types, including answers
produced by Logic Engines.

Styla also supports Interclausal logic variables -
see [paper](https://arxiv.org/abs/1406.1393)!

### On Scala goodies used

Styla uses a few interesting Scala goodies, not available in Java:

- higher order functions, maps, folds, case classes etc.
- combinator parsers - "poor man's DCGs" :-)
- Scala's elegant implicit conversions between
  lists, arrays, sequences etc.
- Scala's arbitrary length integers and decimals (with
  a natural syntax, in contrast to Java)
- Scala's """...""" strings - for regexps and to embed
  Prolog code directly in Scala classes
- a few IO abstractions available in Scala that
  view things like file operations as iterators -
  a natural match to the original Fluents of
  Kernel Prolog from which Styla was derived

- a few IO abstractions available in Scala that
  view things like file operations as iterators -
  a natural match to the original Fluents of the
  Java-based Kernel Prolog from which Styla was derived

Note that [Kernel Prolog](https://github.com/ptarau/kernel-prolog)
(Styla’s Java based ancestor) is also an open source project.

### Limitations

Here are some limitations, most things not on this list
and expected from a Prolog system should work.

- toplevel goals that are not conjunctions should be parenthesized
- operators are all fixed and "xfx" associativity, but they match the
  default priorities of most Prologs' operators - see examples in "progs"
- no gui or networking - Scala can do all that better

Take a look at "prolog.Main" for the start-up sequence - that gives
a glimpse of how to embed it into a Scala or Java program.

To add a new built-in, just clone the closest match
in prolog.builtins and drop it in the same directory - the
runtime system it will instantly recognize it.

Enjoy,

Paul Tarau
