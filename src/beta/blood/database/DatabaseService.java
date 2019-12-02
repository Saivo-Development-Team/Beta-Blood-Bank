/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.database;

import java.sql.*;

/**
 *
 * @author perso
 */
public class DatabaseService {

    private Statement statement;
    private Connection connection;

    private final String user = "root";
    private final String password = "Kueyf2J5Z4Vb";
    private final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/orion";
    
    private static DatabaseService service = null;

    private DatabaseService() {
        setService();
        service = this;
    }
    
    public static DatabaseService databaseService() {
        if (service == null)
            new DatabaseService();
        return service;
    }

    private void setService() {
        try {
            Class.forName(jdbc_driver);
            this.connection = DriverManager.getConnection(url, user, password);
            setStatement(this.connection.createStatement());
        } catch (ClassNotFoundException | SQLException ignored) {

        }
    }

    private void setStatement(Statement statement) {
        this.statement = statement;
    }

    private Statement getStatement() {
        if (this.statement == null) {
            try {
                this.statement = this.connection.createStatement();
            } catch (SQLException ignored) {
            }
        }
        return this.statement;
    }
}
