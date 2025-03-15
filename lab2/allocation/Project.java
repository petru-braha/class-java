package lab2.allocation;

/**
 * Represents a project that can be assigned to a student and a teacher.
 */
public class Project {

  private static int availableId;
  static {
    availableId = 0;
  }

  private final int name, id; // Unique identifier for the project
  private final ProjectType type; // Type of project (theoretical or practical)

  private Student mStudent; // The student who selected this project
  private Teacher mTeacher; // The teacher supervising this project

  /**
   * Constructs a Project with the given details.
   *
   * @param pType The type of the project.
   * @param pId   The unique identifier of the project.
   * @param pSt   The student associated with the project.
   * @param pTh   The teacher supervising the project.
   */
  public Project(final ProjectType pType, final int pName,
      final Student pSt, final Teacher pTh) {
    type = pType;
    name = pName;
    id = availableId++;

    mStudent = pSt;
    mTeacher = pTh;
  }

  /**
   * Sets the student for this project.
   * <p>
   * Default modifier: only accessible within the {@code allocation} package.
   * </p>
   *
   * @param pStudent The student to be assigned.
   */
  void setStudent(final Student pStudent) {
    mStudent = pStudent;
  }

  /**
   * Sets the teacher for this project.
   * <p>
   * Default modifier: only accessible within the {@code allocation} package.
   * </p>
   *
   * @param pTeacher The teacher to be assigned.
   */
  void setTeacher(final Teacher pTeacher) {
    mTeacher = pTeacher;
  }

  /**
   * @return The project name.
   */
  public int getName() {
    return name;
  }

  /**
   * Retrieves the unique identifier of the project.
   *
   * @return The project ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Retrieves the type of the project.
   *
   * @return The {@link ProjectType} of the project.
   */
  public ProjectType getType() {
    return type;
  }

  /**
   * Retrieves the student assigned to this project.
   *
   * @return The {@link Student} assigned to this project, or {@code null} if
   *         unassigned.
   */
  public Student getStudent() {
    return mStudent;
  }

  /**
   * Retrieves the teacher supervising this project.
   *
   * @return The {@link Teacher} supervising this project, or {@code null} if
   *         unassigned.
   */
  public Teacher getTeacher() {
    return mTeacher;
  }

  /**
   * Returns a string representation of the project, including ID, type,
   * and whether it has been selected by a student.
   *
   * @return A formatted string describing the project.
   */
  @Override
  public String toString() {

    StringBuilder build = new StringBuilder("project id: ");
    build.append(id).append("; type: ");
    if (type == ProjectType.theoretical)
      build.append("theoretical; ");
    else
      build.append("practical; ");

    if (null != mStudent)
      build.append("selected by the student: ")
          .append(mStudent.getId())
          .append(";");
    else
      build.append("was not selected;");
    return build.toString();
  }
}
