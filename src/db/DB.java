
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
   
    private static Connection conn = null;
    
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost/projeto-estoque";
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url, "root", "");
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeConnection(){
        try {
            if(conn != null){
              conn.close();  
            }
            
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }
    
     public static void closeStatement(Statement st){
        try {
            if(st != null){
              st.close();  
            }
            
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }
    
      public static void closeResultSet(ResultSet rs){
        try {
            if(rs != null){
              rs.close();  
            }
            
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }
}
