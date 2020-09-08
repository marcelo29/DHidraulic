package br.com.android.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.android.domain.Banheiro;

public class BanheiroDAO extends SQLiteOpenHelper {
    public BanheiroDAO(Context context) {
        super(context, Db.DATABASE, null, Db.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insereBanheiro(Banheiro banheiro, SQLiteDatabase sqLiteDatabase) {
        ContentValues cv = new ContentValues();

        cv.put("num_banheiro", banheiro.getId());
        cv.put("num_torneiras", banheiro.getNumTorneira());
        cv.put("num_chuveiros", banheiro.getNumChuveiro());
        cv.put("num_privadas", banheiro.getNumPrivada());
        cv.put("num_duchas", banheiro.getNumDucha());
        cv.put("num_banheira", banheiro.getNumBanheira());
        cv.put("num_bebedouro", banheiro.getNumBebedouro());
        cv.put("num_tanque", banheiro.getNumTanque());
        cv.put("num_MC", banheiro.getNumMC());
        //cv.put("valvula", banheiro.isValvula());

        sqLiteDatabase.insert(Db.tbBanheiro, null, cv);
    }

    public void atualizaBanheiro(Banheiro banheiro, SQLiteDatabase escrita) {
        String sql = "update banheiro set num_torneiras = " + banheiro.getNumTorneira() + ", num_chuveiros = " + banheiro.getNumChuveiro()
                + ", num_privadas = " + banheiro.getNumPrivada() + ", num_duchas = " + banheiro.getNumDucha() + ", num_banheira = " + banheiro.getNumBanheira()
                + ", num_bebedouro = " + banheiro.getNumBebedouro() + ", num_tanque = " + banheiro.getNumTanque() + ", num_MC = " + banheiro.getNumMC() + ", valvula = "
                + banheiro.isValvula() + " where num_banheiro = " + banheiro.getId();

        escrita.execSQL(sql);
    }

    public void insereCasaBanheiro(int idCasa, int idBanheiro, SQLiteDatabase escrita) {
        ContentValues cv = new ContentValues();
        cv.put("id_casa", idCasa);
        cv.put("id_banheiro", idBanheiro);

        escrita.insert(Db.tbCasaBanheiro, null, cv);
    }

    public boolean existeBanheiro(int id) {
        String sql = "select * from banheiro";
        Cursor cs = getReadableDatabase().rawQuery(sql, null);

        try {
            return cs.moveToFirst();
        } catch (Exception e) {
            return false;
        } finally {
            cs.close();
        }
    }

    public Banheiro getBanheiro(int id) {
        String sql = "select * from banheiro where num_banheiro = " + id;
        Cursor cs = getReadableDatabase().rawQuery(sql, null);
        Banheiro banheiro = new Banheiro();
        try {
            banheiro.setId(cs.getInt(0));
            banheiro.setNumTorneira(cs.getInt(1));
            banheiro.setNumChuveiro(cs.getInt(2));
            banheiro.setNumPrivada(cs.getInt(3));
            banheiro.setNumDucha(cs.getInt(4));
            banheiro.setNumBanheira(cs.getInt(5));
            banheiro.setNumBebedouro(cs.getInt(6));
            banheiro.setNumTanque(cs.getInt(7));
            banheiro.setNumMC(cs.getInt(8));
            banheiro.setValvula(cs.getInt(9) > 0);
            return banheiro;
        } catch (Exception e) {
            return null;
        } finally {
            cs.close();
        }
    }
}