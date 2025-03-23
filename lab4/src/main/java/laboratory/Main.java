package laboratory;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    Vertex v0 = new Vertex(Safety.Friendly),
        v1 = new Vertex(Safety.Neutral),
        v2 = new Vertex(Safety.Enemy),
        v3 = new Vertex(Safety.Friendly),
        v4 = new Vertex(Safety.Neutral),
        v5 = new Vertex(Safety.Enemy);

    Vertex[] arrayLocation = { v0, v1, v2, v3, v4, v5 };
    System.out.println(v1.toString());

    // todo defineste treeset
    Set<Vertex> friendlyLocation = List.of(arrayLocation)
        .stream()
        .filter(v -> Safety.Friendly == v.getType())
        .collect(Collectors.toSet());

    List<Vertex> enemyLocation = List.of(arrayLocation)
        .stream()
        .filter(v -> Safety.Enemy == v.getType())
        .sorted(Comparator.comparing(Vertex::getType)
            .thenComparing(Vertex::getId))
        .collect(Collectors.toCollection(LinkedList::new));

    Iterator<Vertex> it0 = friendlyLocation.iterator();
    while (it0.hasNext())
      System.out.printf("%s ", it0.next().toString());
    System.out.printf("\n");

    Iterator<Vertex> it1 = enemyLocation.iterator();
    while (it1.hasNext())
      System.out.printf("%s ", it1.next().toString());
  }
}