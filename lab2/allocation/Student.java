package lab2.allocation;

public class Student {

  private final int id;
  private int[] projectPreferences;
  private int countPreferences;
  private Project mProject;

  public Student(final int pId) {
    id = pId;
    projectPreferences = new int[10];
    countPreferences = 0;
    mProject = null;
  }

  public Student(final int pId, final int[] pProjectPreferences) {
    id = pId;
    projectPreferences = pProjectPreferences;
    countPreferences = pProjectPreferences.length;
    mProject = null;
  }

  public void setPreferences(final int[] pProjectPreferences) {
    projectPreferences = pProjectPreferences;
    countPreferences = projectPreferences.length;
  }

  public void addPreference(int preference) {

    if (countPreferences + 1 == projectPreferences.length) {
      int[] temp = projectPreferences;
      projectPreferences = new int[2 * countPreferences];
      for (int i = 0; i < countPreferences; i++)
        projectPreferences[i] = temp[i];
    }

    projectPreferences[countPreferences++] = preference;
  }

  public void select(final Project pProject) {

    if (-1 != pProject.getStudent()) {
      System.out.printf("error: project %d was already selected.\n",
          pProject.getId());
      return;
    }

    mProject = pProject;
    mProject.setStudent(id);
  }

  public int getId() {
    return id;
  }

  public int[] getPreferences() {
    return projectPreferences;
  }

  public int getCount() {
    return countPreferences;
  }

  public Project getProject() {
    return mProject;
  }

  @Override
  public String toString() {
    StringBuilder build = new StringBuilder("student name: ");
    build.append(Integer.toString(id)).append("; preferences:");
    for (int i = 0; i < countPreferences; i++)
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
