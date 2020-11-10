//shaertling
//block is a piece of the maze
public class Block
{

    public Block()
    {
        this.top = true;
        this.bot = true;
        this.right = true;
        this.left = true;
    }

    public Block( boolean t, boolean b, boolean r, boolean l )
    {
        this.top = t;
        this.bot = b;
        this.right = r;
        this.left = l;
    }

    // get methods for the edges
    public boolean getTop()
    {
        return this.top;
    }

    public boolean getBot()
    {
        return this.bot;
    }

    public boolean getRight()
    {
        return this.right;
    }

    public boolean getLeft()
    {
        return this.left;
    }

    // set methods for the edges
    public void setTop( boolean topVal )
    {
        this.top = topVal;
    }

    public void setBot( boolean botVal )
    {
        this.bot = botVal;
    }

    public void setRight( boolean rightVal ) {
        this.right = rightVal;
    }

    public void setLeft( boolean leftVal ) {
        this.left = leftVal;
    }

    private boolean top;
    private boolean bot;
    private boolean right;
    private boolean left;
}
