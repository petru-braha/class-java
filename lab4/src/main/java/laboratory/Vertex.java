package laboratory;

public class Vertex implements Comparable<Vertex> {

  private static int availableId = 0;
  private final int id;
  private Safety type;

  public Vertex(final Safety t) {
    id = availableId++;
    type = t;
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
  public int compareTo(Vertex arg) {
    return Integer.compare(this.id, arg.id);
  }
}
