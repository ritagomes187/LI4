package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.R;

public class ProcurarRestauranteActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_restaurante);

        findViewById(R.id.procurarCaro).setOnClickListener(this);
        findViewById(R.id.procurarMedio).setOnClickListener(this);
        findViewById(R.id.procurarBarato).setOnClickListener(this);
        findViewById(R.id.procurar).setOnClickListener(this);
        findViewById(R.id.avaliacao1).setOnClickListener(this);
        findViewById(R.id.avaliacao2).setOnClickListener(this);
        findViewById(R.id.avaliacao3).setOnClickListener(this);
        findViewById(R.id.avaliacao4).setOnClickListener(this);
        findViewById(R.id.avaliacao5).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        boolean pesquisaNome = false;
        Intent newIntent = new Intent(getApplicationContext(), RestauranteActivity.class);
        switch(view.getId()){
            case R.id.procurarCaro -> {newIntent.putExtra("tipo","preco");newIntent.putExtra("alvo","caro");}
            case R.id.procurarMedio -> {newIntent.putExtra("tipo","preco");newIntent.putExtra("alvo","medio");}
            case R.id.procurarBarato -> {newIntent.putExtra("tipo","preco");newIntent.putExtra("alvo","barato");}
            case R.id.procurar -> {pesquisaNome = true; newIntent.putExtra("tipo","nome");newIntent.putExtra("alvo",((EditText) findViewById(R.id.procuraRestaurante)).getText().toString());}
            case R.id.avaliacao1 -> {newIntent.putExtra("tipo","classificacao");newIntent.putExtra("alvo","1.0");}
            case R.id.avaliacao2 -> {newIntent.putExtra("tipo","classificacao");newIntent.putExtra("alvo","2.0");}
            case R.id.avaliacao3 -> {newIntent.putExtra("tipo","classificacao");newIntent.putExtra("alvo","3.0");}
            case R.id.avaliacao4 -> {newIntent.putExtra("tipo","classificacao");newIntent.putExtra("alvo","4.0");}
            case R.id.avaliacao5 -> {newIntent.putExtra("tipo","classificacao");newIntent.putExtra("alvo","5.0");}
        }
        String nome = ((EditText) findViewById(R.id.procuraRestaurante)).getText().toString();
        if(pesquisaNome && EstadoSingleton.getInstance().filtraNome(nome).size() == 0){
            Toast.makeText(getApplicationContext(), "Não existem restaurantes com esse nome", Toast.LENGTH_SHORT).show();
        }else
            startActivity(newIntent);
    }
}