package br.com.android.dhidraulic;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.android.dao.Db;

public class BanheiroActivity extends AppCompatActivity {
    private Spinner spnIdBanheiro;
    Db db;
    SQLiteDatabase escrita;
    TextView txtTorneira, txtChuveiro, txtBebedouro, txtPrivada, txtDucha, txtBanheira, txtTanque, txtMC;
    FloatingActionButton fabAddTorneira, fabAddChuveiro, fabAddBebedouro, fabAddPrivada, fabAddDucha, fabAddBanheira, fabAddTanque, fabAddMC;
    FloatingActionButton fabSubTorneira, fabSubChuveiro, fabSubBebedouro, fabSubPrivada, fabSubDucha, fabSubBanheira, fabSubTanque, fabSubMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banheiro);

        db = new Db(this);

        // pega permissao de escrita no banco
        escrita = db.getWritableDatabase();

        spnIdBanheiro = (Spinner) findViewById(R.id.spnIdBanheiro);

        txtBanheira = (TextView) findViewById(R.id.txtBanheira);
        txtBebedouro = (TextView) findViewById(R.id.txtBebedouro);
        txtChuveiro = (TextView) findViewById(R.id.txtChuveiro);
        txtDucha = (TextView) findViewById(R.id.txtDucha);
        txtMC = (TextView) findViewById(R.id.txtMC);
        txtTanque = (TextView) findViewById(R.id.txtTanque);
        txtPrivada = (TextView) findViewById(R.id.txtPrivada);
        txtTorneira = (TextView) findViewById(R.id.txtTorneira);

        fabAddBanheira = (FloatingActionButton) findViewById(R.id.fabAddBanheira);
        fabAddBebedouro = (FloatingActionButton) findViewById(R.id.fabAddBebedouro);
        fabAddChuveiro = (FloatingActionButton) findViewById(R.id.fabAddChuveiro);
        fabAddDucha = (FloatingActionButton) findViewById(R.id.fabAddDucha);
        fabAddMC = (FloatingActionButton) findViewById(R.id.fabAddMC);
        fabAddPrivada = (FloatingActionButton) findViewById(R.id.fabAddPrivada);
        fabAddTanque = (FloatingActionButton) findViewById(R.id.fabAddTanque);
        fabAddTorneira = (FloatingActionButton) findViewById(R.id.fabAddTorneira);

        fabSubBanheira = (FloatingActionButton) findViewById(R.id.fabSubBanheira);
        fabSubBebedouro = (FloatingActionButton) findViewById(R.id.fabSubBebedouro);
        fabSubChuveiro = (FloatingActionButton) findViewById(R.id.fabSubChuveiro);
        fabSubDucha = (FloatingActionButton) findViewById(R.id.fabSubDucha);
        fabSubMC = (FloatingActionButton) findViewById(R.id.fabSubMC);
        fabSubPrivada = (FloatingActionButton) findViewById(R.id.fabSubPrivada);
        fabSubTanque = (FloatingActionButton) findViewById(R.id.fabSubTanque);
        fabSubTorneira = (FloatingActionButton) findViewById(R.id.fabSubTorneira);

        Integer numBanheiro = db.retornaCampoTabela("num_banheiro", "casa");
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= numBanheiro; i++){
            list.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnIdBanheiro.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}