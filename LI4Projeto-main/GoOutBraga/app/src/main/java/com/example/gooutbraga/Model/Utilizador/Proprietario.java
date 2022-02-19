package com.example.gooutbraga.Model.Utilizador;

import java.util.Objects;

public class Proprietario extends Model.Utilizador.User {
    private final String idRest; // id do restaurante

    public Proprietario(String username, String nome, String password, String idRest) {
        super(username, nome,password);
        this.idRest = idRest;
    }

    public Proprietario(Proprietario proprietario) {
        super(proprietario);
        this.idRest = proprietario.getIdRest();
    }

    public String getIdRest() {
        return idRest;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("     Id restaurante: ").append(this.idRest).append('\n');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Proprietario that = (Proprietario) o;
        return Objects.equals(idRest, that.idRest);
    }

    public Proprietario clone() {
        return new Proprietario(this);
    }
}