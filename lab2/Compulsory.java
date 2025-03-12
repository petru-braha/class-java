package lab2;

import lab2.allocation.*;

public class Compulsory {

  public static void main(String[] args) {

    // Create projects with different project types and IDs
    // Project p0 and p1 are theoretical projects, while p2 is a practical project
    Project p0 = new Project(
        ProjectType.thoretical, 102, null, null); // Theoretical project with ID 102
    Project p1 = new Project(
        ProjectType.thoretical, 103, null, null); // Theoretical project with ID 103
    Project p2 = new Project(
        ProjectType.practical, 104, null, null); // Practical project with ID 104

    // Create student preferences arrays representing the preferred project IDs
    int[] pref0 = { 100, 101, 102 }; // Preferences for student s0 (project IDs 100, 101, 102)
    Student s0 = new Student(10, 16092003, pref0, null); // Student s0 with preferences pref0

    int[] pref1 = { 103, 104, 105 }; // Preferences for student s1 (project IDs 103, 104, 105)
    Student s1 = new Student(11, 17092003, pref1, null); // Student s1 with preferences pref1
    s1.equals((Object) p2); // Checking equality between student s1 and project p2 (this is not a valid
                            // comparison)

    int[] pref2 = { 106, 107, 108 }; // Preferences for student s2 (project IDs 106, 107, 108)
    Student s2 = new Student(12, 17092003, pref2, null); // Student s2 with preferences pref2

    // Create teacher proposals: teacher t0 has two projects (p0, p1), while teacher
    // t1 has one project (p2)
    Project[] proposals0 = { p0, p1 }; // Teacher t0's proposed projects (p0 and p1)
    Project[] proposals1 = { p2 }; // Teacher t1's proposed project (p2)

    // Create teachers with their IDs, birthdates, and project proposals
    Teacher t0 = new Teacher(0, 17291998, proposals0); // Teacher t0 with proposals p0 and p1
    Teacher t1 = new Teacher(1, 17291998, proposals1); // Teacher t1 with proposal p2

    // Begin test cases for comparing students to projects (using .equals method)
    System.out.println("tests.");
    s0.equals((Object) p2); // This is a bad comparison since s0 and p2 are different types (Student vs
                            // Project)
    s0.equals((Object) p0); // This is a good comparison because s0 and p0 are compatible in terms of their
                            // project ID
    s0.equals((Object) p1); // This is a good comparison as well (s0 is compatible with p1)
    s2.equals((Object) p1); // This is a bad comparison because s2 doesn't prefer p1 based on its
                            // preferences
    s2.equals((Object) p0); // This is a bad comparison as well, similar reason to the previous one
    System.out.println("end tests.\n");

    // Print project details using their overridden toString() methods
    System.out.printf("print project details:\n%s\n%s\n%s\n\n",
        p0.toString(), p1.toString(), p2.toString()); // Printing details of p0, p1, and p2

    // Print student details using their overridden toString() methods
    System.out.printf("print student details:\n%s\n%s\n%s\n\n",
        s0.toString(), s1.toString(), s2.toString()); // Printing details of s0, s1, and s2

    // Print teacher details using their overridden toString() methods
    System.out.printf("print teacher details:\n%s\n%s\n\n",
        t0.toString(), t1.toString()); // Printing details of t0 and t1
  }
}
