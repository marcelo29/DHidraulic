package br.com.android.dhidraulic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import br.com.android.adapters.CasaAdapter;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        CasaAdapter adapter = new CasaAdapter(this);
        rv.setAdapter(adapter);*/
        Button btnAreaDServico = (Button) findViewById(R.id.btnAreaServico);
        Button btnBanheiro = (Button) findViewById(R.id.btnBanheiro);
        Button btnCozinha = (Button) findViewById(R.id.btnCozinha);

        btnAreaDServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AreaDeServicoActivity.class);
                startActivity(intent);
            }
        });

        btnBanheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, BanheiroActivity.class);
                startActivity(intent);
            }
        });

        btnCozinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CozinhaActivity.class);
                startActivity(intent);
            }
        });

    }
}