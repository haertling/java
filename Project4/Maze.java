//shaertling
import java.util.Random;
public class Maze {

    public Maze( Random number, int rows, int cols ) {

        int numEdges = 2 * rows * cols - rows - cols;

        this.maze = new Block[ rows ][ cols ];
        this.edges = new Edges[ numEdges ];
        this.randNum = number;

        int num = 0;

        for ( int i = 0; i < rows; i++ ) {
            for ( int j = 0; j < cols; j++ ) {
                this.maze[ i ][ j ] = new Block();
                if ( i < rows - 1 ) {
                    this.edges[num++] = new Edges( i, j, 'h' );
                }
                if ( j < cols - 1 ) {
                    this.edges[num++] = new Edges( i, j, 'v' );
                }
            }
        }
    }

    public void genMaze( int rows, int cols )
    {

        this.maze[ 0 ][ 0 ].setLeft( false );
        this.maze[ rows - 1 ][ cols - 1 ].setRight( false );
        int numElements = rows * cols;
        DisjSets ds = new DisjSets( numElements );
        int a, b;
        int i = 0;
        this.displayMaze( rows, cols );
        while ( ( i < numElements * 20 ) && ( ds.find( 0 ) != ds.find( numElements - 1 )) ) {//
            Edges randEdge = this.edges[ this.randNum.nextInt( this.edges.length ) ];
            int x = randEdge.getX();
            int y = randEdge.getY();
            int block1 = x * cols + y;

            if ( randEdge.getDirection() == 'h' )
            {
                int block2 = ( x + 1 ) * cols + y;

                if ( ds.find( block1 ) != ds.find( block2 ) )
                {
                    this.maze[ x ][ y ].setBot( false );
                    this.maze[ x + 1 ][ y ].setTop( false );
                    a = ds.find( block1 );
                    b = ds.find( block2 );
                    ds.union( a, b );
                }
            }
            else
            {
                int block2 = x * cols + y + 1;
                a = ds.find( block1 );
                b = ds.find( block2 );

                if ( ds.find( block1 ) != ds.find( block2 ) )
                {
                    this.maze[ x ][ y ].setRight( false );
                    this.maze[ x ][ y + 1 ].setLeft( false );
                    a = ds.find( block1 );
                    b = ds.find( block2 );
                    ds.union( a, b );
                }
            }
            i++;
        }
    }

    public void displayMaze( int x, int y )
    {
        //print top boundary
        for(int i = 0; i < y; i++)
        {
            System.out.print( " _ " );
        }
        System.out.println();

        for( int i = 0; i < x; i++ )//rows
        {
            for( int j = 0; j < y; j++ )//elements
            {
                Block print = this.maze[ i ][ j ];
                if ( print.getLeft() == true ) System.out.print( "|" );
                else System.out.print( " " );

                if ( print.getBot() == true ) System.out.print( "_" );
                else System.out.print( " " );

                if ( print.getRight() == true ) System.out.print( "|" );
                else System.out.print( " " );
            }
            System.out.println();
        }
    }

    public Block getBlock( int row, int col ) {
        return this.maze[ row ][ col ];
    }

    public void setBlock(int r, int c, Block block) {
        this.maze[r][c] = block;
    }

    private Block[][] maze;
    private Random randNum;
    private Edges[] edges;

    public static void main( String [ ] args )
    {
        if ( args.length < 2 )
        {
            throw new IllegalArgumentException("Two arguments are required.");
        }
        int x = Integer.parseInt( args[ 0 ] );
        int y = Integer.parseInt( args[ 1 ] );

        Maze maze = new Maze( new Random(), x, y );

        maze.genMaze( x, y );
        maze.displayMaze( x, y );
    }
}
