/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Dao;
import model.Loja;

/**
 *
 * @author Adson
 */
public abstract class LojaControl {
    public static boolean cadastrarLoja(String[] dadosLoja) {
        if (dadosLoja.length < 4) {
            return false;
        }
        
        for (String dado : dadosLoja) {
            if (dado == null)
                return false;
        }

        try {
            Loja loja = new Loja(
                    null,
                    dadosLoja[0],
                    dadosLoja[1],
                    dadosLoja[2],
                    dadosLoja[3]
            );

            return Dao.insert(loja);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean alterarLoja(String[] dadosLoja) {
        if (dadosLoja.length < 5) {
            return false;
        }

        for (String dado : dadosLoja) {
            if (dado == null)
                return false;
        }

        try {
            Loja loja = new Loja(
                    null,
                    dadosLoja[1],
                    dadosLoja[2],
                    dadosLoja[3],
                    dadosLoja[4]
            );

            return Dao.update(loja);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean excluirLoja(Integer id){
        if (id == null)
            return false;
        
        try{
            return Dao.delete(new Loja(id));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }
    
    public static String [] consultarLoja(Integer id){
        if (id == null)
            return null;
        
        String [] dados = null;
        try{
            Object [] objectData = Dao.query(new Loja(id));
            
            dados = new String []{
                objectData[4].toString(),
                objectData[0].toString(),
                objectData[1].toString(),
                objectData[2].toString(),
                objectData[3].toString()
            };
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }        
        
        return dados;
    }
    
    public static ArrayList<String []> listarLojas(String filtro){
        if (filtro == null)
            return null;
        
        ArrayList<String []> lojas = new ArrayList();
        
        try {
            ArrayList<Object []> lista = Dao.query(new Loja(null), "nome", filtro);
            for (Object [] item : lista){
                lojas.add(new String[]{
                    item[4].toString(),
                    item[0].toString(),
                    item[1].toString(),
                    item[2].toString(),
                    item[3].toString()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return lojas;
    }

}
