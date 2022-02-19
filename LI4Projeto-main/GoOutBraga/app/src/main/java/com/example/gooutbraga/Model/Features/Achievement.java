package com.example.gooutbraga.Model.Features;

public class Achievement {
    private String idAchiev; // MUDEI DE INT PARA STRING
    private String descricao;
    private String estado;
    // private Cliente username;
    // private Restaurante idRest;

    public Achievement(String idAchiev, String descricao, String estado) {
        this.idAchiev = idAchiev;
        this.descricao = descricao;
        this.estado = estado;
    }

    public Achievement(Achievement achiev) {
        this.idAchiev = achiev.getIdAchiev();
        this.descricao = achiev.getDescricao();
        this.estado = achiev.getEstado();
    }

    public String getIdAchiev() {
        return idAchiev;
    }

    public void setIdAchiev(String idAchiev) {
        this.idAchiev = idAchiev;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "idAchiev=" + idAchiev +
                ", descricao='" + descricao + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement achiev = (Achievement) o;
        return this.idAchiev.equals(achiev.getIdAchiev());
    }

    public Achievement clone() {
        return new Achievement(this);
    }
}