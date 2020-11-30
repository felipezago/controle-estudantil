package DAO;

import Model.Atividades;
import Model.Materia;
import Util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AtividadesDAO {   
    
    public static void insertDB(Atividades atv) throws SQLException{
        Connection c = Conexao.getConexao();
        Statement stmt = c.createStatement();
        c.setAutoCommit(false);
        
        String sql= "INSERT INTO atividades (titulo, desc, data_prevista, status, id_materia)"
                + "values ('"+atv.getTitulo()+"', '"+atv.getDesc()+"', '"+atv.getData()+"',"
                + "'A', "+atv.getMateria().getId()+")";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }
    
    public static void updateDB(Atividades atv) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt= con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "update atividades set desc = '"+atv.getDesc()+"', "
                + " titulo= '"+atv.getTitulo()+"', data_prevista= '"+atv.getData()+"',"
                + " id_materia= "+atv.getMateria().getId()+" where id = "+atv.getId()+"";
        stmt.executeUpdate(sql);
        stmt.close();
        con.commit();
        con.close();
    }
    
    public static void deleteDB(Atividades atv) throws SQLException{
        Connection c = Conexao.getConexao();
        Statement stmt= c.createStatement();
        c.setAutoCommit(false);
        
        String sql= "delete from atividades where id = "+atv.getId()+"";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
        
    }
    
    public static List<Atividades> pesquisarMateria(Atividades atvPesq) throws SQLException{
        List<Atividades> ativi = new ArrayList<>();
        Connection c= Conexao.getConexao();
        Statement stmt= c.createStatement();
        c.setAutoCommit(false);
        
        String sql = "select * from atividades inner join materia on atividades.id_materia= materia.id where status <> 'F'"
                + "and materia.nome_materia like '%"+atvPesq.getMateria().getNome().toString()+"%'";
        stmt.execute(sql);
        
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            Atividades atividades= new Atividades();
            atividades.setTitulo(rs.getString("titulo"));
            atividades.setDesc(rs.getString("desc"));
            atividades.setId(rs.getInt("id"));
            atividades.setData(rs.getString("data_prevista"));
            atividades.setStatus(rs.getString("status"));
            
            Materia mat= new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            atividades.setMateria(mat);
            
            ativi.add(atividades);
        }
        c.commit();
        stmt.close();
        rs.close();
        c.close();
        
        return ativi;
    }
    
        public static List<Atividades> pesquisar() throws SQLException{
        List<Atividades> ativi = new ArrayList<>();
        Connection c= Conexao.getConexao();
        Statement stmt= c.createStatement();
        c.setAutoCommit(false);
        
        String sql = "select * from atividades inner join materia on atividades.id_materia= materia.id where status <> 'F'";

        stmt.execute(sql);
        
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            Atividades atividades= new Atividades();
            atividades.setTitulo(rs.getString("titulo"));
            atividades.setDesc(rs.getString("desc"));
            atividades.setId(rs.getInt("id"));
            atividades.setData(rs.getString("data_prevista"));
            atividades.setStatus(rs.getString("status"));
            
            Materia mat= new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            atividades.setMateria(mat);
            
            ativi.add(atividades);
        }
        System.out.println(ativi);
        c.commit();
        stmt.close();
        rs.close();
        
        c.close();
        
        
        return ativi;
    }
        
    public static List<Atividades> pesquisarTit(Atividades atvPesq) throws SQLException{
        List<Atividades> ativi = new ArrayList<>();
        Connection c= Conexao.getConexao();
        Statement stmt= c.createStatement();
        c.setAutoCommit(false);
        
        String sql = "select * from atividades inner join materia on atividades.id_materia= materia.id "
                + "where status <> 'F' and titulo like '%"+atvPesq.getTitulo()+"%'";
        stmt.execute(sql);
        
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            Atividades atividades= new Atividades();
            atividades.setTitulo(rs.getString("titulo"));
            atividades.setDesc(rs.getString("desc"));
            atividades.setId(rs.getInt("id"));
            atividades.setData(rs.getString("data_prevista"));
            atividades.setStatus(rs.getString("status"));
            
            Materia mat= new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            atividades.setMateria(mat);
            
            ativi.add(atividades);
        }
        c.commit();
        stmt.close();
        rs.close();
        c.close();
        
        return ativi;
    }
    
    public static List<Atividades> pesquisarStatusAmbAtividades() throws SQLException{
        List<Atividades> ativi = new ArrayList<>();
        Connection c= Conexao.getConexao();
        Statement stmt= c.createStatement();
        c.setAutoCommit(false);
        
        String sql = "select * from atividades inner join materia on atividades.id_materia= materia.id "
                + "where status in ('A', 'F')";
        stmt.execute(sql);
        
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            Atividades atividades= new Atividades();
            atividades.setTitulo(rs.getString("titulo"));
            atividades.setDesc(rs.getString("desc"));
            atividades.setId(rs.getInt("id"));
            atividades.setData(rs.getString("data_prevista"));
            atividades.setStatus(rs.getString("status"));
            
            Materia mat= new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            atividades.setMateria(mat);
            
            ativi.add(atividades);
        }
        c.commit();
        stmt.close();
        rs.close();
        c.close();
        
        return ativi;
    }
    
    public static List<Atividades> pesquisarStatusF() throws SQLException{
        List<Atividades> ativi = new ArrayList<>();
        Connection c= Conexao.getConexao();
        Statement stmt= c.createStatement();
        c.setAutoCommit(false);
        
        String sql = "select * from atividades inner join materia on atividades.id_materia= materia.id "
                + "where status = 'F'";
        stmt.execute(sql);
        
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            Atividades atividades= new Atividades();
            atividades.setTitulo(rs.getString("titulo"));
            atividades.setDesc(rs.getString("desc"));
            atividades.setId(rs.getInt("id"));
            atividades.setData(rs.getString("data_prevista"));
            atividades.setStatus(rs.getString("status"));
            
            Materia mat= new Materia();
            mat.setNome(rs.getString("nome_materia"));
            mat.setId(rs.getInt("id"));
            atividades.setMateria(mat);
            
            ativi.add(atividades);
        }
        
        c.commit();
        stmt.close();
        rs.close();
        c.close();
        
        return ativi;
    }
    
    public static void finalizaAtividade(Atividades atv) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt= con.createStatement();
        con.setAutoCommit(false);
        
        String sql= "update atividades set status = 'F' where id = "+atv.getId()+"";
        stmt.executeUpdate(sql);
        stmt.close();
        con.commit();
        con.close();
    }
    
    public static String prazoAtividade()throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt= con.createStatement();
        con.setAutoCommit(false);
        String lista = null;
        String sql= "select strftime('%d/%m/%Y', 'now', 'localtime') as data;";
        stmt.execute(sql);
        
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            Atividades atv= new Atividades();
            atv.setData(rs.getString("data"));
            lista= atv.getData();
        }
        con.commit();
        stmt.close();
        rs.close();
        con.close();
        
        return lista;
    }
    
    public static List<Atividades> retornaDatas() throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt= con.createStatement();
        con.setAutoCommit(false);
        List<Atividades> lista= new ArrayList<>();
        String sql= "select data_prevista from atividades";
        stmt.execute(sql);
        
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Atividades ati= new Atividades();
            ati.setData(rs.getString("data_prevista"));
            lista.add(ati);
            
        }
        con.commit();
        stmt.close();
        rs.close();
        con.close();
        
        return lista;
        
    }
    
    public static List<Atividades> retornaStatus() throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt= con.createStatement();
        con.setAutoCommit(false);
        List<Atividades> lista= new ArrayList<>();
        String sql= "select status from atividades";
        stmt.execute(sql);
        
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Atividades ati= new Atividades();
            ati.setStatus(rs.getString("status"));
            lista.add(ati);
            
        }
        con.commit();
        stmt.close();
        rs.close();
        con.close();
        
        return lista;
        
    }
    
    
    
    public static void main(String[] args) throws SQLException {
        //retornaDatas();
    }
    
}
