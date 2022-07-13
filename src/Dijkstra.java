public class Dijkstra {

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
