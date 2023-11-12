package Partie_1;

import java.util.*;

public class GraphAStarSearch {

    public static void main(String[] args) {
        Map<Character, List<Edge>> graph = new HashMap<>();
        // Assuming a simple graph
        addEdge(graph, 'A', 'B', 3);
        addEdge(graph, 'A', 'C', 1);
        addEdge(graph, 'B', 'C', 1);
        addEdge(graph, 'B', 'D', 5);
        addEdge(graph, 'C', 'D', 2);

        try {
            aStarSearch(graph, 'A', 'D');
        } catch (IllegalArgumentException e) {
            System.out.println("Vertex not found in the graph");
        }
    }

    private static void aStarSearch(Map<Character, List<Edge>> graph, char start, char goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Character, Node> nodes = new HashMap<>();
        Node startNode = new Node(start, 0);
        nodes.put(start, startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.getVertex() == goal) {
                printPath(nodes, goal);
                return;
            }

            for (Edge edge : graph.get(currentNode.getVertex())) {
                Node neighborNode = nodes.get(edge.getTo());
                if (neighborNode == null) {
                    neighborNode = new Node(edge.getTo(), Integer.MAX_VALUE);
                    nodes.put(edge.getTo(), neighborNode);
                }

                int tentativeScore = currentNode.getScore() + edge.getCost();
                if (tentativeScore < neighborNode.getScore()) {
                    neighborNode.setScore(tentativeScore);
                    neighborNode.setPrevious(currentNode);
                    queue.add(neighborNode);
                }
            }
        }
    }

    private static void printPath(Map<Character, Node> nodes, char goal) {
        List<Character> path = new ArrayList<>();
        Node currentNode = nodes.get(goal);
        while (currentNode != null) {
            path.add(0, currentNode.getVertex());
            currentNode = currentNode.getPrevious();
        }
        System.out.println("Path: " + path);
    }

    private static void addEdge(Map<Character, List<Edge>> graph, char from, char to, int cost) {
        List<Edge> edges = graph.get(from);
        if (edges == null) {
            edges = new ArrayList<>();
            graph.put(from, edges);
        }
        edges.add(new Edge(to, cost));
    }

    static class Node implements Comparable<Node> {
        private final char vertex;
        private int score;
        private Node previous;

        public Node(char vertex, int score) {
            this.vertex = vertex;
            this.score = score;
        }

        public char getVertex() {
            return vertex;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(score, other.score);
        }
    }

    static class Edge {
        private final char to;
        private final int cost;

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
}