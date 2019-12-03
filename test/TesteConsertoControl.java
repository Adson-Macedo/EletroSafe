/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.ConsertoControl;
import java.text.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adson
 */
public class TesteConsertoControl {
    
    public TesteConsertoControl() {
    }
    
    @Test
    public void testeCadastrarConserto() throws ParseException{
        String conserto[] = {
            "Teste de conserto",
            "1",
            "1500.00",
            "1",
            "02/12/2019",
            "3"
        };
        
        assertTrue(ConsertoControl.incluirConserto(conserto, 44));
    }

    @Test
    public void testeAlterarConserto() throws ParseException{
        String conserto[] = {
            "Teste de conserto 2.0",
            "1",
            "1500.00",
            "1",
            "02/12/2019",
            "3",
            "44"
        };
        
        assertTrue(ConsertoControl.alterarConserto(conserto, 1));
    }
    
}
