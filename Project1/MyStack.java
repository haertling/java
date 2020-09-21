// Haertling, Steven 9/20/2020
//

import java.util.Arrays;
import java.util.Stack;

public class MyStack
{

  private char[] elements;
  private int size;

  public MyStack()
  {
    size = 0;
    elements = new char[4];
  }

  public void push(char item) {
	    ensureCapacity(size + 1);
	    elements[size] = item;
	    size++;
	}

	private void ensureCapacity(int newSize) {
	    char newBiggerArray[];
	    if (elements.length < newSize) {
	        newBiggerArray = new char[elements.length * 2];
	        System.arraycopy(elements, 0, newBiggerArray, 0, size);
	        elements = newBiggerArray;
	    }
	}

  public char peek() {
  	  return elements[size - 1];
  	}

  public char pop()
  {
  	return elements[--size];
  }

  public char checkBalance()
  {
  	return elements[--size];
  }

  public static void main( String args[] )
  {
    /*
      Given an expression like this, [({}{})], an algorithm to check for balanced symbols is:
        Push each opening symbol onto a stack.
        For each closing symbol, pop the stack, and if the symbol popped is not the matching opening  symbol, report an error.
        When done, the stack should be empty.
    */
    Stack<Character> charStack = new Stack<>();

    String str = "[({{{{}{})]";
    char ch[] = str.toCharArray();
    int count = 0;
    System.out.println( "running [({{{{}{})] through stack " );
    for ( int i = 0; i < ch.length; i++ )
    {
      if ( ch[i] == '}' || ch[i] == ')' || ch[i] == ']' )
      {
        char pop = charStack.pop();
        count --;
        switch(ch[i]) {
          case '}':
            if ( pop != '{' )
              System.out.printf( "bad match %s, expected {\n", pop );
            break;
          case ')':
            if ( pop != '(' )
              System.out.printf( "bad match %s, expected (\n", pop );
            break;
          case ']':
            if ( pop != '[' )
              System.out.printf( "bad match %s, expected [\n", pop );
            break;
        }

        // System.out.println( pop );
      }
      else
      {
        charStack.push(ch[i]);
        count ++;
      }
    }
    while (count != 0)
    {
      charStack.pop();
      count --;
    }

    System.out.println( charStack );
    System.out.println( "running [({}{})] through stack " );

    str = "[({}{})]";
    char ch1[] = str.toCharArray();
    count = 0;

    for ( int i = 0; i < ch1.length; i++ )
    {
      if ( ch1[i] == '}' || ch1[i] == ')' || ch1[i] == ']' )
      {
        char pop = charStack.pop();
        count --;
        switch(ch1[i]) {
          case '}':
            if ( pop != '{' )
              System.out.printf( "bad match %s, expected {\n", pop );
            break;
          case ')':
            if ( pop != '(' )
              System.out.printf( "bad match %s, expected (\n", pop );
            break;
          case ']':
            if ( pop != '[' )
              System.out.printf( "bad match %s, expected [\n", pop );
            break;
        }

        // System.out.println( pop );
      }
      else
      {
        charStack.push(ch1[i]);
        count ++;
      }
    }
    while (count != 0)
    {
      charStack.pop();
      count --;
    }

    System.out.println( charStack );



  }
}
