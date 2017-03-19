/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import javax.ejb.Stateless;
import org.json.JSONArray;
import to.TOBase;

/**
 *
 * @author daniel
 */

@Stateless
public class DAOBase {
    
    protected long idretorno;

    public void inserirIDString(Connection c, String t) throws Exception{
    }    
     
    public long inserir(Connection c, TOBase t) throws Exception{
        return this.idretorno;
    }    
    
    public void editar(Connection c, String t) throws Exception{
    }
    
    public void excluir(Connection c, String t) throws Exception{
    }
    
    public TOBase buscar(Connection c, List<Object> u) throws Exception{
        return null;
    }
    
    public TOBase buscar(Connection c, TOBase t) throws Exception{
        return null;
    }
    
    public TOBase buscar(Connection c) throws Exception{
        return null;
    }
        
    public JSONArray listar(Connection c) throws Exception{
        return null;
    }
    
    public JSONArray listar(Connection c, String t) throws Exception{
        return null;
    }

    //    public JSONArray listar(Connection c, String dataJson) throws Exception{
//        return null;
//    }
    
}
