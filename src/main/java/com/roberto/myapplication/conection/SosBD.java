package com.roberto.myapplication.conection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.roberto.myapplication.model.Sos;

import java.util.ArrayList;
import java.util.List;

public class SosBD extends SQLiteOpenHelper {

    private static final String TAG = "sql_sos";
    public static final String NOME_BANCO = "sos";
    private static final int VERSAO = 1;

    public SosBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando a tabela");
        db.execSQL(
                "CREATE TABLE sos (" +
                        "  idsos int(10) NOT NULL," +
                        "  data_sos date NOT NULL," +
                        "  hora_sos time NOT NULL," +
                        "  usuario int(10) NOT NULL," +
                        "  ocorrencia int(10) NOT NULL," +
                        "  latitude_sos double NOT NULL," +
                        "  longitude_sos double NOT NULL," +
                        "  descricao_sos text," +
                        "  atendido_sos int(11) DEFAULT NULL," +
                        "  vizualizado_sos int(10) DEFAULT NULL," +
                        "  cancelar tinyint(1)," +
                        "  PRIMARY KEY (`idsos`)" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private List<Sos> toList(Cursor c) {
        Log.i(TAG, "" + c.getCount());
        List<Sos> list = new ArrayList<Sos>();
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                Sos sos = new Sos();
                Log.i(TAG, "" + c.getInt(c.getColumnIndex("idsos")));
                sos.setIdsos(c.getInt(c.getColumnIndex("idsos")));
                sos.setDataSos(c.getString(c.getColumnIndex("data_sos")));
                sos.setHoraSos(c.getString(c.getColumnIndex("hora_sos")));
                sos.setUsuario(c.getInt(c.getColumnIndex("usuario")));
                sos.setOcorrencia(c.getInt(c.getColumnIndex("ocorrencia")));
                sos.setLatitudeSos(c.getDouble(c.getColumnIndex("latitude_sos")));
                sos.setLongitudeSos(c.getDouble(c.getColumnIndex("longitude_sos")));
                sos.setDescricaoSos(c.getString(c.getColumnIndex("descricao_sos")));
                sos.setAtendidoSos(c.getInt(c.getColumnIndex("atendido_sos")));
                sos.setVizualizadoSos(c.getInt(c.getColumnIndex("vizualizado_sos")));
                sos.setCanceladoSos((c.getInt(c.getColumnIndex("cancelar"))));
                list.add(sos);
            } while (c.moveToNext());
        }
        return list;
    }

    public void save(Sos sos) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("idsos", sos.getIdsos());
            Log.i(TAG, "insert" + sos.getIdsos());
            values.put("data_sos", sos.getDataSos());
            values.put("hora_sos", sos.getHoraSos());
            values.put("usuario", sos.getUsuario());
            values.put("ocorrencia", sos.getOcorrencia());
            values.put("latitude_sos", sos.getLatitudeSos());
            values.put("longitude_sos", sos.getLongitudeSos());
            values.put("atendido_sos", sos.getAtendidoSos());
            values.put("vizualizado_sos", sos.getVizualizadoSos());
            values.put("descricao_sos", sos.getDescricaoSos());
            values.put("cancelar", sos.getCanceladoSos());
            if (!countId(sos)) {
                db.insert("sos", "", values);
                Log.i(TAG, "insert");
            } else {
                String idsos = String.valueOf(sos.getIdsos());
                String[] _idsos = new String[]{idsos};
                db.update("sos", values, "idsos=?", _idsos);
                Log.i(TAG, "update");
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            //db.close();
        }
    }

    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete("sos", null, null);
        } catch (Exception e) {

        } finally {
            //db.close();
        }
    }

    public List<Sos> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.query("sos",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            Log.i(TAG, "findAll");
            return toList(c);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        } finally {
            //db.close();
        }
    }

    public boolean countId(Sos sos) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.query("sos", null, "idsos=" + sos.getIdsos(), null, null, null, null, null);
            Log.i(TAG, "" + (c.getCount() > 0));
            return c.getCount() > 0;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return false;
        } finally {
            //db.close();
        }
    }

    public Sos findSos(int idsos) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.query("sos", null, "idsos=" + idsos, null, null, null, null, null);
            Log.i(TAG, "findSos()");
            return toList(c).get(0);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        } finally {
            //db.close();
        }
    }

    public void atendido(Sos sos) {
        sos.setAtendidoSos(1);
        save(sos);
    }

    public void cancelado(Sos sos) {
        sos.setCanceladoSos(1);
        save(sos);
    }
}