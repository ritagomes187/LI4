package com.example.gooutbraga.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Features.Favorito;
import com.example.gooutbraga.Model.Utilizador.Cliente;
import com.example.gooutbraga.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MenuUserActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        findViewById(R.id.sair2).setOnClickListener(this);
        findViewById(R.id.verFavoritos).setOnClickListener(this);
        findViewById(R.id.listarRestaurantes).setOnClickListener(this);
        findViewById(R.id.procurarRestaurante).setOnClickListener(this);
        findViewById(R.id.verPerfil).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.sair2:
                Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.verFavoritos:
                Cliente c = (Cliente) EstadoSingleton.getInstance().getUsers().get(getIntent().getExtras().get("username").toString());
                if(c.getFavoritos().isEmpty())
                    Toast.makeText(getApplicationContext(), "Lista de Favoritos vazia", Toast.LENGTH_SHORT).show();
                else{
                    Intent favIntent = new Intent(getApplicationContext(), RestauranteActivity.class);
                    favIntent.putExtra("username",getIntent().getExtras().get("username").toString());
                    favIntent.putExtra("fav","sim");
                    startActivity(favIntent);
                }
                break;
            case R.id.listarRestaurantes:
                if(EstadoSingleton.getInstance().getRestaurantes().entrySet().isEmpty())
                    Toast.makeText(getApplicationContext(), "Lista de Restaurantes vazia", Toast.LENGTH_SHORT).show();
                else{
                    Intent favIntent = new Intent(getApplicationContext(), RestauranteActivity.class);
                    favIntent.putExtra("username",getIntent().getExtras().get("username").toString());
                    favIntent.putExtra("fav","nao");
                    startActivity(favIntent);
                }
                break;
            case R.id.procurarRestaurante:
                Intent searchRestaurante = new Intent(getApplicationContext(), ProcurarRestauranteActivity.class);
                startActivity(searchRestaurante);
                break;
            case R.id.verPerfil:
                Intent verPerfil = new Intent(getApplicationContext(), PerfilUserEAlterarDadosActivity.class);
                startActivity(verPerfil);
                break;
        }
    }
}