package com.lab7;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.util.concurrent.atomic.AtomicBoolean;

class Player extends Thread {

  public static final int COUNT_TILES = 7;

  private final String name;
  private final Board game;
  private final AtomicBoolean gameRunning;

  public Player(String name, Board game, AtomicBoolean run) {
    this.name = name;
    this.game = game;
    this.gameRunning = run;
  }

  @Override
  public void run() {

    List<Tile> hand = game.getBag().extractTiles(COUNT_TILES);
    while (gameRunning.get() && !hand.isEmpty()) {

      List<String> permutations = getAllPermutations(hand);
      Optional<String> validWord = permutations.stream()
          .filter(Dictionary::isValid)
          .findFirst();

      if (!validWord.isPresent()) {
        System.out.println(name + " passed turn.");
        hand = game.getBag().extractTiles(COUNT_TILES);
      } else {
        int points = calculatePoints(validWord.get(), hand);
        game.submitWord(name, validWord.get(), points);
        hand = replaceTiles(hand, validWord.get().length());
      }

      try {
        Thread.sleep(Generation.g(0, 300));
      } catch (InterruptedException e) {
        break;
      }
    }
  }

  private int calculatePoints(String word, List<Tile> tiles) {
    int points = 0;
    for (char c : word.toCharArray()) {
      for (Tile t : tiles) {
        if (t.letter() == c) {
          points += t.points();
          break;
        }
      }
    }
    return points;
  }

  private List<Tile> replaceTiles(List<Tile> hand, int usedCount) {
    List<Tile> newHand = new ArrayList<>(hand.subList(usedCount, hand.size()));
    newHand.addAll(game.getBag().extractTiles(usedCount));
    return newHand;
  }

  private List<String> getAllPermutations(List<Tile> tiles) {
    Set<String> results = new HashSet<>();
    permute(tiles, 0, results);
    return new ArrayList<>(results);
  }

  private void permute(List<Tile> tiles, int index, Set<String> results) {
    if (index >= tiles.size()) {
      StringBuilder sb = new StringBuilder();
      for (Tile tile : tiles) {
        sb.append(tile.letter());
      }
      results.add(sb.toString());
      return;
    }

    for (int i = index; i < tiles.size(); i++) {
      Collections.swap(tiles, i, index);
      permute(tiles, index + 1, results);
      Collections.swap(tiles, i, index);
    }
  }
}

public class Homework {

  public static void main(String[] args) {

    Bag bag = new Bag();
    Board board = new Board(bag);
    AtomicBoolean gameRunning = new AtomicBoolean(true);

    List<Player> players = List.of(
        new Player("Alice", board, gameRunning),
        new Player("Bob", board, gameRunning),
        new Player("Charlie", board, gameRunning));

    TimeKeeper timekeeper = new TimeKeeper(gameRunning, 20);
    timekeeper.start();

    players.forEach(Thread::start);
    players.forEach(p -> {
      try {
        p.join();
      } catch (InterruptedException ignored) {
        System.out.println(ignored.getMessage());
      }
    });

    board.showWinner();
  }
}
