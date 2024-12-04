
import java.math.BigDecimal;

/**
 *  Implements external representations of Prolog
 *  compound terms - a functor of the form Symbol / Arity
 *  Uses full hashing on all arguments so if that if is GROUND 
 *  it can be used as a key in an Map
 */
public class Fun {
  private static final long serialVersionUID=222L;
 
  
  public Fun(Object name,Object[] args){
    this.name=name;
    this.args=args;
  }
  
  
  final public Object name;
  
  final public Object[] args;
  
  
  public static String toQuoted(String s) {
    if(null==s)
      return s;
    char quote='\'';
    
    // empty string needs quotes!
    if(s.length()==0)
      return quote+s+quote;
    
    char a=s.charAt(0);
    
    // check if already quoted
    if(a==quote) {
      if(1==s.length())
        s=quote+s+s+quote; // ' ==> ''''
      return s;
    }
    boolean needsq=false;
    if((Character.isUpperCase(a)||Character.isDigit(a)||'_'==a))
      needsq=true;
    else if(s.equals("!")) { /* without quotes */
    } else
      for(int i=0;i<s.length();i++) {
        char c=s.charAt(i);
        if(Character.isLetterOrDigit(c)||c=='_')
          continue;
        needsq=true;
        break;
      }
    if(needsq) {
      StringBuffer sb=new StringBuffer();
      for(int i=0;i<s.length();i++) {
        char c=s.charAt(i);
        if(quote==c) {
          sb.append(quote); // double it
        }
        sb.append(c);
      }
      s="\'"+sb+"\'";
    }
    return s;
  }
  
  private final static String snull="\'$null\'";
  
  private String maybeNull(Object O) {
    if(null==O)
      return snull;
    if(O instanceof String)
      return toQuoted((String)O);
    return O.toString();
  }
  
  public String toString() {
    StringBuffer buf=new StringBuffer();
    
    if(args.length==2
        &&("/".equals(name)||"-".equals(name)||"+".equals(name)||"="
            .equals(name))) {
      buf.append("(");
      buf.append(maybeNull(args[0]));
      buf.append(" "+name+" ");
      buf.append(maybeNull(args[1]));
      buf.append(")");
    } else if(args.length==2&&".".equals(name)) {
      buf.append('[');
      {
        Object tail=this;
        for(boolean first=true;;) {
          if("[]".equals(tail))
            break;
          if(!(tail instanceof Fun)) {
            buf.append('|');
            buf.append(maybeNull(tail));
            break;
          }
          Fun list=(Fun)tail;
          if(!(list.args.length==2&&".".equals(list.name))) {
            buf.append('|');
            buf.append(maybeNull(tail));
            break;
          } else {
            if(first)
              first=false;
            else
              buf.append(',');
            buf.append(maybeNull(list.args[0]));
            tail=list.args[1];
          }
        }
      }
      buf.append(']');
    } else if(args.length==1&&"$VAR".equals(name)) {
      buf.append("_"+args[0]);
    } else {
      String qname=maybeNull(name);
      buf.append(qname);
      buf.append("(");
      for(int i=0;i<args.length;i++) {
        Object O=args[i];
        buf.append(maybeNull(O));
        if(i<args.length-1)
          buf.append(",");
      }
      buf.append(")");
    }
    return buf.toString();
  }
  
}