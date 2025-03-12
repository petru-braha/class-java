package lab3.aircrafts;

import lab3.aircrafts.capabilities.CargoCapable;

public class Freighter extends Aircraft implements CargoCapable {

  private final int maxPayload;

  public Freighter(final int model, final int name,
      final int maxP) {
    super(model, name);
    this.maxPayload = maxP;
  }

  @Override
  public int getMaxPayload() {
    return maxPayload;
  }
}
