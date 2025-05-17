package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConexion {
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bibliosocial";
        String user = "admin";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("¡Conexión exitosa!");
        } catch (Exception e) {
            System.out.println("Error en la conexión:");
            e.printStackTrace();
        }
    }
}
