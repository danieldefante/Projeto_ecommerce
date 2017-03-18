/*
 Abre o banco de dados, e faz o crud
 */
package fw;

import java.sql.*;
import java.util.List;

/**
 *
 * @author Inovação02
 */
public class Data {
    
    //abre uma conexao banco de dados
    public static Connection openConnection () throws Exception{
        return openConnectionPostgre("localhost", "ecommerce", "ecommerce", "admin", "1527"); //sistema rodando fora
    }

    //abrir a conexao banco de dados
    public static Connection openConnectionPostgre(String server, String database, String user, String password, String porta) throws Exception {
        Connection conn = null;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://" + server
                + ":"+porta+"/" + database, user, password);
        return conn;
    }
    //fechar a conexao banco de dados
    public static void closeConnection(Connection con) throws SQLException{
        if(con != null){
            try{
                con.close();
            }catch(SQLException e){
                System.err.println("Error closing connection :" + e.getMessage());
            }
        }
    }
    
    //executa uma query no banco de dados
    public static ResultSet executeQuery(Connection conn, String query) throws SQLException{
        Statement sta = conn.createStatement();
        ResultSet rs = null;
        try{
            rs = sta.executeQuery(query);
        }catch(Exception err){
            System.err.println("Error na Pesquisa :" + err.getMessage());
        }
        
        return rs;
    }
    
    //executa update passando objeto
    public static int executeUpdate(Connection conn, String query, Object[] parametros) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query);
        //recebe os parametros da query
        for (int i = 1; i<= parametros.length; i++){
            pstmt.setObject(i, retiraInject(parametros[i - 1]));
        }
        return pstmt.executeUpdate();
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
        //executa update passando lista
    public static void executeUpdateString(Connection conn, String query, List<Object> p) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        //recebe os parametros da query
        int i = 1;
        for (Object o : p){
            pstmt.setObject(i++, retiraInject(o));
        }
        
        pstmt.executeUpdate();
        
//        ResultSet rs = pstmt.getGeneratedKeys();
//
//        String idGerado = "";
//
//        if(rs.next()) {
//            idGerado = rs.getString(1);
//        }		
	

//        return idGerado;
    }
    //executa update passando conexao e sql
    public static int executeUptade(Connection conn, String query) throws SQLException{
        Statement stm = conn.createStatement();
        return stm.executeUpdate(query);
    }
    
    public static ResultSet executeQuery(Connection conn, String query, Object[] parametros )throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query);
        for (int i = 1; i <= parametros.length; i++){
            pstmt.setObject(i, retiraInject(parametros[i-1]));
        }
        return pstmt.executeQuery();
    }
    
    public static ResultSet executeQuery(Connection conn, String query, Object p )throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setObject(1, retiraInject(p));
        
        return pstmt.executeQuery();
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
    
    public static void closeResultSet(ResultSet rs) throws Exception{
        if( rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                throw new Exception("Error closing ResultSet: " + e.getMessage());
            }
        }
    }
}
