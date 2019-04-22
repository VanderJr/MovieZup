package com.example.vande.moviezup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vande.moviezup.webservices.Api;

public class MainActivity extends AppCompatActivity {
    private EditText ETtitulo, ETtitulocad;
    private String nomeTitulo, nomeTituloCad;
    Button btnClickMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETtitulo = (EditText) findViewById(R.id.ETtitulocad);
        ETtitulocad = (EditText) findViewById(R.id.ETtitulocad);

        btnClickMe = (Button) findViewById(R.id.BTCadastrar);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastraTitulo();
            }
        });

        ETtitulo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    buscaTitutlo();
                    return true;
                }
                return false;
            }
        });

        ETtitulocad.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    cadastraTitulo();
                    return true;
                }
                return false;
            }
        });
    }

    private void buscaTitutlo() {
        nomeTitulo = ETtitulo.getText().toString();
        String params = "&s=" + nomeTitulo;

        Api api = new Api(MainActivity.this);
        api.buscaFilmes(params, MainActivity.this.getSupportFragmentManager());
    }

    public void cadastraTitulo() {
        nomeTituloCad = ETtitulocad.getText().toString();

        if(nomeTituloCad.isEmpty()) {
            Toast.makeText(this, "Informe o nome do filme!", Toast.LENGTH_SHORT).show();
        }else {
            nomeTituloCad = nomeTituloCad.replace(" ", "+");
            String params = "&t=" + nomeTituloCad;

            Api api = new Api(this);
            api.cadastraFilme(params);

            ETtitulocad.setText("");
        }
    }

    public void verFilmesCadastrados(View view) {
        Intent itFilmesCadastrados = new Intent(this, MoviesOfflineActivity.class);
        startActivity(itFilmesCadastrados);
    }


}
