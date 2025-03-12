package lab2;

import lab2.allocation.Project;
import lab2.allocation.ProjectType;
import lab2.allocation.Student;
import lab2.allocation.Teacher;

public class Homework {

  public static void main(String[] args) {

    // project
    Project p0 = new Project(
        ProjectType.thoretical, 102, null, null);
    Project p1 = new Project(
        ProjectType.thoretical, 103, null, null);
    Project p2 = new Project(
        ProjectType.practical, 104, null, null);

    // student
    int[] pref0 = { 100, 101, 102 };
    int[] pref1 = { 103, 104, 105 };
    int[] pref2 = { 106, 107, 108 };
    Student s0 = new Student(10, 16092003, pref0, null);
    Student s1 = new Student(11, 17092003, pref1, null);
    Student s2 = new Student(12, 17092003, pref2, null);

    // teacher
    Project[] proposals0 = { p0, p1 };
    Project[] proposals1 = { p2 };
    Teacher t0 = new Teacher(0, 17291998, proposals0);
    Teacher t1 = new Teacher(1, 17291998, proposals1);

    // problem
    Student[] s = { s0, s1, s2 };
    Teacher[] t = { t0, t1 };
    Problem problem0 = new Problem(s, t);
    Solution solution0 = new Solution(problem0);

    boolean answer = solution0.greedyFind();
    if (answer)
      System.out.println("a greedy solution was found.");
    else
      System.out.println("a greedy solution was NOT found.");

    System.out.printf("s0 - expected: 102, received: %d;\n",
        s0.getProject().getId());
    System.out.printf("s1 - expected: 103, received: %d;\n",
        s1.getProject().getId());
    System.out.printf("s2 - expected: 104%s, received: %s;\n\n",
        " (not a preffered value)",
        null == s2.getProject()
            ? "null"
            : Integer.toString(s2.getProject().getId()));
  }
}
