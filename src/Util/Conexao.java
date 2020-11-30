/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *
 * @author felip
 */
public class Conexao{
    public static Connection getConexao()throws SQLException{
                  
        try {
          Class.forName("org.sqlite.JDBC");
          return(Connection) DriverManager.getConnection("jdbc:sqlite:Atividades.db");
        }catch(ClassNotFoundException ex){
          throw new SQLDataException(ex.getMessage());
        }
            
    }
    
    public static void main(String[] args) {
        try{
            getConexao();
            System.out.println("Conexão estabelecida");
        }catch(SQLException ex){
            System.out.println("Conexao não realizada, verificar log.");
            ex.printStackTrace();
        }
    }
}
