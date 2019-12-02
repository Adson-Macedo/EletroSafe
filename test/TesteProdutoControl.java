/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.ProdutoControl;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adson
 */
public class TesteProdutoControl {
    
    public TesteProdutoControl() {
        
    }
    
    @Test
    public void testeCadastrarProduto(){
        String [] produto = {
            "Celular",
            "899.89",
            "01/12/2019",
            "2",
            "644654SAD654FSD654",
            null
        };
        
        assertFalse(ProdutoControl.cadastrarProduto(produto));
        produto[5] = "0";
        assertTrue(ProdutoControl.cadastrarProduto(produto));
    }

    @Test
    public void testeConsultaProduto(){
        String [] produto = ProdutoControl.consultarProduto(44);
        
        for (String str : produto) {
            System.out.println(str);
        }
        
        assertEquals(produto[0], "44");
    }
    
    @Test
    public void testeConsultaProdutos(){
        ArrayList<String []> produtos = ProdutoControl.obterListaProdutos("Note");
        
        for (String [] produto : produtos) {
            System.out.println(produto[0] + " " + produto[1]);
        }
    }
}
