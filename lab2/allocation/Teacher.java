package lab2.allocation;

/**
 * Represents a teacher who can propose multiple projects.
 * <p>
 * Proposals can be updated but not inserted.
 * </p>
 */
public class Teacher extends Person {

  private Project[] projectProposals; // Array of projects proposed by the teacher

  /**
   * Constructs a Teacher instance with the given details.
   *
   * @param pName  The name of the teacher (as an integer identifier).
   * @param pBirth The birth year of the teacher.
   * @param pref   The list of project proposals.
   */
  public Teacher(final int pName, final int pBirth,
      final Project[] pref) {

    super(pName, pBirth);
    projectProposals = pref;
    if (null == pref)
      System.out.printf("warning: Teacher() - %s.\n",
          "received null");
    else
      for (int i = 0; i < pref.length; i++)
        pref[i].setTeacher(this);
  }

  /**
   * Assigns a new set of project proposals to the teacher,
   * replacing the existing ones if applicable.
   *
   * @param arrProposals The array of new project proposals.
   * @return {@code true} if the assignment was successful, {@code false}
   *         otherwise.
   */
  @Override
  public boolean equals(Object arrProposals) {

    if (!(arrProposals instanceof Project[])) {
      System.out.printf("error: Teacher equals failed - %s.\n",
          "wrong type of instance");
      return false;
    }

    Project[] temp = (Project[]) arrProposals;
    for (int i = 0; i < temp.length; i++)
      if (temp[i].getTeacher() != null) {
        System.out.printf("error: Teacher equals failed - %s.\n",
            "already proposed project");
        return false;
      }

    for (int i = 0; i < projectProposals.length; i++)
      if (null != projectProposals[i])
        projectProposals[i].setTeacher(null);
    projectProposals = temp;

    for (int i = 0; i < projectProposals.length; i++)
      projectProposals[i].setTeacher(this);
    return true;
  }

  /**
   * Retrieves the list of projects proposed by the teacher.
   *
   * @return An array of {@link Project} objects proposed by the teacher.
   */
  public Project[] getProposals() {
    return projectProposals;
  }

  /**
   * Returns a string representation of the teacher, including name
   * and the list of proposed projects.
   *
   * @return A formatted string describing the teacher.
   */
  @Override
  public String toString() {

    StringBuilder build = new StringBuilder("teacher name: ");
    build.append(name).append("; proposals:");

    if (null == projectProposals || 0 == projectProposals.length)
      return build.append("\n  nothing;").toString();

    for (int i = 0; i < projectProposals.length; i++)
      build.append("\n  ").append(projectProposals[i].toString());
    return build.toString();
  }
}
