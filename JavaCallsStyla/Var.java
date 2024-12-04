import prolog.terms.*;

/**
 * Implements a lightweight external representation of Prolog
 * variables - each Var wraps an int id which makes it unique.
 */
public class Var {
  private static final long serialVersionUID=222L;
  
  final private int id;
  
  public Var(int id){
    this.id=id;
  }
  
  
  public boolean equals(Object O) {
    return O instanceof Var&&((Var)O).id==id;
  }
  
  public int hashCode() {
    return id;
  }
  
  public String toString() {
    return "_j"+id;
  }

}
