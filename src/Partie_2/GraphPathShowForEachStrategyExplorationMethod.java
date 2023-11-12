package Partie_2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPathShowForEachStrategyExplorationMethod {
	public static void showPath(List<Character> path, Color color) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        // Créez un composant graphique personnalisé pour afficher le chemin
        CustomPathComponent pathComponent = new CustomPathComponent(path, color);
        frame.add(pathComponent);
        frame.setVisible(true);
    }
	
	public static class CustomPathComponent extends JComponent {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<Character> path;
        private Color color;
        public CustomPathComponent(List<Character> path, Color color) {
            this.path = path;
            this.color = color;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            int x1, y1, x2, y2;
            // Dessinez des lignes entre les sommets du chemin
            for (int i = 0; i < path.size() - 1; i++) {
                // Calculez les coordonnées pour le sommet courant et le sommet suivant
                // Assurez-vous que vous avez des coordonnées appropriées pour chaque sommet
                x1 = i * 50;  // Utilisez des coordonnées réelles ou adaptez-les à votre représentation graphique
                y1 = 100;
                x2 = (i + 1) * 50;
                y2 = 100;
                g.drawLine(x1, y1, x2, y2);
            }
        }
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
        List<Character> pathBFS = GraphStrategyExploration.breadthFirstSearch(graph, start, goal);
        System.out.println("Chemin BFS : " + pathBFS);
        showPath(pathBFS, Color.RED);

        // b. Exploration en graphe à coût uniforme (UCS)
        List<Character> pathUCS = GraphStrategyExploration.uniformCostSearch(graph, start, goal);
        System.out.println("Chemin UCS : " + pathUCS);
        showPath(pathUCS, Color.BLUE);

        // c. Exploration en graphe en profondeur d'abord (DFS)
        List<Character> pathDFS = GraphStrategyExploration.depthFirstSearch(graph, start, goal);
        System.out.println("Chemin DFS : " + pathDFS);
        showPath(pathDFS, Color.GREEN);
        
        // d. Exploration en graphe A* avec une heuristique cohérente (consistante)
        List<Character> pathAStar = GraphStrategyExploration.aStarSearch(graph, start, goal, null);
        System.out.println("Chemin A* : " + pathAStar);
        showPath(pathAStar, Color.ORANGE);
    }
}