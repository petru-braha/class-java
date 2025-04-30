package com.lab7;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {

  private final Map<String, Integer> playerScores = new ConcurrentHashMap<>();
  private final Bag bag;

  public Board(final Bag bag) {
    this.bag = bag;
  }

  public synchronized void submitWord(String player, String word, int points) {

    playerScores.put(
        player,
        points + playerScores.getOrDefault(
            player, 0));

    System.out.printf("%s submitted %s ( %d points)%n",
        player, word, points);
  }

  public void showWinner() {

    if (playerScores.isEmpty()) {
      System.out.printf("no winner%n");
      return;
    }

    String winnerName = null;
    int max = -99999;
    for (Map.Entry<String, Integer> entry : playerScores.entrySet())
      if (entry.getValue() > max) {
        winnerName = entry.getKey();
        max = entry.getValue();
      }

    System.out.printf("winner: %s; with score: %d;%n",
        winnerName, max);
  }

  public Bag getBag() {
    return bag;
  }
}
