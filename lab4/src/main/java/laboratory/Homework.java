package laboratory;

import com.github.javafaker.Faker;

import org.graph4j.Edge;
import org.graph4j.EdgeIterator;
import org.graph4j.Graph;
import org.graph4j.generate.GraphGenerator;
import org.graph4j.traverse.BFSIterator;
import org.graph4j.traverse.SearchNode;

class PrinterGraph {

  // white == no edge
  public static final char COLOR_WHITE = 0x25A0;
  public static final char COLOR_BLACK = 0x25A1;

  public static void adjancyMatrix(final int[][] matrix) {

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (matrix[i][j] == 0)
          System.out.printf("%c ", COLOR_WHITE);
        else
          System.out.printf("%c ", COLOR_BLACK);
      }

      System.out.printf("\n");
    }
  }
}

/*
 * Faker to generate random fake names for locations.
 * fastest routes from the start location to all other locations:
 * Using Stream API, create a data structure that stores, for each type, the
 * locations of that type.
 * Display the fastest times computed above: first for the friendly locations,
 * then for the neutral and then for the enemy locations.
 */
public class Homework {

  private static final int VRX_COUNT = 10;
  private static final double EDGE_PROB = 1.0 / 2.0;

  private static final double MINIMUM_COST = 0.0;
  private static final double MAXIMUM_COST = 10.0;

  private static Faker fake = new Faker();
  private static Graph<PVertex, PEdge> graph;

  private static PVertex newRandomVertex() {

    Safety safe = Safety.Friendly;
    final int indexSafety = Generation.g(0, 3);

    if (indexSafety == 1)
      safe = Safety.Neutral;
    else if (indexSafety == 2)
      safe = Safety.Enemy;

    return new PVertex(safe, fake.name().lastName());
  }

  private static PEdge newRandomEdge(final PVertex u, final PVertex v) {
    return new PEdge(u, v, Generation.g(MINIMUM_COST, MAXIMUM_COST));
  }

  /*
   * receives the following optional parameters:
   * n = count of vertices
   * m = count of edges
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    if (args.length > 2) {
      System.out.printf("error: %s failed - %s.\n",
          "main()", "wrong number of arguments");
      return;
    }

    int n = VRX_COUNT;
    if (args.length > 0)
      n = Integer.parseInt(args[0]);

    if (args.length == 0)
      graph = GraphGenerator.randomGnp(n, EDGE_PROB);
    else if (args.length == 1)
      graph = GraphGenerator.randomGnp(
          Generation.g(n - n / 2, n + n / 2),
          EDGE_PROB);
    else
      graph = GraphGenerator.randomGnp(n, Float.parseFloat(args[1]));

    // add vertex labels
    var bfs = new BFSIterator(graph);
    while (bfs.hasNext()) {
      SearchNode node = bfs.next();
      int index = node.vertex();
      graph.setVertexLabel(index, newRandomVertex());
    }

    // add edge labels
    EdgeIterator<PEdge> itEdge = graph.edgeIterator();
    while (itEdge.hasNext()) {
      Edge<PEdge> edge = itEdge.next();
      int v0 = edge.source(), v1 = edge.target();
      PVertex p0 = graph.getVertexLabel(v0), p1 = graph.getVertexLabel(v1);
      itEdge.setLabel(newRandomEdge(p0, p1));
      itEdge.setWeight(itEdge.getLabel().getCost());
    }

    int[][] matrix = graph.adjacencyMatrix();
    PrinterGraph.adjancyMatrix(matrix);

    // give start location
    // data structure with time
    // graph.addVertex(new PVertex(Safety.Enemy, fake.name().lastName()));
    // System.out.println(fake.name().lastName());

    // data structure for location of each type
    // measure time
  }
}
