/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.user_interface.Menu;
import model.user_interface.Msg;
import model.user_interface.Tabela;

/**
 *
 * @author Adson
 */
public class SistemaControl {
    private static Menu menuMain;
    private static Menu menuProdutos;
    private static Menu menuLojas;
    
    private static Tabela tbProdutos;
    private static Tabela tbConsertos;
    private static Tabela tbLojas;
    private static Tabela tbContrGarantia;
    private static Tabela tbNotasFiscais;
    
    private static final int MENU_PRODUTOS = 1;
    private static final int MENU_LOJAS = 2;
    
    private static final int CADASTRAR_PRODUTO = 1;
    private static final int ALTERAR_PRODUTO = 2;
    private static final int LISTAR_PRODUTOS = 3;
    private static final int REMOVER_PRODUTO = 4;
    private static final int REGISTRAR_CONSERTO = 5;
    private static final int INCLUIR_NF = 6;
    private static final int HISTORICO_MANUTENCAO = 7;
    
    private static final int CADASTRAR_LOJA = 1;
    private static final int ALTERAR_LOJA = 2;
    private static final int LISTAR_LOJAS = 3;
    private static final int REMOVER_LOJA = 4;
    
    private static void setup(){
        menuMain = new Menu("MENU PRINCIPAL", 
                                new String[]{
                                    "PRODUTOS", 
                                    "LOJAS"
                                });
        
        menuProdutos = new Menu("MENU PRODUTOS", 
                                new String[]{
                                    "CADASTRAR PRODUTO", 
                                    "ALTERAR PRODUTO", 
                                    "LISTAR PRODUTOS", 
                                    "REMOVER PRODUTO", 
                                    "REGISTRAR CONSERTO",
                                    "INCLUIR NOTA FISCAL",
                                    "VER HISTORICO DE MANUTENCAO"                                    
                                });
        
        menuLojas = new Menu("MENU LOJAS", 
                                new String[]{
                                    "CADASTRAR LOJA", 
                                    "ALTERAR LOJA", 
                                    "LISTAR LOJAS", 
                                    "REMOVER LOJA"                                    
                                });

        tbProdutos = new Tabela("LISTA DE PRODUTOS", 
                               new String [] {
                                   "ID",
                                   "DESCRICAO",
                                   "VALOR"
                               },
                               new int[]{11,38,13}
                        );

        tbLojas = new Tabela("LISTA DE LOJAS", 
                               new String [] {
                                   "ID",
                                   "CNPJ",
                                   "NOME"
                               },
                               new int[]{11,20,38}
                        );
    }
       
    private static void listarProdutos(String filtro){
        ArrayList<String []> lista = ProdutoControl.obterListaProdutos(filtro);
        if (lista != null){
            tbProdutos.setLista(lista);
            Msg.printTabela(tbProdutos, false);
            Msg.showMessage("");
        }
    }
    
    private static void listarLojas(String filtro){
        ArrayList<String []> lista = LojaControl.listarLojas(filtro);
        if (lista != null){
            tbLojas.setLista(lista);
            Msg.printTabela(tbLojas, false);
            Msg.showMessage("");
        }
    }

    private static String [] getDadosProduto(){
        Scanner scan = new Scanner(System.in);
        String [] dadosProduto = new String [6];
        
        System.out.print("DESCRICAO: ");
        dadosProduto[0] = scan.nextLine();
        
        System.out.print("VALOR: ");
        dadosProduto[1] = scan.nextLine();

        System.out.print("DATA DA COMPRA (DD/MM/AAAA): ");
        dadosProduto[2] = scan.nextLine();

        listarLojas("");
        
        System.out.print("ID DA LOJA: ");
        dadosProduto[3] = scan.nextLine();
        dadosProduto[4] = "0";
        dadosProduto[5] = "0";
        
        return dadosProduto;
    }
 
    private static void menuLojaExecute(){
        int option;
        
        do{
            option = menuLojas.getMenuOption();
            switch (option){
                case CADASTRAR_LOJA:
                    String [] dadosLoja = getDadosProduto();
                    if (LojaControl.cadastrarLoja(dadosLoja)){
                        Msg.showMessage("LOJA CADASTRADA COM SUCESSO!");
                    }else{
                        Msg.showMessage("FALHA AO CADASTRAR LOJA!");
                    }
                    
                    break;
                case LISTAR_LOJAS:
                    Scanner scan = new Scanner(System.in);
                    System.out.print("DIGITE A LOJA PESQUISADA: ");
                    listarProdutos(scan.nextLine());
                    break;
            }
        } while (option != 0);
    }

    private static void menuProdutoExecute(){
        int option;
        Scanner scan = new Scanner(System.in);
        
        do{
            option = menuProdutos.getMenuOption();
            switch (option){
                case CADASTRAR_PRODUTO:
                    if (ProdutoControl.cadastrarProduto(getDadosProduto())){
                        Msg.showMessage("PRODUTO CADASTRADO COM SUCESSO!");
                    }else{
                        Msg.showMessage("FALHA AO CADASTRAR PRODUTO!");
                    }
                    
                    break;
                case ALTERAR_PRODUTO:
                    System.out.print("PESQUISA (OU <ENTER> PARA CONTINUAR): ");
                    listarProdutos(scan.nextLine());
                    System.out.print("DIGITE O ID DO PRODUTO: ");
                    
                    try{
                        Integer id = Integer.parseInt(scan.nextLine());
                        if (ProdutoControl.alterarProduto(getDadosProduto(), id)){
                            Msg.showMessage("PRODUTO ALTERADO COM SUCESSO!");
                        }else{
                            Msg.showMessage("FALHA AO ALTERAR PRODUTO!");
                        }
                    } catch (NumberFormatException ex){
                        System.out.println(ex.getMessage());
                    }
                    
                    break;
                case LISTAR_PRODUTOS:
                    System.out.print("PESQUISA (OU <ENTER> PARA MOSTRAR TODOS): ");
                    listarProdutos(scan.next());
                    break;
                case REMOVER_PRODUTO:
                    System.out.print("PESQUISA (OU <ENTER> PARA CONTINUAR): ");
                    listarProdutos(scan.nextLine());
                    System.out.print("DIGITE O ID DO PRODUTO: ");
                    
                    try{
                        Integer id = Integer.parseInt(scan.nextLine());
                        if (ProdutoControl.excluirProduto(id)){
                            Msg.showMessage("PRODUTO EXCLUIDO COM SUCESSO!");
                        }else{
                            Msg.showMessage("FALHA AO EXCLUIR PRODUTO!");
                        }
                    } catch (NumberFormatException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case REGISTRAR_CONSERTO:
                    break;
                case INCLUIR_NF:
                    break;
                case HISTORICO_MANUTENCAO:
                    break;
            }
        } while (option != 0);
    }
    
    public static void run(){
        setup();

        int option;
        
        do{
            option = menuMain.getMenuOption();
            switch (option){
                case MENU_PRODUTOS:
                    menuProdutoExecute();
                    break;
                case MENU_LOJAS:
                    menuLojaExecute();
                    break;
            }
        } while (option != 0);
    }
    
}
