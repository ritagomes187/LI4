package com.example.gooutbraga.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gooutbraga.Model.Estabelecimento.Horario;
import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.R;


public class RegisterProprietarioActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_proprietario);

        findViewById(R.id.criarProp).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
        EditText etUsername = (EditText) findViewById(R.id.usernameProp);
        etUsername.getText().toString();
        if(view.getId() == R.id.criarProp){
            if(EstadoSingleton.getInstance().registaProprietario(
                    ((EditText) findViewById(R.id.usernameProp)).getText().toString(),
                    ((EditText) findViewById(R.id.nomeProp)).getText().toString(),
                    ((EditText) findViewById(R.id.passwordProp)).getText().toString(),
                    ((EditText) findViewById(R.id.Id)).getText().toString(),
                    ((EditText) findViewById(R.id.nomeRest)).getText().toString(),
                    new Horario(Integer.parseInt(((EditText) findViewById(R.id.horaA)).toString()), Integer.parseInt(((EditText) findViewById(R.id.horaF)).getText().toString())),
                    Integer.parseInt(((EditText) findViewById(R.id.contacto)).toString()),
                    ((EditText) findViewById(R.id.Cardapio)).getText().toString())){
                loginIntent.putExtra("Resultado", "Registo concluído");
            } else {
                loginIntent.putExtra("Resultado", "Registo falhou. Tente novamente mais tarde.");
            }

            startActivity(loginIntent);
        }
    }
}