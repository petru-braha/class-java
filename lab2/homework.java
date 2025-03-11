package lab2;

/* one stundet can be assigned to one project
 * one project can be done by one student
 * one teacher has multiple projects
 * one project is proposed by one teacher
 * 
 * student equals project
 * teacher equals project
 */

/* random generation
 * the problem can be solved if count(student) <= count(projects)
 */

import algo.Generation;

import java.rmi.ConnectIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import lab2.allocation.*;

class Problem {

  private final Student[] arrayStudent;
  private final Teacher[] arrayTeacher;

  public Problem(final Student[] s, final Teacher[] t) {
    arrayStudent = s;
    arrayTeacher = t;
  }

  public Problem(final int seed) {

    if (seed <= 0 || seed >= Integer.MAX_VALUE / 3 * 2) {
      System.out.println("error: wrong seed.\n");
      return;
    }

    // generate project
    int cp = Generation.g(seed - seed / 2, seed + seed / 2);
    Project[] arrayProject = new Project[cp];

    for (int p = 0; p < cp; p++) {

      ProjectType type = ProjectType.practical;
      if (0 == Generation.g(0, 2))
        type = ProjectType.thoretical;

      arrayProject[p] = new Project(type, p, null, null);
    }

    // generate student - 10 % chance to be an invalid number
    int countDigits = 0, temp = seed;
    while (temp != 0) {
      countDigits++;
      temp /= 10;
    }

    int cs = Generation.g(cp - 8 * countDigits, cp + 2 * countDigits);
    arrayStudent = new Student[cs];

    for (int s = 0; s < cs; s++) {
      arrayStudent[s] = new Student(s,
          Generation.g(10012000, 20122030),
          null, null);
    }

    // generate teacher
    int ct = Generation.g(0, 1);
    arrayTeacher = new Teacher[ct];
    Generation.select(arrayProject.length, arrayProject);

    for (int t = 0, prev = 0; t < ct; t++) {
      int next = Generation.g(prev, ct);
      prev;
      next;
      prev = next;

      // proposals
      Project[] proposals = new Project[next - prev];

      arrayTeacher[t] = new Teacher(t, 
      Generation.g(0, 20122030),
      proposals
      );
    }
  }

  public void printPeople() {

    System.out.println("all the involved students:");
    for (int i = 0; i < arrayStudent.length; i++)
      System.out.printf("name:%d-id:%d; ",
          arrayStudent[i].getName(),
          arrayStudent[i].getId());

    System.out.println("all the involved teachers:");
    for (int i = 0; i < arrayTeacher.length; i++)
      System.out.printf("name:%d; ",
          arrayTeacher[i].getName());
  }

  Student[] getStudents() {
    return arrayStudent;
  }

  Teacher[] getTeachers() {
    return arrayTeacher;
  }
}

class Solution {

  class PreferenceCountSorting implements Comparator<Student> {

    public int compare(Student x, Student y) {
      return x.getPreferences().length -
          y.getPreferences().length;
    }
  }

  private Student[] arrayStudents;
  private Teacher[] arrayTeachers;

  public Solution(Problem problem) {
    arrayStudents = problem.getStudents().clone();
    arrayTeachers = problem.getTeachers();
    Arrays.sort(arrayStudents, new PreferenceCountSorting());
  }

  private Project exists(final int projectName) {

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
    for (int s = 0; s < arrayStudents.length; s++) {

      int[] pref = arrayStudents[s].getPreferences();
      for (int p = 0; p < pref.length; p++) {

        Project prefered = exists(pref[p]);
        if (null == prefered)
          continue;

        arrayStudents[s].equals((Object)prefered);
        break;
      }

      // default assignation
      if(null == arrayStudents[s].getProject()){
        
        returnValue = false;
      }

    }

    return returnValue;
  }

  public boolean backtrackingFind() {

  }

  public boolean hopcroftKarpFind() {

  }
}

public class homework {

  public static void main(String[] args) {

    // init some Problems
  }

}
