package com.lab7;

import java.util.Random;
import java.util.List;

public class Generation {

  public static final Random utility = new Random();

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

    if (k > results.length) {
      System.out.printf("warning: select() failed\n");
      return;
    }

    int itIndexArray = 0;
    for (; itIndexArray < k; itIndexArray++) {
      final int itIndexChosen = g(itIndexArray, results.length);
      final int answer = results[itIndexChosen];
      results[itIndexChosen] = results[itIndexArray];
      results[itIndexArray] = answer;
    }
  }

  public static <T> void select(final int k, T[] results) {

    if (k > results.length) {
      System.out.println("warning: select() failed");
      return;
    }

    int itIndexArray = 0;
    for (; itIndexArray < k; itIndexArray++) {
      final int itIndexChosen = g(itIndexArray, results.length);
      final T answer = results[itIndexChosen];
      results[itIndexChosen] = results[itIndexArray];
      results[itIndexArray] = answer;
    }
  }

  public static <T> void select(final int k, List<T> results) {

    if (k > results.size()) {
      System.out.println("warning: select() failed");
      return;
    }

    int itIndexArray = 0;
    for (; itIndexArray < k; itIndexArray++) {
      final int itIndexChosen = g(itIndexArray, results.size());
      final T answer = results.get(itIndexChosen);
      results.set(itIndexChosen, results.get(itIndexArray));
      results.set(itIndexArray, answer);
    }
  }

  private Generation() {
  }
}
