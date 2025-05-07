package org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Optional;

public class CountryDAO implements DaoCountry {

  public void create(Country country) throws SQLException {

    String sql = "INSERT INTO countries (name, code, idContinent) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, country.name());
      stmt.setString(2, country.code());
      stmt.setInt(3, country.continent().id());

      stmt.executeUpdate();
    }
  }

  public Optional<Country> findById(int id) throws SQLException {

    String sql = """
          SELECT c.id, c.name, c.code, ct.id, ct.name
          FROM country c
          JOIN continent ct ON c.idContinent = ct.id
          WHERE c.id = ?
        """;

    try (Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Continent continent = new Continent(
            rs.getInt("id"),
            rs.getString("name"));

        Country country = new Country(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("code"),
            continent);

        return Optional.of(country);
      }
    }

    return Optional.empty();
  }

  public Optional<Country> findByName(String name) throws SQLException {

    String sql = """
          SELECT c.id, c.name, c.code, ct.id, ct.name
          FROM country c
          JOIN continent ct ON c.idContinent = ct.id
          WHERE c.name = ?
        """;

    try (Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Continent continent = new Continent(
            rs.getInt("continent_id"),
            rs.getString("continent_name"));

        Country country = new Country(
            rs.getInt("country_id"),
            rs.getString("country_name"),
            rs.getString("code"),
            continent);

        return Optional.of(country);
      }
    }

    return Optional.empty();
  }
}
