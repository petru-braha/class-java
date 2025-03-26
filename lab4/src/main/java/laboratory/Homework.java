package laboratory;

import java.util.ArrayList;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

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

  @SuppressWarnings("unchecked")
  private static List<PVertex> staticGeneration(String[] args) {

    List<PVertex> allVertices = new ArrayList<>();

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
      PVertex label = newRandomVertex();
      graph.setVertexLabel(index, label);
      allVertices.add(label);
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

    return allVertices;
  }

  public static double[] dijkstraAlgorithm(final int s) {

    if (null == graph) {
      System.out.printf("error: %s failed - %s.\n",
          "dijkstraAlgorithm()", "invalid starting vertex");
      return null;
    }

    final int n = graph.numVertices();
    if (s >= n) {
      System.out.printf("error: %s failed - %s.\n",
          "dijkstraAlgorithm()", "invalid starting vertex");
      return null;
    }

    Set<Integer> VrtxHash = new HashSet<>(); // S
    Set<Integer> VrtxShut = new HashSet<>(); // V / S
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
      double minimum = Double.POSITIVE_INFINITY;

      Iterator<Integer> it = VrtxShut.iterator();
      while (it.hasNext()) {
        int point = it.next();

        if (-1 != point && costPath[point] < minimum) {
          vertex = point;
          minimum = costPath[v];
        }
      }

      // something went wrong
      if (-1 == vertex) {
        System.out.printf("warning: %s failed - %s %d.\n",
            "dijkstraAlgorithm", "no vertex found", v);
        return costPath;
      }
      if (false == VrtxShut.remove(((Integer) vertex))) {
        System.out.printf("warning: %s failed - %s.\n",
            "dijkstraAlgorithm", "no remove");
        return costPath;
      }
      if (false == VrtxHash.add((Integer) vertex)) {
        System.out.printf("warning: %s failed - %s.\n",
            "dijkstraAlgorithm", "no insert");
        return costPath;
      }

      it = VrtxShut.iterator();
      while (it.hasNext()) {
        int point = it.next();

        double value = costPath[vertex] +
            graph.getEdgeWeight(vertex, point);

        if (costPath[point] > value) {
          costPath[point] = value;
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
  public static void main(String[] args) {

    if (args.length > 2) {
      System.out.printf("error: %s failed - %s.\n",
          "main()", "wrong number of arguments");
      return;
    }

    List<PVertex> allVertices = staticGeneration(args);
    int[][] matrix = graph.adjacencyMatrix();
    PrinterGraph.adjacencyMatrix(matrix);

    final int start = Generation.g(0, graph.numVertices());
    System.out.printf("the starting vertex is %d.\n\n", start);
    double[] shortestPaths = dijkstraAlgorithm(start);

    for (int i = 0; i < graph.numVertices(); i++)
      if (start != i)
        System.out.printf("path: %d-%d, cost: %f;\n",
            start, i, shortestPaths[i]);

    // data structure, stream api, measure time
    Map<Safety, List<PVertex>> map = allVertices
        .stream()
        .collect(Collectors.groupingBy(PVertex::getType));

    System.out.println(map);
  }
}
