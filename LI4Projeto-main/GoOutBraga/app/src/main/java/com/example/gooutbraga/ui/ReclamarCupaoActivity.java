package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Features.Cupao;
import com.example.gooutbraga.R;

import java.util.List;

public class ReclamarCupaoActivity extends AppCompatActivity implements View.OnClickListener{

    List<Cupao> listaCupoesRes;
    int i = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamar_cupao);

        listaCupoesRes = EstadoSingleton.getInstance().getCupoesRest(getIntent().getExtras().get("IdRes").toString());
        if(listaCupoesRes.isEmpty())
            finish();
        else{
            popularCupao();
        }

        findViewById(R.id.btNext).setOnClickListener(this);
        findViewById(R.id.btAnterior).setOnClickListener(this);
        findViewById(R.id.btResg).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btNext -> {
                if(i == listaCupoesRes.size()-1)
                    Toast.makeText(getApplicationContext(), "Já estás na última página.", Toast.LENGTH_SHORT).show();
                else{
                    i++;
                    popularCupao();
                }

            }
            case R.id.btAnterior -> {
                if(i == 0)
                    Toast.makeText(getApplicationContext(), "Já estás na primeira página.", Toast.LENGTH_SHORT).show();
                else {
                    i--;
                    popularCupao();
                }
            }
            case R.id.btResg -> {
                EstadoSingleton.getInstance().reclamarCupao(listaCupoesRes.get(i).getIdRest(), listaCupoesRes.get(i).getIdCupao());
            }
        }
    }

    public void popularCupao(){
        Cupao c = listaCupoesRes.get(i);
        ((TextView) findViewById(R.id.tvId)).setText("ID cupão: " +c.getIdCupao());
        ((TextView) findViewById(R.id.tvDesc)).setText("Descrição: " + c.getDescricao());
        ((TextView) findViewById(R.id.tvTimer)).setText("Timer: " + c.getTimer());
        ((TextView) findViewById(R.id.tvDataRec)).setText("Ainda não foi reclamado.");
        ((TextView) findViewById(R.id.tvIdRes)).setText("ID Restaurante: " + c.getIdRest());
    }



}