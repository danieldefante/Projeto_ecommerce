/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import to.TOBase;
import to.TOUsuario;

/**
 *
 * @author daniel
 */
public class DAOUsuario extends DAOBase{

    
    @Override
    public TOBase buscar(Connection c, TOBase t) throws Exception {
        
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
             //string com o comando sql para editar o banco de dados
            String sql  = "SELECT USERNAME, EMAIL, NOME, GRUPO FROM USUARIO INNER JOIN PAPEIS ON(USUARIO = USERNAME) WHERE USERNAME IN(?) AND SENHA IN(?)";
                
            u.add(((TOUsuario)t).getUsuario());
            u.add(((TOUsuario)t).getSenha());
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOUsuario(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
        
    }

    
}
