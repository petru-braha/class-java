package lab3;

import lab3.aircrafts.*;
import lab3.airfield.*;
import java.util.TreeSet;

public class Homework {

  public static void main(String[] args) {

    Aircraft v0 = new Airliner(0, 10, 2, 300),
        v1 = new Freighter(0, 11, 500),
        v2 = new Drone(5, 1002, 16, 2);

    Airport airport = new Airport(2);
    TreeSet<Flight> schedule = new TreeSet<Flight>();
    schedule.add(new Flight(v0, 12, 16));
    schedule.add(new Flight(v1, 13, 16));
    schedule.add(new Flight(v2, 20, 23));

    Problem p0 = new Problem(airport, schedule);
    Solution s0 = new Solution(p0);
    //System.out.printf("result: %d.\n", s0.greedyFind());
  }
}