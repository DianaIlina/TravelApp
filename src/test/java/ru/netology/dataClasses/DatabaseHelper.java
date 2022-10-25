package ru.netology.dataClasses;

import java.sql.*;

public class DatabaseHelper {
    private final String username;
    private final String password;
    private final String url;
    public DatabaseHelper() {
        this.username = System.getProperty("username");
        this.password = System.getProperty("password");
        this.url = System.getProperty("url");
    }

    private String getQuery(String table, String status) {
        return "SELECT COUNT(*) AS number FROM " + table + " WHERE status='" + status + "';";
    }

    public int findRecords(String table, String status) {
        String query = this.getQuery(table, status);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt("number");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
