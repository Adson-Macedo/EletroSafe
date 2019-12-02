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
public class ContratoGarantia implements Entity {
    private Integer id;
    private Calendar dataVigencia;
    private Double valor;
    private String descricao;
    
    public ContratoGarantia(){
        this.id = null;
    }
    
    public ContratoGarantia(Integer id, Calendar dataVigencia, Double valor, String descricao) {
        this.id = id;
        this.dataVigencia = dataVigencia;
        this.valor = valor;
        this.descricao = descricao;
    }

    public ContratoGarantia(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Calendar getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(Calendar dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String[] getFields() {
        return new String[]{
            "data_vigencia",
            "valor",
            "descricao"
        };
    }

    @Override
    public Object[] getValues() {
        return new Object[]{
            getDataVigencia(),
            getValor(),
            getDescricao()
        };
    }

    @Override
    public Class<?>[] getFieldTypes() {
        return new Class<?>[]{
            Calendar.class,
            Double.class,
            String.class
        };
    }

    @Override
    public String getTable() {
        return "contrato_garantia";
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
