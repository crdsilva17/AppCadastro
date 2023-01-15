/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Aluno;
import beans.Curso;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author crist
 */
public class AlunoDAO {
    
    private ConnectionFactory conexao;
    private Connection conn;
    
    public AlunoDAO(){
        this.conexao = new ConnectionFactory();
        this.conn = this.conexao.getConnection();
    }
    
    public void inserir(Aluno aluno){
        String sql = "INSERT INTO alunos(nomealuno, cursoid) VALUES "
                + "(?, ?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNomealuno());
            stmt.setInt(2, aluno.getCursoid().getId());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
        
    }
    
    public void editar(Aluno aluno)
    {
        String sql = "UPDATE alunos SET nomealuno=?, cursoid=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNomealuno());
            stmt.setInt(2, aluno.getCursoid().getId());
            stmt.setInt(3, aluno.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao editar aluno: " + e.getMessage());
        }
    }
    
    public void excluir(int id)
    {
        String sql = "DELETE FROM alunos WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
    
    public Aluno getAluno(int id)
    {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Aluno aluno = new Aluno();
            rs.first();
            aluno.setId(id);
            aluno.setNomealuno(rs.getString("nomealuno"));
            Curso cursoid = new Curso();
            cursoid.setId(rs.getInt("cursoid"));
            aluno.setCursoid(cursoid);
            return aluno;
        }catch(Exception e){
            System.out.println("Erro ao buscar curso: " + e.getMessage());
            return null;
        }
    }
    
    public List<Aluno> getAlunos()
    {
        String sql = "SELECT alunos.id as id, nomealuno, cursoid, nome_do_curso FROM "
                + "alunos INNER JOIN cursos ON alunos.cursoid = cursos.id";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Aluno> lista = new ArrayList<>();
            while(rs.next()){
                Aluno aluno = new Aluno();
                Curso curso = new Curso();
                
                aluno.setId(rs.getInt("id"));
                aluno.setNomealuno(rs.getString("nomealuno"));
                curso.setId(rs.getInt("cursoid"));
                curso.setNome_do_curso(rs.getString("nome_do_curso"));
                aluno.setCursoid(curso);
                
                lista.add(aluno);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
