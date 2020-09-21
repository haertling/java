// Haertling, Steven 9/20/2020
//

import java.util.Arrays;

public class MyStack <E>
{
  private int size = 0;
  private static final int DEFAULT_CAPACITY = 10;
  private Object elements[];

  public void CustomStack()
  {
    elements = new Object[DEFAULT_CAPACITY];
  }

  public void push( E e ) {
    if (size == elements.length)
    {
      ensureCapacity();
    }
    elements[ size++ ] = e;
  }

  @SuppressWarnings( "unchecked" )
  public E pop()
  {
    E e = ( E ) elements[ --size ];
    elements[ size ] = null;
    return e;
  }

  private void ensureCapacity() {
    int newSize = elements.length * 2;
    elements = Arrays.copyOf( elements, newSize );
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for(int i = 0; i < size ;i++) {
      sb.append(elements[i].toString());
    if( i < size - 1 )
    {
      sb.append( "," );
    }
  }
    sb.append(']');
    return sb.toString();
  }

  public static void main( String args[] )
  {
    /*
      Given an expression like this, [({}{})], an algorithm to check for balanced symbols is:
        Push each opening symbol onto a stack.
        For each closing symbol, pop the stack, and if the symbol popped is not the matching opening  symbol, report an error.
        When done, the stack should be empty.
    */
    MyStack<Char> stack = new MyStack<>();

    String str = "[({}{})]";
    char ch[] = str.toCharArray();

    stack.push(ch[0]);
    System.out.println( stack );



  }
}
