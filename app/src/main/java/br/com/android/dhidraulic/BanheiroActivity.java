package br.com.android.dhidraulic;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.android.dao.Db;

public class BanheiroActivity extends AppCompatActivity {
    private Spinner spnIdBanheiro;
    Db db;
    SQLiteDatabase escrita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banheiro);

        db = new Db(this);

        // pega permissao de escrita no banco
        escrita = db.getWritableDatabase();

        spnIdBanheiro = (Spinner) findViewById(R.id.spnIdBanheiro);

        Integer numBanheiro = db.retornaCampoTabela("numBanheiro", "casa");
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= numBanheiro; i++){
            list.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnIdBanheiro.setAdapter(adapter);


    }
}