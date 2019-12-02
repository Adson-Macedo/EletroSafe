/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static Produto consProduto(Integer id) throws SQLException{
        Produto produto = null;
        
        if (id == null)
            return null;
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE id = ?;", 1);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                Calendar dataCompra = Calendar.getInstance();
                dataCompra.setTime(rs.getDate("datacompra"));
            
                produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        dataCompra,
                        rs.getInt("id_loja"),
                        rs.getInt("id_nf"),
                        rs.getInt("id_contr_garantia")
                );
            }else{
                return null;
            }
        } catch (SQLException ex) {
            throw ex;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return produto;
    }
    
    public static ArrayList<Produto> listarProdutos(String descricao) throws SQLException{
        ArrayList<Produto> produtos = new ArrayList();
        
        if (descricao == null)
            return null;
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao = ?;", 1);
            stmt.setString(1, descricao);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Calendar dataCompra = Calendar.getInstance();
                dataCompra.setTime(rs.getDate("datacompra"));
            
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        dataCompra,
                        rs.getInt("id_loja"),
                        rs.getInt("id_nf"),
                        rs.getInt("id_contr_garantia")
                ));
            }
        } catch (SQLException ex) {
            throw ex;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return produtos;
    }

    @Override
    public String[] getFields() {
        return new String []{
            "descricao",
            "valor",
            "dataCompra",
            "id_loja",
            "id_NF",
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
            this.getNotaFiscal().getId(),
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
            Integer.class,
            Integer.class
        };
    }
}
