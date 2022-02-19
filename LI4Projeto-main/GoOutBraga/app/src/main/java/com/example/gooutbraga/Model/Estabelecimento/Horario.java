package com.example.gooutbraga.Model.Estabelecimento;

import java.util.Objects;


/*
    Vamos assumir que o horario é igual para todos os dias seg-dom
 */


public class Horario {
    private int abertura;   // hora de abertura
    private int fecho;      // hora de fecho

    public Horario(int abertura, int fecho) {
        this.abertura = abertura;
        this.fecho = fecho;
    }

    public Horario(Horario horario) {
        this.abertura = horario.getAbertura();
        this.fecho = horario.getFecho();
    }

    public int getAbertura() {
        return abertura;
    }
    public void setAbertura(int abertura) {
        this.abertura = abertura;
    }

    public int getFecho() {
        return fecho;
    }
    public void setFecho(int fecho) {
        this.fecho = fecho;
    }

    @Override
    public String toString() {
        return " |" + this.abertura + " : " + this.fecho + "| ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return abertura == horario.abertura && fecho == horario.fecho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abertura, fecho);
    }

    public Horario clone() {
        return new Horario(this);
    }
}
