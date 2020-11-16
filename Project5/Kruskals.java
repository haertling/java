//shaertling
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Kruskals {

    public static class Connection implements Comparable<Connection>
    {

        int distance;
        String cityA, cityB;

        Connection( int d, String str1, String str2 )
        {
            this.distance = d;
            this.cityA = str1;
            this.cityB = str2;
        }

        @Override
        public int compareTo( Connection edge)
        {
            if ( this.distance < edge.distance ) return -1;
            else if ( this.distance > edge.distance ) return 1;
            else return 0;
        }
    }

    public void kruskal()
    {

        ArrayList<Connection> Connections = new ArrayList<>();
        ArrayList<String> Cities = new ArrayList<>();

        int totalCities = 0;
        int sumDistance = 0;

        try
        {
            FileInputStream fstream = new FileInputStream( "assn9_data.csv" );
            BufferedReader br = new BufferedReader( new InputStreamReader( fstream ));
            String line = "";
            String delimeter = ",";
            try
            {

                while (( line = br.readLine() ) != null )
                {
                    String[] token = line.split( delimeter );
                    Cities.add( token[0] );
                    Connections.add( new Connection( Integer.parseInt(token[2]), token[0], token[1] ));
                    //System.out.printf("int: %s, cityA: %s, cityB: %s\n", token[2], token[0], token[1] );
                    for ( int i = 3; i < token.length; i++ )
                    {
                        Connections.add(new Connection( Integer.parseInt(token[i + 1]), token[0], token[i] ));
                        //System.out.printf("int: %s, cityA: %s, cityB: %s\n", token[i+1], token[0], token[i] );
                        i++;
                    }
                    totalCities++;
                }

                fstream.close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        catch( FileNotFoundException ex){}

        int edgesAccepted = 0;
        DisjSets ds = new DisjSets( Cities.size() );

        PriorityQueue<Connection> queue = new PriorityQueue<>();

        for ( Connection edge : Connections )
        {
            queue.add( edge );
        }

        while ( edgesAccepted < Cities.size() - 1 )
        {
            Connection edge = queue.poll();

            if ( ds.find( Cities.indexOf( new String( edge.cityA ))) != ds.find( Cities.indexOf( new String( edge.cityB ))) )
            {
                ds.union( ds.find( Cities.indexOf( new String( edge.cityA )) ), ds.find( Cities.indexOf( new String( edge.cityB ))) );
                sumDistance = sumDistance + edge.distance;
                System.out.println( "Distance from " + edge.cityA + " to " + edge.cityB + ": " + edge.distance );
                edgesAccepted++;
            }

        }
        System.out.println();
        System.out.println( "sum of all of the distances in the tree: " + sumDistance );

    }

    public static void main( String[] args )
    {
        Kruskals algorithm = new Kruskals();
        algorithm.kruskal();
    }

}
