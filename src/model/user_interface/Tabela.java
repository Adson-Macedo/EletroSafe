/*
 * A classe tabela armazena elementos do tipo que implementa
 * a interface ListItem
 */
package model.user_interface;

import model.user_interface.exceptions.NotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Adson Macêdo
 */
public class Tabela {
    private ArrayList<String[]> lista = new ArrayList<>();
    private String header;
    private final String [] COL_HEADERS;
    public final int [] COL_SIZES;
    
    //  Construtor
    public Tabela(String header, String[] colHeaders, int [] colSizes){
        this.header = header;
        this.COL_HEADERS = colHeaders;
        this.COL_SIZES = colSizes;
    }
    
    //  Retorna a largura da tabela (soma dos tamanhos das colunas)
    public int getTableWidth(){
        int tam = 0;
        for (int i = 0; i < COL_SIZES.length; i++) {
            tam += COL_SIZES[i];
        }
        
        return tam;
    }

    //  Retorna o título da tabela centralizado, de acordo com a soma das larguras das colunas
    public String getHeader() {
        return header;
    }

    //  Retorna um array com os títulos das colunas
    public String getColHeaders() {
        String colHead = new String();
        
        for (int i = 0; i < COL_SIZES.length; i++) {
            //  formata o espaçamento de acordo com o tamanho da coluna i
            String format = "%-" + COL_SIZES[i] + "s";
            colHead = colHead + String.format(format, COL_HEADERS[i]);
        }

        return colHead;
    }

    //  Retona um arraylist com os itens da tabela
    public ArrayList<String []> getLista(){
        return lista;
    }
    
    public void setLista(ArrayList<String []> lista){
        this.lista = lista;
    }
    
    //  Adiciona um item na tabela
    public void insertItem(String [] item){
        lista.add(item);
    }
    
    //  Retorna um item da tabela através do ID
    public String [] getItemById(Integer id) throws NotFoundException{
        for (String [] item: lista) {
            if (Integer.parseInt(item[0]) == id) 
                return item;
        }
        
        throw new NotFoundException("ITEM NÃO ENCONTRADO");
    }
    
    //  Retorna o tamanho da coluna de totais
    public int getTotalColSize(){
        return COL_SIZES[COL_SIZES.length - 1];
    }

}
