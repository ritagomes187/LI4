package com.example.gooutbraga.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.gooutbraga.Model.Estabelecimento.Avaliacao;
import com.example.gooutbraga.Model.Estabelecimento.Horario;
import com.example.gooutbraga.Model.Estabelecimento.Restaurante;
import com.example.gooutbraga.Model.Features.Cupao;
import com.example.gooutbraga.Model.Features.Favorito;
import com.example.gooutbraga.Model.Features.Localizacao;
import com.example.gooutbraga.Model.Utilizador.Cliente;
import com.example.gooutbraga.Model.Utilizador.Proprietario;

import Model.Utilizador.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estado {
    Map<String, User> users;
    Map<String, Restaurante> restaurantes;
    User current;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Estado() {
        this.users = new HashMap<>();
        this.restaurantes = new HashMap<>();
        povoar();
    }
    public List<String> getAvaliacoes(String idRest) {
        Restaurante res = restaurantes.get(idRest);
        List<String> avaliacoes = new ArrayList<>();
        for (Avaliacao a: res.getAvaliacoes())
            avaliacoes.add(a.toString());
        return avaliacoes;
    }

    public boolean containsFav(String username, String id){
        List<Favorito> fav = ((Cliente) this.users.get(username)).getFavoritos();
        for(Favorito f : fav){
            if(f.getId().equals(id))
                return true;
        }
        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public Map<String,User> getUsers() {
        return this.users.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().clone()));
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setUsers(Map<String,User> users) {
        this.users = users.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().clone()));
    }

    public User getCurrent() {
        return this.current.clone();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Map<String,Restaurante> getRestaurantes() {
        return this.restaurantes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().clone()));
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setRestaurantes(Map<String,Restaurante> restaurantes) {
        this.restaurantes = restaurantes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().clone()));
    }
    public void setCurrent(User user) {
        this.current = user.clone();
    }

    /**
     * Método responsável por registar um cliente
     * @param username Username do cliente
     * @param nome Nome do cliente
     * @param password Password do cliente
     * @param idade Idade do cliente
     * @return true se ficou registado, falso caso contrário
     */
    public boolean registaCliente(String username, String nome, String password, int idade, String email) {
        boolean registo = false;

        if (!users.containsKey(username)) {
            Cliente cliente = new Cliente(username,nome,password,idade,new Localizacao(),0,new ArrayList<>(),email,new ArrayList<>());
            users.put(username,cliente);
            registo = true;
        }

        return registo;
    }


    /**
     * Método responsável por registar um proprietario e o seu restaurante
     * @param username Username do proprietario
     * @param nome Nome do proprietario
     * @param password Password do proprietario
     * @param idRest Id do restaurante
     * @param nomeRest Nome do restaurante
     * @param horario Horario de funcionamento do restaurante
     * @param contacto Contacto do restaurante
     * @param cardapio Cardapio do restaurante
     * @return true se ficou registado, falso caso contrário
     */
    public boolean registaProprietario(String username, String nome, String password, String idRest,
                                       String nomeRest, Horario horario, int contacto, String cardapio) {
        boolean registo = false;

        if (!users.containsKey(username)) {
            Proprietario proprietario = new Proprietario(username,nome,password,idRest);
            Restaurante restaurante = new Restaurante(idRest,nomeRest,horario,contacto,cardapio,
                    null,new Localizacao(),new ArrayList<>(),0,new ArrayList<>(),new ArrayList<>(),new ArrayList<Cupao>());
            users.put(username,proprietario);
            restaurantes.put(idRest,restaurante);
            registo = true;
        }

        return registo;
    }


    /**
     * Método responsável por dar login de um user
     * @param username Username do user
     * @param password Password do user
     * @return true se o login foi efetuado com sucesso, false caso contrário
     */
    public boolean login(String username, String password) {
        boolean registo = false;
        boolean login = false;

        if (users.containsKey(username)) registo = true;
        if (registo && users.get(username).getPassword().equals(password)) login = true;
        if (registo && login) setCurrent(users.get(username));

        return registo && login;
    }

    public List<Cupao> getCupoesRest(String idRest) {
        return restaurantes.get(idRest).getCupoes();
    }

    public List<Cupao> getCupoesCliente() {
        return ((Cliente) current).getCupoes();
    }




    /**
     * Método responsável por editar o perfil de um user
     * @param campo Campo a mudar
     * @param novo Nova alteração
     * @return true se campo foi alterado, false caso contrário
     */
    public boolean editaPerfil(String campo, String novo) {
        // perfil cliente
        if (current instanceof Cliente) {
            switch (campo) {
                case "password" -> current.setPassword(novo);
                case "idade" -> ((Cliente) current).setIdade(Integer.parseInt(novo));
                case "email" -> ((Cliente) current).setEmail(novo);
                default -> {return false;}
            }
        }
        // perfil proprietário
        else {
            Restaurante res = restaurantes.get(((Proprietario) current).getIdRest());
            switch (campo) {
                case "password" -> current.setPassword(novo);
                case "nome" -> res.setNome(novo);
                case "contacto" -> res.setContacto(Integer.parseInt(novo));
                case "cardapio" -> res.setCardapio(novo);
                default -> {return false;}
            }
            restaurantes.put(res.getId(),res);
        }

        users.put(current.getUsername(),current);
        return true;
    }


    /**
     * Método que devolve uma lista de restaurantes com um dado nome
     * @param nome Nome do restaurante
     * @return Lista de restaurantes
     */
    public List<Restaurante> filtraNome(String nome) {
        List<Restaurante> lista = new ArrayList<>();

        for (Restaurante r: restaurantes.values())
            if (r.getNome().equalsIgnoreCase(nome))
                lista.add(r);

        return lista;
    }


    /**
     * Método que devolve uma lista de restaurantes com um determinado preco
     * @param preco Preco do restaurante
     * @return Lista de restaurantes
     */
    public List<Restaurante> filtraPreco(String preco) {
        List<Restaurante> lista = new ArrayList<>();

        for (Restaurante r: restaurantes.values())
            if (r.getPreco().equalsIgnoreCase(preco))
                lista.add(r);

        return lista;
    }


    /**
     * Método que devolve uma lista de restaurantes com uma determinada classificacao
     * @param estrelas Classificacao do restaurante
     * @return Lista de restaurantes
     */
    public List<Restaurante> filtraClassificacao(double estrelas) {
        List<Restaurante> lista = new ArrayList<>();

        for (Restaurante r: restaurantes.values())
            if (r.getEstrelas() == estrelas)
                lista.add(r);

        return lista;
    }


    /**
     * Método que devolve uma lista de restaurantes a uma determinada distância do user
     * @param distancia Distancia
     * @return Lista de restaurantes
     */
    public List<Restaurante> filtraDistancia(int distancia) {
        Localizacao localizacao = ((Cliente) current).getLocalizacao();
        List<Restaurante> lista = new ArrayList<>();

        for (Restaurante r: restaurantes.values())
            if (localizacao.isInLimit(distancia))
                lista.add(r);

        return lista;
    }


    /**
     * Método responsável por devolver uma lista de restaurantes com base num filtro
     * @param tipo Tipo de filtro
     * @param alvo Especificação do filtro
     * @return Lista de restaurantes
     */
    public List<Restaurante> pesquisaRestaurante(String tipo, String alvo) {
        List<Restaurante> lista = new ArrayList<>();

        switch (tipo) {
            case "nome" -> lista = filtraNome(alvo);
            case "preco" -> lista = filtraPreco(alvo);
            case "classificacao" -> lista = filtraClassificacao(Double.parseDouble(alvo));
            case "distancia" -> lista = filtraDistancia(Integer.parseInt(alvo));
        }

        return lista;
    }


    /**
     * Método responsável por adicionar ou remover um favorito da lista de restaurantes
     * favoritos do cliente
     * @param id Id do restaurante
     * @return true se foi adicionado, false se foi removido
     */
    public boolean favoritos(String id) {
        boolean pertence = false;
        List<Favorito> favoritos = ((Cliente) current).getFavoritos();
        Favorito fav = null;
        for (Favorito f: favoritos) {
            if (f.getId().equals(id)) {
                pertence = true;
                fav = f;
                break;
            }
        }

        // se nao pertencer, adicionamos
        if (!pertence) favoritos.add(new Favorito(id));
        // se pertencer, removemos
        else favoritos.remove(fav);

        ((Cliente) current).setFavoritos(favoritos);
        users.put(current.getUsername(),current);

        return !pertence;
    }


    /**
     * Método responsável por permitir que um cliente avalie um restaurante
     * @param idRest Id do restaurante
     * @param estrelas Classificacao
     * @param comentario Comentario
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void avaliarRestaurante(String idRest, int estrelas, String comentario, String preco) {
        Avaliacao avaliacao = new Avaliacao(current.getUsername(),estrelas,comentario, LocalDateTime.now(),preco);
        Restaurante restaurante = restaurantes.get(idRest);
        restaurante.avaliaRest(avaliacao);
        restaurantes.put(idRest,restaurante);
    }


    /**
     * Método responsável por adicionar um cupão à lista de cupões disponíveis de um restaurante
     * @param id Id do cupao
     * @param descricao Descrição do cupao
     * @param timer Tempo de expiração do cupao
     */
    public void distribuiCupao(String id, String descricao, int timer) {
        String idRest = ((Proprietario) current).getIdRest();
        Cupao cupao = new Cupao(id,timer,descricao,idRest);

        Restaurante rest = restaurantes.get(idRest);
        List<Cupao> lista = rest.getCupoes();
        lista.add(cupao);
        rest.setCupoes(lista);
        restaurantes.put(idRest,rest);
    }


    /**
     * Método responsável por tratar da obtenção de um cupão por parte do cliente
     * O cupão é removido da lista de cupões do restaurante e adicionado à do cliente
     * @param idRest Id do restaurante
     * @param idCupao Id do cupão
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reclamarCupao(String idRest, String idCupao) {
        Restaurante res = restaurantes.get(idRest);
        List<Cupao> cupoesRest = res.getCupoes();
        Cupao cupao = null;

        // guardamos o cupao e removemos do restaurante
        for (Cupao c: cupoesRest) {
            if (c.getIdCupao().equals(idCupao)) {
                cupao = c;
                cupoesRest.remove(c);
                break;
            }
        }

        res.setCupoes(cupoesRest);
        restaurantes.put(res.getId(),res);

        // atualizamos a data de obtencao do cupao e adicionamos à lista de cupoes do cliente
        cupao.setData(LocalDateTime.now());
        List<Cupao> cupaosCliente = ((Cliente) current).getCupoes();
        cupaosCliente.add(cupao);
        ((Cliente) current).setCupoes(cupaosCliente);
        users.put(current.getUsername(),current);
    }


    /**
     * Método responsável por ativar um cupão do cliente com base no tempo de expiração do mesmo
     * @param idRest Id do restaurante
     * @param idCupao Id do cupão
     * @return true se foi possivel ativar, false caso contrário
     */
    public boolean usarCupao(String idRest, String idCupao) {
        boolean sucesso = false;

        Restaurante res = restaurantes.get(idRest);
        List<Cupao> cupoesCli = ((Cliente) current).getCupoes();

        for (Cupao c: cupoesCli) {
            if (c.getIdCupao().equals(idCupao) && c.getIdRest().equals(res.getId()) && c.validaUso()) {
                sucesso = true;
                cupoesCli.remove(c);
                break;
            }
        }

        ((Cliente) current).setCupoes(cupoesCli);
        users.put(current.getUsername(),current);
        return sucesso;
    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    public void povoar() {


        // RESTAURANTES

        List<Avaliacao> avaliacoes1 = new ArrayList<>();
        Avaliacao a1 = new Avaliacao("user1",1,"Péssimo, preços muito altos.",LocalDateTime.now(),"caro");
        avaliacoes1.add(a1);
        Avaliacao a2 = new Avaliacao("user2",2,"Não gostamos nada do atendimento e da comida.",LocalDateTime.now(),"medio");
        avaliacoes1.add(a2);
        Avaliacao a3 = new Avaliacao("user3",3,"Nada de especial, agradável.",LocalDateTime.now(),"medio");
        avaliacoes1.add(a3);
        Avaliacao a4 = new Avaliacao("user4",4,"Ótima comida apesar da demora.",LocalDateTime.now(),"barato");
        avaliacoes1.add(a4);
        Avaliacao a5 = new Avaliacao("user5",5,"Muito boa comida.",LocalDateTime.now(),"medio");
        avaliacoes1.add(a5);

        double estrelas1 = 0;
        int barato1 = 0,medio1 = 0,caro1 = 0;

        for (Avaliacao a: avaliacoes1) {
            estrelas1 += a.getEstrelas();
            switch (a.getPreco()) {
                case "barato" -> barato1++;
                case "medio" -> medio1++;
                case "caro" -> caro1++;
            }
        }
        estrelas1 = estrelas1/avaliacoes1.size();

        List<String> rank1 = new ArrayList<>();
        rank1.add("user5");
        rank1.add("user10");
        rank1.add("user7");

        String preco1 = "";
        if (barato1 >= medio1 && barato1 >= caro1) preco1 = "barato";
        if (medio1 >= barato1 && medio1 >= caro1) preco1 = "medio";
        if (caro1 >= barato1 && caro1 >= medio1) preco1 = "caro";

        Restaurante r1 = new Restaurante("#001","Restaurante1",new Horario(11,23),999999999,"Menu",preco1,new Localizacao("0","0"),avaliacoes1,estrelas1,rank1,new ArrayList<>(),new ArrayList<>());



        List<Avaliacao> avaliacoes2 = new ArrayList<>();
        Avaliacao a6 = new Avaliacao("user9",1,"Péssimo, preços muito altos.",LocalDateTime.now(),"caro");
        avaliacoes2.add(a6);
        Avaliacao a7 = new Avaliacao("user20",2,"Não gostamos nada do atendimento.",LocalDateTime.now(),"caro");
        avaliacoes2.add(a7);
        Avaliacao a8 = new Avaliacao("user5",5,"Muito agradável.",LocalDateTime.now(),"caro");
        avaliacoes2.add(a8);
        Avaliacao a9 = new Avaliacao("user7",5,"Apesar da demora, adorei.",LocalDateTime.now(),"barato");
        avaliacoes2.add(a9);
        Avaliacao a10 = new Avaliacao("user3",5,"Muito boa comida.",LocalDateTime.now(),"medio");
        avaliacoes2.add(a10);

        double estrelas2 = 0;
        int barato2 = 0,medio2 = 0,caro2 = 0;

        for (Avaliacao a: avaliacoes2) {
            estrelas2 += a.getEstrelas();
            switch (a.getPreco()) {
                case "barato" -> barato2++;
                case "medio" -> medio2++;
                case "caro" -> caro2++;
            }
        }
        estrelas2 = estrelas2/avaliacoes2.size();

        List<String> rank2 = new ArrayList<>();
        rank2.add("user3");
        rank2.add("user14");
        rank2.add("user6");

        String preco2 = "";
        if (barato2 >= medio2 && barato2 >= caro2) preco2 = "barato";
        if (medio2 >= barato2 && medio2 >= caro2) preco2 = "medio";
        if (caro2 >= barato2 && caro2 >= medio2) preco2 = "caro";

        Cupao cupao1 = new Cupao("#1",10,"Sobremesa por conta da casa.","#002");
        Cupao cupao3 = new Cupao("#3",5,"Bebidas de graça.","#002");
        List<Cupao> cupoes1 = new ArrayList<>();
        cupoes1.add(cupao1);
        cupoes1.add(cupao3);
        Restaurante r2 = new Restaurante("#002","Restaurante2",new Horario(10,24),999999999,"Menu",preco2,new Localizacao("10","10"),avaliacoes2,estrelas2,rank2,new ArrayList<>(),cupoes1);

        restaurantes.put("#001",r1);
        restaurantes.put("#002",r2);


        // PROPRIETARIOS

        Proprietario p1 = new Proprietario("proprietario1","Proprietario 1","p1","#001");
        Proprietario p2 = new Proprietario("proprietario2","Proprietario 2","p2","#002");
        users.put("proprietario1",p1);
        users.put("proprietario2",p2);


        // CLIENTES

        List<Favorito> favoritos = new ArrayList<>();
        favoritos.add(new Favorito("#001"));
        favoritos.add(new Favorito("#002"));

        Cupao cupao2 = new Cupao("#2",1,"Oferecemos o prato do dia.","#002");
        List<Cupao> cupoes2 = new ArrayList<>();
        cupoes2.add(cupao2);
        Cliente c1 = new Cliente("user1","user1","user1",20,new Localizacao(),0,favoritos,"email@hotmail.com",cupoes2);

        users.put("user1",c1);
    }
}
