package lab1;

public class bonus {

  static char PROPERTY_CLIQUE = homework.COLOR_BLACK;
  static char PROPERTY_STABLE = homework.COLOR_WHITE;

  public static char[][] default_random_matrix(final int n) {

    char[][] matrix = new char[n][n];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][i] = homework.COLOR_WHITE;

      for (int j = i + 1; j < matrix.length; j++) {
        matrix[i][j] = matrix[j][i] = generation.g(
            homework.COLOR_WHITE,
            (char) (homework.COLOR_BLACK + 1));
      }
    }

    return matrix;
  }

  static boolean is_valid(final char[][] matrix,
      final boolean[] selected, final int v,
      final char property) {

    for (int i = 0; i < selected.length; i++)
      if (selected[i] &&
          matrix[i][v] != property)
        return false;

    return true;
  }

  /* prints to screen! */
  static boolean find_property(final char[][] matrix, final int k,
      final int start, boolean[] selected, final int count,
      final char property) {

    if (count == k) {
      for (int i = 0; i < selected.length; i++)
        if (selected[i])
          System.out.printf("%d ", i);
      return true;
    }

    for (int i = start; i < matrix.length; i++) {
      if (false == is_valid(matrix, selected, i, property))
        continue;

      selected[i] = true;
      if (find_property(matrix, k,
          i + 1, selected, count + 1, property))
        return true;
      selected[i] = false;
    }

    return false;
  }

  /*
   * recommandation: run with 5 5
   * takes as arguments: k and
      the number of random matrices to be verified
   * backtracking is involved
   * a faster algorithm is expected to be uploaded
   */
  public static void main(String[] args) {

    if (2 != args.length) {
      System.out.println("error: wrong number of args");
      return;
    }

    final int k = Integer.parseInt(args[0]),
        count_cases = Integer.parseInt(args[1]);

    if (k < 1 || k >= homework.SIZE_NORMAL ||
        count_cases < 1) {
      System.out.println("error: argument is not valid");
      return;
    }

    for (int i = 0; i < count_cases; i++) {
      long time = System.nanoTime();

      // for a guided experience SIZE_SMALL is used
      final int n;
      if (k < homework.SIZE_SMALL / 3)
        n = generation.g(k, homework.SIZE_SMALL);
      else
        n = generation.g(k, homework.SIZE_NORMAL);
      System.out.printf("case %d: n == %d\n", i, n);

      char[][] matrix = default_random_matrix(n);

      boolean result = find_property(matrix, k,
          0, new boolean[matrix.length], 0,
          PROPERTY_CLIQUE);

      if (result)
        System.out.println("are the nodes of the k_clique");
      else
        System.out.println("there is no k_clique");

      result = find_property(matrix, k,
          0, new boolean[matrix.length], 0,
          PROPERTY_STABLE);

      if (result)
        System.out.println("are the nodes of the stable_set");
      else
        System.out.println("there is no stable_set");

      if (n < homework.SIZE_SMALL)
        homework.print_adjancy(matrix);
      else
        System.out.printf("running time in seconds: %d\n",
            (System.nanoTime() - time) / homework.TO_SECOND);
      System.out.print("\n");

    }
  }
}
