//shaertling
//the edges of the block
public class Edges {

    public Edges( int x, int y, char dir )
    {
        this.x = x;
        this.y = y;
        this.direction = dir;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public char getDirection()
    {
        return this.direction;
    }

    private int x;
    private int y;
    private char direction;
}
