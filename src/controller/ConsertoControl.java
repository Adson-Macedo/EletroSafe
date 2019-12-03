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
import utils.Utils;

/**
 *
 * @author Adson
 */
public abstract class ConsertoControl {
    public static boolean incluirConserto(String[] dadosConserto, Integer idProduto) throws ParseException {
        if (dadosConserto == null || idProduto == null || dadosConserto.length < 6) {
            return false;
        }
        
        for (String dado : dadosConserto)
            if (dado == null)
                return false;

        try {
            Conserto conserto = new Conserto(
                    null,
                    dadosConserto[0],
                    Integer.parseInt(dadosConserto[1]),
                    Double.parseDouble(dadosConserto[2]),
                    Integer.parseInt(dadosConserto[3]),
                    Utils.toCalendar(dadosConserto[4]),
                    dadosConserto[5],
                    idProduto
            );

            return Dao.insert(conserto);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean alterarConserto(String[] dadosConserto, Integer idConserto) {
        if (dadosConserto == null || idConserto == null || dadosConserto.length < 7) {
            return false;
        }
        
        for (String dado : dadosConserto)
            if (dado == null)
                return false;

        try {
            Conserto conserto = new Conserto(
                    idConserto,
                    dadosConserto[0],
                    Integer.parseInt(dadosConserto[1]),
                    Double.parseDouble(dadosConserto[2]),
                    Integer.parseInt(dadosConserto[3]),
                    Utils.toCalendar(dadosConserto[4]),
                    dadosConserto[5],
                    Integer.parseInt(dadosConserto[6])
            );

            return Dao.update(conserto);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean removerConserto(Integer id){
        if (id == null)
            return false;
        
        try{
            return Dao.delete(new Conserto(id));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }
    
    public static String [] consultarConserto(Integer id){
        if (id == null)
            return null;
        
        String [] dados = null;
        try{
            Object [] objectData = Dao.query(new Conserto(id));
            
            if (objectData == null)
                return null;
            
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
        if (idProduto == null)
            return null;
        
        ArrayList<String []> produtos = new ArrayList();
        
        try {
            ArrayList<Object []> lista = Dao.query(new Conserto(null), "id_produto", idProduto);
            
            if (lista == null)
                return null;
            
            for (Object [] item : lista){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataConserto = sdf.format(((Calendar)item[4]).getTime());

                produtos.add(new String[]{
                    item[6].toString(), //  id
                    item[0].toString(), //  descricao
                    item[1].toString(), //  id_loja
                    item[2].toString(), //  valor
                    item[3].toString(), //  id_contr_garantia
                    dataConserto,       //  data
                    item[5].toString()  //  id_nf
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return produtos;
    }
}
