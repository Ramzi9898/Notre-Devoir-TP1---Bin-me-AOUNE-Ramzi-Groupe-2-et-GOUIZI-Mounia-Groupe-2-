package Partie_2;

import java.awt.Color;
import java.util.*;


public class GraphStrategyExploration {
    public static List<Character> breadthFirstSearch(GraphImplementation graph, char start, char goal) {
        Queue<List<Character>> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(Arrays.asList(start));

        while (!queue.isEmpty()) {
            List<Character> path = queue.poll();
            char current = path.get(path.size() - 1);
            if (current == goal) {
                return path;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                List<Character> neighbors = graph.getNeighbors(current);
                if (neighbors != null) {
                	for (char neighbor : neighbors) {
                		if (!visited.contains(neighbor)) {
                			List<Character> newPath = new ArrayList<>(path);
                			newPath.add(neighbor);
                			queue.add(newPath);
                		}
                	}
                }	
            }
        }
        return null;
    }
    public static List<Character> uniformCostSearch(GraphImplementation graph, char start, char goal) {
        PriorityQueue<List<Character>> priorityQueue = new PriorityQueue<>(
            Comparator.comparingInt(path -> getPathCost(path, graph))
        );
        Set<Character> visited = new HashSet<>();
        priorityQueue.add(Arrays.asList(start));

        while (!priorityQueue.isEmpty()) {
            List<Character> path = priorityQueue.poll();
            char current = path.get(path.size() - 1);
            if (current == goal) {
                return path;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                List<Character> neighbors = graph.getNeighbors(current);
                if (neighbors != null) {
                	for (char neighbor : neighbors) {
                		if (!visited.contains(neighbor)) {
                			List<Character> newPath = new ArrayList<>(path);
                			newPath.add(neighbor);
                			priorityQueue.add(newPath);
                		}
                	}
                }	
            }
        }
        return null;
    }
    public static List<Character> depthFirstSearch(GraphImplementation graph, char start, char goal) {
        Stack<List<Character>> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        stack.add(Arrays.asList(start));

        while (!stack.isEmpty()) {
            List<Character> path = stack.pop();
            char current = path.get(path.size() - 1);
            if (current == goal) {
                return path;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                List<Character> neighbors = graph.getNeighbors(current);
                if (neighbors != null) {
                	for (char neighbor : neighbors) {
                		if (!visited.contains(neighbor)) {
                			List<Character> newPath = new ArrayList<>(path);
                			newPath.add(neighbor);
                			stack.push(newPath);
                		}
                	}
                }	
            }
        }
        return null;
    }
    public static List<Character> aStarSearch(GraphImplementation graph, char start, char goal, MyHeuristic heuristic) {
        PriorityQueue<List<Character>> priorityQueue = new PriorityQueue<>(
        		Comparator.comparingInt(path -> getPathCost(path, graph) + heuristic.calculate(path.get(path.size() - 1), goal))
                );
        Set<Character> visited = new HashSet<>();
        priorityQueue.add(Arrays.asList(start));
        
        while (!priorityQueue.isEmpty()) {
            List<Character> path = priorityQueue.poll();
            char current = path.get(path.size() - 1);
            if (current == goal) {
                return path;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                List<Character> neighbors = graph.getNeighbors(current);
                if (neighbors != null) {
                	for (char neighbor : neighbors) {
                		if (!visited.contains(neighbor)) {
                			List<Character> newPath = new ArrayList<>(path);
                			newPath.add(neighbor);
                			priorityQueue.add(newPath);
                		}
                	}
                }	
            }
        }
        return null;
    }
    
    private static int getPathCost(List<Character> path, GraphImplementation graph) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph.getEdgeWeight(path.get(i), path.get(i + 1));
        }
        return cost;
    }
    
    public static void main(String[] args) {
    	GraphImplementation graph = new GraphImplementation();
        // Ajouter les sommets et les arêtes (comme précédemment)
        graph.addVertex('S');
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('G');
        graph.addEdge('S', 'A', 1);
        graph.addEdge('A', 'B', 3);
        graph.addEdge('A', 'C', 1);
        graph.addEdge('B', 'D', 3);
        graph.addEdge('C', 'D', 1);
        graph.addEdge('C', 'G', 2);
        graph.addEdge('D', 'G', 3);

        char start = 'S';
        char goal = 'G';

        // a. Exploration en graphe en largeur d'abord (BFS)
        List<Character> pathBFS = breadthFirstSearch(graph, start, goal);
        System.out.println("Chemin BFS : " + pathBFS);
        showPath(pathBFS, Color.RED);

        // b. Exploration en graphe à coût uniforme (UCS)
        List<Character> pathUCS = uniformCostSearch(graph, start, goal);
        System.out.println("Chemin UCS : " + pathUCS);
        showPath(pathUCS, Color.BLUE);

        // c. Exploration en graphe en profondeur d'abord (DFS)
        List<Character> pathDFS = depthFirstSearch(graph, start, goal);
        System.out.println("Chemin DFS : " + pathDFS);
        showPath(pathDFS, Color.GREEN);
        
        // d. Exploration en graphe A* avec une heuristique cohérente (consistante)
        MyHeuristic heuristic = new MyHeuristic();
        List<Character> pathAStar = aStarSearch(graph, start, goal, heuristic);
        System.out.println("Chemin A* : " + pathAStar);
        showPath(pathAStar, Color.ORANGE);
    }
	private static void showPath(List<Character> pathBFS, Color red) {
		// TODO Auto-generated method stub	
	}
}