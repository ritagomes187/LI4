package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gooutbraga.Model.Estabelecimento.Restaurante;
import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Features.Favorito;
import com.example.gooutbraga.Model.Utilizador.Cliente;
import com.example.gooutbraga.R;

import java.util.ArrayList;
import java.util.List;

public class RestauranteActivity extends AppCompatActivity implements View.OnClickListener{

    List<Favorito> lista;
    List<Restaurante> listaRestaurante;
    int tipo; //0 para favoritos, 1 para todos restaurantes, 2 para filtros
    int i;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        Log.d("Restaurante","aqui");
        i = 1;

        if(getIntent().hasExtra("fav") && getIntent().getExtras().get("fav").toString().equals("sim")){
            lista = ((Cliente) EstadoSingleton.getInstance().getUsers().get(getIntent().getExtras().get("username").toString())).getFavoritos();
            tipo = 0;
            setFavRestaurant();
        } else if(getIntent().hasExtra("fav") && getIntent().getExtras().get("fav").toString().equals("nao")){
            listaRestaurante = new ArrayList<>(EstadoSingleton.getInstance().getRestaurantes().values());
            tipo = 1;
            setFavRestaurant();
        }  else {
            String tipoR = getIntent().getExtras().get("tipo").toString();
            String alvo = getIntent().getExtras().get("alvo").toString();
            listaRestaurante = EstadoSingleton.getInstance().pesquisaRestaurante(tipoR,alvo);
            tipo = 2;
            if(listaRestaurante.isEmpty()){
                ((TextView) findViewById(R.id.textView3)).setText("NÃO ENCONTRADO");
                ((TextView) findViewById(R.id.textView5)).setText("NÃO ENCONTRADO");
                ((TextView) findViewById(R.id.textView7)).setText("NÃO ENCONTRADO");
                ((TextView) findViewById(R.id.textView9)).setText("NÃO ENCONTRADO");
                ((TextView) findViewById(R.id.textView10)).setText("NÃO ENCONTRADO");
                ((TextView) findViewById(R.id.textView11)).setText("NÃO ENCONTRADO");
                ((TextView) findViewById(R.id.textView6)).setText("NÃO ENCONTRADO");
            } else {
                setFavRestaurant();
            }
        }

        findViewById(R.id.proximo2).setOnClickListener(this);
        findViewById(R.id.anterior2).setOnClickListener(this);
        findViewById(R.id.addFav).setOnClickListener(this);
        findViewById(R.id.sair4).setOnClickListener(this);
        findViewById(R.id.sair5).setOnClickListener(this);
        findViewById(R.id.sair6).setOnClickListener(this);
        findViewById(R.id.sair7).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.proximo2:
                if(tipo == 0 && lista.size() < i+1)
                    Toast.makeText(getApplicationContext(), "Não existem mais favoritos", Toast.LENGTH_SHORT).show();
                else if(tipo == 0){
                    i++;
                    setFavRestaurant();
                } else if((tipo == 1 || tipo == 2) && listaRestaurante.size() < i+1)
                    Toast.makeText(getApplicationContext(), "Não existem mais restaurantes", Toast.LENGTH_SHORT).show();
                else{
                    i++;
                    setFavRestaurant();
                }
                break;
            case R.id.anterior2:
                if(i == 1)
                    Toast.makeText(getApplicationContext(), "Está na página inicial", Toast.LENGTH_SHORT).show();
                else{
                    i--;
                    setFavRestaurant();
                }
                break;
            case R.id.addFav:
                if(tipo == 0) { //se já estiver nos favs, só podemos retirar
                    EstadoSingleton.getInstance().favoritos(lista.get(i-1).getId());
                } else if(tipo == 1 || tipo == 2){
                    EstadoSingleton.getInstance().favoritos(listaRestaurante.get(i-1).getId());
                }
                break;
            case R.id.sair5:
                Intent newIntent = new Intent(getApplicationContext(), AvaliarActivity.class);
                if(tipo == 0) { //se já estiver nos favs, só podemos retirar
                    newIntent.putExtra("IdRes",lista.get(i-1).getId());
                } else if(tipo == 1 || tipo == 2){
                    newIntent.putExtra("IdRes",listaRestaurante.get(i-1).getId());
                }
                startActivity(newIntent);
                break;
            case R.id.sair6:
                Intent avaliacoesIntent = new Intent(getApplicationContext(), VerComentariosActivity.class);
                if(tipo == 0) {
                    avaliacoesIntent.putExtra("IdRes",lista.get(i-1).getId());
                } else if(tipo == 1 || tipo == 2){
                    avaliacoesIntent.putExtra("IdRes",listaRestaurante.get(i-1).getId());
                }
                startActivity(avaliacoesIntent);
                break;
            case R.id.sair7:
                Intent reclamarCupao = new Intent(getApplicationContext(), ReclamarCupaoActivity.class);
                if(tipo == 0) {
                    reclamarCupao.putExtra("IdRes",lista.get(i-1).getId());
                } else if(tipo == 1 || tipo == 2){
                    reclamarCupao.putExtra("IdRes",listaRestaurante.get(i-1).getId());
                }
                startActivity(reclamarCupao);
                break;
            case R.id.sair4:
                Intent usarCupao = new Intent(getApplicationContext(), UsarCupaoActivity.class);
                if(tipo == 0) {
                    usarCupao.putExtra("IdRes",lista.get(i-1).getId());
                } else if(tipo == 1 || tipo == 2){
                    usarCupao.putExtra("IdRes",listaRestaurante.get(i-1).getId());
                }
                startActivity(usarCupao);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setFavRestaurant(){

        Restaurante atual;
        if(tipo == 0)
            atual = EstadoSingleton.getInstance().getRestaurantes().get(lista.get(i-1).getId());
        else
            atual = listaRestaurante.get(i-1);

        ((TextView) findViewById(R.id.textView3)).setText(atual.getNome());
        ((TextView) findViewById(R.id.textView5)).setText(atual.getContacto()+"");
        ((TextView) findViewById(R.id.textView7)).setText(atual.getPreco());
        ((TextView) findViewById(R.id.textView9)).setText(atual.getLocalizacao().toString());
        ((TextView) findViewById(R.id.textView10)).setText("Null");
        ((TextView) findViewById(R.id.textView11)).setText(String.valueOf(atual.getEstrelas()));
        ((TextView) findViewById(R.id.textView6)).setText(atual.getCardapio());

    }
}