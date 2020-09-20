// Ozbirn, 09/19/16
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
      //check size of list by counting nodes to null from head
      Node temp = head;

      while( temp != null ){
          temp = temp.next;
      }

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

      // node = null;
      //update size
      size -= num;
      return;

    }
    /**
      receives another MyLinkedList and an index position as parameters, and
      copies the list from the passed list into the list at the specified
      position, provided the index position does not exceed the size.
    */
    public void insertList( int d )
    {

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

       System.out.println( list );

       // list.swap( 0, 3 );
       // System.out.println(list);

       // int j = -6;
       // list.shift( j );
       // System.out.printf( "shift by %d\n", j );
       // System.out.println( list );

       // list.erase( 0, 5 );
       // System.out.println(list);



//        for (int i=0; i<5; i++)
//           System.out.println(list.get(i));
//
//           //System.out.println(list.get(6));
//
//
//        list.reverse();
//
//        System.out.println(list);
//
//
//        for (int i=0; i<5; i++)
//           list.addBack(i);
//
//        System.out.println(list);
//
//        list.removeBack();
//        list.removeFront();
//
//        System.out.println(list);

    }
}
