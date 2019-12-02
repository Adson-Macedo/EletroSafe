/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adson
 */
public interface Entity {
    public String [] getFields();
    public Object [] getValues();
    public Class<?> [] getFieldTypes();
    public String getTable();
    public Integer getId();
    public void setId(Integer id);
}
