/*
 faz o crud de qualquer classe que herde de tobase e daobase
 */
package bo;

import fw.Data;
import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;
import dao.DAOBase;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class BOFactory {
       
    
    public static long inserir(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.inserir(c, t);
        }finally{
            c.close();
        }
    }
    
    public static void editar(DAOBase d, String t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.editar(c, t);
        }finally{
            c.close();
        }
    }

    public static void excluir(DAOBase d, String t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.excluir(c, t);
        }finally{
            c.close();
        }
    }
    
    
    public static TOBase buscar(DAOBase d, List<Object> u) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscar(c, u);
        }finally{
            c.close();
        }
    }
    
    public static TOBase buscar(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscar(c, t);
        }finally{
            c.close();
        }
    }
    
    public static TOBase buscar(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscar(c);
        }finally{
            c.close();
        }
    }
    
    public static JSONArray listar(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listar(c);
        }finally{
            c.close();
        }
    }
    
    public static int buscarRateio(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscarRateio(c);
        }finally{
            c.close();
        }
    }
    
    
    
//    public static JSONArray listar(DAOBase d, String t) throws Exception {
//        Connection c = null;
//        
//        try{
//            c =  Data.openConnection();
//            return d.listar(c, t);
//        }finally{
//            c.close();
//        }  
//    
//    }   
       

//    public static JSONArray listar(DAOBase d, String dataJson) throws Exception{
//        Connection c = null;
//        
//        try{
//            c =  Data.openConnection();
//            
//            return d.listar(c, dataJson);
//        }finally{
//            c.close();
//        }
//    }

 
    
    
}
