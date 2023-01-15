/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Curso;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crist
 */
public class CursoDAO {
    private ConnectionFactory conexao;
    private Connection conn;
    
    public CursoDAO(){
        this.conexao = new ConnectionFactory();
        this.conn = this.conexao.getConnection();
    }
    
    public void inserir(Curso curso){
        String sql = "INSERT INTO cursos(nome_do_curso, nivel, duracao) VALUES "
                + "(?, ?, ?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome_do_curso());
            stmt.setString(2, curso.getNivel());
            stmt.setInt(3, curso.getDuracao());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir curso: " + e.getMessage());
        }
        
    }
    
    public void editar(Curso curso)
    {
        String sql = "UPDATE cursos SET nome_do_curso=?, nivel=?, duracao=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome_do_curso());
            stmt.setString(2, curso.getNivel());
            stmt.setInt(3, curso.getDuracao());
            stmt.setInt(4, curso.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao editar curso: " + e.getMessage());
        }
    }
    
    public void excluir(int id)
    {
        String sql = "DELETE FROM cursos WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage());
        }
    }
    
    public Curso getCurso(int id){
        String sql = "SELECT * FROM cursos WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Curso curso = new Curso();
            rs.first();
            curso.setId(id);
            curso.setNome_do_curso(rs.getString("nome_do_curso"));
            curso.setNivel(rs.getString("nivel"));
            curso.setDuracao(rs.getInt("Duracao"));
            return curso;
        }catch(Exception e){
            System.out.println("Erro ao buscar curso: " + e.getMessage());
            return null;
        }
    }
 
    public List<Curso> getCursos()
    {
        String sql = "SELECT * FROM cursos";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Curso> listaCursos = new ArrayList<>();
            //rs.first();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome_do_curso(rs.getString("nome_do_curso"));
                curso.setNivel(rs.getString("nivel"));
                curso.setDuracao(rs.getInt("Duracao"));
                listaCursos.add(curso);
            }
            return listaCursos;
        } catch (Exception e) {
            return null;
        }
    }
    
        public List<Curso> getCursos(String nomeCurso)
    {
        String sql = "SELECT * FROM cursos WHERE nome_do_curso LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeCurso + "%");
            ResultSet rs = stmt.executeQuery();
            List<Curso> listaCursos = new ArrayList<>();
            //rs.first();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome_do_curso(rs.getString("nome_do_curso"));
                curso.setNivel(rs.getString("nivel"));
                curso.setDuracao(rs.getInt("Duracao"));
                listaCursos.add(curso);
            }
            return listaCursos;
        } catch (Exception e) {
            return null;
        }
    }
}
