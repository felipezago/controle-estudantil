/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Materia;
import Util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
    
/**
 *
 * @author felip
 */
public class MateriaDAO {
    public static void insertDB(Materia materia) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "insert into materia(nome_materia, qtd_max_faltas, qtd_atual_faltas) "
                + "values ('"+materia.getNome()+"', "+materia.getQtd_max_faltas()+", 0);";
        stmt.executeUpdate(sql);
        stmt.close();
        con.commit();
        con.close();
    }
    
    public static void updateDB(Materia materia) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "update materia set nome_materia = '"+materia.getNome()+"', qtd_max_faltas= "+materia.getQtd_max_faltas()+" "
                + "where id = "+materia.getId()+"";
        stmt.executeUpdate(sql);
        
        stmt.close();
        con.commit();
        con.close();
    }
    
    public static void deleteDB(Materia materia) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "delete from materia where id= "+materia.getId()+"";
        stmt.executeUpdate(sql);
        
        stmt.close();
        con.commit();
        con.close();
    }
    
    public static List<Materia> pesquisa(Materia materia) throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia "
                + "where nome_materia like '%"+materia.getNome()+"%';");
        while(rs.next()){
            Materia mat = new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            
            lista.add(mat);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
    }
    
    public static List<Materia> pesquisarCombo() throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia");
        while(rs.next()){
            Materia mat = new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            lista.add(mat);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
    }
    
       public static List<Materia> selectDB(Materia mat) throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materiaa "
                + "where nome_materia like '%"+mat.getNome()+"%';");
        while(rs.next()){
            Materia mate = new Materia();
            mate.setNome(rs.getString("nome_materia"));
            mate.setId(rs.getInt("id"));
            mate.setQtd_max_faltas(rs.getInt("qtd_max_faltas"));
            
            lista.add(mate);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
        
    }
    
    public static void adicionarFaltas(int faltas, int id) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "update materia set qtd_atual_faltas = qtd_atual_faltas + "+faltas+" where id = "+id+"";
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();
        con.close();
        
    }
    
    public static void removerFaltas(int faltas, int id) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "update materia set qtd_atual_faltas = qtd_atual_faltas - "+faltas+" where id = "+id+"";
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();
        con.close();
        
    }
    
    
    
    public static List<Materia> pesquisarComboFaltas() throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia");
        while(rs.next()){
            Materia mat = new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            lista.add(mat);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
    }
    
    public static List<Materia> selectDB() throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia");
        
        while(rs.next()){
            Materia mate = new Materia();
            int max, atual;
            max = rs.getInt("qtd_max_faltas");
            atual= rs.getInt("qtd_atual_faltas");
            
            mate.setId(rs.getInt("id"));
            mate.setQtd_max_faltas(rs.getInt("qtd_max_faltas"));
            mate.setQtd_atual_faltas(rs.getInt("qtd_atual_faltas"));
            if(atual > max){
                mate.setNome(rs.getString("nome_materia").concat(" (Reprovado)"));
            }else{
                mate.setNome(rs.getString("nome_materia"));
            }

            
            lista.add(mate);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
        
    }
    
    public static List<Materia> pesquisa(String sql) throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia where nome_materia= '"+sql+"'");
        while(rs.next()){
            Materia mate = new Materia();
            int max, atual;
            max = rs.getInt("qtd_max_faltas");
            atual= rs.getInt("qtd_atual_faltas");
            
            mate.setId(rs.getInt("id"));
            mate.setQtd_max_faltas(rs.getInt("qtd_max_faltas"));
            mate.setQtd_atual_faltas(rs.getInt("qtd_atual_faltas"));
            if(atual > max){
                mate.setNome(rs.getString("nome_materia").concat(" (Reprovado)"));
            }else{
                mate.setNome(rs.getString("nome_materia"));
            }
            
            lista.add(mate);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
        
    }
    
    
    public static List<Materia> ordenarMat() throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia order by nome_materia asc");
        while(rs.next()){
            Materia mate = new Materia();
            int max, atual;
            max = rs.getInt("qtd_max_faltas");
            atual= rs.getInt("qtd_atual_faltas");
            
            mate.setId(rs.getInt("id"));
            mate.setQtd_max_faltas(rs.getInt("qtd_max_faltas"));
            mate.setQtd_atual_faltas(rs.getInt("qtd_atual_faltas"));
            if(atual > max){
                mate.setNome(rs.getString("nome_materia").concat(" (Reprovado)"));
            }else{
                mate.setNome(rs.getString("nome_materia"));
            }
            
            lista.add(mate);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
    }
    
    public static List<Materia> ordenarQtMax() throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia order by qtd_max_faltas asc");
        while(rs.next()){
            Materia mate = new Materia();
            int max, atual;
            max = rs.getInt("qtd_max_faltas");
            atual= rs.getInt("qtd_atual_faltas");
            
            mate.setId(rs.getInt("id"));
            mate.setQtd_max_faltas(rs.getInt("qtd_max_faltas"));
            mate.setQtd_atual_faltas(rs.getInt("qtd_atual_faltas"));
            if(atual > max){
                mate.setNome(rs.getString("nome_materia").concat(" (Reprovado)"));
            }else{
                mate.setNome(rs.getString("nome_materia"));
            }
            
            lista.add(mate);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
    }
    
    public static List<Materia> ordenarQtAtual() throws SQLException{
        List<Materia> lista= new ArrayList<>();
        
        Connection con = Conexao.getConexao();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        
        ResultSet rs= stmt.executeQuery("select * from materia order by qtd_atual_faltas asc");
        while(rs.next()){
            Materia mate = new Materia();
            int max, atual;
            max = rs.getInt("qtd_max_faltas");
            atual= rs.getInt("qtd_atual_faltas");
            
            mate.setId(rs.getInt("id"));
            mate.setQtd_max_faltas(rs.getInt("qtd_max_faltas"));
            mate.setQtd_atual_faltas(rs.getInt("qtd_atual_faltas"));
            if(atual > max){
                mate.setNome(rs.getString("nome_materia").concat(" (Reprovado)"));
            }else{
                mate.setNome(rs.getString("nome_materia"));
            }
            
            lista.add(mate);
        }
        rs.close();
        stmt.close();
        con.commit();
        con.close();
        
        return lista;
    }
}
