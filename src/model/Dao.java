/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Adson
 */
public class Dao {
    
    private static String getInsertSql(Entity entity){
        String [] fields = entity.getFields();
        
        String fieldList = " (";
        String valueList = " (";
        
        boolean first = true; 
        for (String fieldName : fields){
            if (!first){
                fieldList += ", ";
                valueList += ", ";
            }
            fieldList += fieldName;
            valueList += "?";
            first = false;
        }
        
        fieldList += ") ";
        valueList += ") ";
        
        return "INSERT INTO " + entity.getTable() + fieldList + " VALUES " + valueList + ";";
    }
    
    private static String getUpdateSql(Entity entity){
        String [] fields = entity.getFields();
        String valueList = " ";
        
        boolean first = true; 
        for (String fieldName : fields){
            if (!first){
                valueList += ", ";
            }
            valueList += fieldName+"=?";
            first = false;
        }
        
        return "UPDATE " + entity.getTable() +" SET " + valueList + " WHERE id = ?;";
    }

    private static void addParams(PreparedStatement stmt, Entity entity) throws SQLException{
        Object [] values = entity.getValues();
        Class<?> [] fieldTypes = entity.getFieldTypes();
        
        for (int i = 0; i < values.length; i++){
            if (values[i] == null){
                //stmt.setNull(i+1, 3);
            }else if (fieldTypes[i] == Integer.class){
                stmt.setInt(i+1, (Integer)values[i]);
            }else if (fieldTypes[i] == String.class){
                stmt.setString(i+1, (String)(values[i]));
            }else if (fieldTypes[i] == Double.class){
                stmt.setDouble(i+1, (Double)values[i]);
            }else if (fieldTypes[i] == Calendar.class){
                stmt.setDate(i+1, new java.sql.Date(((Calendar)values[i]).getTimeInMillis()));
            }
        }
    }
    
    private static Object [] getData(ResultSet rs, Entity entity) throws SQLException{
        String [] fields = entity.getFields();
        Class<?> [] fieldTypes = entity.getFieldTypes();
        
        Object [] data = new Object[fields.length+1];
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null){
                data[i] = null;
            }else if (fieldTypes[i] == Integer.class){
                data[i] = rs.getInt(fields[i]);
            }else if (fieldTypes[i] == String.class){
                data[i] = rs.getString(fields[i]);
            }else if (fieldTypes[i] == Double.class){
                data[i] = rs.getDouble(fields[i]);
            }else if (fieldTypes[i] == Calendar.class){
                data[i] = Calendar.getInstance();
                ((Calendar)data[i]).setTime(rs.getDate(fields[i]));
            }
        }
        
        data[fields.length] = entity.getId();
        
        return data;
    }
    
    public static boolean insert(Entity entity) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = getInsertSql(entity);
            
            stmt = con.prepareStatement(sql, 1);
            addParams(stmt, entity);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return true;
    }

    public static boolean update(Entity entity) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String [] fields = entity.getFields();
        
        if (entity.getId() == null)
            return false;
        
        try {
            String sql = getUpdateSql(entity);
            
            stmt = con.prepareStatement(sql, 1);
            addParams(stmt, entity);
            stmt.setInt(fields.length+1, entity.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return true;
    }
    
    public static boolean delete(Entity entity) throws SQLException{
        if (entity.getId() == null)
            return false;

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM " + entity.getTable() + " WHERE id = ?;", 1);
            stmt.setInt(1, entity.getId());
            stmt.execute();
        } catch (SQLException ex) {
            throw ex;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return true;
    }
    
    public static Object [] query(Entity entity) throws SQLException{
        if (entity.getId() == null)
            return null;
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        Object [] data = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM " + entity.getTable()+ " WHERE id = ?;", 1);
            stmt.setInt(1, entity.getId());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                data = getData(rs, entity);
            }
        } catch (SQLException ex) {
            throw ex;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return data;
    }
    
    public static ArrayList<Object []> query(Entity entity, String columnFilter, Object filter) throws SQLException{
        ArrayList<Object[]> lista = new ArrayList();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql;
            if (filter.getClass() == String.class){
                stmt = con.prepareStatement("SELECT * FROM " + entity.getTable() + " WHERE " + columnFilter + " like '" + filter + "%'", 1);
            }else if (filter.getClass() == Integer.class){
                stmt = con.prepareStatement("SELECT * FROM " + entity.getTable() + " WHERE " + columnFilter + " = " + filter, 1);
            }else{
                return null;
            }
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                entity.setId(rs.getInt("id"));
                lista.add (getData(rs, entity));
            }
            
        } catch (SQLException ex) {
            throw ex;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return lista;
    }
}
