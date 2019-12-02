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
    public void testeConsultaProduto(){
        String [] produto = ProdutoControl.consultarProduto(44);
        
        for (String str : produto) {
            System.out.println(str);
        }
        
        assertEquals(produto[0], "44");
    }
    
    @Test
    public void testeConsultaProdutos(){
        ArrayList<String []> produtos = ProdutoControl.listarProdutos("Note");
        
        for (String [] produto : produtos) {
            System.out.println(produto[0] + " " + produto[1]);
        }
    }
}
