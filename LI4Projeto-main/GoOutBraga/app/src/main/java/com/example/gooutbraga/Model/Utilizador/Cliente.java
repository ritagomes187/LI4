package com.example.gooutbraga.Model.Utilizador;

import com.example.gooutbraga.Model.Features.Cupao;
import com.example.gooutbraga.Model.Features.Favorito;
import com.example.gooutbraga.Model.Features.Localizacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
    Número de favoritos não faz sentido uma vez que temos uma lista de favoritos, assim como o numero de cupoes
    Não percebi os gostos culinários, fica de fora para já
    Adicionei uma lista de cupoes para o cliente, acho que nao é feito referencia a isso
 */


public class Cliente extends Model.Utilizador.User {
    private int idade;                  // idade do cliente
    private Localizacao localizacao;    // localização do cliente
    private int nrAchievements;         // numero de achivs
    private List<Favorito> favoritos;   // lista de favoritos
    private String email;               // email do cliente
    private List<Cupao> cupoes;         // cupoes do cliente

    public Cliente(String username, String nome, String password,
                   int idade, Localizacao localizacao, int nrAchievements,
                   List<Favorito> favoritos, String email, List<Cupao> cupoes) {
        super(username,nome,password);
        this.idade = idade;
        this.localizacao = localizacao.clone();
        this.nrAchievements = nrAchievements;
        this.favoritos = favoritos;
        this.email = email;
        this.cupoes = new ArrayList<>(cupoes);
    }

    public Cliente(Cliente cliente) {
        super(cliente);
        this.idade = cliente.getIdade();
        this.localizacao = cliente.getLocalizacao();
        this.nrAchievements = cliente.getNrAchievements();
        this.favoritos = cliente.getFavoritos();
        this.email = cliente.getEmail();
        this.cupoes = cliente.getCupoes();
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Localizacao getLocalizacao() {
        return localizacao.clone();
    }
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao.clone();
    }

    public int getNrAchievements() {
        return nrAchievements;
    }
    public void setNrAchievements(int nrAchievements) {
        this.nrAchievements = nrAchievements;
    }

    public List<Favorito> getFavoritos() {
        return new ArrayList<>(favoritos);
    }
    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = new ArrayList<>(favoritos);
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cupao> getCupoes() {
        return new ArrayList<>(cupoes);
    }
    public void setCupoes(List<Cupao> cupoes) {
        this.cupoes = new ArrayList<>(cupoes);
    }

    /**
     * Retorna true se o User está atualmente numa localizacao e false caso não esteja
     * @param l localizacao
     * @return true se estiver na localizacao, false caso contrário
     */
    public boolean isInLocation(Localizacao l) {
        return this.localizacao.equals(l);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("     Idade: ").append(this.idade).append('\n');
        sb.append("     Localização: ").append(this.localizacao.toString()).append('\n');
        sb.append("     Número de achievements: ").append(this.nrAchievements).append('\n');
        sb.append("     Restaurantes favoritos: ").append(this.favoritos.toString()).append('\n');
        sb.append("     Email: ").append(this.email).append('\n');
        sb.append("     Cupões: ").append(this.cupoes.toString()).append('\n');
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return  idade == cliente.getIdade() &&
                nrAchievements == cliente.getNrAchievements() &&
                Objects.equals(localizacao, cliente.localizacao) &&
                Objects.equals(favoritos, cliente.favoritos) &&
                email.equals(cliente.getEmail()) &&
                cupoes.equals(cliente.getCupoes());
    }


    public Cliente clone() {
        return new Cliente(this);
    }
}