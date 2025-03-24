package laboratory;

import com.github.javafaker.Faker;
import org.graph4j.Graph;
import org.graph4j.generate.GraphGenerator;

/*
 * Faker to generate random fake names for locations.
 * fastest routes from the start location to all other locations:
 * Using Stream API, create a data structure that stores, for each type, the locations of that type.
 * Display the fastest times computed above: first for the friendly locations, then for the neutral and then for the enemy locations.
 */
public class Homework {

  private static final int vrxCount = 10;
  private static final float edgeProb = 1 / 2;

  private static Faker fake = new Faker();
  private static Graph<PVertex, PEdge> graph;

  /*
   * receives the following optional parameters:
   * n = count of vertices
   * m = count of edges
   */
  public static void main(String[] args) {

    if (args.length > 2) {
      System.out.printf("error: %s failed - %s.\n",
          "main()", "wrong number of arguments");
      return;
    }

    int n = Integer.parseInt(args[0]);
    if (args.length == 0)
      graph = GraphGenerator.randomGnp(
          Generation.g(vrxCount - vrxCount / 2,
              vrxCount + vrxCount / 2),
          edgeProb);
    else if (args.length == 1)
      graph = GraphGenerator.randomGnp(n, edgeProb);
    else
      graph = GraphGenerator.randomGnp(n, Integer.parseInt(args[1]));

    
    // print info
    // give start location
    // data structure with time
    graph.addVertex(new PVertex(Safety.Enemy, fake.name().lastName()));
    System.out.println(fake.name().lastName());

    // data structure for location of each type
    // measure time
  }
}
