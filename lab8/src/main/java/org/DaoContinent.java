package org;

import java.sql.SQLException;
import java.util.Optional;

public interface DaoContinent {

  public void create(Continent continent) throws SQLException;

  public Optional<Continent> findById(int id) throws SQLException;

  public Optional<Continent> findByName(String name) throws SQLException;
}
