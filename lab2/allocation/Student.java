package lab2.allocation;

/* preferences can be updated but not inserted */
public class Student extends Person {

  private int[] projectPreferences;
  private Project mProject;

  static private int availableId = 1000;
  private int registrationNumber;

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

  public void setPreferences(final int[] pref) {
    if (null != pref)
      projectPreferences = pref;
    else
      System.out.printf("error: Student() received null.\n");
  }

  @Override
  public boolean equals(Object pProject) {

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

  public int[] getPreferences() {
    return projectPreferences;
  }

  public Project getProject() {
    return mProject;
  }

  public int getId() {
    return registrationNumber;
  }

  @Override
  public String toString() {

    StringBuilder build = new StringBuilder("student id: ");
    build.append(Integer.toString(registrationNumber)).append("; preferences:");
    for (int i = 0; i < projectPreferences.length; i++)
      build.append(" ").append(Integer.toString(projectPreferences[i]));
    build.append("; ");
    if (null != mProject)
      build.append("selected the project: ")
          .append(Integer.toString(mProject.getId()))
          .append(";");
    else
      build.append("did not select a project;");
    return build.toString();
  }
}
