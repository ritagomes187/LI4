package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.R;

import java.util.List;

public class VerComentariosActivity extends AppCompatActivity implements View.OnClickListener {

    int i = 1;
    List<String> avaliacoes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_comentarios);

        findViewById(R.id.anterior).setOnClickListener(this);
        findViewById(R.id.proximo).setOnClickListener(this);

        avaliacoes = EstadoSingleton.getInstance().getAvaliacoes(getIntent().getExtras().get("IdRes").toString());
        popularAvaliacoes();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.anterior -> {
                if(i == 1)
                    Toast.makeText(getApplicationContext(), "Está na página inicial", Toast.LENGTH_SHORT).show();
                else{
                    i-=4;
                    popularAvaliacoes();
                }
            }
            case R.id.proximo -> {
                if(i+1 > avaliacoes.size())
                    Toast.makeText(getApplicationContext(), "Está na última página", Toast.LENGTH_SHORT).show();
                else{
                    i+=4;
                    popularAvaliacoes();
                }
            }
        }

    }

    public void popularAvaliacoes(){
        String avaliacao1 = i > avaliacoes.size() ? "-" : avaliacoes.get(i-1);
        String avaliacao2 = i+1 > avaliacoes.size() ? "-" : avaliacoes.get(i);
        String avaliacao3 = i+2 > avaliacoes.size() ? "-" : avaliacoes.get(i+1);
        String avaliacao4 = i+3 > avaliacoes.size() ? "-" : avaliacoes.get(i+2);

        ((TextView) findViewById(R.id.comentario)).setText(avaliacao1);
        ((TextView) findViewById(R.id.comentario2)).setText(avaliacao2);
        ((TextView) findViewById(R.id.comentario3)).setText(avaliacao3);
        ((TextView) findViewById(R.id.comentario4)).setText(avaliacao4);
    }
}