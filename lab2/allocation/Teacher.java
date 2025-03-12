package lab2.allocation;

/* proposals can be updated but not inserted */
public class Teacher extends Person {

  private Project[] projectProposals;

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

  public Project[] getProposals() {
    return projectProposals;
  }

  @Override
  public String toString() {
    StringBuilder build = new StringBuilder("teacher name: ");
    build.append(Integer.toString(name)).append("; proposals:");

    if (null == projectProposals || 0 == projectProposals.length)
      build.append(" nothing;");

    for (int i = 0; i < projectProposals.length; i++)
      build.append(" ").append(projectProposals[i].getId());
    build.append(";");
    return build.toString();
  }
}
