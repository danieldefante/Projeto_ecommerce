/*
 * Classe fabrica, faz o crud de qualquer classe que herde de tobase e daobase
 */
package bo;

import fw.Data;
import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;
import dao.DAOBase;

/**
 *
 * @author Daniel
 */
public class BOFactory {
       
    
    public static long inserir(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnectionJavaDB();
            return d.inserir(c, t);
        }finally{
            c.close();
        }
    }

    
    public static TOBase buscar(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnectionJavaDB();
            
            return d.buscar(c, t);
        }finally{
            c.close();
        }
    }
    
    public static TOBase buscar(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnectionJavaDB();
            
            return d.buscar(c);
        }finally{
            c.close();
        }
    }
    
    public static JSONArray listar(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnectionJavaDB();
            
            return d.listar(c);
        }finally{
            c.close();
        }
    }
    
    public static int buscarRateio(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnectionJavaDB();
            
            return d.buscarRateio(c);
        }finally{
            c.close();
        }
    }
    
}
