package br.com.android.dhidraulic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.android.Util;
import br.com.android.domain.EstruturaCasa;

public class MainActivity extends AppCompatActivity {

    EditText edtNumPavimentos, edtNumPessoas, edtNumCozinha, edtNumBanheiro, edtNumAreaDeServico;
    Button fabCtn;
    EstruturaCasa casa;
    Context ctx;
    FirebaseDatabase db;
    private FirebaseAuth fbau;

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

        fbau = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = fbau.getCurrentUser();
        //updateUI(user);
        clique();
    }

    private void clique() {
        fabCtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camposValidos()) {
                    casa = new EstruturaCasa();
                    casa.setNumPavimentos(Integer.parseInt(edtNumPavimentos.getText().toString()));
                    casa.setNumAreaServico(Integer.parseInt(edtNumAreaDeServico.getText().toString()));
                    casa.setNumBanheiro(Integer.parseInt(edtNumBanheiro.getText().toString()));
                    casa.setNumCozinha(Integer.parseInt(edtNumCozinha.getText().toString()));
                    casa.setNumPessoas(Integer.parseInt(edtNumPessoas.getText().toString()));

                    db = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = db.getReference("dhidraulic/casa");
                    dbRef.setValue(casa);

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