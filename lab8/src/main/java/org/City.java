package org;

public record City(int id, String name,
    Country country, boolean capital,
    double latitude, double longitude) {
}
