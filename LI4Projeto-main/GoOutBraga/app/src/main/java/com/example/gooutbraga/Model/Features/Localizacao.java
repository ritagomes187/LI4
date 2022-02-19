package com.example.gooutbraga.Model.Features;

import java.util.Objects;

public class Localizacao {
    private String coordX;
    private String coordY;

    public Localizacao() {
        this.coordX = "";
        this.coordY = "";
    }

    public Localizacao(String coordX, String coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Localizacao(Localizacao loc) {
        this.coordX = loc.getCoordX();
        this.coordY = loc.getCoordY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacao location = (Localizacao) o;
        return Objects.equals(coordX, location.coordX) && Objects.equals(coordY, location.coordY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordX, coordY);
    }

    public String getCoordX() {
        return this.coordX;
    }
    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return this.coordY;
    }
    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public Localizacao clone() {
        return new Localizacao(this);
    }

    public int compareTo(Localizacao localizacao) {
        int r;
        if (this.coordX.equals(localizacao.coordX)) {
            r = this.coordY.compareTo(localizacao.coordY);
        } else {
            r = this.coordX.compareTo(localizacao.coordX);
        }
        return r;
    }

    @Override
    public String toString() {
        return "(" + this.coordX + ", " + this.coordY + ")";
    }

    /**
     * Averigua se a localização está dentro do limite (o limite corresponde ao raio que o cliente pretende filtrar os restaurantes)
     * @param limit limite
     * @return true se estiver dentro do limite
     */
    public boolean isInLimit(int limit) {
        return Integer.parseInt(this.coordX) < limit && Integer.parseInt(this.coordY) < limit &&
                Integer.parseInt(this.coordX) >= 0 && Integer.parseInt(this.coordY) >= 0;
    }
}
