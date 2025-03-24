package laboratory;

public class PVertex implements Comparable<PVertex> {

  private static int availableId = 0;
  private final int id;
  private Safety type;
  private String name;

  public PVertex(final Safety t) {
    id = availableId++;
    type = t;
    name = null;
  }

  public PVertex(final Safety t, final String n) {
    id = availableId++;
    type = t;
    name = n;
  }

  public void setType(final Safety t) {
    type = t;
  }

  public int getId() {
    return id;
  }

  public Safety getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    StringBuilder build = new StringBuilder("vertex: ");
    build.append(id)
        .append(" - ")
        .append(type)
        .append(";");
    return build.toString();
  }

  @Override
  public int compareTo(PVertex arg) {
    return Integer.compare(this.id, arg.id);
  }
}
