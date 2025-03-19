package lab3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import lab3.airfield.Flight;

public class Solution {

  // sorted by beginTime && computed conflicts
  private final TreeSet<Flight> schedule;
  private final int countRunways;
  private int isAssigned;

  public Solution(Problem p) {
    schedule = p.getFlights();
    countRunways = p.getRunways();
    isAssigned = -1;
  }

  public int greedyFind() {

    if (-1 != isAssigned)
      return -1;

    HashMap<Flight, Integer> runways = new HashMap<Flight, Integer>();

    for (Flight it0 : schedule) {
      HashSet<Integer> colors = new HashSet<>();
      for (Flight neighbor : it0.getConflicts())
        if (runways.containsKey(neighbor))
          colors.add(runways.get(neighbor));

      for (Integer i = 0; i < schedule.size(); i++)
        if (false == colors.contains(i)) {
          runways.put(it0, i);
          break;
        }
    }

    return -1;
  }

  public void reset() {

    if (-1 == isAssigned)
      return;

    isAssigned = -1;
  }
}
