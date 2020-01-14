/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.database;

import beta.blood.Handler;
import static beta.blood.Handler.QueryType.RESULT;
import java.sql.*;
import beta.blood.Handler.Function;
import static beta.blood.Handler.QueryType.UPDATE;
import static beta.blood.Handler.sqlExceptionHandler;

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
    }

    public static DatabaseService service() {
        if (service == null) {
            service = new DatabaseService();
        }
        return service;
    }

    public void setService() {
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

    public void executeResultQuery(String query, Function<ResultSet> function) {
        sqlExceptionHandler().runCatching(getStatement(), RESULT, query, (Function<ResultSet>) (result) -> {
            if (function != null) {
                function.cb(result);
            }
        });
    }

    public void executeUpdateQuery(String query, Function<Integer> function) {
        sqlExceptionHandler().runCatching(getStatement(), UPDATE, query, (Function<Integer>) (result) -> {
            if (function != null) {
                function.cb(result);
            }
        });
    }

    public void executeQuery(String query, Handler.QueryType type, Function function) {
        switch (type) {
            case RESULT:
                executeResultQuery(query, function);
                break;
            case UPDATE:
                executeUpdateQuery(query, function);
                break;
        }
    }

}
