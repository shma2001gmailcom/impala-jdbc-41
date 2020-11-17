package org.misha;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Launcher {
    private static final String CONNECTION_URL_PROPERTY = "connection.url";
    private static final String JDBC_DRIVER_NAME_PROPERTY = "jdbc.driver.class.name";
    private static String connectionUrl;
    private static String jdbcDriverName;

    private static void loadConfiguration() throws IOException {
        try (InputStream input = propsStream()) {
            Properties prop = new Properties();
            prop.load(input);
            connectionUrl = prop.getProperty(CONNECTION_URL_PROPERTY);
            jdbcDriverName = prop.getProperty(JDBC_DRIVER_NAME_PROPERTY);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static InputStream propsStream() {
        return Launcher.class.getClassLoader().getResourceAsStream("application.propertiess");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 1) {
            System.out.println("Syntax: Launcher \"<SQL_query>\"");
            System.exit(1);
        }
        String sqlStatement = args[0];
        loadConfiguration();
        System.out.println("\n========================\n");
        System.out.println("connection url: " + connectionUrl);
        System.out.println("query: " + sqlStatement);
        Class.forName(jdbcDriverName);
        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sqlStatement)
        ) {
            System.out.println("\n=== query results ===");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            System.out.println("=======================\n\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
