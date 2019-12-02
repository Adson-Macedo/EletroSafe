/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.text.ParseException;
import model.Dao;
import model.Produto;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Utils;

/**
 *
 * @author Adson
 */
public class TesteDao {

    Produto produto;

    public TesteDao() throws ParseException {
        this.produto = new Produto(null, "Notebook 2.0", 1450.54,Utils.toCalendar("01/12/2019"),1,1,1);
    }
    
    @Test
    public void testeRegProduto() throws SQLException{
        assertTrue(Dao.insert(produto));
        produto.setId(1);
        produto.setDescricao("PC");
        assertTrue(Dao.insert(produto));
    }

    @Test
    public void testeAlterarProduto() throws SQLException{
        produto.setId(2);
        produto.setDescricao("teste de alteração");
        assertTrue(Dao.update(produto));
        produto.setId(null);
        assertFalse(Dao.update(produto));
    }

    @Test
    public void testeDeleteProduto() throws SQLException{
        produto.setId(2);
        assertTrue(Dao.delete(produto));
        produto.setId(null);
        assertFalse(Dao.delete(produto));
    }

    @Test
    public void testeConsProduto() throws SQLException{
        Produto p = Produto.consProduto(1);
        
        //assertNull(p);
    }
}
