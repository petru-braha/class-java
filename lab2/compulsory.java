package lab2;

import lab2.allocation.*;

public class compulsory {

  public static void main(String[] args) {

    Project p0 = new Project(ProjectType.thoretical, 102);
    Project p1 = new Project(ProjectType.thoretical, 103);
    Project p2 = new Project(ProjectType.practical, 104);

    int[] pref0 = { 100, 101, 102 };
    Student s0 = new Student(10, pref0);

    int[] pref1 = { 103, 104, 105 };
    Student s1 = new Student(11, pref1);
    s1.select(p2);

    int[] pref2 = { 106, 107, 108 };
    Student s2 = new Student(12, pref2);

    System.out.printf("print project details:\n");
    System.out.println(p0.toString());
    System.out.println(p1.toString());
    System.out.println(p2.toString());
    System.out.printf("\n");

    System.out.printf("print student details:\n");
    System.out.println(s0.toString());
    System.out.println(s1.toString());
    System.out.println(s2.toString());
  }
}
