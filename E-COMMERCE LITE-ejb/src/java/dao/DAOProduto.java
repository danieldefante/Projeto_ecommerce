/*
 * Classe DAO de persistencia de dados
 */
package dao;

import fw.Data;
import to.TOProduto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;

/**
 *
 * @author daniel
 */
public class DAOProduto extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        String sql = "INSERT INTO PRODUTOS(NOME, DESCRICAO, IMAGEM, VALOR_ENTRADA, VALOR_SAIDA) VALUES (?, ?, ?, ?, ?)";
        u.add(((TOProduto)t).getNOME());
        u.add(((TOProduto)t).getDESCRICAO());
        u.add(((TOProduto)t).getIMAGEM());
        u.add(((TOProduto)t).getVALOR_ENTRADA());
        u.add(((TOProduto)t).getVALOR_SAIDA());
 
        //passa por parametros a conexao e a lista de objetos da insercao
        return Data.executeUpdate(c, sql, u);
        
    }
    
    @Override
    public JSONArray listar(Connection c) throws Exception {
        JSONArray  ja = new JSONArray();           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            String sql = null;
            
            sql = "SELECT * FROM PRODUTOS ";

            //Chama a funcao de comunicacao com o banco
            rs = Data.executeQuery(c, sql, u);
            
            //cria uma array de TOProdutos
            while (rs.next()){
                TOProduto tc = new TOProduto(rs);
                ja.put(tc.buscarJson());
            }
            
        }finally{
            rs.close();
        }
        return ja;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}














//
// @Override
//    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
//
//        
//        ResultSet rs = null;
//        
//        try{
//            String sql = null;
//            //variavel sendo convertida para toUsuarios
////            TOEstoque to = ((TOEstoque)t);
//            //variavel com lista dos parametros
//            List<Object> u = new ArrayList<Object>();
//            
//            switch(metodo){
//                case "GET_CULTIVAR":
//                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, e.quantidade, um.grandeza FROM estoque e "
//                            + "INNER JOIN cultivar c ON(c.idcultivar = e.cultivar_idcultivar) "
//                            + "INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida) "
//                            + "WHERE cultivar_idcultivar IN(?) AND unidade_idunidade IN(?)";
//                    u.add(((TOEstoque)t).getCultivar_idcultivar()); 
//                    u.add(((TOEstoque)t).getUnidade_idunidade());
//                    break;
//                default:
//                    sql = "SELECT * FROM estoque "
//                            + "WHERE cultivar_idcultivar IN(?) AND unidade_idunidade IN(?)";
//                    u.add(((TOEstoque)t).getCultivar_idcultivar()); 
//                    u.add(((TOEstoque)t).getUnidade_idunidade());
//                    break;
//            }
//            
//            rs = Data.executeQuery(c, sql, u);
//            
//            
//            if(rs.next()){
//                return new TOEstoque(rs, metodo);
//            }else{
//                return null;
//            }
//        }finally{
//            rs.close();
//        }
//    }
//
//    @Override
//    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
//        //string com o comando sql para editar o banco de dados
//        String sql = null;
//  
//        //variavel sendo convertida para toUsuarios
////        TOEstoque to = (TOEstoque)t;
//        //variavel com lista dos parametros
//        List<Object> u = new ArrayList<Object>();
//        
//        switch(metodo){
//            default:    
//                sql = "INSERT INTO estoque(unidade_idunidade, cultivar_idcultivar, quantidade) VALUES (?, ?, ?)";
//                u.add(((TOEstoque)t).getUnidade_idunidade());
//                u.add(((TOEstoque)t).getCultivar_idcultivar());
//                u.add(((TOEstoque)t).getQuantidade());
//                break;
//        }
//        //passa por parametros a conexao e a lista de objetos da insercao
//        return Data.executeUpdate(c, sql, u);
//    }
//
//    @Override
//    public void editar(Connection c, TOBase t, String metodo) throws Exception {
//        String sql = null;
//        
//        
//        
////        TOEstoque to = (TOEstoque)t;
//        
//        List<Object> p = new ArrayList<Object>();
//        
//        switch(metodo){
//            default:
//                sql = "UPDATE estoque set quantidade = ? WHERE unidade_idunidade IN(?) AND cultivar_idcultivar IN(?)";
//                
//                p.add(((TOEstoque)t).getQuantidade());
//                p.add(((TOEstoque)t).getUnidade_idunidade());
//                p.add(((TOEstoque)t).getCultivar_idcultivar());
//                break;
//        }
//        
//        
//        Data.executeUpdate(c, sql, p);
//    }
//
//    @Override
//    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
//        JSONArray  ja = new JSONArray();           
//        
//        
//        ResultSet rs = null;
//        try{
//            //variavel com lista dos parametros
//            List<Object> u = new ArrayList<Object>();
//            
//            String sql = null;
//            
//            switch(metodo){
//                case "TODOS":
//                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, u.grandeza, e.quantidade, c.nomecultivar FROM estoque e"
//                        + " INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar)"
//                        + " INNER JOIN unidademedida u ON (u.idunidademedida = c.unidademedida_idunidademedida)"
//                        + " WHERE unidade_idunidade IN(?) AND e.quantidade > 0";
//
//                    u.add(((TOEstoque) t).getUnidade_idunidade());
//                    break;
//                case "estoqueunidade_select":
//                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, u.grandeza, e.quantidade, c.nomecultivar FROM estoque e"
//                        + " INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar)"
//                        + " INNER JOIN unidademedida u ON (u.idunidademedida = c.unidademedida_idunidademedida)"
//                        + " WHERE unidade_idunidade IN(?) AND e.quantidade > 0";
//
//                    u.add(((TOEstoque) t).getUnidade_idunidade());
//                    break;
//                default:
//                    sql = "SELECT * FROM estoque";
//                    break;
//            
//            }
//            
//            
//            rs = Data.executeQuery(c, sql, u);
//            
//            while (rs.next()){
//                TOEstoque tc = new TOEstoque(rs, metodo);
//                ja.put(tc.getJson(metodo));
//            }
//            
//        }finally{
//            rs.close();
//        }
//        return ja;
//    }
//    
////    @Override
////    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
////        JSONArray  ja = new JSONArray();           
////        
////        
////        ResultSet rs = null;
////        try{
////            //variavel com lista dos parametros
////            List<Object> u = new ArrayList<Object>();
////            String sql = null;
////            
////            if(metodo.equals("listar_estoqueunidade_select")){
////                sql = "SELECT e.cultivar_idcultivar, c.nomecultivar FROM estoque e "
////                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
////                        + "WHERE unidade_idunidade IN(?) AND e.quantidade > 0";
////
////                u.add(((TOEstoque) t).getUnidade_idunidade());
////                
////            }else if(metodo.equals("listar_estoqueunidade")){
////                sql = "SELECT e.cultivar_idcultivar, c.nomecultivar, e.quantidade, u.grandeza FROM estoque e "
////                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
////                        + " INNER JOIN unidademedida u ON (u.idunidademedida = e.unidademedida_idunidademedida)"
////                        + "WHERE e.unidade_idunidade IN(?)";
////
////                u.add(((TOEstoque) t).getUnidade_idunidade());
////            }
////            rs = Data.executeQuery(c, sql, u);
////            
////            while (rs.next()){
////                TOEstoque tc = new TOEstoque(rs, metodo);
////                ja.put(tc.getJson(metodo));
////            }
////            
////        }finally{
////            rs.close();
////        }
////        return ja;
////    }
//    
//    
//    
//    
//}
