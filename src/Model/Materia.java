/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author felip
 */
public class Materia {
    private String nome;
    private int qtd_atual_faltas;
    private int qtd_max_faltas;
    private int id;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd_atual_faltas() {
        return qtd_atual_faltas;
    }

    public void setQtd_atual_faltas(int qtd_atual_faltas) {
        this.qtd_atual_faltas = qtd_atual_faltas;
    }

    public int getQtd_max_faltas() {
        return qtd_max_faltas;
    }

    public void setQtd_max_faltas(int qtd_max_faltas) {
        this.qtd_max_faltas = qtd_max_faltas;
    }


    
}
