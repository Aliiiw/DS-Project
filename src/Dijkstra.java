import java.util.LinkedList;
import java.util.Scanner;

public class Dijkstra {
    public static Scanner input = new Scanner(System.in);

    static class MinHeap{
        int allCapacity;
        int size;
        HeapNode[] minHeaps;
        int [] indexes;


        public MinHeap(int capacity) {
            this.allCapacity = capacity;
            minHeaps = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            minHeaps[0] = new HeapNode();                      //initial kardan minHeap
            minHeaps[0].distance = Integer.MIN_VALUE;
            minHeaps[0].vertex = -1;
            size = 0;
        }

        public void insertToMinHeap(HeapNode node) {
            size++;
            int index = size;
            minHeaps[index] = node;
            indexes[node.vertex] = index;
            maxHeapify(index);
        }

        public void maxHeapify(int position) {
            int parentIndex = position / 2;
            int currentIndex = position;

            while (currentIndex > 0 && minHeaps[parentIndex].distance > minHeaps[currentIndex].distance) {   //jaye pedar va farzand avaz mishe
                HeapNode currentNode = minHeaps[currentIndex];
                HeapNode parentNode = minHeaps[parentIndex];


                indexes[currentNode.vertex] = parentIndex;
                indexes[parentNode.vertex] = currentIndex;
                swapNodes(currentIndex, parentIndex);
                currentIndex = parentIndex;
                parentIndex =  parentIndex / 2;
            }
        }

        public HeapNode getMinimumNode() {
            HeapNode minimumNode = minHeaps[1];
            HeapNode lastNodeHeap = minHeaps[size];

            indexes[lastNodeHeap.vertex] = 1;                        //node akhar ro miarim balaye hame
            minHeaps[1] = lastNodeHeap;
            minHeaps[size] = null;
            downHeapify(1);
            size--;
            return minimumNode;
        }

        public void downHeapify(int value) {
            int smallestValue = value;

            int rightChildIndex = 2 * value + 1;
            int leftChildIndex = 2 * value;
            if (leftChildIndex < size && minHeaps[smallestValue].distance > minHeaps[leftChildIndex].distance) {
                smallestValue = leftChildIndex;
            }
            if (rightChildIndex < size && minHeaps[smallestValue].distance > minHeaps[rightChildIndex].distance) {
                smallestValue = rightChildIndex;
            }
            if (smallestValue != value) {

                HeapNode smallestNodeInHeap = minHeaps[smallestValue];
                HeapNode nodeValue = minHeaps[value];

                indexes[smallestNodeInHeap.vertex] = value;
                indexes[nodeValue.vertex] = smallestValue;
                swapNodes(value, smallestValue);
                downHeapify(smallestValue);
            }
        }

        public void swapNodes(int node1, int node2) {
            HeapNode temp = minHeaps[node1];
            minHeaps[node1] = minHeaps[node2];
            minHeaps[node2] = temp;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    static class Map {
        int vertices;
        LinkedList<Node>[] neighbors;

        Map(int vertices) {
            this.vertices = vertices;
            neighbors = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                neighbors[i] = new LinkedList<>();
            }
        }

        public void addNodeToMap(int source, int destination, int weight) {     //be aval linked list ezaf mishe
            Node node = new Node(source, destination, weight);
            neighbors[source].addFirst(node);
        }

        public void findMinimumDistance(int source){
            int maxValue = Integer.MAX_VALUE;
            boolean[] visited = new boolean[vertices];
            HeapNode [] heapNodes = new HeapNode[vertices];         //heap node dorost mikonim ta ras haro negah darim

            for (int i = 0; i < vertices; i++) {                   //meghdar dehi avalie hame bi nahayat bashe
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].distance = maxValue;
            }


            heapNodes[source].distance = 0;                       //ras source faselash ba khodesh sefre

            MinHeap minHeap = new MinHeap(vertices);             //hame ras haro mirizim tooye min heap

            for (int i = 0; i < vertices; i++) {
                minHeap.insertToMinHeap(heapNodes[i]);
            }

            while(!minHeap.isEmpty()){                          //minimum ro dar miarim inja

                HeapNode extractedNode = minHeap.getMinimumNode();

                int extractedVertex = extractedNode.vertex;
                visited[extractedVertex] = true;                     //peydash kardim pass true mikonim meghdar visit boodanesh ro


                LinkedList<Node> nodes = neighbors[extractedVertex];

                for (Node node : nodes) {
                    int destination = node.destination;

                    if (!visited[destination]) {                      //hanuz nadide bashim check mikonim niaz dasht update mikonim(decrease key)
                        int newKeyValue = heapNodes[extractedVertex].distance + node.weight;
                        int currentKey = heapNodes[destination].distance;

                        if (currentKey > newKeyValue) {
                            decreaseKey(minHeap, newKeyValue, destination);
                            heapNodes[destination].distance = newKeyValue;
                        }
                    }
                }
            }

            showMap(heapNodes, source);
        }

        public void decreaseKey(MinHeap minHeap, int newKeyValue, int vertex){


            int index = minHeap.indexes[vertex];                          //jayi ke bayad update beshe ro indexesh ro dar miarim

            HeapNode node = minHeap.minHeaps[index];
            node.distance = newKeyValue;
            minHeap.maxHeapify(index);
        }

        public void showMap(HeapNode[] heapNodes, int source){
            System.out.println("\n");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Start from " + source + " to vertex " + i + " Minimum Distance " + heapNodes[i].distance);
            }
        }
    }

    static class HeapNode{
        int vertex;
        int distance;
    }

    static class Node {
        int source;
        int destination;
        int weight;

        public Node(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        System.out.print("Please enter the number of Vertices : ");
        int vertices = input.nextInt();

        System.out.print("Please enter the number of Lines : ");
        int lines = input.nextInt();

        System.out.print("Please enter the source Vertex: ");
        int source = input.nextInt();
        input.nextLine();

        Map map = new Map(vertices);
        int counter = 1;
        for (int i = 0; i < lines; i++){
            System.out.print("Please enter data Number " + counter + " in form (sorce destination weight) : ");
            String data = input.nextLine();

            String[] splited = data.split(" ");
            int _source = Integer.parseInt(splited[0]);
            int _destination = Integer.parseInt(splited[1]);
            int _weight = Integer.parseInt(splited[2]);
            map.addNodeToMap(_source, _destination, _weight);
            counter++;
        }

        map.findMinimumDistance(source);
    }
}