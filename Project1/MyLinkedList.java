// Haertling, Steven 9/20/2020
//

import java.util.NoSuchElementException;

public class MyLinkedList
{
  private Node head;
  private Node tail;
  int size = 0;

  public void addFront(int d)
  {
    if (head == null)
    {
      head = new Node(d, null);
      tail = head;
    }
    else
    {
      head = new Node(d, head);
    }
      size++;
  }

  public void addBack(int d)
  {
    if (head == null)
    {
      head = new Node(d, null);
      tail = head;
    }
      else
      {
        tail.next = new Node(d, null);
        tail = tail.next;
      }
      size++;
  }

  /**
    receives two index positions as parameters, and swaps the nodes at
    these positions by changing the links, provided both positions are
    within the current size
  */
  public void swap( int i, int j )
  {
    Node prevI = null;
    Node prevJ = null;
    Node nodeI = head;
    Node nodeJ = head;
    //skip if indixes are the same or if list is empty
    if( head == null )
      return;
    if ( i == j )
      return;
    if ( i < 0 || i > size - 1 )
      throw new ArrayIndexOutOfBoundsException( "Index " + i + "; size " + size );
    if ( j < 0 || j > size - 1 )
      throw new ArrayIndexOutOfBoundsException( "Index " + j + "; size " + size );
    //search the list for the 4 nodes we need to update
    for ( int k = 0; k < i; k++ )
    {
      if( nodeI != null )
      {
        prevI = nodeI;
        nodeI = nodeI.next;
      }
    }

    for ( int k = 0; k < j; k++ )
    {
      if( nodeJ != null )
      {
        prevJ = nodeJ;
        nodeJ = nodeJ.next;
      }
    }

    //make sure we have nodes to swap
    if( nodeI != null && nodeJ != null )
    {
      //check if moving to head, swap the prev nodes
      if( prevI != null )
        prevI.next = nodeJ;
      else
        head  = nodeJ;

      if( prevJ != null )
        prevJ.next = nodeI;
      else
        head  = nodeI;

      //Swap the next nodes
      Node temp = nodeI.next;
      nodeI.next = nodeJ.next;
      nodeJ.next = temp;

      //check if moving to tail, swap the prev nodes
      if( nodeI.next == null )
        tail = nodeI;
      if( nodeJ.next == null )
        tail = nodeJ;
    }
    else System.out.println( "Can't swap" );

  }

  /**
    receives an integer (positive or negative) and shifts the list this
    many positions forward (if positive) or backward (if negative).
      1,2,3,4    shifted +2    3,4,1,2
      1,2,3,4    shifted -1    4,1,2,3
  */
  public void shift( int x )
  {

    //check if there is a list
    if ( head == null )
      return;
    Node temp = head;

    x = x % size;

    if( x == 0 ) return;
    //check negative and handle
    if ( x < 0 )
    {
      //rotate right
      x *= -1;
    }
    else
    {
      //rotate left
      x = size - x;
    }

    temp = head;
    for( int i = 0; i < size-x-1; i++ ){
        temp = temp.next;
    }

    Node temp2 = temp.next;
    temp.next = null;
    Node temp3 = temp2;

    while( temp3.next != null ){
        temp3 = temp3.next;
    }

    temp3.next = head;
    head = temp2;
    tail = temp;
    return;

  }


  /**
    receives an index position and number of elements as parameters, and
    removes elements beginning at the index position for the number of
    elements specified, provided the index position is within the size
    and together with the number of elements does not exceed the size
  */
  public void erase( int index, int num )
  {
    Node prev = null;
    Node node = head;
    Node temp = null;

    //validate index and num
    if ( index < 0 || (index + num) > size )
      throw new ArrayIndexOutOfBoundsException( "Erase " + (index + num) + "; size " + size );

    //update pointers
    for ( int k = 0; k < index; k++ )
    {
      if( node != null )
      {
        prev = node;
        node = node.next;
      }
    }

    for ( int j = 0; j < num - 1 ; j++ )
    {
      if( node != null )
      {
        node = node.next;
      }
    }
    if ( index == 0 )
    {
      head = node.next;
    }
    else
    {
      prev.next = node.next;
    }
    if ( ( index + num ) == size )
    {
      tail = prev;
    }

    //update size
    size -= num;
    return;

  }
  /**
    receives another MyLinkedList and an index position as parameters, and
    copies the list from the passed list into the list at the specified
    position, provided the index position does not exceed the size.
  */
  public void insertList( int index, MyLinkedList list )
  {
    Node prev = null;
    Node node = head;
    //verify index falls in range //head?
    if ( index < 0 || index > size )
      throw new ArrayIndexOutOfBoundsException( "Erase " + index + "; size " + size );

      //find index point and prev value
      for ( int k = 0; k < index; k++ )
      {
        if( node != null )
        {
          prev = node;
          node = node.next;
        }
      }

      list.tail.next = node;
      if ( index == 0 )
      {
        head = list.head;
      }
      else prev.next = list.head;

      if ( index == size )
        tail = list.tail;
  }

  public int removeFront()
  {
     int olddata;

     if ( head == null )
         throw new NoSuchElementException();
     else if ( head == tail )
     {
         olddata = head.data;
         head = null;
         tail = null;
     }
     else
     {
         olddata = head.data;
         head = head.next;
     }
     size--;
     return olddata;
  }

