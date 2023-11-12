package Partie_2;

import java.util.Map;
import java.util.List;
import Partie_1.GraphImplementation;

public class GraphAdjacencyMatrix {
	public static void printAdjacencyMatrix(Map<Character, List<GraphImplementation.Edge>> adjacencyList) {
	    char maxVertex = 'A'; // Initialisation à la première lettre de l'alphabet
	    for (char vertex : adjacencyList.keySet()) {
	        if (vertex > maxVertex) {
	            maxVertex = vertex;
	        }
	    }

	    int matrixSize = maxVertex - 'A' + 1; // Taille de la matrice basée sur la lettre maximale
	    int[][] adjacencyMatrix = new int[matrixSize][matrixSize];

	    for (char vertex : adjacencyList.keySet()) {
	        int row = vertex - 'A';
	        for (GraphImplementation.Edge edge : adjacencyList.get(vertex)) {
	            int col = edge.getTo() - 'A';
	            adjacencyMatrix[row][col] = edge.getCost();
	        }
	    }

	    for (int i = 0; i < matrixSize; i++) {
	        for (int j = 0; j < matrixSize; j++) {
	            System.out.print(adjacencyMatrix[i][j] + " ");
	        }
	        System.out.println();
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphImplementation graph = new GraphImplementation();
        // Ajouter les sommets
        graph.addVertex('S');
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('G');
        // Ajouter les arêtes
        graph.addEdge('S', 'A', 1);
        graph.addEdge('A', 'B', 3);
        graph.addEdge('A', 'C', 1);
        graph.addEdge('B', 'D', 3);
        graph.addEdge('C', 'D', 1);
        graph.addEdge('C', 'G', 2);
        graph.addEdge('D', 'G', 3);
        printAdjacencyMatrix(graph.getAdjacencyList());
	}
}