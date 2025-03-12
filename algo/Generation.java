package algo;

import java.util.Random;

public class Generation {

  public static Random utility = new Random();

  /* generates random number within this range */
  public static char g(final char startInclusive, final char endExclusive) {

    if (startInclusive >= endExclusive) {
      System.out.printf("error: g() failed.\n");
      return (char) -1;
    }

    return (char) utility.nextInt(startInclusive, endExclusive);
  }

  public static int g(final int startInclusive, final int endExclusive) {

    if (startInclusive >= endExclusive) {
      System.out.printf("error: g() failed.\n");
      return -1;
    }

    return utility.nextInt(startInclusive, endExclusive);
  }

  /*
   * selects k random values from n in O(k)
   * behaves similarly to a random permutation
   */
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

  public static <T> void select(final int k, T[] results) {

    if (k >= results.length) {
      System.out.println("warning: select() failed");
      return;
    }

    int it_index_array = 0;
    for (; it_index_array < k; it_index_array++) {
      final int it_index_chosen = g(it_index_array, results.length);
      final T answer = results[it_index_chosen];
      results[it_index_chosen] = results[it_index_array];
      results[it_index_array] = answer;
    }
  }
}