  public int removeBack()
  {
     int olddata;

     if ( head == null )
         throw new NoSuchElementException();
     else if ( head == tail )
     {
         olddata = head.data;
         head = null;
         tail = null;
     }
     else
     {
         Node p = head;
         while ( p.next != tail )
            p = p.next;
         olddata = tail.data;
         p.next = null;
         tail = p;
     }
     size--;
     return olddata;
  }

  public int get(int i)
  {
      if ( i < 0 || i > size - 1 )
         throw new ArrayIndexOutOfBoundsException( "Index " + i + "; size " + size );

      Node p = head;
      for ( int k=0; k<i; k++ )
         p = p.next;

      return p.data;
  }

  public void reverse()
  {
     if ( head == null || head == tail ) // 0 or 1 nodes
         return;

     Node p1 = null;
     Node p2 = head;
     Node p3 = head.next;

     while ( p3 != null )
     {
        p2.next = p1;
        p1 = p2;
        p2 = p3;
        p3 = p3.next;
     }
     p2.next = p1;

     Node temp = head;
     head = tail;
     tail = temp;

  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder( "[ " );

    Node p = head;
    while ( p != null )
    {
      sb.append( p.data + " " );
      p = p.next;
    }

    sb.append( "]" );

    return new String( sb );
  }

  private static class Node
  {
    int data;
    Node next;

    Node(int d, Node n)
    {
      data = d;
      next = n;
    }
  }

  public static void main( String args[] )
  {
    MyLinkedList list = new MyLinkedList();
    for ( int i = 0; i < 5; i++ )
      list.addBack( i );



    /*
      Swap the HEAD
    */
    System.out.println("list before swap HEAD");
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.println( "swap( 0, 3 )" );
    list.swap( 0, 3 );
    System.out.println(list);
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    list.swap( 0, 3 ); // reset the list
    System.out.printf("\n");

    /*
      Swap middle
    */
    System.out.println("list before swap middle");
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.println( "swap( 2, 3 )" );
    list.swap( 2, 3 );
    System.out.println(list);
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    list.swap( 2, 3 ); // reset the list
    System.out.printf("\n");

    /*
      Swap the TAIL
    */
    System.out.println( "list before swap to TAIL" );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.println( "swap( 3, 5 )" );
    list.swap( 3, 4 );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    list.swap( 3, 4 );// reset the list
    System.out.printf("\n");

    for (int loop = -6; loop < 7; loop++)
    {
      System.out.println( "list before shift" );
      System.out.println( list );
      System.out.printf( "shift( %d )\n", loop );
      list.shift( loop );
      System.out.println( list );
      System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
      list.shift( - loop );
      System.out.printf("\n");
    }

    System.out.println( "erase head" );
    System.out.println( "list before erase" );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.println( "erase( 0, 1 )" );
    list.erase( 0, 1 );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.printf("\n");

    System.out.println( "erase tail" );
    System.out.println( "list before 2nd erase" );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.println( "erase( 3, 1 )" );
    list.erase( 3, 1 );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.printf("\n");

    System.out.println( "erase middle" );
    System.out.println( "list before 3nd erase" );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.println( "erase( 1, 2 )" );
    list.erase( 1, 2 );
    System.out.println( list );
    System.out.printf( "head = %d tail = %d\n", list.head.data, list.tail.data );
    System.out.printf("\n");

    /*
      INSERT LIST TO HEAD
    */
    MyLinkedList listNew0 = new MyLinkedList();
    MyLinkedList listNew1 = new MyLinkedList();
    MyLinkedList listNew2 = new MyLinkedList();
    for ( int i = 5; i < 10; i++ )
    {
      listNew0.addBack( i );
      listNew1.addBack( i );
      listNew2.addBack( i );
    }

    MyLinkedList list0 = new MyLinkedList();
    MyLinkedList list1 = new MyLinkedList();
    MyLinkedList list2 = new MyLinkedList();
    for ( int i = 0; i < 5; i++ )
    {
      list0.addBack( i );
      list1.addBack( i );
      list2.addBack( i );
    }

    System.out.println("list before insert");
    System.out.println( list0 );
    System.out.printf( "head = %d tail = %d\n\n", list0.head.data, list0.tail.data );
    System.out.println( "insertList( 0 , list )" );
    list0.insertList( 0 , listNew0 );
    System.out.println("Insert a list on the head");
    System.out.println( list0 );
    System.out.printf( "head = %d tail = %d\n\n", list0.head.data, list0.tail.data );
    System.out.printf("\n");

    /*
      INSERT LIST TO the middle
    */
    System.out.println("list before insert");
    System.out.println( list1 );
    System.out.printf( "head = %d tail = %d\n\n", list1.head.data, list1.tail.data );
    System.out.println( "insertList( 2 , list )" );
    list1.insertList( 2 , listNew1 );
    System.out.println("Insert a list in the middle");
    System.out.println( list1 );
    System.out.printf( "head = %d tail = %d\n\n", list1.head.data, list1.tail.data );
    System.out.printf("\n");

    /*
      INSERT LIST TO TAIL
    */
    System.out.println( "list before insert" );
    System.out.println( list2 );
    System.out.printf( "head = %d tail = %d\n\n", list2.head.data, list2.tail.data );
    System.out.println( "insertList( 5 , list )" );
    list2.insertList( 5 , listNew2 );
    System.out.println( "Insert a list on the tail" );
    System.out.println( list2 );
    System.out.printf( "head = %d tail = %d\n\n", list2.head.data, list2.tail.data );
    System.out.printf("\n");

  }
}
