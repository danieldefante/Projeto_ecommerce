/*
 * Classe base e seus metodos sao sobrescrito em suas classes por heranca
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
    

    public long inserir(Connection c, TOBase t) throws Exception{
        return 0;
    }    
    
    public int buscarRateio(Connection c) throws Exception{
        return 0;
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
    
}
