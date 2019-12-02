/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adson
 */
public class Loja implements Entity{
    private Integer id;
    private String cnpj;
    private String nome;
    private String telefone;
    private String site;

    public Loja(Integer id, String cnpj, String nome, String telefone, String site) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.site = site;
    }

    public Loja(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String[] getFields() {
        return new String []{
            "cnpj",
            "nome",
            "telefone",
            "site"
        };
    }

    @Override
    public Object[] getValues() {
        return new Object[]{
            getNome(),
            getCnpj(),
            getTelefone(),
            getSite(),
        };
    }

    @Override
    public Class<?>[] getFieldTypes() {
        return new Class<?>[]{
            String.class,
            String.class,
            String.class,
            String.class
        };
    }

    @Override
    public String getTable() {
        return "loja";
    }
    
    
}
