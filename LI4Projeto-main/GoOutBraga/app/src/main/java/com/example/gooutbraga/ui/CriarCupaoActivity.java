package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.R;

public class CriarCupaoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_cupao);

        findViewById(R.id.btCriarCp).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btCriarCp){
            EstadoSingleton.getInstance().distribuiCupao(
                    ((EditText) findViewById(R.id.nome)).getText().toString(),
                    ((EditText) findViewById(R.id.descricao)).getText().toString(),
                    Integer.parseInt(((EditText) findViewById(R.id.timer)).getText().toString()));
            finish();
        }
    }
}