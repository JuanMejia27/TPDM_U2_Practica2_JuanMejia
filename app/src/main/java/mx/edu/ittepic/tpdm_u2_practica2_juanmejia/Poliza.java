package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Poliza {
    int idcoche,año;
    String modelo, marca, tipopoliza, iddueño, fechaInicio;
    float precio;
    SegurosCoche base;

    public Poliza(int id, String mdl, String mrc, int añ, String fecha, float p,String tipo, String idd){
        idcoche = id;
        modelo = mdl;
        marca = mrc;
        año = añ;
        fechaInicio = fecha;
        precio = p;
        tipopoliza = tipo;
        iddueño = idd;
    }

    public Poliza(Activity activity){
        base = new SegurosCoche(activity,"buffete",null,1);
    }

    public boolean insertar(Poliza poliza){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("MODELO",poliza.modelo);
            data.put("MARCA",poliza.marca);
            data.put("AÑO",poliza.año);
            data.put("FECHAINICIO",poliza.fechaInicio);
            data.put("PRECIO",poliza.precio);
            data.put("TIPOPOLIZA",poliza.tipopoliza);
            data.put("IDDUEÑO",poliza.iddueño);
            long res = tabla.insert("POLIZA","IDCOCHE",data);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            Log.e("ERROR: ",e.getMessage());
            return false;
        }
        return true;
    }

    public Poliza consultar(String coche){
        Poliza pl=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM POLIZA WHERE IDCOCHE=?";
            String claves[] = {coche};

            Cursor c = tabla.rawQuery(SQL,claves);
            if(c.moveToFirst()){
                pl = new Poliza(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3)
                        ,c.getString(4),c.getFloat(5),c.getString(6),c.getString(7));
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return pl;
    }

    public Poliza[] consulta(){
        Poliza[] resultado=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM POLIZA";

            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Poliza[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Poliza(c.getInt(0),c.getString(1),c.getString(2)
                            ,c.getInt(3),c.getString(4),c.getFloat(5),c.getString(6)
                            ,c.getString(7));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean eliminar(Poliza poliza){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String[] data = {""+poliza.idcoche};
            long res = tabla.delete("POLIZA","IDCOCHE=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(Poliza poliza){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("MODELO",poliza.modelo);
            data.put("MARCA",poliza.marca);
            data.put("AÑO",poliza.año);
            data.put("FECHAINICIO",poliza.fechaInicio);
            data.put("PRECIO",poliza.precio);
            data.put("TIPOPOLIZA",poliza.tipopoliza);
            data.put("IDDUEÑO",poliza.iddueño);
            String[] clave = {""+poliza.idcoche};
            long res = tabla.update("POLIZA",data,"IDCOCHE=?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }
}
