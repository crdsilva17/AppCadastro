/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author cristiano
 */
public class Curso {
    private int id;
    private String nome_do_curso;
    private String nivel;
    private int duracao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_do_curso() {
        return nome_do_curso;
    }

    public void setNome_do_curso(String nome_do_curso) {
        this.nome_do_curso = nome_do_curso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    //O método toString é a representação do objeto em String
    @Override
    public String toString()
    {
        return this.nome_do_curso;
    }
    
    @Override
    public boolean equals(Object objeto)
    {
        Curso c = (Curso) objeto;
        if(this.id == c.getId()){
            return true;
        }else{
            return false;
        }
    }
    
}
