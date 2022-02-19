package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Utilizador.Proprietario;
import com.example.gooutbraga.R;

public class MenuProprietarioActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proprietario);

        findViewById(R.id.btLogout).setOnClickListener(this);
        findViewById(R.id.btPerfil).setOnClickListener(this);
        findViewById(R.id.btCupao).setOnClickListener(this);
        findViewById(R.id.btAval).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btLogout -> {
                Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(loginIntent);
            }
            case R.id.btPerfil -> {
                Intent verPerfil = new Intent(getApplicationContext(),VerPerfilProprietarioActivity.class);
                startActivity(verPerfil);
            }
            case R.id.btAval -> {
                Intent avaliacoes = new Intent(getApplicationContext(), VerComentariosActivity.class);
                avaliacoes.putExtra("IdRes", ((Proprietario) EstadoSingleton.getInstance().getCurrent()).getIdRest());
                startActivity(avaliacoes);
            }
            case R.id.btCupao -> {
                Intent cpIntent = new Intent(getApplicationContext(), CriarCupaoActivity.class);
                startActivity(cpIntent);
            }
        }
    }
}