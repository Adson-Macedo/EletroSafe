/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import model.ContratoGarantia;
import model.Dao;
import utils.Utils;

/**
 *
 * @author Adson
 */
public abstract class ContratoGarantiaControl {

    public static boolean incluirContrato(String[] dadosGarantia) {
        if (dadosGarantia == null || dadosGarantia.length < 3) {
            return false;
        }

        for (String dado : dadosGarantia) {
            if (dado == null) {
                return false;
            }
        }

        try {
            ContratoGarantia garantia = new ContratoGarantia(
                    null,
                    Utils.toCalendar(dadosGarantia[0]),
                    Double.parseDouble(dadosGarantia[1]),
                    dadosGarantia[2]
            );

            return Dao.insert(garantia);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean alterarContrato(String[] dadosGarantia, Integer idGarantia) {
        if (dadosGarantia == null || idGarantia == null || dadosGarantia.length < 3) {
            return false;
        }

        for (String dado : dadosGarantia) {
            if (dado == null) {
                return false;
            }
        }

        try {
            ContratoGarantia garantia = new ContratoGarantia(
                    idGarantia,
                    Utils.toCalendar(dadosGarantia[0]),
                    Double.parseDouble(dadosGarantia[1]),
                    dadosGarantia[2]
            );

            return Dao.update(garantia);
        } catch (ParseException | SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean removerContrato(Integer id) {
        if (id == null) {
            return false;
        }

        try {
            return Dao.delete(new ContratoGarantia(id));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static String[] obterDadosContratoGarantia(Integer id) {
        if (id == null) {
            return null;
        }

        String[] dados = null;
        try {
            Object[] objectData = Dao.query(new ContratoGarantia(id));
            
            if (objectData == null)
                return null;
            
            dados = new String[]{
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

}
