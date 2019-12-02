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
import model.Dao;
import model.Produto;
import utils.Utils;

/**
 *
 * @author Adson
 */
public abstract class ProdutoControl {

    public static boolean cadastrarProduto(String[] dadosProduto) {
        if (dadosProduto.length < 6) {
            return false;
        }
        
        for (String dado : dadosProduto) {
            if (dado == null)
                return false;
        }

        try {
            Produto produto = new Produto(
                    null,                                   //  id
                    dadosProduto[0],                        //  descricao
                    Double.parseDouble(dadosProduto[1]),    //  valor
                    Utils.toCalendar(dadosProduto[2]),      //  data_compra
                    Integer.parseInt(dadosProduto[3]),      //  id_loja
                    dadosProduto[4],                        //  chave_NF
                    Integer.parseInt(dadosProduto[5])       //  id_contr_garantia
            );

            return Dao.insert(produto);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean alterarProduto(String[] dadosProduto, Integer id) {
        if (id == null || dadosProduto == null || dadosProduto.length < 6) {
            return false;
        }

        for (String dado : dadosProduto) {
            if (dado == null)
                return false;
        }

        try {
            Produto produto = new Produto(
                    id,
                    dadosProduto[0],
                    Double.parseDouble(dadosProduto[1]),
                    Utils.toCalendar(dadosProduto[2]),
                    Integer.parseInt(dadosProduto[3]),
                    dadosProduto[4],
                    Integer.parseInt(dadosProduto[5])
            );

            return Dao.update(produto);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean excluirProduto(Integer id){
        if (id == null)
            return false;
        
        try{
            return Dao.delete(new Produto(id));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }
    
    public static String [] consultarProduto(Integer id){
        if (id == null)
            return null;

        String [] dados = null;
        try{
            Object [] objectData = Dao.query(new Produto(id));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataCompra = sdf.format(((Calendar)objectData[2]).getTime());

            dados = new String []{
                objectData[6].toString(),
                objectData[0].toString(),
                objectData[1].toString(),
                dataCompra,
                objectData[3].toString(),
                objectData[4].toString(),
                objectData[5].toString()
            };
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }        
        
        return dados;
    }
    
    public static ArrayList<String []> obterListaProdutos(String filtro){
        if (filtro == null)
            return null;
        
        ArrayList<String []> produtos = new ArrayList();
        
        try {
            ArrayList<Object []> lista = Dao.query(new Produto(null), "descricao", filtro);
            for (Object [] item : lista){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataCompra = sdf.format(((Calendar)item[2]).getTime());

                produtos.add(new String[]{
                    item[6].toString(),
                    item[0].toString(),
                    item[1].toString(),
                    dataCompra,
                    item[3].toString(),
                    item[4].toString(),
                    item[5].toString()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return produtos;
    }
}
