package org;

import java.sql.SQLException;
import java.util.Optional;

public interface DaoCountry {

  public void create(Country continent) throws SQLException;

  public Optional<Country> findById(int id) throws SQLException;

  public Optional<Country> findByName(String name) throws SQLException;
}
