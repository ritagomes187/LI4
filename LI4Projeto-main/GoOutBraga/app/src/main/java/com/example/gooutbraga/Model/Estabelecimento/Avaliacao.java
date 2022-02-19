package com.example.gooutbraga.Model.Estabelecimento;


/*
    O id da avaliacao nao serve para nada acho eu
    O valor suponho que seja a classificacao de 1..5 ou seja estrelas
    Tem um id que nao percebi para que era por isso nao pus (base de dados)
    Resumi a hora e o dia num LocalDateTime
    O modelo de dominio diz que uma avaliacao tem uma lista de comentarios e na base de dados diz
    que so tem um comentario (o que acho q faz mais sentido)
 */


import java.time.LocalDateTime;

public class Avaliacao {
    private String username;    // username do cliente
    private int estrelas;       // estrelas de 1 a 5
    private String comentario;  // comentario
    private LocalDateTime data; // data da avalicao
    private String preco;       // barato | medio | caro

    public Avaliacao(String username, int estrelas, String comentario, LocalDateTime data, String preco) {
        this.username = username;
        this.estrelas = estrelas;
        this.comentario = comentario;
        this.data = data;
        this.preco = preco;
    }

    public Avaliacao(Avaliacao avaliacao) {
        this.username = avaliacao.getUsername();
        this.estrelas = avaliacao.getEstrelas();
        this.comentario = avaliacao.getComentario();
        this.data = avaliacao.getData();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getEstrelas() {
        return estrelas;
    }
    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return  this.username.equals(avaliacao.getUsername()) &&
                this.estrelas == avaliacao.getEstrelas() &&
                this.comentario.equals(avaliacao.getComentario()) &&
                this.data.equals(avaliacao.getData()) &&
                this.preco.equals(avaliacao.getPreco());
    }


    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        sb.append("\n           Avaliacao do ").append(this.username).append('\n');
        sb.append("                Estrelas: ").append(this.estrelas).append('\n');
        sb.append("                Comentario: ").append(this.comentario).append('\n');
        sb.append("                Data: ").append(this.data).append('\n');
        sb.append("                Preco: ").append(this.preco).append('\n');
        return sb.toString();
    }

    public Avaliacao clone() {
        return new Avaliacao(this);
    }
}
