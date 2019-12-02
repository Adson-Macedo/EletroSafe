/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import model.Conserto;
import model.Dao;
import model.Produto;
import utils.Utils;

/**
 *
 * @author Adson
 */
public abstract class ConsertoControl {
    public static boolean cadastrarConserto(String[] dadosConserto) throws ParseException {
        if (dadosConserto.length < 7) {
            return false;
        }

        try {
            Conserto conserto = new Conserto(
                    null,
                    dadosConserto[0],
                    Integer.parseInt(dadosConserto[1]),
                    Double.parseDouble(dadosConserto[2]),
                    Integer.parseInt(dadosConserto[3]),
                    Utils.toCalendar(dadosConserto[4]),
                    Integer.parseInt(dadosConserto[5])
            );

            return Dao.insert(conserto);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean alterarConserto(String[] dadosConserto) {
        if (dadosConserto.length < 7) {
            return false;
        }

        try {
            Conserto conserto = new Conserto(
                    Integer.parseInt(dadosConserto[0]),
                    dadosConserto[1],
                    Integer.parseInt(dadosConserto[2]),
                    Double.parseDouble(dadosConserto[3]),
                    Integer.parseInt(dadosConserto[4]),
                    Utils.toCalendar(dadosConserto[5]),
                    Integer.parseInt(dadosConserto[6])
            );

            return Dao.update(conserto);
        } catch (ParseException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean excluirConserto(Integer id){
        try{
            return Dao.delete(new Conserto(id));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }
    
    public static String [] consultarConserto(Integer id){
        String [] dados = null;
        try{
            Object [] objectData = Dao.query(new Conserto(id));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataConserto = sdf.format(((Calendar)objectData[4]).getTime());

            dados = new String []{
                objectData[6].toString(),
                objectData[0].toString(),
                objectData[1].toString(),
                objectData[2].toString(),
                objectData[3].toString(),
                dataConserto,
                objectData[5].toString()
            };
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }        
        
        return dados;
    }
    
    public static ArrayList<String []> listarConsertos(Integer idProduto){
        ArrayList<String []> produtos = new ArrayList();
        
        try {
            ArrayList<Object []> lista = Dao.query(new Conserto(null), "id_produto", idProduto);
            
            for (Object [] item : lista){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataConserto = sdf.format(((Calendar)item[4]).getTime());

                produtos.add(new String[]{
                    item[6].toString(),
                    item[0].toString(),
                    item[1].toString(),
                    item[2].toString(),
                    item[3].toString(),
                    dataConserto,
                    item[5].toString()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return produtos;
    }
}
