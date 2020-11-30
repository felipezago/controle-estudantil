/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
public class CreateDB {
    public static void createDB() throws SQLException{
          
        Connection c = Conexao.getConexao();
        Statement stmt = c.createStatement();

          String sql = "CREATE TABLE IF NOT EXISTS atividades" +
                       "(ID INTEGER PRIMARY KEY autoincrement," +
                       " titulo        CHAR(50)    NOT NULL, " + 
                       " desc    TEXT             NOT NULL   ,"
                  + "    data_prevista  INTEGER       NOT NULL,"
                  + "    status         char(1)   NOT NULL,"
                  + "    id_materia integer,"
                  + "    foreign key(id_materia) references materia(id));"
                  + ""
                  + "   CREATE TABLE IF NOT EXISTS materia("
                  + "   id integer primary key autoincrement,"
                  + "   nome_materia    CHAR(20) NOT NULL,"
                  + "   qtd_max_faltas  integer  not null,"
                  + "   qtd_atual_faltas integer)"
;
                     
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        System.out.println("Tabela criada com sucesso");
    }
    
    public static void createDBteste() throws SQLException{
          
        Connection c = Conexao.getConexao();
        Statement stmt = c.createStatement();

          String sql = "CREATE TABLE IF NOT EXISTS atividades" +
                       "(ID INTEGER PRIMARY KEY autoincrement," +
                       " titulo        CHAR(50)    NOT NULL, " + 
                       " desc    TEXT             NOT NULL   ,"
                  + "    data_prevista  DATETIME       NOT NULL,"
                  + "    status         char(1)   NOT NULL,"
                  + "    id_materia integer,"
                  + "    foreign key(id_materia) references materia(id));"
                  + ""
                  + "   CREATE TABLE IF NOT EXISTS materia("
                  + "   id integer primary key autoincrement,"
                  + "   nome_materia    CHAR(20) NOT NULL);"
;
                     
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        System.out.println("Tabela criada com sucesso");
    }
    
    public static void main(String[] args) {
        try {
            createDB();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
