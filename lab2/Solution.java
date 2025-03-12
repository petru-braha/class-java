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
  private boolean isAssigned;

  public Solution(Problem problem) {

    arrayStudents = problem.getStudents().clone();
    Arrays.sort(arrayStudents, new PreferenceCountSorting());

    arrayTeachers = problem.getTeachers();

    int sum = 0;
    for (int i = 0; i < arrayTeachers.length; i++)
      sum += arrayTeachers[i].getProposals().length;
    countProject = sum;

    isAssigned = false;
  }

  private Project greedySearch(final int projectName, Project project) {

    if (null != project && projectName == project.getId())
      return project;

    // optimizes the search after passing by projectName
    boolean optimize = false;
    for (int t = 0; t < arrayTeachers.length; t++) {

      Project[] projects = arrayTeachers[t].getProposals();
      for (int p = 0; p < projects.length; p++) {

        if (projectName == projects[p].getId()) {
          if (null == projects[p].getStudent())
            return projects[p];
          if (null != project)
            return project;
          optimize = true;
        }

        if (null != projects[p].getStudent())
          continue;
        if (null == project)
          project = projects[p];
        if (true == optimize)
          return project;
      }
    }

    return project;
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

    reset();
    boolean returnValue = true;
    if (arrayStudents.length > countProject)
      returnValue = false;

    for (int s = 0; s < arrayStudents.length; s++) {

      Project prefered = null, project = null;
      int[] pref = arrayStudents[s].getPreferences();

      for (int p = 0; p < pref.length && null == prefered; p++) {

        project = greedySearch(pref[p], project);
        if (null != project && pref[p] == project.getId()) {
          prefered = project;
        }
      }

      if (null == prefered) {
        prefered = project;
        returnValue = false;
      }
      arrayStudents[s].equals((Object) prefered);
    }

    isAssigned = true;
    return returnValue;
  }

  private boolean backtrack(final int indexStudent) {

    if (indexStudent >= arrayStudents.length)
      return true;

    Student stud = arrayStudents[indexStudent];
    int[] pref = stud.getPreferences();
    
    for (int p = 0; p < pref.length; p++) {

      Project prefered = search(pref[p]);
      stud.equals((Object) prefered);
      if (null == prefered)
        continue;
      if (backtrack(indexStudent + 1))
        return true;
      stud.equals(null);
    }

    return false;
  }

  public boolean backtrackingFind() {

    reset();
    if (arrayStudents.length > countProject)
      return false;

    isAssigned = backtrack(0);
    return isAssigned;
  }

  public boolean hopcroftKarpFind() {

    reset();
    if (arrayStudents.length > countProject)
      return false;

    isAssigned = true;
    return true;
  }

  public void reset() {

    if (false == isAssigned)
      return;
    for (int s = 0; s < arrayStudents.length; s++)
      arrayStudents[s].equals(null);
    isAssigned = false;
  }

  @Override
  public String toString() {

    if (false == isAssigned)
      return "the projects are not assigned";

    StringBuilder build = new StringBuilder("the project assignation:\n");
    for (int s = 0; s < arrayStudents.length; s++) {
      if (arrayStudents[s].getPreferences().length < 20)
        build.append(arrayStudents[s].toString());
      else {
        build.append(arrayStudents[s].getId());
        if (null == arrayStudents[s].getProject())
          build.append(" did not select project;\n");
        else
          build.append(" selected ")
              .append(arrayStudents[s].getProject().getId())
              .append(";\n");
      }
    }

    return build.toString();
  }
}
