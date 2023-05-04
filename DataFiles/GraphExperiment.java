
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
//import java.util.List;
import java.util.Queue;

import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;


// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void unweighted( String s )  --> Single-source unweighted
// void dijkstra( String s )    --> Single-source weighted
// void negative( String s )    --> Single-source negative weighted
// void acyclic( String s )     --> Single-source acyclic
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.
/*
 * class that takes the count of edges and vertex processing 
 * takes the count of priority queue operation
 */
public class GraphExperiment {
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    static int eCount;
    static int vCount;
    static int pqCount;

    /**
     * Add a new edge to the graph.
     */
    public void addEdge(String sourceName, String destName, double cost) {
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
        v.adj.add(new Edge(w, cost));
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null)
            throw new NoSuchElementException("Destination vertex not found");
        else if (w.dist == INFINITY)
            System.out.println(destName + " is unreachable");
        else {
            System.out.print("(Cost is: " + w.dist + ") ");
            printPath(w);
            System.out.println();
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath(Vertex dest) {
        if (dest.prev != null) {
            printPath(dest.prev);
            System.out.print(" to ");
        }
        System.out.print(dest.name);
    }

    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll() {
        for (Vertex v : vertexMap.values())
            v.reset();
    }

    /**
     * Single-source unweighted shortest-path algorithm.
     */
    public void unweighted(String startName) {
        clearAll();

        Vertex start = vertexMap.get(startName);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");

        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        start.dist = 0;

        while (!q.isEmpty()) {
            Vertex v = q.remove();

            for (Edge e : v.adj) {
                Vertex w = e.dest;
                if (w.dist == INFINITY) {
                    w.dist = v.dist + 1;
                    w.prev = v;
                    q.add(w);
                }
            }
        }
    }

    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra)
     * using priority queues based on the binary heap
     */
    public void dijkstra(String startName) {
        PriorityQueue<Path> pq = new PriorityQueue<Path>();
        GraphExperiment g = new GraphExperiment();

        Vertex start = vertexMap.get(startName);
        // System.out.println(g.startVertex);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");

        clearAll();
        pq.add(new Path(start, 0));
        start.dist = 0;

        int nodesSeen = 0;
        // int vCount=0;//counts the number processed vertices
        // int eCount=0;// counts the number of processed edge
        pqCount = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {

            pqCount += (int) (Math.log(pq.size()) / Math.log(2));
            ;
            Path vrec = pq.remove();
            Vertex v = vrec.dest;
            if (v.scratch != 0) // already processed v
                continue;

            v.scratch = 1;
            nodesSeen++;
            // counts all the processed vertices
            vCount++;

            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost;

                if (cvw < 0)
                    throw new GraphException("Graph has negative edges");
                // opcounrt_e++;
                // edges being processed.
                eCount++;
                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                    pq.add(new Path(w, w.dist));
                    // opcount_pq=+=int(math.log(pq.size())/math.log(2))
                    // eCount++;
                    // opcount_pq++; coun for the pq
                    pqCount += (int) (Math.log(pq.size()) / Math.log(2));
                    pqCount++;
                }
            }
        }
    }

    /**
     * Single-source negative-weighted shortest-path algorithm.
     * Bellman-Ford Algorithm
     */
    public void negative(String startName) {
        clearAll();

        Vertex start = vertexMap.get(startName);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");

        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        start.dist = 0;
        start.scratch++;

        while (!q.isEmpty()) {
            Vertex v = q.remove();
            if (v.scratch++ > 2 * vertexMap.size())
                throw new GraphException("Negative cycle detected");

            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost;

                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                    // Enqueue only if not already on the queue
                    if (w.scratch++ % 2 == 0)
                        q.add(w);
                    else
                        w.scratch--; // undo the enqueue increment
                }
            }
        }
    }

    /**
     * Single-source negative-weighted acyclic-graph shortest-path algorithm.
     */
    public void acyclic(String startName) {
        Vertex start = vertexMap.get(startName);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");
        // System.out.println();

        clearAll();
        Queue<Vertex> q = new LinkedList<Vertex>();
        start.dist = 0;

        // Compute the indegrees
        Collection<Vertex> vertexSet = vertexMap.values();
        for (Vertex v : vertexSet)
            for (Edge e : v.adj)
                e.dest.scratch++;

        // Enqueue vertices of indegree zero
        for (Vertex v : vertexSet)
            if (v.scratch == 0)
                q.add(v);

        int iterations;
        for (iterations = 0; !q.isEmpty(); iterations++) {
            Vertex v = q.remove();

            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost;

                if (--w.scratch == 0)
                    q.add(w);

                if (v.dist == INFINITY)
                    continue;

                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                }
            }
        }

        if (iterations != vertexMap.size())
            throw new GraphException("Graph has a cycle!");
    }

    

    /**
     * A main routine that:
     * 1. Reads a file containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Repeatedly prompts for two vertices and
     * runs the shortest path algorithm.
     * The data file is a sequence of lines of the format
     * source destination cost
     */
    public static void main(String[] args) {

        GraphExperiment g = new GraphExperiment();

        

        try {
            FileReader fin = new FileReader("Dataset.v20.e35");// args[0]
            // String startVertex= fin[0];

            Scanner graphFile = new Scanner(fin);
            System.out.println("Vertex" + "  Edge" + "  eCount" + " vCount" + " pqCount");
            while (graphFile.hasNext()) {
                String startLine = graphFile.nextLine();
                String[] splitLine = startLine.split(" ");
                String firstVertex = splitLine[0];
                String secondVertex = splitLine[1];
                int firstEdge = Integer.parseInt(splitLine[2]);
                g.addEdge(firstVertex, secondVertex, firstEdge);
    
                g.dijkstra(firstVertex);
                //System.out.println("File read...");
                //System.out.println(g.vertexMap.size() + " vertices");
                //System.out.println("Vertex" + " Edge" + " eCount" + " vCount" + " pqCount");
                System.out.println((firstVertex+"") +" ,"+ (firstEdge+" ") + " ,  " + 
                        eCount + " , " + vCount + " ,    " + pqCount);// change all these avlues to static
            }
        } catch (IOException e) {
            System.err.println(e);
        }
       
      
       

    }
}
