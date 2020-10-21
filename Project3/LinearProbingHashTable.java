
// QuadraticProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items

import java.io.*;
import java.util.Random;
/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class LinearProbingHashTable<AnyType>
{
    /**
     * Construct the hash table.
     */
    public LinearProbingHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public LinearProbingHashTable( int size )
    {
        allocateArray( size );
        doClear( );
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param x the item to insert.
     */
    public boolean insert( AnyType x )
    {
            // Insert x as active
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
            return false;

        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>( x, true );
        theSize++;

            // Rehash; see Section 5.5// don't exceed half full to  maintain 2.5 on fail and 1.5 on success
        if( occupied > array.length / 2 )
            rehash( );

        return true;
    }

    /**
     * Expand the hash table.
     */
    private void rehash( )
    {
        HashEntry<AnyType> [ ] oldArray = array;

            // Create a new double-sized, empty table
        allocateArray( 2 * oldArray.length );
        occupied = 0;
        theSize = 0;

            // Copy table over
        for( HashEntry<AnyType> entry : oldArray )
            if( entry != null && entry.isActive )
                insert( entry.element );
    }

    // /**
    //  * Expand the hash table.
    //  */
    // public void printHashTable( )
    // {
    //     printElements();
    // }
    //
    // /**
    //  * Expand the hash table.
    //  */
    // private void printElements( )
    // {
    //     for( HashEntry<AnyType> entry : oldArray )
    //         if( entry != null && entry.isActive )
    //             System.out.println( entry.element );
    // }

    /**
     * Method that performs linear probing resolution.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    public int getPos( AnyType x )
    {
        return findPos( x );
    }

    /**
     * Method that performs linear probing resolution.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos( AnyType x )
    {
        int offset = 1;
        int currentPos = myhash( x );

        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            currentPos += offset;  // Compute ith probe
            if( currentPos >= array.length )
                currentPos -= array.length;
        }

        return currentPos;
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     * @return true if item removed
     */
    public boolean remove( AnyType x )
    {
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
        {
            array[ currentPos ].isActive = false;
            theSize--;
            return true;
        }
        else
            return false;
    }

    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }

    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( AnyType x )
    {
        int currentPos = findPos( x );
        return isActive( currentPos );
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    /**
     * Make a random grid of characters.
     */
    public char[][] makeGrid( int x )
    {
        //if ( x > 20 ) x = 20;//limiting grid size max to 20
        if ( x < 2 ) x = 2;//limiting grid size min to 2
        return randomGrid( x );
    }

    // /**
    //  * Make a random grid of characters.
    //  */
    // public void checkForWord( char[][] grid )
    // {
    //     lookForWord( grid );
    // }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        doClear( );
    }

    private void doClear( )
    {
        occupied = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }

    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }

    private static class HashEntry<AnyType>
    {
        public AnyType  element;   // the element
        public boolean isActive;  // false if marked deleted

        public HashEntry( AnyType e )
        {
            this( e, true );
        }

        public HashEntry( AnyType e, boolean i )
        {
            element  = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType> [ ] array; // The array of elements
    private int occupied;                 // The number of occupied cells
    private int theSize;                  // Current size

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static char[][] randomGrid( int n )
    {
        Random r = new Random();
        char[][] grid = new char[n][n];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < n; i++) {
            for ( int j = 0; j < n; j++ )
            {
                grid[i][j] = alphabet.charAt(r.nextInt(alphabet.length()));
                System.out.print( grid[i][j] );
                System.out.print(" ");
            }
            System.out.println();
        } // prints 50 random characters from alphabet
        return grid;
    }

    // /**
    //  * Internal method to find a prime number at least as large as n.
    //  * @param n the starting number (must be positive).
    //  * @return a prime number larger than or equal to n.
    //  */
    // private static String lookRight( char[][] grid, int row, int col, int numChar )
    // {
    //
    //     int i, j = 0;
    //     while ( i > maxChar )
    //     {
    //         while( j > maxChar )
    //         {
    //             String str = String.copyValueOf( grid[row], , numChar );
    //             System.out.println( str );
    //             if(contains( str ))
    //             {
    //                 System.out.println( str );
    //             }
    //             j++;
    //         }
    //         i++;
    //     }
    //     return
    // }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }


    // Simple main
    public static void main( String [ ] args )
    {
        LinearProbingHashTable<String> H = new LinearProbingHashTable<>( );


        long startTime = System.currentTimeMillis( );

        final int NUMS = 2000000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        try
        {
            FileInputStream fstream = new FileInputStream("D:\\ProgramFiles\\java\\Project3\\dictionary.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            try
            {
                //Read File Line By Line
                while ((strLine = br.readLine()) != null)   {
                    // Print the content on the console
                    //System.out.println (strLine);
                    H.insert( strLine );
                }

                //Close the input stream
                fstream.close();
            }
            catch ( IOException e ){}
        }
        catch( FileNotFoundException ex){}

        //H.printHashTable();
        int x = Integer.parseInt( args[0] );
        char[][] grid = H.makeGrid( x );
        String str = "";
        //look right
        System.out.println( "\nlook right"  );
        for ( int i = 0; i < x; i++ )
        {
            for( int j = 0; j < x; j++ )
            {
                for( int k = 0; k < ( x - j ); k++ )
                {
                    str = str + grid[i][j+k];
                    //System.out.println( str );
                    if( H.contains( ""+ str ) )
                    {
                        System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                    }
                }
                str = "";
            }
        }
        //look left
        System.out.println( "\nlook left" );
        for ( int i = 0; i < x; i++ )
        {
            for( int j = x-1 ; j >= 0; j-- )
            {
                //System.out.printf( "x %d, y %d\n", i, j );
                for( int k = 0; k < j; k++ )
                {
                    //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                    str = str + grid[ i ][ j - k ];
                    //System.out.println( str );
                    if( H.contains( ""+ str ) )
                    {
                        System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                    }
                }
                str = "";
            }
        }
        //look down
        System.out.println( "\nlook down" );

        for ( int j = 0; j < x; j++ )
        {
            for( int i = 0; i < x; i++ )
            {
                for( int k = 0; k < ( x - i ); k++ )
                {
                    str = str + grid[i+k][j];
                    //System.out.println( str );
                    if( H.contains( ""+ str ) )
                    {
                        System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                    }

                }
                str = "";
            }
        }
        //look up
        System.out.println( "\nlook up" );
        for ( int j = 0; j < x; j++ )
        {
            for( int i = x-1 ; i >= 0; i-- )
            {
                //System.out.printf( "x %d, y %d\n", i, j );
                for( int k = 0; k < i; k++ )
                {
                    //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                    str = str + grid[ i - k ][ j ];
                    //System.out.println( str );
                    if( H.contains( ""+ str ) )
                    {
                        System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                    }
                }
                str = "";
            }
        }
        //up right
        System.out.println( "\nlook up-right" );
        //i moving
        for( int i = 0 ; i < x; i++ )
        {
            int j = 0;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < i + 1; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i - k ][ j + k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        //j moving
        for( int j = x - 1 ; j > 0; j-- )
        {
            int i = x - 1;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < x - j; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i - k ][ j + k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        // up left
        System.out.println( "\nlook up-left" );
        //j moving
        for( int j = 0 ; j < x; j++ )
        {
            int i = x - 1;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < j + 1; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i - k ][ j - k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        //i moving
        for( int i = 0; i < x - 1; i++ )
        {
            int j = x - 1;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < i + 1; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i - k ][ j - k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        //down left
        System.out.println( "\nlook down-left" );
        //j moving
        for( int j = 0 ; j < x; j++ )
        {
            int i = 0;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < j + 1; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i + k ][ j - k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        //i moving
        for( int i = x - 1; i > 0; i-- )
        {
            int j = x - 1;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < x - i; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i + k ][ j - k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        // down right
        System.out.println( "\nlook down-right" );
        //i moving
        for( int i = x - 1; i >= 0; i-- )
        {
            int j = 0;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < x - i; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i + k ][ j + k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }
        //j moving
        for( int j = x - 1; j > 0; j-- )
        {
            int i = 0;
            //System.out.printf( "x %d, y %d\n", i, j );
            for( int k = 0; k < x - j; k++ )
            {
                //System.out.printf( "x %d, y %d, k %d, %s\n", i, j, k, str );
                str = str + grid[ i + k ][ j + k ];
                //System.out.println( str );
                if( H.contains( ""+ str ) )
                {
                    System.out.printf( "x %d, y %d, char %d, %s\n", i, j, k, str );
                }
            }
            str = "";
        }

        long endTime = System.currentTimeMillis( );

        System.out.println( "Elapsed time: " + (endTime - startTime) );
    }

}
