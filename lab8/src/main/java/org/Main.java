package org;

import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.Optional;

import java.util.Map;
import java.util.HashMap;

public class Main {

  private static Logger output = Logger.getLogger("Logger");

  // ! important function
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

  // small integoration of the database
  public static void compulsory() {

    try {
      ContinentDAO continentDAO = new ContinentDAO();
      Optional<Continent> eu = continentDAO.findByName("Europe");
      if (eu.isPresent())
        output.info("continent found: " + eu.get().name());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void homework() {
    // read file
    // store to database
    // showcase the calculation
  }

  public static void main(String[] args) {

    if (args.length == 0)
      compulsory();
    else
      homework();
  }
}
