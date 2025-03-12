package lab2;

import java.util.Arrays;
import java.util.Comparator;
import lab2.allocation.*;

class Solution {

  class PreferenceCountSorting implements Comparator<Student> {

    public int compare(Student x, Student y) {
      return x.getPreferences().length -
          y.getPreferences().length;
    }
  }

  private Student[] arrayStudents;
  private Teacher[] arrayTeachers;
  private final int countProject;

  public Solution(Problem problem) {

    arrayStudents = problem.getStudents().clone();
    Arrays.sort(arrayStudents, new PreferenceCountSorting());

    arrayTeachers = problem.getTeachers();

    int sum = 0;
    for (int i = 0; i < arrayTeachers.length; i++)
      sum += arrayTeachers[i].getProposals().length;
    countProject = sum;
  }

  private void greedySearch(final int projectName, Project project) {

    // optimizes the search after passing by projectName
    boolean optimize = false;
    for (int t = 0; t < arrayTeachers.length; t++) {

      Project[] projects = arrayTeachers[t].getProposals();
      for (int p = 0; p < projects.length; p++) {

        if (projectName == projects[p].getId()) {
          if (null == projects[p].getStudent())
            project = projects[p];
          if (null != project)
            return;
          optimize = true;
        }

        if (null != projects[p].getStudent())
          continue;

        project = projects[p];
        if (true == optimize)
          return;
      }
    }
  }

  private Project search(final int projectName) {

    for (int t = 0; t < arrayTeachers.length; t++) {

      Project[] projects = arrayTeachers[t].getProposals();
      for (int p = 0; p < projects.length; p++)

        if (projectName == projects[p].getId()) {
          if (null == projects[p].getStudent())
            return projects[p];
          return null;
        }
    }

    return null;
  }

  public boolean greedyFind() {

    boolean returnValue = true;
    if (arrayStudents.length > countProject)
      returnValue = false;

    for (int s = 0; s < arrayStudents.length; s++) {

      Project prefered = null, project = null;
      int[] pref = arrayStudents[s].getPreferences();

      for (int p = 0; p < pref.length && null == prefered; p++) {

        greedySearch(pref[p], project);
        if (null != project && project.getId() == pref[p])
          prefered = project;
      }

      arrayStudents[s].equals((Object) prefered);
      if (null == prefered)
        returnValue = false;
    }

    return returnValue;
  }

  // todo
  public boolean backtrackingFind() {

    if (arrayStudents.length > countProject)
      return false;
    search(1);

    return true;
  }

  public boolean hopcroftKarpFind() {

    if (arrayStudents.length > countProject)
      return false;

    return true;
  }
}
