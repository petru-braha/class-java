package lab3;

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

    int[] runways = new int[countRunways];

    for (Flight it0 : schedule) {
      HashSet colors = new HashSet<>();
      for(Flight neighbor : it0.getConflicts())
        if
    }

    return -1;
  }

  public void reset() {

    if (-1 == isAssigned)
      return;

    isAssigned = -1;
  }
}
