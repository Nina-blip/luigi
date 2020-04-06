package be.vdab.luigi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMain {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8080/luigi?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Brussels";
        String username = "cursist";
        String password = "cursist";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
