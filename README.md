## Overview

This project aims to implement Dijkstra's shortest path algorithm to find the shortest path between two vertices using weighted edges. The experiment involves generating datasets, processing data, and analyzing the results.

## Classes Created

1. **DataGenerator**
   - Generates datasets for vertices and edges.
   - Uses a Java `FileWriter` function to write the generated data into a text file.
   - Vertices are created by iterating through a specified range (e.g., 10, 20).
   - Randomly joins vertices and assigns a cost to the edge.
   - Data is stored in a text file.

2. **GraphExperiment**
   - Reads data from the text file using a Java `FileReader`.
   - Processes data by splitting and indexing lines.
   - Provides methods for manipulating data, such as the `addEdge` method.
   - Utilizes `Vertex` objects to store previous nodes and `Edge` objects to represent costs.
   - Contains a `Dijkstra` method to find the shortest path.
   - Outputs vertex sets and associated costs.
   - Tracks `eCount`, `vCount`, and `pqCount`.

3. **Edge**
   - Represents an edge in the graph.
   - Stores distance and cost information.
   - Used by the `addEdge` method in the `GraphExperiment` class.

4. **Vertex**
   - Represents a vertex in the graph.
   - Used in the `GraphExperiment` class to handle source and destination vertices.
   - An `Edge` object is used to add the adjoining cost.

5. **Path**
   - Uses a data type of type `Vertex`.
   - Compares destination vertices to the previous ones.

## Experiment Methodology

1. **Dataset Generation (Step One)**
   - Created a `DataGenerator` class.
   - Varied the cost of edges for different vertex ranges (e.g., 10, 20, 30, 40, 50).
   - Vertices were randomly joined, and edge costs were assigned.
   - Data stored in separate text files.

2. **Data Processing (Step Two)**
   - Used a `FileReader` to read data from the text file.
   - Split each line into indexed strings (start vertex, destination index, cost).
   - Passed these parameters to the `addEdge` method and the `Dijkstra` method in the `GraphExperiment` class.
   - `Dijkstra` method finds the shortest path.
   - `addEdge` method records source vertex, destination vertex, and cost after finding the shortest path.

3. **Instrumentation (Step Three)**
   - Added counters for edges (`eCount`), vertices (`vCount`), and priority queue (`pqCount`).
   - Used to calculate the number of operations and analyze complexities.

## Analysis

- As the number of vertices increases, the range of operations also appears to increase.
- The scattered points in the graph represent different numbers of edges processed.
- There is a drop after 40 vertices, suggesting that operations increase with the vertex count up to a certain threshold.

- The graph showing the number of operations against the number of edges indicates that as the number of edges increases, the bound of operations also increases.
- Initially, the edges are tightly clustered, but as they increase, they begin to scatter, leading to higher operations.

- Based on the given data, it appears that the number of operations is bound by |E|Log|V|.
- The closeness of the two trends suggests that the operation trend is bound by a constant.
- Operations and E|Log|V| are not directly related; E|Log|V| serves as an upper bound for the operations undertaken.

## Conclusion

The programmatically obtained results align with the theoretical performance bounds. The complexity E|Log|V| holds true for any number of vertices, provided the graph is neither too sparse nor too dense.
