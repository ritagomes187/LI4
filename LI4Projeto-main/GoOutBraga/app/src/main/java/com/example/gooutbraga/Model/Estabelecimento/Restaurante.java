package com.example.gooutbraga.Model.Estabelecimento;


import com.example.gooutbraga.Model.Features.Cupao;
import com.example.gooutbraga.Model.Features.Localizacao;

import java.util.ArrayList;
import java.util.List;


/*
    N faco a minima ideia do que fazer com os links
    O rank Int não faz sentido, por isso fica rank de maiores consumidores, ou seja,
    uma lista com os nomes dos users q mais consumiram
    Avaliacoes DOUBLE tmb nao faz sentido, fica uma lista de avaliacoes e estrelas que sera uma media das avaliacoes (1..5)
    Como é suposto haver distribuição de cupões, tomei a liberdade de adicionar uma lista de cupoes disponiveis a cada restaurante
 */


public class Restaurante {
    private String id;                  // id do restaurante
    private String nome;                // nome do restaurante
    private Horario horario;            // horario de funcionamento
    private int contacto;               // contacto do restaurante
    private String cardapio;            // cardapio do restaurante
    private String preco;               // barato | medio | caro
    private Localizacao localizacao;    // localizacao do restaurante
    private List<Avaliacao> avaliacoes; // lista de avaliacoes
    private double estrelas;            // estrelas de 0 a 5
    private List<String> rank;          // lista de usernames
    private List<String> comentarios;   // lista de comentarios
    private List<Cupao> cupoes;         // lista de cupoes disponiveis
    // private List<Foto?> fotos;       // ns como é q é suposto termos uma lista de fotos
    // private String link_site;
    // private String link_maps;


    public Restaurante(String id, String nome, Horario horario, int contacto,
                       String cardapio, String preco, Localizacao localizacao,
                       List<Avaliacao> avaliacoes, double estrelas, List<String> rank,
                       List<String> comentarios,List<Cupao> cupoes) {
        this.id = id;
        this.nome = nome;
        this.horario = horario.clone();
        this.contacto = contacto;
        this.cardapio = cardapio;
        this.preco = preco;
        this.localizacao = localizacao.clone();
        this.avaliacoes = new ArrayList<>(avaliacoes);
        this.estrelas = estrelas;
        this.rank = rank;
        this.comentarios = comentarios;
        this.cupoes = new ArrayList<>(cupoes);
    }

    public Restaurante(Restaurante restaurante) {
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.horario = restaurante.getHorario();
        this.contacto = restaurante.getContacto();
        this.cardapio = restaurante.getCardapio();
        this.preco = restaurante.getPreco();
        this.localizacao = restaurante.getLocalizacao();
        this.avaliacoes = restaurante.getAvaliacoes();
        this.estrelas = restaurante.getEstrelas();
        this.rank = restaurante.getRank();
        this.comentarios = restaurante.getComentarios();
        this.cupoes = restaurante.getCupoes();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Horario getHorario() {
        return horario.clone();
    }
    public void setHorario(Horario horario) {
        this.horario = horario.clone();
    }

    public int getContacto() {
        return contacto;
    }
    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getCardapio() {
        return cardapio;
    }
    public void setCardapio(String cardapio) {
        this.cardapio = cardapio;
    }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Localizacao getLocalizacao() {
        return localizacao.clone();
    }
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao.clone();
    }

    public List<Avaliacao> getAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = new ArrayList<>(avaliacoes);
    }

    public double getEstrelas() {
        return estrelas;
    }
    public void setEstrelas(double estrelas) {
        this.estrelas = estrelas;
    }

    public List<String> getRank() {
        return rank;
    }
    public void setRank(List<String> rank) {
        this.rank = rank;
    }

    public List<String> getComentarios() {
        return comentarios;
    }
    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Cupao> getCupoes() {
        return new ArrayList<>(cupoes);
    }
    public void setCupoes(List<Cupao> cupoes) {
        this.cupoes = new ArrayList<>(cupoes);
    }

    public void avaliaRest(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
        comentarios.add(avaliacao.getComentario());

        double estrelas1 = 0;
        int barato = 0,medio = 0,caro = 0;

        for (Avaliacao a: avaliacoes) {
            estrelas1 += a.getEstrelas();
            switch (a.getPreco()) {
                case "barato" -> barato++;
                case "medio" -> medio++;
                case "caro" -> caro++;
            }
        }
        estrelas = estrelas1/avaliacoes.size();

        if (barato >= medio && barato >= caro) preco = "barato";
        if (medio >= barato && medio >= caro) preco = "medio";
        if (caro >= barato && caro >= medio) preco = "caro";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n| Restaurante |").append('\n');
        sb.append("     Id: ").append(this.id).append('\n');
        sb.append("     Nome: ").append(this.nome).append('\n');
        sb.append("     Contacto: ").append(this.contacto).append('\n');
        sb.append("     Cardapio: ").append(this.cardapio).append('\n');
        sb.append("     Preco: ").append(this.preco).append('\n');
        sb.append("     Localizacao: ").append(this.localizacao.toString()).append('\n');
        sb.append("     Avaliacoes: ").append(this.avaliacoes.toString()).append('\n');
        sb.append("     Estrelas: ").append(this.estrelas).append('\n');
        sb.append("     Rank de maiores consumidores: ").append(this.rank).append('\n');
        sb.append("     Cupões disponíveis: ").append(this.cupoes).append('\n');
        return sb.toString();
    }

    public Restaurante clone() {
        return new Restaurante(this);
    }
}