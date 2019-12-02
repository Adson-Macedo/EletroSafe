/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Adson
 */
public class Produto implements Entity{

    private Integer id;
    private String descricao;
    private Double valor;
    private Calendar dataCompra;
    private Loja lojaCompra;
    private NotaFiscal notaFiscal;
    private ContratoGarantia contratoGarantia;

    public Produto(Integer id, String descricao, Double valor, Calendar dataCompra, Integer idLoja, Integer idNF, Integer idGarantia) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCompra = dataCompra;
        this.lojaCompra = new Loja(idLoja);
        this.notaFiscal = new NotaFiscal(idNF);
        this.contratoGarantia = new ContratoGarantia(idGarantia);
    }

    public Produto(Integer id, String descricao, Double valor, Calendar dataCompra, Integer idLoja, String chaveNF, Integer idGarantia) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCompra = dataCompra;
        this.lojaCompra = new Loja(idLoja);
        this.notaFiscal = new NotaFiscal(chaveNF);
        this.contratoGarantia = new ContratoGarantia(idGarantia);
    }

    public Produto(Integer id) {
        this.id = id;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Loja getLojaCompra() {
        return lojaCompra;
    }

    public void setLojaCompra(Loja lojaCompra) {
        this.lojaCompra = lojaCompra;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public List<Conserto> getHistoricoConsertos() {
        List<Conserto> historicoConsertos = new ArrayList();
        
        return historicoConsertos;
    }


    public ContratoGarantia getContratoGarantia() {
        return contratoGarantia;
    }

    public void setContratoGarantia(ContratoGarantia contratoGarantia) {
        this.contratoGarantia = contratoGarantia;
    }

    @Override
    public String[] getFields() {
        return new String []{
            "descricao",
            "valor",
            "dataCompra",
            "id_loja",
            "chave_NF",
            "id_contr_garantia"
        };
    }

    @Override
    public Object[] getValues() {
        return new Object[]{
            this.getDescricao(),
            this.getValor(),
            this.getDataCompra(),
            this.getLojaCompra().getId(),
            this.getNotaFiscal().getChave(),
            this.getContratoGarantia().getId()
        };
    }

    @Override
    public String getTable() {
        return "produto";
    }

    @Override
    public Class<?>[] getFieldTypes() {
        return new Class<?>[]{
            String.class,
            Double.class,
            Calendar.class,
            Integer.class,
            String.class,
            Integer.class
        };
    }
}
