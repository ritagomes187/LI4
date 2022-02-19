package Model.Utilizador;

import java.util.Objects;

public abstract class User {
    private final String username; // nao pode ser alterado
    private final String nome;     // nao pode ser alterado
    private String password;

    public User(String username, String nome, String password) {
        this.username = username;
        this.nome = nome;
        this.password = password;
    }

    public User(User user) {
        this.username = user.getUsername();
        this.nome = user.getNome();
        this.password = user.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     Username: ").append(this.username).append('\n');
        sb.append("     Nome: ").append(this.nome).append('\n');
        sb.append("     Password: ").append(this.password).append('\n');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    public abstract User clone();
}
