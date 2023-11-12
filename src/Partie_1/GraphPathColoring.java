package Partie_1;

import java.util.*;

class Node {
    public int name;
    public List<Edge> edges = new ArrayList<>();

    public Node(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + name;
    }
}

class Edge {
    public Node target;
    public int cost;

    public Edge(Node target, int cost) {
        this.target = target;
        this.cost = cost;
    }
}

class Graph {
    public List<Node> nodes = new ArrayList<>();

    public Node getNodeByName(int name) {
        for (Node node : nodes) {
            if (node.name == name) {
                return node;
            }
        }
        return null;
    }

    public void addEdge(Node source, Node target, int cost) {
        source.edges.add(new Edge(target, cost));
        nodes.add(source);
        nodes.add(target);
    }

    public void pathColoring() {
        Node source = getNodeByName(0); // Start node
        Node destination = getNodeByName(4); // End node

        List<Node> path = new ArrayList<>();
        boolean[] visited = new boolean[nodes.size()];
        pathColoringHelper(source, destination, visited, path);

        // Once you have the path determined, you can print the path with colors as follows:
        for (Node node : path) {
            if (node.name == source.name || node.name == destination.name) {
                System.out.print("red ");
            } else {
                System.out.print("black ");
            }
            System.out.print(node.toString() + " ");
        }
    }

    private void pathColoringHelper(Node source, Node destination, boolean[] visited, List<Node> path) {
        visited[source.name] = true;
        path.add(source);

        if (source == destination) {
            return;
        }

        for (Edge edge : source.edges) {
            if (!visited[edge.target.name]) {
                pathColoringHelper(edge.target, destination, visited, path);
                if (path.size() == nodes.size()) {
                    return;
                }
            }
        }

        path.remove(path.size() - 1);
        visited[source.name] = false;
    }
}

public class GraphPathColoring {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node S = new Node(0);
        Node A = new Node(1);
        Node B = new Node(2);
        Node C = new Node(3);
        Node D = new Node(4);

        graph.addEdge(S, A, 1);
        graph.addEdge(S, B, 1);
        graph.addEdge(A, B, 1);
        graph.addEdge(A, C, 1);
        graph.addEdge(B, C, 1);
        graph.addEdge(B, D, 1);
        graph.addEdge(C, D, 1);

        graph.pathColoring();
    }
}