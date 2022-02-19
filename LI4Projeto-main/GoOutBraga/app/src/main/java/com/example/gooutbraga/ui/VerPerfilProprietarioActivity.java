package com.example.gooutbraga.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gooutbraga.Model.Estabelecimento.Restaurante;
import com.example.gooutbraga.Model.EstadoSingleton;
import com.example.gooutbraga.Model.Utilizador.Proprietario;
import com.example.gooutbraga.R;

public class VerPerfilProprietarioActivity extends AppCompatActivity implements View.OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil_proprietario);

        Proprietario prop = (Proprietario) EstadoSingleton.getInstance().getCurrent();
        ((TextView) findViewById(R.id.verNome)).setText(prop.getNome());
        ((TextView) findViewById(R.id.verUsername)).setText(prop.getUsername());
        ((TextView) findViewById(R.id.verIdRestaurante)).setText(prop.getIdRest());
        Restaurante r = EstadoSingleton.getInstance().getRestaurantes().get(prop.getIdRest());
        ((TextView) findViewById(R.id.verNomeRestaurante)).setText(r.getNome());
        ((TextView) findViewById(R.id.verHoradeAbertura)).setText(r.getHorario().getAbertura()+"");
        ((TextView) findViewById(R.id.verHoradeFecho)).setText(r.getHorario().getFecho()+"");
        ((TextView) findViewById(R.id.verContacto)).setText(r.getContacto()+"");
        ((TextView) findViewById(R.id.verCardapio)).setText(r.getCardapio()+"");

        findViewById(R.id.btMudarNomeRest).setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btMudarNomeRest){
            EstadoSingleton.getInstance().editaPerfil("nome",((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString());
            ((TextView) findViewById(R.id.verNomeRestaurante)).setText(((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString());
        }
    }
}