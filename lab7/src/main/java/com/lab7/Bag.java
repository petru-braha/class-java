package com.lab7;

import java.util.List;
import java.util.ArrayList;

public class Bag {

  public static final byte COUNT_LETTERS = 1 + 'z' - 'a';
  public static final byte COUNT_REPLICA = 10;

  private final List<Tile> tiles;

  public Bag() {

    tiles = new ArrayList<>(COUNT_LETTERS * COUNT_REPLICA);
    for (int i = 0; i < COUNT_LETTERS; i++) {
      int points = Generation.g(1, 11);
      for (int j = 0; j < COUNT_REPLICA; j++)
        tiles.add(i + j, new Tile((char) i, points));
    }

    Generation.select(COUNT_LETTERS * COUNT_REPLICA, tiles);
  }

  public synchronized List<Tile> extractTiles(final int count) {

    if (tiles.isEmpty())
      return new ArrayList<>();

    int limit = count;
    if (count >= tiles.size())
      limit = tiles.size();

    List<Tile> result = new ArrayList<>();
    for (int i = 0; i < limit && !tiles.isEmpty(); i++)
      result.add(tiles.remove(i));
    return result;
  }

  public synchronized boolean isEmpty() {
    return tiles.isEmpty();
  }
}
