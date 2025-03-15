package lab2;

public class Bonus {

  private static final int DEFAULT_SEED = 7;
  private static final int PRINTABLE_SEED = 15;

  /*
   * the time measurement is performed for the solution
   * the space measurement is performed for
   * the problem
   * the running solution at different times
   */
  public static void main(String[] args) {

    if (args.length > 1) {
      System.out.printf("error: bonus failed - %s.\n",
          "too many arguments");
      return;
    }

    int seed = DEFAULT_SEED;
    if (args.length == 1)
      seed = Integer.parseInt(args[0]);

    Problem p = new Problem(seed);
    Solution s = new Solution(p);

    if (seed < PRINTABLE_SEED)
      p.printDetails();
    else
      p.printPeople();

    // the problem resets when a solution is executed
    long time = System.nanoTime();
    boolean result = s.greedyFind();
    time = System.nanoTime() - time;
    System.out.printf("nanoseconds: %d - greedy - %b.\n", time, result);
    if (result)
      s.printAssignation();

    // greedy true => backtrack true
    time = System.nanoTime();
    result = s.backtrackingFind();
    time = System.nanoTime() - time;
    System.out.printf("nanoseconds: %d - backtracking - %b.\n", time, result);
    if (result)
      s.printAssignation();

    time = System.nanoTime();
    result = s.hopcroftKarpFind();
    time = System.nanoTime() - time;
    System.out.printf("nanoseconds: %d - hopcroftKarp - %b.\n", time, result);
    if (result)
      s.printAssignation();
  }
}
