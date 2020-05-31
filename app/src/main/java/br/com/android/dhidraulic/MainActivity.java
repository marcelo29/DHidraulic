package br.com.android.dhidraulic;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import br.com.android.dhidraulic.databinding.ActivityMainBinding;
import br.com.android.domain.EstruturaCasa;

public class MainActivity extends AppCompatActivity {

    EditText edtNumPavimentos, edtNumPessoas;
    EstruturaCasa casa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casa = new EstruturaCasa();
        //casa.setNumPavimentos();
    }
}