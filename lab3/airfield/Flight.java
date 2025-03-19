package lab3.airfield;

import java.util.HashSet;

import lab3.aircrafts.*;

public class Flight implements Comparable<Flight> {

  private static int availableId = 0;
  private Aircraft vehicle;

  private final int id;
  private final int beginTime, endTime;

  private HashSet<Flight> neighbors;

  public Flight(Aircraft v, int b, int e) {
    vehicle = v;
    id = availableId++;
    beginTime = b;
    endTime = e;

    neighbors = new HashSet<Flight>();
  }

  public boolean addConflict(final Flight f) {
    return neighbors.add(f);
  }

  @Override
  public int compareTo(Flight other) {
    return Integer.compare(this.beginTime, other.beginTime);
  }

  public Aircraft getAircraft() {
    return vehicle;
  }

  public int getId() {
    return id;
  }

  public int getBeginInterval() {
    return beginTime;
  }

  public int getEndInterval() {
    return endTime;
  }

  public HashSet<Flight> getConflicts() {
    return neighbors;
  }

  @Override
  public String toString() {
    StringBuilder build = new StringBuilder("the flight ");
    build.append(id)
        .append(" - ")
        .append(vehicle.toString())
        .append(", ")
        .append("departs at ")
        .append(beginTime)
        .append(", ")
        .append("arrives at ")
        .append(endTime);
    return build.append(";").toString();
  }
}
