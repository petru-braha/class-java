package laboratory;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

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

  public static void adjacencyMatrix(final int[][] matrix) {

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

  @SuppressWarnings("unchecked")
  public static void costArray(final Graph<PVertex, PEdge> graph) {

    EdgeIterator<PEdge> itEdge = graph.edgeIterator();
    while (itEdge.hasNext()) {
      Edge<PEdge> edge = itEdge.next();
      System.out.printf("%d-%d, cost: %f; ", edge.source(), edge.target(),
          edge.weight());
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

  private static final double MINIMUM_COST = -10.0;
  private static final double MAXIMUM_COST = 20.0;

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

  public static double[] dijkstraAlgorithm(
      final Graph<PVertex, PEdge> graph, final int n, final int s) {

    if (null == graph) {
      System.out.printf("error: %s failed - %s.\n",
          "dijkstraAlgorithm()", "invalid starting vertex");
      return null;
    }

    if (s >= n) {
      System.out.printf("error: %s failed - %s.\n",
          "dijkstraAlgorithm()", "invalid starting vertex");
      return null;
    }

    Set<Integer> VrtxHash = new HashSet<>();
    Set<Integer> VrtxShut = new HashSet<>();
    int[] prevVrtx = new int[n];
    double[] costPath = new double[n];

    VrtxHash.add(s);
    for (int v = 0; v < n; v++) {
      if (v != s)
        VrtxShut.add(v);
      prevVrtx[v] = s;
      costPath[v] = graph.getEdgeWeight(s, v);
    }

    for (int v = 1; v < n; v++) {

      int vertex = -1;
      for (Iterator<Integer> it = VrtxShut.iterator(); it
          .hasNext(); vertex = it.next()) {
        // todo treeset
      }

      // todo warning
      if (-1 == vertex)
        break;
      if (false == VrtxShut.remove(vertex))
        break;
      if (false == VrtxHash.add(vertex))
        break;

      int point = 0;
      for (Iterator<Integer> it = VrtxShut.iterator(); it
          .hasNext(); vertex = it.next()) {

        if (costPath[point] > costPath[vertex] +
            graph.getEdgeWeight(vertex, point)) {

          costPath[point] = costPath[vertex] + graph.getEdgeWeight(vertex, point);
          prevVrtx[point] = vertex;
        }
      }
    }

    return costPath;
  }

  /*
   * receives the following optional parameters:
   * n = count of vertices
   * m = count of edges
   * starting vertex - randomly selected
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
      graph = GraphGenerator.randomGnp(n, Double.parseDouble(args[1]));

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
    PrinterGraph.adjacencyMatrix(matrix);

    int start = Generation.g(0, n);
    System.out.printf("the starting vertex is %d.\n", start);
    double[] shortestPaths = new double[n];// dijkstraAlgorithm(graph, n, start);

    // todo
    for (int i = 0; i < n; i++)
      System.out.printf("path: %d-%d, cost: %f;\n",
          start, i, shortestPaths[i]);

    // data structure with time

    // data structure for location of each type
    // measure time
  }
}
