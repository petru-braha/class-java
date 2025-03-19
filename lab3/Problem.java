package lab3;

import java.util.TreeSet;
import lab3.airfield.*;

public class Problem {

  private final Airport airport;
  private final TreeSet<Flight> flights;

  private boolean isConflict(final Flight f0, final Flight f1) {
    if (f0.getBeginInterval() < f1.getEndInterval())
      return true;
    if (f1.getBeginInterval() < f0.getEndInterval())
      return true;
    return false;
  }

  public Problem(Airport a, TreeSet<Flight> f) {
    airport = a;
    flights = f;

    for (Flight it0 : flights)
      for (Flight it1 : flights.tailSet(it0, false))
        if (isConflict(it0, it1)) {
          it0.addConflict(it1);
          it1.addConflict(it0);
        }
  }

  public final int getRunways() {
    return airport.getRunways();
  }

  public final TreeSet<Flight> getFlights() {
    return flights;
  }

  @Override
  public String toString() {
    StringBuilder build  = new StringBuilder(airport.toString());
    build.append("\n").append(flights.toString());
    return build.toString();
  }
}
