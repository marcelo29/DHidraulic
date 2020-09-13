package br.com.android.dhidraulic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.android.Util;
import br.com.android.domain.Banheiro;
import br.com.android.domain.EstruturaCasa;

public class BanheiroActivity extends AppCompatActivity {

    private Spinner spnIdBanheiro;
    TextView txtTorneira, txtChuveiro, txtBebedouro, txtPrivada, txtDucha, txtBanheira, txtTanque, txtMC;
    FloatingActionButton fabAddTorneira, fabAddChuveiro, fabAddBebedouro, fabAddPrivada, fabAddDucha, fabAddBanheira, fabAddTanque, fabAddMC;
    FloatingActionButton fabSubTorneira, fabSubChuveiro, fabSubBebedouro, fabSubPrivada, fabSubDucha, fabSubBanheira, fabSubTanque, fabSubMC;
    Button btnCtn;
    Integer numBanheiro;
    Switch swtValvula;
    private Banheiro banheiro;
    private EstruturaCasa casa;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banheiro);

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

        //fazer leitura do banco
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("dhidraulic/casa");

        casa = new EstruturaCasa();
        numBanheiro = 0;

        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                casa = dataSnapshot.getValue(EstruturaCasa.class);
                numBanheiro = casa.getNumBanheiro();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("onCancelled", "loadPost:onCancelled", databaseError.toException());
            }
        };

        dbRef.addValueEventListener(vel);

        ArrayList<Integer> list = new ArrayList<>();

        if (numBanheiro >= 1)
            for (int i = 1; i <= numBanheiro; i++) {
                list.add(i);
            }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnIdBanheiro.setAdapter(adapter);

        cliqueContinuar(this);
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
        //BanheiroDAO dao = new BanheiroDAO(ctx);
        //int idCasa = db.retornaCampoTabela("_id", Db.tbBanheiro);

        banheiro = new Banheiro();
        banheiro.setId(Integer.parseInt(spnIdBanheiro.getSelectedItem().toString()));

        if (banheiro.getId() != 0) {
            txtBanheira.setText(String.valueOf(banheiro.getNumBanheira()));
            txtBebedouro.setText(banheiro.getNumBebedouro());
            txtChuveiro.setText(banheiro.getNumChuveiro());
            txtDucha.setText(banheiro.getNumDucha());
            txtMC.setText(banheiro.getNumMC());
            txtPrivada.setText(banheiro.getNumPrivada());
            txtTanque.setText(banheiro.getNumTanque());
            txtTorneira.setText(banheiro.getNumTorneira());
            swtValvula.setChecked(banheiro.isValvula());

            //dao.atualizaBanheiro(banheiro, escrita);
            spnIdBanheiro.getNextFocusDownId();
            Util.showAviso(ctx, R.string.aviso_banheiro_atualizado);
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

            dbRef = db.getReference("dhidraulic/casa/banheiro");
            dbRef.setValue(banheiro);

            Util.showAviso(ctx, R.string.aviso_banheiro_salvo);
        }
    }
}