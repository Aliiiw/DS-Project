import java.util.LinkedList;

public class Dijkstra {


    static class Map{
        int vertices;
        LinkedList<Node>[] neighbors;

        public Map(int vertices) {
            this.vertices = vertices;
            this.neighbors = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++){
                neighbors[i] = new LinkedList<>();
            }
        }

        public void addNodeToMap(int source, int destination, int weight){
            Node node = new Node(source, destination, weight);
            neighbors[source].addFirst(node);                               //be aval linked list ezaf mishe

        }

    }

    static class HeapNode{
        int vertex;
        int distance;
    }

    static class Node{
        int source;
        int destination;
        int weight;

        public Node(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

    }
}
