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
        return compareStructure( root , r);
    }

    /**
     * Compares the structure of current tree to another tree and returns
     * true if they match.
     * @param r root of second tree to compare to
     * @return true if trees are identical
     */
    public boolean equals( BinaryNode<AnyType> r )
    {
        return equals( root , r);
    }

    /**
     * Creates and returns a new tree that is a copy of the original tree.
     * @return BinaryNode root of the copied tree
     */
    public BinaryNode<AnyType> copy( )
    {
        return copy( root );
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
     * @return BinaryNode root of new tree
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
        final int NUMS = 8;
        final int GAP  =  2;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
            t.insert( i );
            r.insert( i );
        }
        for( int i = 1; i < NUMS; i+= 2 )
        {
            t.remove( i );
            r.remove( i );
        }

        if( NUMS < 40 )
        {
            t.printTree( );
            r.printTree( );
            int count = t.nodeCount( );
            System.out.printf( "nodeCount = %d\n", count );
            boolean full = t.isFull( );
            if ( full == true )
            {
                System.out.println( "The tree is full" );
            }
            else
            {
                System.out.println( "The tree is not full" );
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
            boolean id = t.equals( r.root );
            if ( id == true )
            {
                System.out.println( "The trees are identical" );
            }
            else
            {
                System.out.println( "The trees are not identical" );
            }
            e.root = t.copy( );
            e.printTree( );

        }

        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
    }
}
