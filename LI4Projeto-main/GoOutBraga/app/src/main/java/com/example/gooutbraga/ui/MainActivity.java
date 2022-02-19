package com.example.gooutbraga.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Utilizador.Cliente;
import com.example.gooutbraga.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getIntent().hasExtra("Resultado")){
            String s = (String) getIntent().getExtras().get("Resultado");
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }

        Button login = (Button) findViewById(R.id.entrar);
        login.setOnClickListener(this);

        findViewById(R.id.registarUser).setOnClickListener(this);
        findViewById(R.id.registarProprietario).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v){
        EditText etUsername = (EditText) findViewById(R.id.nomeUtilizador);
        EditText etPassword = (EditText) findViewById(R.id.password);
        TextView tvError = (TextView) findViewById(R.id.error);
        switch(v.getId()){
            case R.id.entrar:
                if(EstadoSingleton.getInstance().login(etUsername.getText().toString(),etPassword.getText().toString())) {
                    Intent loginIntent;
                    if (EstadoSingleton.getInstance().getUsers().get(etUsername.getText().toString()) instanceof Cliente){
                        loginIntent = new Intent(getApplicationContext(), MenuUserActivity.class);
                    }else {
                        loginIntent = new Intent(getApplicationContext(), MenuProprietarioActivity.class);
                    }
                    loginIntent.putExtra("username",etUsername.getText().toString());
                    startActivity(loginIntent);
                    Log.d("Login","Login!");
                } else {
                    tvError.setText("Login failed!");
                    Log.d("Login","Login failed!");
                }
                break;
            case R.id.registarUser:
                Intent userIntent = new Intent(getApplicationContext(),RegisterUserActivity.class);
                startActivity(userIntent);
                break;
            case R.id.registarProprietario:
                Intent propIntent = new Intent(getApplicationContext(),RegisterProprietarioActivity.class);
                startActivity(propIntent);
                break;
        }
    }
}
