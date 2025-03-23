package junk;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MapTest {

  public static void main(String[] args) {

    var numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<String> dada = numbers.stream()
        .map(x -> "number: " + Integer.toString(x) + ";")
        .collect(Collectors.toList());
    System.out.println(dada);
  }

}
