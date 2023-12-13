//package src;

// Represents an entry in the priority queue for Dijkstra's algorithm.
// 
public class Path implements Comparable<Path>
{
    public Vertex     dest;   // w
    public double     cost;   // d(w)
    /**
    path taken by the graph of the datatype vertex class and double for distance */
    public Path( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
    /** 
    compare method to check which path path is shorter */
    public int compareTo( Path rhs )
    {
        double otherCost = rhs.cost;
        
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}
