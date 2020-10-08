package br.com.android.dhidraulic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.android.Util;
import br.com.android.dao.BanheiroDAO;
import br.com.android.dao.Db;
import br.com.android.domain.Banheiro;

public class BanheiroActivity extends AppCompatActivity {
    private Spinner spnIdBanheiro;
    Db db;
    SQLiteDatabase escrita;
    TextView txtTorneira, txtChuveiro, txtBebedouro, txtPrivada, txtDucha, txtBanheira, txtTanque, txtMC;
    FloatingActionButton fabAddTorneira, fabAddChuveiro, fabAddBebedouro, fabAddPrivada, fabAddDucha, fabAddBanheira, fabAddTanque, fabAddMC;
    FloatingActionButton fabSubTorneira, fabSubChuveiro, fabSubBebedouro, fabSubPrivada, fabSubDucha, fabSubBanheira, fabSubTanque, fabSubMC;
    Button btnCtn;
    Integer numBanheiro;
    Switch swtValvula;
    Banheiro banheiro;
    BanheiroDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banheiro);

        db = new Db(this);

        // pega permissao de escrita no banco
        escrita = db.getWritableDatabase();

        spnIdBanheiro = (Spinner) findViewById(R.id.spnIdBanheiro);
        swtValvula = (Switch) findViewById(R.id.swtValvula);
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

        btnCtn = (Button) findViewById(R.id.btnCtn);

        numBanheiro = db.retornaCampoTabela("num_banheiro", "casa");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        for (int i = 2; i <= numBanheiro; i++) {
            list.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnIdBanheiro.setAdapter(adapter);

        cliqueContinuar(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void cliqueContinuar(final Context ctx) {
        btnCtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (spnIdBanheiro.getSelectedItem().equals(numBanheiro)) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(ctx);
                    dialogo.setMessage(R.string.aviso_banheiros_corretos);
                    dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //carrega proxima tela
                        }
                    });
                    dialogo.setNegativeButton("Não", null);
                    dialogo.show();
                } else {
                    carregaBanheiro(ctx);
                }
            }
        });
    }

    private void carregaBanheiro(Context ctx) {
        BanheiroDAO dao = new BanheiroDAO(ctx);
        int idCasa = db.retornaCampoTabela("_id", Db.tbCasa);

        banheiro = new Banheiro();
        banheiro.setId(Integer.parseInt(spnIdBanheiro.getSelectedItem().toString()));

        if (dao.existeBanheiro(banheiro.getId())) {
            banheiro = dao.getBanheiro(banheiro.getId());
            txtBanheira.setText(String.valueOf(banheiro.getNumBanheira()));
            txtBebedouro.setText(banheiro.getNumBebedouro());
            txtChuveiro.setText(banheiro.getNumChuveiro());
            txtDucha.setText(banheiro.getNumDucha());
            txtMC.setText(banheiro.getNumMC());
            txtPrivada.setText(banheiro.getNumPrivada());
            txtTanque.setText(banheiro.getNumTanque());
            txtTorneira.setText(banheiro.getNumTorneira());
            swtValvula.setChecked(banheiro.isValvula());

            dao.atualizaBanheiro(banheiro, escrita);
            spnIdBanheiro.getNextFocusDownId();
            //Util.showAviso(ctx, R.string.aviso_banheiro_atualizado);
        } else {
            banheiro.setNumBanheira(Util.converteParaInt(txtBanheira.getText().toString()));
            banheiro.setNumBebedouro(Integer.parseInt(txtBebedouro.getText().toString()));
            banheiro.setNumChuveiro(Integer.parseInt(txtChuveiro.getText().toString()));
            banheiro.setNumDucha(Integer.parseInt(txtDucha.getText().toString()));
            banheiro.setNumMC(Integer.parseInt(txtMC.getText().toString()));
            banheiro.setNumPrivada(Integer.parseInt(txtPrivada.getText().toString()));
            banheiro.setNumTanque(Integer.parseInt(txtTanque.getText().toString()));
            banheiro.setNumTorneira(Integer.parseInt(txtTorneira.getText().toString()));
            banheiro.setValvula(swtValvula.isChecked());

            dao.insereBanheiro(banheiro, escrita);
            dao.insereCasaBanheiro(idCasa, banheiro.getId(), escrita);
            //Util.showAviso(ctx, R.string.aviso_banheiro_salvo);
        }
    }
}