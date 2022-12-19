package ConfigPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roni
 */
public class Config {
    private static Connection connection;
    public static Connection configDataBase()throws SQLException{
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost/anterinkirimin", "root", "");
        } catch (SQLException e) {
            Logger.getLogger("Koneksi gagal"+e.getMessage());
        }
        return connection;
    }
}
