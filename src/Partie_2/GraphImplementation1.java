package Partie_2;

import java.util.*;

public class GraphImplementation {
	protected Map<Character, List<Edge>> adjacencyList;
    public GraphImplementation() {
        adjacencyList = new HashMap<>();
    }
    public void addVertex(char vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }
    public void addEdge(char from, char to, int cost) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }
        adjacencyList.get(from).add(new Edge(to, cost));
    }
    public List<Edge> getEdges(char vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }
        return adjacencyList.get(vertex);
    }
    public Map<Character, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
    public int getHeuristic(char vertex) {
        // Ajoutez ici la logique pour obtenir la valeur heuristique du sommet
        return 0;
    }
    public static class Edge {
        private char to;
        private int cost;
        public Edge(char to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        public char getTo() {
            return to;
        }
        public int getCost() {
            return cost;
        }
    }
    public List<Character> getNeighbors(char current) {
    	if (!adjacencyList.containsKey(current)) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }
        List<Edge> edges = adjacencyList.get(current);
        List<Character> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            neighbors.add(edge.getTo());
        }
        return neighbors;
	}
    public int getEdgeWeight(Character character, Character character2) {
    	if (!adjacencyList.containsKey(character) || !adjacencyList.containsKey(character2)) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }
        List<Edge> edges = adjacencyList.get(character);
        for (Edge edge : edges) {
            if (edge.getTo() == character2) {
                return edge.getCost();
            }
        }
        return 0; // or throw an exception if the edge is not found
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
	}	
}