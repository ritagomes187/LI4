package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Utilizador.Cliente;
import com.example.gooutbraga.R;

public class PerfilUserEAlterarDadosActivity extends AppCompatActivity implements View.OnClickListener{

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user_e_alterar_dados);

        ((TextView) findViewById(R.id.tvNome)).setText(EstadoSingleton.getInstance().getCurrent().getNome());
        ((TextView) findViewById(R.id.tvEmail)).setText(((Cliente) EstadoSingleton.getInstance().getCurrent()).getEmail());
        ((TextView) findViewById(R.id.tvUsername)).setText(((Cliente) EstadoSingleton.getInstance().getCurrent()).getUsername());
        ((TextView) findViewById(R.id.tvIdade)).setText(((Cliente) EstadoSingleton.getInstance().getCurrent()).getIdade() + "");

        findViewById(R.id.btChEmail).setOnClickListener(this);
        findViewById(R.id.btChIdade).setOnClickListener(this);
        findViewById(R.id.btChPass).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btChEmail -> {
                EstadoSingleton.getInstance().editaPerfil("email",((EditText) findViewById(R.id.EmailProp)).getText().toString());
                ((TextView) findViewById(R.id.tvEmail)).setText(((Cliente) EstadoSingleton.getInstance().getCurrent()).getEmail());
            }
            case R.id.btChIdade -> {
                EstadoSingleton.getInstance().editaPerfil("idade",((EditText) findViewById(R.id.newAge)).getText().toString());
                ((TextView) findViewById(R.id.tvIdade)).setText(((Cliente) EstadoSingleton.getInstance().getCurrent()).getIdade() + "");
            }
            case R.id.btChPass -> {
                EstadoSingleton.getInstance().editaPerfil("password",((EditText) findViewById(R.id.newPassword)).getText().toString());
            }
        }
    }
}