package br.com.android.dhidraulic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private ArrayList<Integer> lista;

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

        lista = new ArrayList<>();
        lista.add(1);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("dhidraulic/casa");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                casa = dataSnapshot.getValue(EstruturaCasa.class);
                numBanheiro = casa.getNumBanheiro();

                if (lista.size() == 1)
                    for (int i = 2; i <= numBanheiro; i++) {
                        lista.add(i);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("onCancelled", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbRef.addValueEventListener(vel);

        spnIdBanheiro.setAdapter(adapter);

        cliqueContinuar(this);
    }

    private void cliqueContinuar(final Context ctx) {
        btnCtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnIdBanheiro.getSelectedItem().equals(numBanheiro)) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(ctx);
                    dialogo.setMessage(R.string.aviso_banheiros_corretos);
                    dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //carrega proxima tela
                            carregaBanheiro(ctx);
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
        banheiro = new Banheiro();
        banheiro.setId(Integer.parseInt(spnIdBanheiro.getSelectedItem().toString()));
        String caminho="";
        for (int i = 1; i <= banheiro.getId(); i++) {
            caminho = caminho+i+"/";
        }
        dbRef = db.getReference("dhidraulic/casa/banheiro/"+caminho);
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                banheiro = dataSnapshot.getValue(Banheiro.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("onCancelled", "loadPost:onCancelled", databaseError.toException());
            }
        };

        if (banheiro != null) {
            txtBanheira.setText(String.valueOf(banheiro.getNumBanheira()));
            txtBebedouro.setText(String.valueOf(banheiro.getNumBebedouro()));
            txtChuveiro.setText(String.valueOf(banheiro.getNumChuveiro()));
            txtDucha.setText(String.valueOf(banheiro.getNumDucha()));
            txtMC.setText(String.valueOf(banheiro.getNumMC()));
            txtPrivada.setText(String.valueOf(banheiro.getNumPrivada()));
            txtTanque.setText(String.valueOf(banheiro.getNumTanque()));
            txtTorneira.setText(String.valueOf(banheiro.getNumTorneira()));
            swtValvula.setChecked(banheiro.isValvula());

            spnIdBanheiro.getNextFocusDownId();
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
        }

        Util.showAviso(ctx, R.string.aviso_banheiro_salvo);
        dbRef.setValue(banheiro);
    }
}