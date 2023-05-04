//package src;

import java.util.List;
import java.util.LinkedList;

// Represents a vertex in the graph.
class Vertex
{
    public String     name;   // Vertex name
    public List<Edge> adj;    // Adjacent vertices
    public double     dist;   // Cost
    public Vertex     prev;   // Previous vertex on shortest path
    public int        scratch;// Extra variable used in algorithm
/**takes the vertex and the name of the vertex
 */
    public Vertex( String nm )
      { name = nm; adj = new LinkedList<Edge>( ); reset( ); }
/**resets the graph vertex */
    public void reset( )
    //  { dist = Graph.INFINITY; prev = null; pos = null; scratch = 0; }    
    { dist = Graph.INFINITY; prev = null; scratch = 0; }
    /*
     * converts the ascii value to a string 
     */
    public String toString(){
        return name;
    }
      
   // public PairingHeap.Position<Path> pos;  // Used for dijkstra2 (Chapter 23)
}
