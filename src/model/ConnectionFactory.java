/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 * Classe gerenciadora de conexões
 * @author Adson Macêdo
 */
public abstract class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/eletrosafe?useTimeZone=true&serverTimeZone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";
    
    /**
     * Método para gerar uma conexão
     * @return Connection
     */
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Falha na conexão!", ex);
        }
    }
    
    /**
     * Método para fechar uma conexão
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException{
        if (con != null)
            con.close();
    }
    
    /**
     * Método para fechar uma conexão e uma statement
     * @param con
     * @param stmt
     * @throws SQLException
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException{
        closeConnection(con);
        if (stmt != null)
            stmt.close();
    }
    
    /**
     * Método para fechar uma conexão, um statement e um resultSet
     * @param con
     * @param stmt
     * @param rs
     * @throws SQLException
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException{
        closeConnection(con, stmt);
        if (rs != null)
            rs.close();
    }
        
}
