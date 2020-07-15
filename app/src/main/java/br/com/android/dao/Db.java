package br.com.android.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

import br.com.android.domain.EstruturaCasa;

public class Db extends SQLiteOpenHelper {

    // nome do banco
    public static final String DATABASE = "db_hidraulic";
    // versao
    public static final int VERSAO = 4;
    // para exibicao no log cat
    private static final String TAG = "appHidraulic";

    // tabelas do banco
    private static String tbCasa = "casa", tbBanheiro = "banheiro", tbCasaBanheiro = "casa_banheiro";

    public Db(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // string p criar a tabela no banco de dados
        String ddl = "create table if not exists " + tbCasa + "(_id integer primary key autoincrement, "
                + "num_Pessoas int, num_pavimentos int, num_cozinha int, num_area_servico int, num_banheiro int)";
        sqLiteDatabase.execSQL(ddl);

        ddl = "create table if not exists " + tbBanheiro + "(_id integer primary key autoincrement, " +
                "num_toneiras int, num_chuveiros int, num_privadas int, num_duchas int, num_banheira int, num_bebedouro int, " +
                "num_tanque int, num_MC int)";
        sqLiteDatabase.execSQL(ddl);

        ddl = "create table if not exists " + tbCasaBanheiro +  "(_id integer primary key autoincrement, " +
                "id_casa int, id_banheiro int, foreign key(id_casa) references casa(_id), " +
                "foreign key(id_banheiro) references banheiro(_id))";
        sqLiteDatabase.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        drop(sqLiteDatabase);

        onCreate(sqLiteDatabase);
    }

    // deleta o banco
    public void drop(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + tbCasa);

        Log.i(TAG, "*********** Drop rolou");
    }

    // verifica se a base ja existe
    public boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public void insereCasa(SQLiteDatabase db, EstruturaCasa casa) {
        ContentValues values = new ContentValues();

        values.put("numPavimentos", casa.getNumPavimentos());
        values.put("numPessoas", casa.getNumPessoas());
        values.put("numBanheiro", casa.getNumBanheiro());
        values.put("numCozinha", casa.getNumCozinha());
        values.put("numAreaServico", casa.getNumAreaServico());

        db.insert(tbCasa, null, values);
        Log.i(TAG, "insert rolou");
    }

    // retorna o id da tabela
    public int retornaCampoTabela(String campo, String tabela) {
        String sql = "select " + campo + " from " + tabela;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try {
            cursor.moveToLast();
            return cursor.getInt(0);
        } catch (Exception e) {
            Log.e("", e.getMessage());
            return 0;
        } finally {
            cursor.close();
        }
    }
}
