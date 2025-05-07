package org;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

  public static double getDistance(
      double lat1, double lon1,
      double lat2, double lon2) {

    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);
    lon1 = Math.toRadians(lon1);
    lon2 = Math.toRadians(lon2);

    double dlong = lon2 - lon1;
    double dlat = lat2 - lat1;

    double temp = Math.pow(Math.sin(dlat / 2), 2)
        + Math.cos(lat1) * Math.cos(lat2)
            * Math.pow(Math.sin(dlong / 2), 2);
    temp = 2 * Math.asin(Math.sqrt(temp));

    return 6371 * temp;
  }

  public static void compulsory() {
    try {
      ContinentDAO continentDAO = new ContinentDAO();
      continentDAO.create(new Continent(0, "Europe"));

      Continent europe = continentDAO.findByName("Europe").get();
      System.out.println("Continent found: " + europe.name());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void homework() {

    String filePath = "./src/main/resources/concap.csv";
    ContinentDAO continentDAO = new ContinentDAO();
    CountryDAO countryDAO = new CountryDAO();

    Map<String, Continent> continentMap = new HashMap<>();
    Map<String, Country> countryMap = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      br.readLine(); // skip header

      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");

        String cityName = parts[0].trim();
        String countryName = parts[1].trim();
        boolean isCapital = parts[2].trim().equalsIgnoreCase("primary");
        String continentName = parts[3].trim();
        double latitude = Double.parseDouble(parts[4].trim());
        double longitude = Double.parseDouble(parts[5].trim());

        // Handle continent
        Continent continent = continentMap.computeIfAbsent(continentName, name -> {
          try {
            Optional<Continent> existing = continentDAO.findByName(name);
            if (existing.isPresent())
              return existing.get();
            Continent newContinent = new Continent(0, name);
            continentDAO.create(newContinent);
            return continentDAO.findByName(name).orElseThrow();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });

        // Handle country (using name only as a key, without code)
        Country country = countryMap.computeIfAbsent(countryName, name -> {
          try {
            Optional<Country> existing = countryDAO.findByName(name);
            if (existing.isPresent())
              return existing.get();
            Country newCountry = new Country(0, name, "N/A", continent);
            countryDAO.create(newCountry);
            return countryDAO.findByName(name).orElseThrow();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });

        // Handle city
        City city = new City(0, cityName, country, isCapital, latitude, longitude);
      }

      System.out.println("Data import complete.");

      // Ask for two cities
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter the first city: ");
      String firstCityName = scanner.nextLine().trim();

      System.out.print("Enter the second city: ");
      String secondCityName = scanner.nextLine().trim();

      Optional<City> city1 = cityDAO.findByName(firstCityName);
      Optional<City> city2 = cityDAO.findByName(secondCityName);

      if (city1.isPresent() && city2.isPresent()) {
        double distance = DistanceUtil.haversine(
            city1.get().latitude(), city1.get().longitude(),
            city2.get().latitude(), city2.get().longitude());
        System.out.printf("Distance between %s and %s: %.2f km%n",
            city1.get().name(), city2.get().name(), distance);
      } else {
        System.out.println("One or both cities not found in the database.");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    compulsory();
    // homework();
  }
}
