package com.revature.repository;

        import org.postgresql.Driver;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {
        // 1. Register the postgres driver with the DriverManager class from JDBC
        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        // 2. Define the database location (URL / Connection String) and username and password
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public";


        String username = System.getenv("database_username"); // System.getenv is used to read the value of an environment variable
        String password = System.getenv("database_password"); // System.getenv is used to read the value of an environment variable

        Connection connectionObject = DriverManager.getConnection(url, "postgres", "Dieuestbon2025+!");

        return connectionObject;
    }

}
