//package src;

// Represents an edge in the graph.
/**edge class that takes the destination and the cost
 */
public class Edge
{
    public Vertex     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    /**edge method
    takes the vertex of type b 
    and the cost of type double
     */
    public Edge( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
    /*
     * converts the value of the edge from a ascii value to a string
     */
    public String toString(){
        return cost+"";
    }
}
