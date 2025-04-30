package com.example.dotsgame.model;

import java.awt.*;

public class Dot extends Point {
    public Dot(int x, int y) {
        super(x, y);
    }

    public boolean contains(Point p) {
        return distance(p) < 10;
    }
}
