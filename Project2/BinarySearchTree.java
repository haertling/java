// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate
import java.util.LinkedList;
import java.util.Queue;
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Recursively traverses the tree and returns the count of nodes in the tree.
     * @return number of nodes
     */
    public int nodeCount( )
    {
        if( isEmpty( ) )
            return 0;
        return nodeCount( root );
    }

    /**
     * Function to check if the tree is Full. A full tree has every node
     * as either a leaf or a parent with two children.
     * @return true if the tree is full
     */
    public boolean isFull( )
    {
        if( isEmpty( ) )
            return true;
        return isFull( root );
    }

    /**
     * Compares the structure of current tree to another tree and returns
     * true if they match.
     * @param r root of second tree to compare to
     * @return true structure matches
     */
    public boolean compareStructure( BinaryNode<AnyType> r )
    {
        return compareStructure( root , r );
    }

    /**
     * Compares the structure of current tree to another tree and returns
     * true if they match.
     * @param r root of second tree to compare to
     * @return true if trees are identical
     */
    public boolean equals( BinaryNode<AnyType> r )
    {
        return equals( root , r );
    }

    /**
     * Creates and returns a new tree that is a copy of the original tree.
     * @return BinaryNode root of the mirrored tree
     */
    public BinaryNode<AnyType> copy( )
    {
        return copy( root );
    }

    /**
     * Creates and returns a new tree that is a mirror image of the original tree.
     * @return BinaryNode root of the copied tree
     */
    public BinaryNode<AnyType> mirror( )
    {
        return mirror( root );
    }

    /**
     * Compares the structure of current tree to another tree and returns
     * true if they are mirrors.
     * @param r root of second tree to compare to
     * @return true if trees are mirrors
     */
    public boolean isMirror( BinaryNode<AnyType> r )
    {
        return isMirror( root , r );
    }

    /**
     * Performs a single rotation on the node having the passed value.
     * @param x the item to rotate on.
     */
    public void rotateRight( AnyType x )
    {
        if ( x  == root.element )
        {
            root = rotateRight( root );
        }
        else
        {
            BinaryNode<AnyType> prev = root;
            BinaryNode<AnyType> node = getNode( x, root, prev );
            node.left = rotateRight( node.left );
        }
    }

    /**
     * Performs a single rotation on the node having the passed value.
     * @param x the item to rotate on.
     */
    public void rotateLeft( AnyType x )
    {
        if ( x  == root.element )
        {
            root = rotateLeft( root );
        }
        else
        {
            BinaryNode<AnyType> prev = root;
            BinaryNode<AnyType> node = getNode( x, root, prev );
            node.right = rotateLeft( node.right );
        }
    }

    /**
     * performs a level-by-level printing of the tree.
     */
    public void printLevels(  )
    {
        printLevels( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    /**
     * Recursively traverses the tree and returns the count of nodes.
     * @param t the node that roots the subtree.
     * @return number of nodes
     */
    private int nodeCount( BinaryNode<AnyType> t )
    {
        if( t == null )
            return 0;
        return 1 + nodeCount ( t.left ) + nodeCount ( t.right );
    }

    /**
     * Function to check if the tree is Full. A full tree has every node
     * as either a leaf or a parent with two children.
     * @param t the node that roots the subtree.
     * @return true if the tree is full
     */
    private boolean isFull( BinaryNode<AnyType> t )
    {
        if( t.left == null && t.right == null )
            return true;
        if( t.left != null && t.right != null )
            return ( isFull( t.left ) && isFull( t.right ));
        return false;
    }

    /**
     * Compares the structure of current tree to another tree and returns
     * true if they match.
     * @param t node that roots the first subtree.
     * @param r node that roots the second subtree.
     * @return true structure matches
     */
    private boolean compareStructure( BinaryNode<AnyType> t, BinaryNode<AnyType> r )
    {
        if( t == null && r == null )
            return true;
        if( t != null && r != null )
            return ( compareStructure( t.left, r.left ) && compareStructure( t.right, r.right ));
        return false;
    }

    /**
     * Compares the current tree to another tree and returns true
     * if they are identical.
     * @param t node that roots the first subtree.
     * @param r node that roots the second subtree.
     * @return true if trees are identical
     */
    private boolean equals( BinaryNode<AnyType> t, BinaryNode<AnyType> r )
    {
        if( t == null && r == null )
            return true;
        if( t != null && r != null )
            return ( t.element == r.element && equals( t.left, r.left ) && equals( t.right, r.right ) );
        return false;
    }

    /**
     * Creates and returns a new tree that is a copy of the original tree.
     * @param t node that roots the first subtree.
     * @return BinaryNode root of coppied tree
     */
    private BinaryNode<AnyType> copy( BinaryNode<AnyType> t )
    {
        if ( t == null )
            return null;
        BinaryNode<AnyType> r = new BinaryNode<AnyType>( t.element );
        r.left  = copy( t.left );
        r.right = copy( t.right );
        return r;
    }

    /**
     * Creates and returns a new tree that is a mirror image of the original tree.
     * @param t node that roots the first subtree.
     * @return BinaryNode root of mirrored tree
     */
    private BinaryNode<AnyType> mirror( BinaryNode<AnyType> t )
    {
        if ( t == null )
            return null;
        BinaryNode<AnyType> r = new BinaryNode<AnyType>( t.element );
        r.left  = mirror( t.right );
        r.right = mirror( t.left );
        return r;
    }

    /**
     * Creates and returns a new tree that is a mirror image of the original tree.
     * @param t node that roots the first subtree.
     * @return BinaryNode root of mirrored tree
     */
    private boolean isMirror( BinaryNode<AnyType> t, BinaryNode<AnyType> r )
    {
        if ( t == null && r == null )
            return true;
        if ( t == null || r == null )
            return false;

        return t.element == r.element && isMirror( t.left, r.right ) && isMirror( t.right, r.left );
    }

    /**
     * Creates and returns a new tree that is a mirror image of the original tree.
     * @param t node that roots the first subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> getNode( AnyType x, BinaryNode<AnyType> t, BinaryNode<AnyType> prev )
    {
        if( t != null )
        {
            if( x.compareTo( t.element ) == 0 )
               return prev;
            else
            {
                BinaryNode<AnyType> foundNode = getNode( x, t.left, t );
                if( foundNode == null )
                    foundNode = getNode( x, t.right, t);
                return foundNode;
            }
        }
        else
            return null;

    }

    private BinaryNode<AnyType> rotateRight( BinaryNode<AnyType> t )
    {
        if ( t.left == null )
            return t;
        BinaryNode<AnyType> x = t.left;
        t.left = x.right;
        x.right = t;

	    return x;
    }

    private BinaryNode<AnyType> rotateLeft( BinaryNode<AnyType> t )
    {
        if ( t.right == null )
            return t;
        BinaryNode<AnyType> x = t.right;
        t.right = x.left;
	    x.left = t;
	    return x;
    }

    /**
     * performs a level-by-level printing of the tree.
     * @param t node that roots the first subtree.
     */
    private void printLevels( BinaryNode<AnyType> t )
    {
        if( t == null )
            return;

        Queue<BinaryNode<AnyType>> q =new LinkedList<BinaryNode<AnyType>>( );
        q.add( t );

        while(true)
        {
            int nodeCount = q.size( );
            if( nodeCount == 0 )
                break;

            while( nodeCount > 0 )
            {
                BinaryNode<AnyType> node = q.peek( );
                System.out.print( node.element + " " );
                q.remove( );
                if( node.left != null )
                    q.add( node.left );
                if( node.right != null )
                    q.add( node.right );
                nodeCount--;
            }
            System.out.println( );
        }

    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> r = new BinarySearchTree<>( );
        BinarySearchTree<Integer> e = new BinarySearchTree<>( );
        BinarySearchTree<Integer> s = new BinarySearchTree<>( );
        BinarySearchTree<Integer> fullTree = new BinarySearchTree<>( );
        BinarySearchTree<Integer> Lroot = new BinarySearchTree<>( );
        BinarySearchTree<Integer> Rroot = new BinarySearchTree<>( );
        final int NUMS =  10;
        final int GAP  =  2;

        //System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
            t.insert( i );
        }
        for( int i = 1; i < NUMS; i+= 2 )
        {
            t.remove( i );
        }

        if( NUMS < 40 )
        {
            System.out.println( "The tree to start" );
            t.printTree( );

            //demonstration of nodeCount
            System.out.println( "demonstration of nodeCount" );
            int count = t.nodeCount( );
            System.out.printf( "nodeCount = %d\n\n", count );

            //demonstration of isFull
            System.out.println( "demonstration of isFull" );
            for( int i = 0; i < 2; i++ )
            {
                boolean full = false;
                if ( i == 1 )
                {
                    fullTree.insert( 4 );
                    fullTree.insert( 2 );
                    fullTree.insert( 6 );
                    System.out.println( "full check tree levels:" );
                    System.out.println( "tree.printLevels();" );
                    fullTree.printLevels();
                }

                if ( i == 0 )
                    full = t.isFull( );
                else
                    full = fullTree.isFull( );
                if ( full == true )
                    System.out.println( "The tree is full\n" );
                else
                    System.out.println( "The tree is not full\n" );
            }





            //demonstration of copy
            System.out.println( "demonstration of copy" );
            r.root = t.copy( );
            System.out.println( "The copied tree of the original" );
            r.printTree( );

            //demonstration of compareStructure
            System.out.println( "demonstration of compareStructure" );
            for( int i = 0; i < 2; i++ )
            {
                if ( i == 1 )
                {
                    t.remove( 8 );
                    System.out.println( "8 was removed from one tree" );
                }

                boolean same = t.compareStructure( r.root );
                if ( same == true )
                {
                    System.out.println( "The trees are the same structure" );
                }
                else
                {
                    System.out.println( "The trees are not the same structure" );
                }
                if ( i == 1 )
                {
                    t.insert( 8 );
                    System.out.println( "8 was added back to one tree\n" );
                }
            }

            //demonstration of equals
            System.out.println( "demonstration of equals" );
            for( int i = 0; i < 2; i++ )
            {
                if ( i == 1 )
                {
                    t.remove( 8 );
                    System.out.println( "8 was removed from one tree" );
                }

                boolean id = t.equals( r.root );
                if ( id == true )
                {
                    System.out.println( "The trees are identical" );
                }
                else
                {
                    System.out.println( "The trees are not identical" );
                }
                if ( i == 1 )
                {
                    t.insert( 8 );
                    System.out.println( "8 was added back to one tree\n" );
                }
            }

            //demonstration of mirror
            System.out.println( "demonstration of mirror" );
            System.out.println( "The tree to start" );
            t.printTree( );
            System.out.println( "The tree to start.printLevels();" );
            t.printLevels( );
            e.root = t.mirror();
            System.out.println( "The mirrored tree to start" );
            e.printTree( );
            System.out.println( "The mirrored tree.printLevels();\n" );
            t.printLevels( );

            //demonstration of isMirror
            System.out.println( "demonstration of isMirror" );
            for( int i = 0; i < 2; i++ )
            {
                if ( i == 1 )
                {
                    t.remove( 8 );
                    System.out.println( "8 was removed from one tree" );
                }

                boolean mirror = e.isMirror( t.root );
                if ( mirror == true )
                {
                    System.out.println( "The trees are mirrors" );
                }
                else
                {
                    System.out.println( "The trees are not mirrors" );
                }

                if ( i == 1 )
                {
                    t.insert( 8 );
                    System.out.println( "8 was added back to one tree\n" );
                }
            }


            //example of rotateLeft
            System.out.println( "example of rotateLeft" );
            System.out.println( "The tree to start" );
            t.printTree( );
            s.root = t.mirror();
            System.out.println( "The tree to start.printLevels();" );
            t.printLevels();
            t.rotateLeft( 4 );// try 2 here for root being rotated out 
            System.out.println( "tree.rotateLeft( 2 );" );
            System.out.println( "The tree one rotate left" );
            t.printTree( );
            System.out.println( "The tree one rotate left.printLevels();" );
            t.printLevels();

            //example of rotateRight
            System.out.println( "\nexample of rotateRight" );
            System.out.println( "The mirror tree to start" );
            s.printTree( );
            System.out.printf( "mirrorTree.printLevels(); \n" );
            s.printLevels();
            s.rotateRight( 4 );// try 2 here for root being rotated out
            System.out.println( "tree.rotateRight( 4 );" );
            System.out.println( "The tree one rotate right" );
            s.printTree( );
            System.out.println( "The tree one rotate right.printLevels();" );
            s.printLevels();
            System.out.println( "to show mirror working, orignal tree levels again:" );
            t.printLevels();
        }
    }
}
