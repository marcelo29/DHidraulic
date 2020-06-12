package br.com.android.dhidraulic;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.android.Util;
import br.com.android.dao.Db;
import br.com.android.domain.EstruturaCasa;

public class MainActivity extends AppCompatActivity {

    EditText edtNumPavimentos, edtNumPessoas, edtNumCozinha, edtNumBanheiro, edtNumAreaDeServico;
    Button fabCtn;
    EstruturaCasa casa;
    Context ctx;
    Db db;
    SQLiteDatabase escrita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);

        edtNumPavimentos = (EditText) findViewById(R.id.edtNumPavimentos);
        edtNumPessoas = (EditText) findViewById(R.id.edtNumPessoas);
        edtNumCozinha = (EditText) findViewById(R.id.edtNumCozinha);
        edtNumBanheiro = (EditText) findViewById(R.id.edtNumBanheiro);
        edtNumAreaDeServico = (EditText) findViewById(R.id.edtNumAreaDeServico);
        fabCtn = (Button) findViewById(R.id.btnCtn);

        db = new Db(this);

        // pega permissao de escrita no banco
        escrita = db.getWritableDatabase();

        // Se o banco nao existir
        if (!db.doesDatabaseExist(this, db.getDatabaseName())) {
            // cria e popula o banco
            db.onCreate(escrita);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        clique();
    }

    private void clique() {
        fabCtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camposValidos()) {
                    casa = new EstruturaCasa();
                    casa.setNumPavimentos(Integer.parseInt(String.valueOf(edtNumPavimentos.getText().toString())));
                    casa.setNumAreaServico(Integer.parseInt(String.valueOf(edtNumAreaDeServico.getText())));
                    casa.setNumBanheiro(Integer.parseInt(String.valueOf(edtNumBanheiro.getText())));
                    casa.setNumCozinha(Integer.parseInt(String.valueOf(edtNumCozinha.getText())));
                    casa.setNumPessoas(Integer.parseInt(String.valueOf(edtNumPessoas.getText())));

                    db.insereCasa(escrita, casa);

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean camposValidos() {
        boolean flag = true;

        //validacao campos nulos
        if (edtNumPessoas.getText().toString().equals("") || Integer.parseInt(edtNumPessoas.getText().toString())==0 ){
            edtNumPessoas.setError(Util.AVISO_CAMPO_OBRIGATORIO);
            flag = false;
        }

        if(edtNumAreaDeServico.getText().toString().equals("") || Integer.parseInt(edtNumAreaDeServico.getText().toString())==0){
            edtNumAreaDeServico.setError(Util.AVISO_CAMPO_OBRIGATORIO);
            flag = false;
        }

        if(edtNumCozinha.getText().toString().equals("") || Integer.parseInt(edtNumCozinha.getText().toString())==0){
            edtNumCozinha.setError(Util.AVISO_CAMPO_OBRIGATORIO);
            flag = false;
        }

        if(edtNumPavimentos.getText().toString().equals("") || Integer.parseInt(edtNumPavimentos.getText().toString())==0){
            edtNumPavimentos.setError(Util.AVISO_CAMPO_OBRIGATORIO);
            flag = false;
        }

        if(edtNumBanheiro.getText().toString().equals("") || Integer.parseInt(edtNumBanheiro.getText().toString())==0){
            edtNumBanheiro.setError(Util.AVISO_CAMPO_OBRIGATORIO);
            flag = false;
        }

        return flag;
    }
}