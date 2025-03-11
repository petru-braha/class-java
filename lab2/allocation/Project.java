package lab2.allocation;

public class Project {

  private final int id;
  private final ProjectType type;

  private Student mStudent;
  private Teacher mTeacher;

  public Project(final ProjectType pType, final int pId,
      final Student pSt, final Teacher pTh) {
    type = pType;
    id = pId;
    mStudent = pSt;
    mTeacher = pTh;
  }

  // default modifier - only allocation package can call this
  void setStudent(final Student pStudent) {
    mStudent = pStudent;
  }

  // default modifier - only allocation package can call this
  void setTeacher(final Teacher pTeacher){
    mTeacher = pTeacher;
  }

  public int getId() {
    return id;
  }

  public ProjectType getType() {
    return type;
  }

  public Student getStudent() {
    return mStudent;
  }

  public Teacher getTeacher() {
    return mTeacher;
  }

  @Override
  public String toString() {

    StringBuilder build = new StringBuilder("project name: ");
    build.append(Integer.toString(id)).append("; type: ");
    if (type == ProjectType.thoretical)
      build.append("thoretical; ");
    else
      build.append("practical; ");

    if (null != mStudent)
      build.append("selected by the student: ")
          .append(Integer.toString(mStudent.getId()))
          .append(";");
    else
      build.append("was not selected;");
    return build.toString();
  }
}
