
package lab3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import lab3.airfield.Flight;

public class Solution {

  // sorted by beginTime && computed conflicts
  private final TreeSet<Flight> schedule;
  private final int countRunways;
  private boolean isAssigned;

  public Solution(Problem p) {
    schedule = p.getFlights();
    countRunways = p.getRunways();
    isAssigned = false;
  }

  public int greedyFind() {

    if (isAssigned)
      return -1;

    HashMap<Flight, Integer> assignation = new HashMap<>();
    Integer maximumRunway = -1;

    for (Flight it : schedule) {

      HashSet<Integer> usedColors = new HashSet<>();
      for (Flight neighbor : it.getConflicts()) {
        Integer color = assignation.get(neighbor);
        if (null != color)
          usedColors.add(color);
      }

      for (Integer i = 0; i < schedule.size(); i++)
        if (false == usedColors.contains(i)) {
          if(i > maximumRunway)
            maximumRunway = i;
          assignation.put(it, i);
          break;
        }
      
    }

    System.out.printf("%s\n", assignation.toString());

    isAssigned = true;
    if (maximumRunway >= countRunways)
      return -1;
    return maximumRunway;
  }

  public void reset() {

    if (false == isAssigned)
      return;

    isAssigned = false;
  }
}
