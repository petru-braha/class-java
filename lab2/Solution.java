package lab2;

import java.util.Arrays;
import java.util.Comparator;

import lab2.allocation.*;
import lab2.structure.*;

class Solution {

  class PreferenceCountSorting implements Comparator<Student> {

    public int compare(Student x, Student y) {
      return x.getPreferences().length -
          y.getPreferences().length;
    }
  }

  private Student[] arrayStudent;
  private Teacher[] arrayTeacher;
  private final int countProject;
  private boolean isAssigned;

  private Project getProject(final int id) {

    if (id >= countProject) {
      System.out.printf("warning: %s failed - %s.\n",
          "Solution.getProject()",
          "bad index");
      return null;
    }

    for (int t = 0; t < arrayTeacher.length; t++) {
      Project[] proposals = arrayTeacher[t].getProposals();
      for (int p = 0; p < proposals.length; p++)
        if (id == proposals[p].getId())
          return proposals[p];
    }

    return null;
  }

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

    arrayTeacher = problem.getTeachers();

    int sum = 0;
    for (int i = 0; i < arrayTeacher.length; i++)
      sum += arrayTeacher[i].getProposals().length;
    countProject = sum;

    isAssigned = false;
  }

  private Project greedyUnselected() {

    for (int t = 0; t < arrayTeacher.length; t++) {

      Project[] projects = arrayTeacher[t].getProposals();
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
    for (int t = 0; t < arrayTeacher.length; t++) {

      Project[] projects = arrayTeacher[t].getProposals();
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

    for (int t = 0; t < arrayTeacher.length; t++) {

      Project[] projects = arrayTeacher[t].getProposals();
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

  private int belongsTo(Student edge, Student[] matchedEdges) {

    int indexNull = -1;
    for (int s = 0; s < matchedEdges.length; s++) {

      if (null == matchedEdges[s]) {
        if (-1 == indexNull)
          indexNull = s;
        continue;
      }

      if (edge.getId() != matchedEdges[s].getId())
        continue;
      Project p0 = edge.getProject(), p1 = matchedEdges[s].getProject();
      if ((null == p0 && null != p1) || (null != p0 && null == p1))
        continue;

      if (null == p0 || p0.getId() == p1.getId())
        return s;
    }

    return indexNull;
  }

  /*
   * the alternating path is obtained through another array of the same students
   * an edge is matched if null != student.getProject()
   * an edge is unmatched if null == student.getProject()
   * 
   * bfs starts from a free node
   */
  private Student[] augmentingPath(Student[] matchedEdges) {

    LinkedList<Student> freeNodes = new LinkedList<>();
    for (int s = 0; s < matchedEdges.length; s++)
      if (null == matchedEdges[s].getProject())
        freeNodes.insertTail(matchedEdges[s]);

    // bfs
    boolean side = false; // false == left
    int prv = 0, idx = freeNodes.getN();

    while (null != freeNodes.getH()) {

      LinkedList<Student>.Node node = freeNodes.getH();
      for (int i = prv; i < idx; i++, node = node.next) {

        if (false == side) /* left */ {

          Student stud = node.data;
          if (null != stud.getProject())
            continue;

          int[] pref = stud.getPreferences();
          for (int p = 0; p < pref.length; p++)
            if (true) {
              getProject(idx);
            }
        } else /* right */ {

        }

      }

      prv = idx;
      idx = freeNodes.getN();
      side = !side;
    }

    // dfs back
    // nothings was found
    return null;
  }

  /*
   * X = students, Y = projects
   * HALL: there is a perfect matching <=> |W| <= |N_G(W)|, any W = subset of X
   * is there a perfect matching?
   * is there a matching that covers every student?
   * 
   * computing each subset is as bad as backtracking in time complexity
   * hopcroftKarpFind() returns a maximum-cardinality matching in O(sqrt(V)*E)
   * if the cardinality is equal to the student count the condition is satisfied
   */
  public boolean hopcroftKarpFind() {

    reset();
    if (arrayStudent.length > countProject)
      return false;

    // only this array will support equals() operations
    Student[] matchedEdges = new Student[arrayStudent.length];
    Arrays.fill(matchedEdges, null);

    // initialization
    for (int s = 0; s < arrayStudent.length; s++) {

      int[] preferences = arrayStudent[s].getPreferences();
      for (int p = 0; s < preferences.length; p++) {

        Project proj = greedySearch(p, null);
        matchedEdges[s] = arrayStudent[s].clone();
        matchedEdges[s].equals((Object) proj);
        if (null != proj)
          break;
      }
    }

    // loop
    Student[] path = augmentingPath(matchedEdges);
    for (; null != path; path = augmentingPath(matchedEdges))

      for (int e = 0; e < path.length; e++) {

        int indexEdge = belongsTo(path[e], matchedEdges);
        if (-1 == indexEdge) {
          System.out.printf("error: hopcroftKarpFind() failed - %s.\n",
              "matched edge array was full");
          return false;
        }

        if (null != matchedEdges[indexEdge])
          matchedEdges[indexEdge] = null;
        else
          matchedEdges[indexEdge] = path[e].clone();
      }

    isAssigned = true;
    return arrayStudent.length == matchedEdges.length;
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
