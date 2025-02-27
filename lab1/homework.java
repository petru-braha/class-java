package lab1;

import java.util.Random;
import java.util.stream.IntStream;

public class homework {

  // white == no edge
  static final char COLOR_BLACK = 0x25A0;
  static final char COLOR_WHITE = 0x25A1;

  /* generates random number within this range */
  static char g(final char startInclusive, final char endExclusive) {
    Random utility = new Random();
    return (char) utility.nextInt(startInclusive, endExclusive);
  }

  static int g(final int startInclusive, final int endExclusive) {
    Random utility = new Random();
    return utility.nextInt(startInclusive, endExclusive);
  }

  /* selects k random values from k in O(k) */
  static void select(final int k, int[] results) {
    if (k >= results.length) {
      System.out.println("warning: select() failed");
      return;
    }

    int it_index_array = 0;
    for (; it_index_array < k; it_index_array++) {
      final int it_index_chosen = g(it_index_array, results.length);
      final int answer = results[it_index_chosen];
      results[it_index_chosen] = results[it_index_array];
      results[it_index_array] = answer;
    }
  }

  static void print_adjancy(char[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j]);
        System.out.print(" ");
      }

      System.out.print("\n");
    }
  }

  public static void main(String[] args) {

    final int n = Integer.parseInt(args[0]),
        k = Integer.parseInt(args[1]);

    if (k < 1 || k > n - k) {
      System.out.println("error: k is not valid");
      return;
    }

    char[][] matrix = new char[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        matrix[i][j] = COLOR_WHITE;

    // generate at random k-clique and stable-sets
    int[] positions = IntStream.range(0, n + 1).toArray();
    select(k * 2, positions);

    // k-clique
    for (int i = 0; i < k; i++)
      for (int j = 0; j < k; j++)
        if (i != j)
          matrix[i][j] = matrix[j][i] = COLOR_BLACK;

    // stable-set
    for (int i = k; i < 2 * k; i++)
      for (int j = k; j < 2 * k; j++)
        if (i != j)
          matrix[i][j] = matrix[j][i] = COLOR_WHITE;

    // the other nodes
    for (int i = 2 * k; i < n; i++)
      for (int j = 0; j < n; j++)
        if (i != j)
          matrix[i][j] = matrix[j][i] = g(
              COLOR_BLACK, (char) (COLOR_WHITE + 1));

    // the required graph here is completely generated
    print_adjancy(matrix);
  }
}
