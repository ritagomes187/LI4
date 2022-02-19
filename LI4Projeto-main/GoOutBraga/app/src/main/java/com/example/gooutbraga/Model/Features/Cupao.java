package com.example.gooutbraga.Model.Features;

/*
    Nao faz sentido o cupao ter um username associado pq no momento da sua criacao, ou seja, quando
    estao na posse do restaurante/proprietario, nao tem user
    Por isso o restaurante e os clientes tem uma lista de cupoes e o cupoe tem o id do restaurante para
    dps o user conseguir associa lo
    Adicionei uma variavel data que representa a altura que o cupao foi adquirido por um cliente para
    dps perceber se pode ser usado ou nao de acordo com o timer
 */

import java.time.LocalDateTime;

public class Cupao {
    private String idCupao;     // id do cupao
    private int timer;          // validade do cupao em dias
    private String descricao;   // o que o cupao oferece
    private String idRest;      // id do restaurante que forneceu o cupao
    private LocalDateTime data; // data de rececao do cupao pelo cliente

    public Cupao(String idCupao, int timer, String descricao, String idRest) {
        this.idCupao = idCupao;
        this.timer = timer;
        this.descricao = descricao;
        this.idRest = idRest;
        this.data = null;
    }

    public Cupao(Cupao cupao) {
        this.idCupao = cupao.getIdCupao();
        this.timer = cupao.getTimer();
        this.descricao = cupao.getDescricao();
        this.idRest = cupao.getIdRest();
        this.data = cupao.getData();
    }

    public String getIdCupao() {
        return this.idCupao;
    }
    public void setIdCupao(String idCupao) {
        this.idCupao = idCupao;
    }

    public int getTimer() {
        return timer;
    }
    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdRest() {
        return this.idRest;
    }
    public void setIdRest(String idRest) {
        this.idRest = idRest;
    }

    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n           Cupão ").append(this.idCupao).append('\n');
        sb.append("                Descrição: ").append(this.descricao).append('\n');
        sb.append("                Id restaurante: ").append(this.idRest).append('\n');
        sb.append("                Timer: ").append(this.timer).append(" dias").append('\n');
        sb.append("                Data de receção: ");
        if (this.data == null) sb.append("cupão não reclamado.").append('\n');
        else sb.append(this.data).append('\n');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cupao cupao = (Cupao) o;
        return  this.idCupao.equals(cupao.getIdCupao()) &&
                this.timer == cupao.getTimer() &&
                this.descricao.equals(cupao.getDescricao()) &&
                this.idRest.equals(cupao.getIdRest()) &&
                this.data.equals(cupao.getData());
    }

    public Cupao clone() {
        return new Cupao(this);
    }


    /**
     * Verifica se um cupao pode ser usado, ou seja, se não expirou
     * @return true se puder ser usado, false caso contrário
     */
    public boolean validaUso() {
        return data.plusDays(timer).isAfter(LocalDateTime.now());
    }
}