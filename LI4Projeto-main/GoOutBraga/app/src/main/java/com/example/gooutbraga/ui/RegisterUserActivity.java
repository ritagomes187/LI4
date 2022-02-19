package com.example.gooutbraga.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gooutbraga.Model.Estabelecimento.Horario;
import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.R;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    if(EstadoSingleton.getInstance().registaCliente(etUsername.getText().toString(),"Teste",etPassword.getText().toString(),20,"exemplo@gmail.com")){
                    tvError.setText("Registo concluído. Faça login.");
                }else {
                    tvError.setText("Erro, registo falhou.");
                    Log.d("Registo","Registo falhou!");
                }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        findViewById(R.id.criarUser).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
        if(view.getId() == R.id.criarUser){
            if(EstadoSingleton.getInstance().registaCliente(
                    ((EditText) findViewById(R.id.username)).getText().toString(),
                    ((EditText) findViewById(R.id.nome)).getText().toString(),
                    ((EditText) findViewById(R.id.password)).getText().toString(),
                    Integer.parseInt(((EditText) findViewById(R.id.idade)).getText().toString()),
                    ((EditText) findViewById(R.id.Email)).getText().toString())){
                loginIntent.putExtra("Resultado", "Registo concluído");
            } else {
                loginIntent.putExtra("Resultado", "Registo falhou. Tente novamente mais tarde.");
            }

            startActivity(loginIntent);
        }
    }


}