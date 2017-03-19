/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import to.TOBase;
import to.TORateio;

/**
 *
 * @author daniel
 */
public class DAORateio extends DAOBase{

    @Override
    public TOBase buscar(Connection c) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT SUM(VALOR_ENTRADA) AS VALOR_TOTAL, COUNT(ID) AS QTD_PRODUTOS FROM PRODUTOS";
        
        try{
            
            rs = Data.executeQuery(c, sql);
            
            if(rs.next()){
                return new TORateio(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
    
    
}
