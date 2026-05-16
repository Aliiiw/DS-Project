# DS Project - Dijkstra Algorithm

A Java implementation of Dijkstra's shortest-path algorithm for directed weighted graphs. The project uses an adjacency-list graph representation backed by `LinkedList` and a custom binary min-heap to efficiently select the next closest vertex.

This is a data structures course project focused on graph representation, heap operations, and shortest-path computation.

## Features

- Directed weighted graph support
- Graph stored as adjacency lists
- Custom `MinHeap` implementation
- `decreaseKey` support for Dijkstra updates
- Computes minimum distance from one source vertex to every other vertex
- Reads graph data from text input
- Includes small and large sample input files

## Tech Stack

- Java
- Standard library collections: `LinkedList`
- File input with `FileInputStream` and `Scanner`

No external dependencies are required.

## Project Structure

```text
.
+-- src/
|   +-- Dijkstra.java      # Main implementation
|   +-- input.txt          # Small sample graph
|   +-- input2.txt         # Larger sample graph
+-- out/                   # Compiled IntelliJ output
+-- DS-Project.iml         # IntelliJ project file
+-- README.md
```

## Main Classes

`Dijkstra.java` contains several nested classes:

| Class | Purpose |
| --- | --- |
| `Dijkstra` | Entry point and file-reading logic |
| `Map` | Directed graph with adjacency lists |
| `Node` | Edge model with source, destination, and weight |
| `HeapNode` | Vertex-distance pair stored in the heap |
| `MinHeap` | Custom binary heap with index tracking for decrease-key |

## Input Format

The first line contains:

```text
number_of_vertices number_of_edges source_vertex
```

Each following line contains one directed weighted edge:

```text
source destination weight
```

Example:

```text
5 10 0
0 1 10
0 4 5
1 2 1
4 3 2
```

This means the graph has `5` vertices, `10` edges, and Dijkstra starts from vertex `0`.

## How It Works

1. The program reads the graph input file.
2. It creates a `Map` with one adjacency list per vertex.
3. Every edge is inserted into the source vertex's adjacency list.
4. All vertices are inserted into a custom min-heap.
5. The source vertex distance is initialized to `0`; all others start at `Integer.MAX_VALUE`.
6. The algorithm repeatedly extracts the closest unvisited vertex.
7. For each outgoing edge, it relaxes the destination distance.
8. Updated distances are applied with `decreaseKey`.
9. Final distances from the source to every vertex are printed.

## How to Run

Compile the source:

```bash
javac -d out src/Dijkstra.java
```

Run the program:

```bash
java -cp out Dijkstra
```

Important: the current `main` method opens an absolute file path:

```java
/Users/alirahimi/Documents/GitHub/DS-Project/src/input2.txt
```

If you run the project from another location, update that path in `src/Dijkstra.java` or change it to a relative path such as:

```java
new FileInputStream("src/input2.txt")
```

## Example Output

The program prints one line per vertex:

```text
Start from 0 to vertex 0 Minimum Distance 0
Start from 0 to vertex 1 Minimum Distance 8
Start from 0 to vertex 2 Minimum Distance 9
```

Exact output depends on the selected input file.

## Notes

- The graph is directed. If an undirected graph is needed, add both edge directions to the input.
- Edge weights should be non-negative for Dijkstra's algorithm.
- The repository includes compiled `out/` files, which are not required for source-based development.
- `src/input2.txt` is a large sample graph; `src/input.txt` is easier for quick manual testing.
- The code currently uses a hard-coded input path.

## Possible Improvements

- Read the input path from command-line arguments
- Replace the hard-coded absolute path with a relative path
- Add path reconstruction, not only distance output
- Add tests for heap operations and shortest-path results
- Remove compiled output files from version control
- Use Java generics more strictly to remove unchecked warnings
- Support undirected graph mode with a flag
