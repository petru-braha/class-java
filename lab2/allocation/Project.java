package lab2.allocation;

public class Project {

  private final ProjectType type;
  private final int id;
  private int mStudent;

  public Project(final ProjectType pType, final int pId) {
    type = pType;
    id = pId;
    mStudent = -1;
  }

  public void setStudent(final int id) {
    mStudent = id;
  }

  public int getId() {
    return id;
  }

  public ProjectType getType() {
    return type;
  }

  public int getStudent() {
    return mStudent;
  }

  @Override
  public String toString() {

    StringBuilder build = new StringBuilder("project name: ");
    build.append(Integer.toString(id)).append("; type: ");
    if (type == ProjectType.thoretical)
      build.append("thoretical; ");
    else
      build.append("practical; ");

    if (-1 != mStudent)
      build.append("selected by the student: ")
          .append(Integer.toString(mStudent))
          .append(";");
    else
      build.append("was not selected;");
    return build.toString();
  }
}
