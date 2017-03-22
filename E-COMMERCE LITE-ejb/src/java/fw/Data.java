/*
 Abre o banco de dados, e faz o crud, passando os parametros de configuracao e dados
 */
package fw;

import java.sql.*;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Data {
    
    private static String server = "localhost";
    private static String database = "ecommerce";
    private static String user = "ecommerce";
    private static String password = "admin";
    private static String porta = "1527";
    

    //abrir a conexao banco de dados
    public static Connection openConnectionJavaDB() throws Exception {
        Connection conn = null;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://" + server
                + ":"+porta+"/" + database, user, password);
        return conn;
    }
    
    //executa uma query no banco de dados
    public static ResultSet executeQuery(Connection conn, String query) throws SQLException{
        Statement sta = conn.createStatement();
        ResultSet rs = null;
        try{
            rs = sta.executeQuery(query);
        }catch(Exception err){
            System.err.println("Erro na Pesquisa :" + err.getMessage());
        }
        
        return rs;
    }    
    
    //executa update passando lista
    public static long executeUpdate(Connection conn, String query, List<Object> p) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        //recebe os parametros da query
        int i = 1;
        for (Object o : p){
            pstmt.setObject(i++, retiraInject(o));
        }
        
        pstmt.executeUpdate();
        
        ResultSet rs = pstmt.getGeneratedKeys();

        long idGerado = 0;

        if(rs.next()) {
            idGerado = rs.getLong(1);
        }		
	

        return idGerado;
    }
      
    public static ResultSet executeQuery(Connection conn, String query, List<Object> p )throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query);
        int i = 1;
        
        for (Object o : p){
            pstmt.setObject(i++, retiraInject(o));
        }
        return pstmt.executeQuery();
    }

    private static Object retiraInject(Object o) {
        if( o != null && o.getClass().getCanonicalName().contains("String")){
            String s = (String) o;
            o = s.replaceAll("<", "&lt;").replaceAll(">", "&gt:");
        }
        return o;
    }
    
}
