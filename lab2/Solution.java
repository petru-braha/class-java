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

  private Student[] arrayStudent;
  private Teacher[] arrayTeachers;
  private final int countProject;
  private boolean isAssigned;

  public Solution(Problem problem) {

    arrayStudent = problem.getStudents().clone();
    Arrays.sort(arrayStudent, new PreferenceCountSorting());

    int s = 0, nS = arrayStudent.length;
    for (; s < nS && 0 == arrayStudent[s].getPreferences().length; s++)
      ;
    for (int i = 0; i < s; i++) {
      Student student = arrayStudent[i];
      arrayStudent[i] = arrayStudent[nS - 1 - i];
      arrayStudent[nS - 1 - i] = student;
    }

    arrayTeachers = problem.getTeachers();

    int sum = 0;
    for (int i = 0; i < arrayTeachers.length; i++)
      sum += arrayTeachers[i].getProposals().length;
    countProject = sum;

    isAssigned = false;
  }

  private Project greedyUnselected() {

    for (int t = 0; t < arrayTeachers.length; t++) {

      Project[] projects = arrayTeachers[t].getProposals();
      for (int p = 0; p < projects.length; p++)
        if (null == projects[p].getStudent())
          return projects[p];
    }

    return null;
  }

  private Project greedySearch(final int projectName, Project project) {

    if (Integer.MAX_VALUE == projectName)
      return project;
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

    if (Integer.MAX_VALUE == projectName)
      return null;

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
    if (arrayStudent.length > countProject)
      returnValue = false;

    for (int s = 0; s < arrayStudent.length; s++) {

      Project prefered = null, project = null;
      int[] pref = arrayStudent[s].getPreferences();

      if (null == pref || 0 == pref.length)
        prefered = greedyUnselected();

      for (int p = 0; p < pref.length && null == prefered; p++) {
        project = greedySearch(pref[p], project);
        if (null != project && pref[p] == project.getId())
          prefered = project;
      }

      if (null == prefered) {
        prefered = project;
        returnValue = false;
      }

      arrayStudent[s].equals((Object) prefered);
    }

    isAssigned = true;
    return returnValue;
  }

  private boolean backtrack(final int indexStudent) {

    if (indexStudent > arrayStudent.length) {
      System.out.printf("warning: backtrack() failed.\n");
      return false;
    }

    if (indexStudent == arrayStudent.length)
      return true;

    Student stud = arrayStudent[indexStudent];
    int[] pref = stud.getPreferences();

    // edge case
    if (null == pref || 0 == pref.length) {
      Project p = greedyUnselected();
      stud.equals((Object) p);
      if (null == p)
        return false;
      if (backtrack(indexStudent + 1))
        return true;
      stud.equals(null);
    }

    // normal case
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
    if (arrayStudent.length > countProject)
      return false;

    isAssigned = true;
    return backtrack(0);
  }

  public boolean hopcroftKarpFind() {

    reset();
    if (arrayStudent.length > countProject)
      return false;

    isAssigned = true;
    return true;
  }

  public void reset() {

    if (false == isAssigned)
      return;
    for (int s = 0; s < arrayStudent.length; s++)
      arrayStudent[s].equals(null);
    isAssigned = false;
  }

  @Override
  public String toString() {

    if (false == isAssigned)
      return "the projects are not assigned";

    StringBuilder build = new StringBuilder("the project assignation:\n");
    for (int s = 0; s < arrayStudent.length; s++) {
      if (arrayStudent[s].getPreferences().length < 20)
        build.append(arrayStudent[s].toString());
      else {
        build.append(arrayStudent[s].getId());
        if (null == arrayStudent[s].getProject())
          build.append(" did not select project;\n");
        else
          build.append(" selected ")
              .append(arrayStudent[s].getProject().getId())
              .append(";\n");
      }
    }

    return build.toString();
  }

  public void printAssignation() {

    if (false == isAssigned) {
      System.out.printf("the projects are not assigned\n\n");
      return;
    }

    for (int i = 0; i < arrayStudent.length; i++) {

      Project p = arrayStudent[i].getProject();
      System.out.printf("stud:%d-proj:%s; ",
          arrayStudent[i].getId(),
          null == p
              ? "no project"
              : Integer.toString(p.getId()));
    }

    System.out.printf("\n");
  }
}
