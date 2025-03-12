package lab3.aircrafts;

import lab3.aircrafts.capabilities.PasangerCapable;

public class Airliner extends Aircraft implements PasangerCapable {

  private final int wingSpan;
  private final int maxNumberPasager;

  public Airliner(final int model, final int name,
      final int wingS, final int nrP) {
    super(model, name);
    this.wingSpan = wingS;
    this.maxNumberPasager = nrP;
  }

  public int getWingSpan() {
    return wingSpan;
  }

  @Override
  public int getPassengerCapacity() {
    return maxNumberPasager;
  }
}
