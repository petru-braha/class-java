package lab3.aircrafts;

public abstract class Aircraft {

  protected final int model;
  protected final int name;

  protected Aircraft(final int model, final int name) {
    this.model = model;
    this.name = name;
  }

  public int getModel() {
    return model;
  }

  public int getName() {
    return name;
  }

  @Override
  public String toString(){
    return Integer.toString(name);
  }
}
