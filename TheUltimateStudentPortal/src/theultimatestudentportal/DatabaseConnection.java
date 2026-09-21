/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

/**
 *
 * @author jorda
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {

        String url = "jdbc:postgresql://localhost:5432/StudentPortal";
        String username = "postgres";
        String password = "Hazard360089?";

        try {
            Connection connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );

            System.out.println("Connected to PostgreSQL.");
            return connection;

        } catch (SQLException error) {
            System.out.println("Connection failed.");
            System.out.println(error.getMessage());
            return null;
        }
    }

    
}
