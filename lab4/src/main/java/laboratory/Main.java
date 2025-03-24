package laboratory;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    PVertex v0 = new PVertex(Safety.Friendly),
        v1 = new PVertex(Safety.Neutral),
        v2 = new PVertex(Safety.Enemy),
        v3 = new PVertex(Safety.Friendly),
        v4 = new PVertex(Safety.Neutral),
        v5 = new PVertex(Safety.Enemy);

    PVertex[] arrayLocation = { v0, v1, v2, v3, v4, v5 };
    System.out.println(v1.toString());

    Set<PVertex> friendlyLocation = List.of(arrayLocation)
        .stream()
        .filter(v -> Safety.Friendly == v.getType())
        .collect(Collectors.toCollection(TreeSet::new));

    List<PVertex> enemyLocation = List.of(arrayLocation)
        .stream()
        .filter(v -> Safety.Enemy == v.getType())
        .sorted(Comparator.comparing(PVertex::getType)
            .thenComparing(PVertex::getId))
        .collect(Collectors.toCollection(LinkedList::new));

    Iterator<PVertex> it0 = friendlyLocation.iterator();
    while (it0.hasNext())
      System.out.printf("%s ", it0.next().toString());
    System.out.printf("\n");

    Iterator<PVertex> it1 = enemyLocation.iterator();
    while (it1.hasNext())
      System.out.printf("%s ", it1.next().toString());
    System.out.printf("\n");

  }
}