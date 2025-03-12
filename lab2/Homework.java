package lab2;

import lab2.allocation.Project;
import lab2.allocation.ProjectType;
import lab2.allocation.Student;
import lab2.allocation.Teacher;

public class Homework {

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
    int[] pref1 = { 103, 104, 105 }; // Preferences for student s1 (project IDs 103, 104, 105)
    int[] pref2 = { 106, 107, 108 }; // Preferences for student s2 (project IDs 106, 107, 108)

    // Create students with their IDs, birthdates, preferences, and assigned
    // projects (null initially)
    Student s0 = new Student(10, 16092003, pref0, null); // Student s0 with preferences pref0
    Student s1 = new Student(11, 17092003, pref1, null); // Student s1 with preferences pref1
    Student s2 = new Student(12, 17092003, pref2, null); // Student s2 with preferences pref2

    // Create teacher proposals: teacher t0 has two projects (p0, p1), while teacher
    // t1 has one project (p2)
    Project[] proposals0 = { p0, p1 }; // Teacher t0's proposed projects (p0 and p1)
    Project[] proposals1 = { p2 }; // Teacher t1's proposed project (p2)

    // Create teachers with their IDs, birthdates, and project proposals
    Teacher t0 = new Teacher(0, 17291998, proposals0); // Teacher t0 with proposals p0 and p1
    Teacher t1 = new Teacher(1, 17291998, proposals1); // Teacher t1 with proposal p2

    // Create a Problem instance, passing in the students and teachers
    Student[] s = { s0, s1, s2 }; // Array of students
    Teacher[] t = { t0, t1 }; // Array of teachers
    Problem problem0 = new Problem(s, t); // Problem instance with the students and teachers

    // Create a Solution instance using the Problem instance
    Solution solution0 = new Solution(problem0);

    // Attempt to find a greedy solution to the problem
    boolean answer = solution0.greedyFind(); // Attempt to find a solution using a greedy algorithm

    // Print whether a greedy solution was found or not
    if (answer)
      System.out.println("a greedy solution was found.");
    else
      System.out.println("a greedy solution was NOT found.");

    // Print the expected project assignments for each student, comparing them with
    // the actual assignments
    System.out.printf("s0 - expected: 102, received: %d;\n",
        s0.getProject().getId()); // Display expected vs. actual for s0 (project ID 102)
    System.out.printf("s1 - expected: 103, received: %d;\n",
        s1.getProject().getId()); // Display expected vs. actual for s1 (project ID 103)
    System.out.printf("s2 - expected: 104%s, received: %s;\n\n",
        " (not a preferred value)", // Display expected vs. actual for s2 (project ID 104 is not preferred)
        null == s2.getProject() // Check if s2 was assigned a project
            ? "null" // If no project was assigned, print "null"
            : Integer.toString(s2.getProject().getId())); // Otherwise, print the assigned project ID
  }
}
