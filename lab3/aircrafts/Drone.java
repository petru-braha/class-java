package lab3.aircrafts;

import lab3.aircrafts.capabilities.CargoCapable;

public class Drone extends Aircraft implements CargoCapable {

  private final int wingSpan;
  private final int maxPayload;

  public Drone(final int model, final int name,
      final int wingS, final int maxP) {
    super(model, name);
    this.wingSpan = wingS;
    this.maxPayload = maxP;
  }

  public int getWingSpan() {
    return wingSpan;
  }

  @Override
  public int getMaxPayload() {
    return maxPayload;
  }
}
