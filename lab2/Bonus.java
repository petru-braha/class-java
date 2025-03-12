package lab2;

public class Bonus {

  private static void greedyExample(final int seed) {

    Problem problem0 = new Problem(seed);
    Problem problem1 = new Problem(seed);
    Problem problem2 = new Problem(seed);

    Solution solution0 = new Solution(problem0);
    Solution solution1 = new Solution(problem1);
    Solution solution2 = new Solution(problem2);

    boolean result = false;
    result = solution0.greedyFind();
    System.out.printf("%s solution : %s\n",
        true == result ? "valid" : "invalid",
        solution0.toString());

    result = solution1.greedyFind();
    System.out.printf("%s solution : %s\n",
        true == result ? "valid" : "invalid",
        solution1.toString());

    result = solution2.greedyFind();
    System.out.printf("%s solution : %s\n",
        true == result ? "valid" : "invalid",
        solution2.toString());
  }

  private static void backtrackExample(final int seed) {

  }

  private static void hopcroftKarpExample(final int seed) {

  }

  public static void main(String[] args) {

    Problem p0 = new Problem(5);
    return;
    
    if (args.length > 1) {
      System.out.printf("error: bonus failed - %s.\n",
          "too many arguments");
      return;
    }

    int seed = 5;
    if (args.length == 1)
      seed = Integer.parseInt(args[0]);

    greedyExample(seed);
    backtrackExample(seed);
    hopcroftKarpExample(seed);
  }
}
