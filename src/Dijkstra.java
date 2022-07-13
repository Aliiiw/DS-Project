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
        public void findMinimumDistance(int source){
            int maximumValue = Integer.MAX_VALUE;
            boolean[] visited = new boolean[vertices];
            HeapNode[] heapNodes = new HeapNode[vertices];                 //heap node dorost mikonim ta ras haro negah darim

            for (int i = 0; i < vertices; i++){                           //meghdar dehi avalie hame bi nahayat bashe
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].distance = maximumValue;
            }

            heapNodes[source].distance = 0;                             //ras source faselash ba khodesh sefre

            MinHeap minHeap = new MinHeap(vertices);                   //hame ras haro mirizim tooye min heap

            for (int i = 0; i < vertices; i++){
                minHeap.insertToMinHeap(heapNodes[i]);
            }

            while (!minHeap.isEmpty()){                             //minimum ro dar miarim inja
                HeapNode minimumNode = minHeap.getMinimumNode();

                int minimumVertex = minimumNode.vertex;
                visited[minimumVertex] = true;                     //peydash kardim pass true mikonim meghdar visit boodanesh ro

                LinkedList<Node> nodes = neighbors[minimumVertex];

                for (Node node : nodes){
                    int destination = node.destination;

                    while (!visited[destination]){             //hanuz nadide bashim check mikonim niaz dasht update mikonim(decrease key)

                        int newKeyValue = heapNodes[minimumVertex].distance + node.weight;
                        int oldKeyValue = heapNodes[destination].distance;

                        if (oldKeyValue > newKeyValue){
                            decreaseKeyValue(minHeap, newKeyValue, destination);
                            heapNodes[destination].distance = newKeyValue;
                        }


                    }
                }

            }
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
