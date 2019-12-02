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
public class Conserto implements Entity {
    private Integer id;
    private String descricao;
    private Loja lojaAssistencia;
    private Double valor;
    private ContratoGarantia garantia;
    private Calendar data;
    private NotaFiscal notaFiscal;
    
    public Conserto(Integer id){
        this.id = id;
    }

    public Conserto(Integer id, String descricao, Integer idLoja, Double valor, Integer idGarantia, Calendar data, Integer idNotaFiscal) {
        this.id = id;
        this.descricao = descricao;
        this.lojaAssistencia = new Loja(idLoja);
        this.valor = valor;
        this.garantia = new ContratoGarantia(idGarantia);
        this.data = data;
        this.notaFiscal = new NotaFiscal(idNotaFiscal);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Loja getLojaAssistencia() {
        return lojaAssistencia;
    }

    public void setLojaAssistencia(Loja lojaAssistencia) {
        this.lojaAssistencia = lojaAssistencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ContratoGarantia getGarantia() {
        return garantia;
    }

    public void setGarantia(ContratoGarantia garantia) {
        this.garantia = garantia;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    @Override
    public String[] getFields() {
        return new String[]{
            "descricao",
            "id_loja",
            "valor",
            "id_contr_garantia",
            "data",
            "id_NF"
        };
    }

    @Override
    public Object[] getValues() {
        return new Object[]{
            getDescricao(),
            getLojaAssistencia().getId(),
            getValor(),
            getGarantia(),
            getData(),
            getNotaFiscal()
        };
    }

    @Override
    public Class<?>[] getFieldTypes() {
        return new Class<?>[]{
            String.class,
            Integer.class,
            Double.class,
            Integer.class,
            Calendar.class,
            Integer.class
        };
    }

    @Override
    public String getTable() {
        return "conserto";
    }
    
}
