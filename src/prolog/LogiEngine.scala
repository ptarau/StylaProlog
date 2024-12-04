package prolog
import prolog.interp.Prog
import prolog.io.IO
import prolog.io.TermParser
import prolog.terms._
import prolog.fluents.DataBase

class LogicEngine(db: DataBase) extends Prog(db) {
  def this() = this(new DataBase(null))
  def this(fname: String) = this(new DataBase(fname))

  val parser = new TermParser()
  parser.vars.clear()

  def setGoal(query: String) = {
    parser.vars.clear()
    set_query(parser.parse(query))
  }
  
  def setGoal(answer: Term, goal: Term) = {
    Prog.init_with(db, answer, goal, this)
  }

  def askAnswer(): Term = {
    val answer=this.getElement()
    if(null==answer)  trail.unwind(0);
    answer;
  }
  
  /*
  def stopEngine() {
    this.stop()
  }
  */
}

object LogicEngine {
  def newFun(sym: String,args: Array[Term]):Fun = 
      TermParser.toFunBuiltin(new Fun(sym,args))
        
  def newConst(sym:String):Const =
      TermParser.toConstBuiltin(new Const(sym))     
}

