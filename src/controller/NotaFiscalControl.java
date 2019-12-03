/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Dao;
import model.NotaFiscal;
import utils.Utils;

/**
 *
 * @author Adson
 */
public abstract class NotaFiscalControl {

    public static boolean incluirNF(String[] dadosNotaFiscal) {
        if (dadosNotaFiscal == null || dadosNotaFiscal.length < 4) {
            return false;
        }

        for (String dado : dadosNotaFiscal) {
            if (dado == null)
                return false;
        }

        try {
            NotaFiscal notaFiscal = new NotaFiscal(
                    null,                                   //  id
                    dadosNotaFiscal[0],                     //  chave
                    Utils.toCalendar(dadosNotaFiscal[1]),   //  data
                    Double.parseDouble(dadosNotaFiscal[2]), //  valor
                    dadosNotaFiscal[3]                      //  nota_fiscal
            );

            return Dao.insert(notaFiscal);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean alterarNF(String[] dadosNotaFiscal, Integer idNotaFiscal) {
        if (dadosNotaFiscal == null || idNotaFiscal == null || dadosNotaFiscal.length < 4) {
            return false;
        }

        for (String dado : dadosNotaFiscal) {
            if (dado == null)
                return false;
        }

        try {
            NotaFiscal notaFiscal = new NotaFiscal(
                    idNotaFiscal,                           //  id
                    dadosNotaFiscal[0],                     //  chave
                    Utils.toCalendar(dadosNotaFiscal[1]),   //  data
                    Double.parseDouble(dadosNotaFiscal[2]), //  valor
                    dadosNotaFiscal[3]                      //  nota_fiscal
            );

            return Dao.update(notaFiscal);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static String[] obterDadosNotaFiscal(String chave) {
        if (chave == null)
            return null;
        
        String[] dados = null;
        try {
            ArrayList<Object[]> lista = Dao.query(new NotaFiscal(), "chave", chave);
            
            if (lista != null && !lista.isEmpty()) {
                Object[] objectData = lista.get(0);

                dados = new String[]{
                    objectData[4].toString(),
                    objectData[0].toString(),
                    objectData[1].toString(),
                    objectData[2].toString(),
                    objectData[3].toString()
                };
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return dados;
    }

}
