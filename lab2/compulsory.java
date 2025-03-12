package lab2;

import lab2.allocation.*;

public class compulsory {

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
    Student s0 = new Student(10, 16092003, pref0, null);

    int[] pref1 = { 103, 104, 105 };
    Student s1 = new Student(11, 17092003, pref1, null);
    s1.equals((Object) p2);

    int[] pref2 = { 106, 107, 108 };
    Student s2 = new Student(12, 17092003, pref2, null);

    // teacher
    Project[] proposals0 = { p0, p1 };
    Project[] proposals1 = { p2 };
    Teacher t0 = new Teacher(0, 17291998, proposals0);
    Teacher t1 = new Teacher(1, 17291998, proposals1);

    System.out.println("tests.");
    s0.equals((Object) p2); // bad
    s0.equals((Object) p0); // good
    s0.equals((Object) p1); // good
    s2.equals((Object) p1); // bad
    s2.equals((Object) p0); // bad
    System.out.println("end tests.\n");

    System.out.printf("print project details:\n%s\n%s\n%s\n\n",
        p0.toString(), p1.toString(), p2.toString());

    System.out.printf("print student details:\n%s\n%s\n%s\n\n",
        s0.toString(), s1.toString(), s2.toString());

    System.out.printf("print teacher details:\n%s\n%s\n\n",
        t0.toString(), t1.toString());
  }
}
