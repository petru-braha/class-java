package lab2;

import algo.Generation;
import lab2.allocation.*;

public class Problem {

  private final Student[] arrayStudent;
  private final Teacher[] arrayTeacher;

  // 10% chance to be an invalid number
  private void generateStudent(final int cp, final int cs) {

    for (int s = 0; s < cs; s++) {

      int countPreference = Generation.g(0, cp + 1);
      int[] pref = new int[countPreference];
      for (int i = 0; i < pref.length; i++)
        pref[i] = i;

      // 1% chance to generate an invalid preference
      Generation.select(pref.length, pref);
      if (0 == Generation.g(0, 100))
        pref[Generation.g(0, pref.length)] = Integer.MAX_VALUE;

      arrayStudent[s] = new Student(s,
          Generation.g(10012000, 20122030),
          pref, null);
    }
  }

  private void generateTeacher(final int cp, final int ct,
      final Project[] arrayProject) {

    int factor = cp / ct;
    if (0 == factor)
      factor = 1;
    factor = factor + factor / 2;

    int t = 0;
    for (int prev = 0; t < ct && prev < ct; t++, prev++) {

      int next = Generation.g(prev, prev + factor + 1);
      if (next >= arrayProject.length)
        next = arrayProject.length - 1;

      Project[] proposals = new Project[next - prev + 1];
      for (int p = 0; p < proposals.length; p++)
        proposals[p] = arrayProject[p + prev];
      prev = next;

      arrayTeacher[t] = new Teacher(t,
          Generation.g(0, 20122030),
          proposals);
    }

    if (t == ct)
      return;

    for (int i = t; i < ct; i++) {
      arrayTeacher[i] = new Teacher(t,
          Generation.g(0, 20122030),
          new Project[0]);
    }
  }

  public Problem(final Student[] s, final Teacher[] t) {
    arrayStudent = s;
    arrayTeacher = t;
  }

  public Problem(final int seed) {

    if (seed <= 0 || seed >= Integer.MAX_VALUE / 3 * 2) {
      System.out.println("error: wrong seed.\n");
      arrayStudent = null;
      arrayTeacher = null;
      return;
    }

    // generate project
    int cp = Generation.g(seed - seed / 2, seed + seed / 2);
    Project[] arrayProject = new Project[cp];

    for (int p = 0; p < cp; p++) {

      ProjectType type = ProjectType.practical;
      if (0 == Generation.g(0, 2))
        type = ProjectType.theoretical;

      arrayProject[p] = new Project(type, p, null, null);
    }

    int cs = Generation.g(0, (cp + 1) + (cp + 1) / 10);
    arrayStudent = new Student[cs];
    generateStudent(cp, cs);

    Generation.select(arrayProject.length, arrayProject);
    int ct = Generation.g(seed - seed / 2, seed + seed / 2);
    arrayTeacher = new Teacher[ct];
    generateTeacher(cp, ct, arrayProject);
  }

  public void printDetails() {

    System.out.println("all the involved students:");
    for (int i = 0; i < arrayStudent.length; i++)
      System.out.printf("%s\n", arrayStudent[i].toString());
    System.out.printf("\n");

    System.out.println("all the involved teachers:");
    for (int i = 0; i < arrayTeacher.length; i++)
      System.out.printf("%s\n", arrayTeacher[i].toString());
    System.out.printf("\n");
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
