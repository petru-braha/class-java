package com.lab7;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    players.forEach(Player::start);
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
