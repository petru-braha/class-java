package lab2.allocation;

/**
 * Represents a student who can select a project based on preferences.
 * Preferences can be updated but not inserted.
 */
public class Student extends Person {

  private int[] projectPreferences; // Array of project preferences
  private Project mProject; // The project assigned to the student

  static private int availableId = 1000; // Static ID counter for students
  private int registrationNumber; // Unique registration number for the student

  /**
   * Constructs a Student instance with the given details.
   *
   * @param pName  The name of the student (as an integer identifier).
   * @param pBirth The birth year of the student.
   * @param pref   The list of project preferences.
   * @param proj   The assigned project, if any.
   */
  public Student(final int pName, final int pBirth,
      final int[] pref, final Project proj) {

    super(pName, pBirth);
    projectPreferences = pref;
    mProject = proj;
    registrationNumber = availableId;
    availableId++;

    if (null == projectPreferences)
      System.out.println("warning: Student() received null.");
    if (null != mProject)
      mProject.setStudent(this);
  }

  /**
   * Updates the project preferences for the student.
   *
   * @param pref The new set of project preferences.
   */
  public void setPreferences(final int[] pref) {
    if (null != pref)
      projectPreferences = pref;
    else
      System.out.printf("error: Student() received null.\n");
  }

  /**
   * Assigns a project to the student if it is available.
   * Also removes the student from the previous project if reassigned.
   *
   * @param pProject The project to be assigned.
   * @return {@code true} if the assignment was successful, {@code false}
   *         otherwise.
   */
  @Override
  public boolean equals(Object pProject) {

    if (null == pProject) {
      if (null != mProject)
        mProject.setStudent(null);
      mProject = null;
      return true;
    }

    if (!(pProject instanceof Project)) {
      System.out.printf("error: Student equals failed - %s.\n",
          "wrong type of instance");
      return false;
    }

    Project temp = (Project) pProject;
    if (temp.getStudent() != null) {
      System.out.printf("error: Student equals failed - %s.\n",
          "already assigned project");
      return false;
    }

    if (null != mProject)
      mProject.setStudent(null);
    mProject = temp;
    mProject.setStudent(this);
    return true;
  }

  /**
   * Retrieves the project preferences of the student.
   *
   * @return An array of preferred project IDs.
   */
  public int[] getPreferences() {
    return projectPreferences;
  }

  /**
   * Gets the currently assigned project.
   *
   * @return The assigned {@link Project}, or {@code null} if no project is
   *         assigned.
   */
  public Project getProject() {
    return mProject;
  }

  /**
   * Gets the student's unique registration number.
   *
   * @return The student's registration number.
   */
  public int getId() {
    return registrationNumber;
  }

  /**
   * Returns a string representation of the student, including
   * ID, preferences, and assigned project (if any).
   *
   * @return A string describing the student's details.
   */
  @Override
  public String toString() {

    StringBuilder build = new StringBuilder("student id: ");
    build.append(registrationNumber).append("; preferences:\n ");

    if (null == projectPreferences || 0 == projectPreferences.length)
      return build.append(" nothing;").toString();

    for (int i = 0; i < projectPreferences.length; i++)
      build.append(" ").append(projectPreferences[i]);
    build.append("; ");

    if (null == mProject)
      return build.append("did not select a project;").toString();

    return build.append("selected the project: ")
        .append(mProject.getId())
        .append(";")
        .toString();
  }

  private Student(final int pName, final int pBirth,
      final int[] pref, final Project proj, boolean t) {

    super(pName, pBirth);
    projectPreferences = pref;
    mProject = proj;
    registrationNumber = availableId;
    // availableId++; commented, should not happen
  }

  public Student clone() {
    return new Student(name, birthDate, projectPreferences, mProject, true);
  }
}
