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

public class AvaliarActivity extends AppCompatActivity implements View.OnClickListener {

    int preco = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);
        
        findViewById(R.id.btBarato).setOnClickListener(this);
        findViewById(R.id.btMedio).setOnClickListener(this);
        findViewById(R.id.btCaro).setOnClickListener(this);
        findViewById(R.id.btEnviarComentario).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btCaro -> preco = 2;
            case R.id.btBarato -> preco = 0;
            case R.id.btMedio -> preco = 1;
            case R.id.btEnviarComentario -> {
                if(preco == -1)
                    Toast.makeText(getApplicationContext(), "Selecione um preço", Toast.LENGTH_SHORT).show();
                else {
                    String sendPreco = "";
                    switch(preco){
                        case 1 -> sendPreco = "medio";
                        case 0 -> sendPreco = "barato";
                        case 2 -> sendPreco = "caro";
                    }
                    EstadoSingleton.getInstance().avaliarRestaurante(
                            getIntent().getExtras().get("IdRes").toString(),
                            Integer.parseInt(((EditText) findViewById(R.id.etEstrelas)).getText().toString()),
                            ((EditText) findViewById(R.id.etComent)).getText().toString(),
                            sendPreco);
                    finish();
                }
            }
        }
    }
}