package Partie_1;

public class GraphAdjacencyMatrix {
    public GraphAdjacencyMatrix() {
        GraphImplementation graph = new GraphImplementation();
     // Ajouter les sommets
        graph.addVertex('S');
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('G');
        // Ajouter les arêtes
        graph.addEdge('S', 'A', 1);
        graph.addEdge('S', 'B', 2);
        graph.addEdge('A', 'C', 1);
        graph.addEdge('B', 'C', 1);
        graph.addEdge('C', 'G', 2);
        printAdjacencyMatrix(graph);
    }
    public static void main(String[] args) {
        new GraphAdjacencyMatrix();
    }
    public void printAdjacencyMatrix(GraphImplementation graph) {
        for (char vertex : graph.getAdjacencyList().keySet()) {
            System.out.print(vertex + ": ");
            for (char otherVertex : graph.getAdjacencyList().keySet()) {
                boolean hasEdge = false;
                for (GraphImplementation.Edge edge : graph.getEdges(vertex)) {
                    if (edge.getTo() == otherVertex) {
                        hasEdge = true;
                        break;
                    }
                }
                if (hasEdge) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
}