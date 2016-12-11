/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * Deals with database connection and prepared statements
 *
 * @author jolaadeadewale
 */
public class DatabaseConnection {

    private static Connection connection;

    /**
     * Gets connection from connection pool created in Glassfish server
     *
     * @return connection the connection Object
     */
    public static Connection getConnection() throws SQLException {
        try {
            LogData.Log("creating connection", "Database Connection");
            javax.naming.InitialContext ctx = new javax.naming.InitialContext();
            javax.sql.DataSource datasource;
            datasource = (javax.sql.DataSource) ctx.lookup("jdbc/myDatasource");
            connection = datasource.getConnection();
            if(connection == null) {
                System.err.println("The connection is null");
                String url = "jdbc:mysql:us-cdbr-iron-east-04.cleardb.net:3306/heroku_5fff44d305e31ec";
               connection = DriverManager.getConnection(url, 
                "b579248f3101c2", "e9ca812d");
            }
            else{
                System.err.println("The connection is not null");
            }
            
            return connection;
        } catch (NamingException | SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        } 
        
        
        
        throw new NullPointerException("Connection object cannot be null");
        
    }

    /**
     *
     * @param conn database connection object
     * @param query Sql query to be executed
     * @return prepared statement to execute or null
     */
    public static PreparedStatement queryDB(Connection conn, String query) {
        try {
            LogData.Log("creating connection in queryDB", "Database Connection");
            if (conn != null) {
                return conn.prepareStatement(query);
            }
            if (connection != null) {
                return connection.prepareStatement(query);
            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }

        throw new NullPointerException("Connection object cannot be null");
    }

    public static PreparedStatement queryDB(Connection conn, String query, String log) {
        try {
            LogData.Log(log, "query Db");
            if (conn != null) {
                return conn.prepareStatement(query);
            }
            if (connection != null) {
                return connection.prepareStatement(query);
            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }

        throw new NullPointerException("Connection object cannot be null");
    }

    /**
     * log data create connection to database query database
     *
     * @param conn connection object to hold connection
     * @param statement prepared statement to query database
     * @param data data[0] log message, data[1] class data[2] *query* to execute
     * @return PreparedStatement
     */
    public static PreparedStatement initialiseDatabase(Connection conn, PreparedStatement statement, String... data) throws SQLException {
        LogData.Log(data[0], data[1]);
        conn = DatabaseConnection.getConnection();
        statement = DatabaseConnection.queryDB(conn, data[2]);
        return statement;
    } 

    public static void setData(PreparedStatement statement, Object data, String type,
            int position) {
        try {
            switch (type) {
                case "String":
                    statement.setString(position, Utility.parseString(data));
                    break;

                case "Int":
                    statement.setInt(position, Utility.parseNumber(data));
                    break;

                case "Time":
                    statement.setTime(position,
                            Utility.parseTime(Utility.parseString(data)));
                    break;

                case "Date":
                    statement.setDate(position, Utility.parseDate(
                            Utility.parseString(data)));
                    break;

                case "Short":
                    statement.setShort(position, Utility.parseShort(
                            Utility.parseString(data)));
                    break;

                default:
                    throw new IllegalArgumentException(type
                            + " Is not a valid data");
            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }
    }

    public static void closeConnection() {
        LogData.Log("closing connection", "Database Connection");
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }
    }

    public static Object getData(ResultSet result, String type, String data) {
        if (result != null) {
            try {
                switch(type) {
                    case "String" : return result.getString(data);
                    case "Int": result.getInt(data);
                }
                
            } catch (SQLException e) {
                LogData.Log(e.getMessage(), "Results set exception thrown");
            }

        }
        
        throw new IllegalStateException("Result set can not be null");
    }

    public static void closeConnection(Connection conn) {
        LogData.Log("closing connection", "Database Connection");
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }
    }

    public static Connection setAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }

        return conn;
    }

    public static void commit(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }
    }

    public static void rollBack(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "DatabaseConnection");
        }
    }

}
