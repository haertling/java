// Ozbirn, 09/19/16
//

import java.util.NoSuchElementException;

public class LL
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
    public void swap(int d)
    {

    }
    /**
      receives an integer (positive or negative) and shifts the list this
      many positions forward (if positive) or backward (if negative).
        1,2,3,4    shifted +2    3,4,1,2
        1,2,3,4    shifted -1    4,1,2,3
    */
    public void shift(int d)
    {

    }
    /**
      receives an index position and number of elements as parameters, and
      removes elements beginning at the index position for the number of
      elements specified, provided the index position is within the size
      and together with the number of elements does not exceed the size
    */
    public void erase(int d)
    {

    }
    /**
      receives another MyLinkedList and an index position as parameters, and
      copies the list from the passed list into the list at the specified
      position, provided the index position does not exceed the size.
    */
    public void insertList(int d)
    {

    }

    public int removeFront()
    {
       int olddata;

       if (head == null)
           throw new NoSuchElementException();
       else if (head == tail)
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

       if (head == null)
           throw new NoSuchElementException();
       else if (head == tail)
       {
           olddata = head.data;
           head = null;
           tail = null;
       }
       else
       {
           Node p = head;
           while (p.next != tail)
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
        if (i < 0 || i > size - 1)
           throw new ArrayIndexOutOfBoundsException("Index " + i + "; size " + size);

        Node p = head;
        for (int k=0; k<i; k++)
           p = p.next;

        return p.data;
    }

    public void reverse()
    {
       if (head == null || head == tail) // 0 or 1 nodes
           return;

       Node p1 = null;
       Node p2 = head;
       Node p3 = head.next;

       while (p3 != null)
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
       StringBuilder sb = new StringBuilder("[ ");

       Node p = head;
       while (p != null)
       {
          sb.append(p.data + " ");
          p = p.next;
       }

       sb.append("]");

       return new String(sb);
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

    public static void main(String args[])
    {
       LL list = new LL();

       for (int i=0; i<5; i++)
          list.addFront(i);

       System.out.println(list);

       for (int i=0; i<5; i++)
          System.out.println(list.get(i));

          System.out.println(list.get(6));

/*
       list.reverse();

       System.out.println(list);


       for (int i=0; i<5; i++)
          list.addBack(i);

       System.out.println(list);

       int x = list.removeBack();
       int y = list.removeFront();

       System.out.println(list);
*/
    }
}
