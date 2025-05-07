package org;

import java.sql.Connection;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

  public static final Dotenv env = Dotenv.load();
  public static final String URL = "jdbc:mysql://localhost:3306/geography";
  public static final String USERNAME = "root";
  public static final String PASS_KEY = "DB_PASS";

  private HikariDataSource dataSource;
  private static DatabaseManager instance;

  private DatabaseManager() {

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(URL);
    config.setUsername(USERNAME);
    config.setPassword(env.get(PASS_KEY));
    config.setMaximumPoolSize(10);
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");

    dataSource = new HikariDataSource(config);
  }

  public static DatabaseManager getInstance() throws SQLException {
    if (instance == null || instance.getConnection().isClosed())
      instance = new DatabaseManager();
    return instance;
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
