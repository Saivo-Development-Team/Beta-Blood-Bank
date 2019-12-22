/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author perso
 */
public class DatabaseService {

    private Statement statement;
    private Connection connection;

    private final String user = "root";
    private final String password = "";
    private final String jdbc_driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/betablooddatabase";

    private static DatabaseService service = null;

    private DatabaseService() {
        setService();
        service = this;
    }

    public static DatabaseService service() {
        if (service == null) {
            new DatabaseService();
        }
        return service;
    }

    private void setService() {
        try {
            Class.forName(jdbc_driver);
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = this.connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    private Statement getStatement() {
        if (this.statement == null) {
            try {
                this.statement = this.connection.createStatement();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return this.statement;
    }

    public ResultSet executeResultQuery(String query) {
        try {
            return this.getStatement().executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int executeUpdateQuery(String query) {
        try {
            return this.getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
