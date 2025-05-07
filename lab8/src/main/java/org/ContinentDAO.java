package org;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class ContinentDAO implements DaoContinent {

  public void create(Continent continent) throws SQLException {

    String sql = "INSERT INTO continent (name) VALUES (?)";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, continent.name());
      stmt.executeUpdate();
    }
  }

  public Optional<Continent> findById(int id) throws SQLException {

    String sql = "SELECT id, name FROM continent WHERE id = ?";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Continent continent = new Continent(
            rs.getInt("id"),
            rs.getString("name"));
        return Optional.of(continent);
      }
    }

    return Optional.empty();
  }

  public Optional<Continent> findByName(String name) throws SQLException {

    String sql = "SELECT id, name FROM continent WHERE name = ?";

    try (Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Continent continent = new Continent(
            rs.getInt("id"),
            rs.getString("name"));
        return Optional.of(continent);
      }
    }

    return Optional.empty();
  }
}
