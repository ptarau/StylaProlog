package prolog.io
import prolog.terms._
import prolog.interp.Prog

//import jline.console.ConsoleReader
import java.io.File
object IO {

  def time = System.currentTimeMillis()

  val t0 = time

  def start = {
    println("Styla: Scala Terms Yield Logic Agents")
    println("Prolog System Version 1.4.1 with LogicActors")
    println("Copyright (C) Paul Tarau 2011-2015")
  }

  def stop = {
    println("Prolog execution halted")
    println("total time in ms=" + (time - t0))
    System.exit(0)
  }

  final def find_file(f0: String): String = {
    if ("stdio" == f0) return f0
    val fs1 = List(f0 + ".pro", f0 + ".pl", f0)
    val fs2 = fs1.map(x => "progs/" + x)
    val fs = (fs1 ++ fs2).dropWhile(x => !new File(x).exists())
    if (fs.isEmpty) {
      IO.warnmes("file not found: " + f0)
      null
    } else
      fs.head
  }
  
  // uncomment this to enable jline Console

  val stdio = new jline.console.ConsoleReader()
  def readLine(prompt: String): String = {
    IO.stdio.readLine(prompt)
    //print(prompt);
    //Console.readLine();
  }

  
  // comment this out when enabling jline Console
  /*
   val stdio = Console.in
  def readLine(prompt: String): String = {
    print(prompt);
    Console.in.readLine();
  }
  */

   
  def println(s: Any) {
    Console.println(s.toString)
    //Console.flush()
  }

  def print(s: Any) {
    Console.print(s.toString)
  }

  def errmes(mes: String, culprit: Term, p: Prog) = {
    IO.println("*** " + mes + " in => " + culprit)
    p.stop()
    0
  }

  def warnmes(mes: String) = {
    IO.println("*** " + mes)
    0
  }

}

