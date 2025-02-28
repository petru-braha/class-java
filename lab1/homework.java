package lab1;

import java.util.Random;
import java.util.stream.IntStream;

class generation {

  /* generates random number within this range */
  public static char g(final char startInclusive, final char endExclusive) {
    Random utility = new Random();
    return (char) utility.nextInt(startInclusive, endExclusive);
  }

  public static int g(final int startInclusive, final int endExclusive) {
    Random utility = new Random();
    return utility.nextInt(startInclusive, endExclusive);
  }

  /* selects k random values from k in O(k) */
  public static void select(final int k, int[] results) {
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
}

public class homework {

  // white == no edge
  static final char COLOR_WHITE = 0x25A0;
  static final char COLOR_BLACK = 0x25A1;
  static final int SIZE_SMALL = 30;
  static final int SIZE_NORMAL = 5_000;
  static final int SIZE_LARGE = 30_000;

  static final long TO_SECOND = 1_000_000_000;

  static int generate_k_clique(char[][] matrix,
      final int[] positions,
      final int k, int[] degrees) {

    int m = 0;
    for (int i = 0; i < k; i++)
      for (int j = 0; j < k; j++) {
        if (i == j)
          continue;

        int v0 = positions[i], v1 = positions[j];
        matrix[v0][v1] = matrix[v1][v0] = COLOR_BLACK;
        degrees[v0]++;
        degrees[v1]++;
        m++;
      }

    return m;
  }

  static int generate_stable_set(char[][] matrix,
      final int[] positions,
      final int k, int[] degrees) {

    int m = 0;
    for (int i = k; i < 2 * k; i++)
      for (int j = 0; j < k; j++) {
        int v0 = positions[i], v1 = positions[j];
        matrix[v0][v1] = matrix[v1][v0] = generation.g(
            COLOR_WHITE, (char) (COLOR_BLACK + 1));
        if (COLOR_BLACK == matrix[v0][v1]) {
          degrees[v0]++;
          degrees[v1]++;
          m++;
        }
      }

    return m;
  }

  static int generate_additional_edges(char[][] matrix,
      final int[] positions, final int k,
      final int n, int[] degrees) {

    int m = 0;
    for (int i = 2 * k; i < n; i++)
      for (int j = 0; j < 2 * k; j++) {
        int v0 = positions[i], v1 = positions[j];
        matrix[v0][v1] = matrix[v1][v0] = generation.g(
            COLOR_WHITE, (char) (COLOR_BLACK + 1));
        if (COLOR_BLACK == matrix[v0][v1]) {
          degrees[v0]++;
          degrees[v1]++;
          m++;
        }
      }

    for (int i = 2 * k; i < n; i++)
      for (int j = i + 1; j < n; j++) {
        int v0 = positions[i], v1 = positions[j];
        matrix[v0][v1] = matrix[v1][v0] = generation.g(
            COLOR_WHITE, (char) (COLOR_BLACK + 1));
        if (COLOR_BLACK == matrix[v0][v1]) {
          degrees[v0]++;
          degrees[v1]++;
          m++;
        }
      }

    return m;
  }

  static void print_adjancy(char[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j]);
        System.out.print(" ");
      }

      System.out.print("\n");
    }
  }

  static void print_degrees(final int[] degrees, final int m) {
    int maximum_degree = degrees[0],
        minimum_degree = degrees[0],
        sum_degree = degrees[0];
    for (int i = 1; i < degrees.length; i++) {
      if (degrees[i] > maximum_degree)
        maximum_degree = degrees[i];
      if (degrees[i] < minimum_degree)
        minimum_degree = degrees[i];
      sum_degree += degrees[i];
    }

    System.out.print("the maximum degree of a vertex is ");
    System.out.println(maximum_degree);
    System.out.print("the minimum degree of a vertex is ");
    System.out.println(minimum_degree);

    if (m * 2 == sum_degree)
      System.out.println("the sum of the degrees equals the value 2 * m");
    else
      System.out.println("verification failed!");
  }

  static void print_information(final char[][] matrix,
      final int n, final int m, final int[] degrees) {
    if (n < SIZE_SMALL)
      print_adjancy(matrix);

    System.out.print("the number of edges is ");
    System.out.println(m);

    print_degrees(degrees, m);
  }

  public static void main(String[] args) {

    if (2 != args.length) {
      System.out.println("error: wrong number of args");
      return;
    }

    final int n = Integer.parseInt(args[0]),
        k = Integer.parseInt(args[1]);

    if (k < 1 || k > n - k) {
      System.out.println("error: k is not valid");
      return;
    }

    // data declaration
    long time = System.nanoTime();
    int m = 0;
    int[] degrees = new int[n];
    for (int i = 0; i < n; i++)
      degrees[i] = 0;

    char[][] matrix = new char[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        matrix[i][j] = COLOR_WHITE;

    // generate at random k-clique and stable-sets
    int[] positions = IntStream.range(0, n).toArray();
    generation.select(k * 2, positions);

    m += generate_k_clique(matrix, positions, k, degrees);
    m += generate_stable_set(matrix, positions, k, degrees);
    m += generate_additional_edges(matrix, positions, k, n, degrees);

    // the required graph here is completely generated
    print_information(matrix, n, m, degrees);
    if (n < SIZE_SMALL)
      return;

    System.out.print("running time in seconds: ");
    System.out.println((System.nanoTime() - time) / TO_SECOND);

    // tried: n > 30_000 - it never stops
    // use this: java -Xms4G -Xmx4G lab1.homework 5000 4
  }
}
