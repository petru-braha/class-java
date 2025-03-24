package laboratory;

public class PEdge {

  private final PVertex v0, v1;
  private final int cost, probability;

  public PEdge(final PVertex v0, final PVertex v1, final int cost) {
    this.v0 = v0;
    this.v1 = v1;
    this.cost = cost;

    int p = 0;
    if (Safety.Friendly == v0.getType()) {
      if (Safety.Friendly == v1.getType())
        p = 100;
      else if (Safety.Neutral == v1.getType())
        p = 75;
      else
        p = 50;
    }

    if (Safety.Neutral == v0.getType()) {
      if (Safety.Friendly == v1.getType())
        p = 75;
      else if (Safety.Neutral == v1.getType())
        p = 50;
      else
        p = 25;
    }

    if (Safety.Enemy == v0.getType()) {
      if (Safety.Friendly == v1.getType())
        p = 50;
      else if (Safety.Neutral == v1.getType())
        p = 25;
      else
        p = 10;
    }

    probability = p;
  }

  public PVertex getLeft() {
    return v0;
  }

  public PVertex getRight() {
    return v1;
  }

  public int getCost() {
    return cost;
  }

  public int getProbability() {
    return probability;
  }
}
