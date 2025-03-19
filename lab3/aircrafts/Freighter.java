package lab3.aircrafts;

import lab3.aircrafts.capabilities.CargoCapable;

public class Freighter extends Aircraft implements CargoCapable {

  private final int maxPayload;

  public Freighter(final int model, final int name,
      final int maxP) {
    super(model, name);
    this.maxPayload = maxP;
  }

  public int getMaxPayload() {
    return maxPayload;
  }

  @Override
  public String toString() {
    StringBuilder build = new StringBuilder("freighter-");
    build.append(model)
        .append("-")
        .append(name)
        .append("-")
        .append(maxPayload);
    return build.toString();
  }
}
