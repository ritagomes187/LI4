package com.example.gooutbraga.Model.Features;


/*
    Não faz sentido um Favorito ter o nome do user pq é o User que vai ter uma lista de Favoritos.
    Tmb não percebi pq é q um Favorito tem uma avaliação por isso não vou por
 */


public class Favorito {
    private String id;  // id do estabelecimaneto

    public Favorito(String id) {
        this.id = id;
    }

    public Favorito(Favorito favorito) {
        this.id = favorito.getId();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorito favorito = (Favorito) o;
        return id.equals(favorito.getId());
    }

    public Favorito clone() {
        return new Favorito(this);
    }
}
