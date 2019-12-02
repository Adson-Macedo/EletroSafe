/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Adson
 */
public class NotaFiscal implements Entity {
    private Integer id;
    private String chave;
    private Calendar data;
    private Double valor;
    private String notaFiscal;
    
    public NotaFiscal(){
        this.id = null;
    }
    
    public NotaFiscal(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public NotaFiscal(Integer id, String chave, Calendar data, Double valor, String notaFiscal) {
        this.id = id;
        this.chave = chave;
        this.data = data;
        this.valor = valor;
        this.notaFiscal = notaFiscal;
    }

    public NotaFiscal(String chave){
        this.chave = chave;
    }
    
    
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    @Override
    public String[] getFields() {
        return new String []{
            "chave",
            "data",
            "valor",
            "nota_fiscal"
        };
    }

    @Override
    public Object[] getValues() {
        return new Object[]{
            getChave(),
            getData(),
            getValor(),
            getNotaFiscal()
        };
    }

    @Override
    public Class<?>[] getFieldTypes() {
        return new Class<?>[]{
            String.class,
            Calendar.class,
            Double.class,
            String.class
        };
    }

    @Override
    public String getTable() {
        return "nota_fiscal";
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
