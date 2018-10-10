package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Dueño {
    String id, nombre, domicilio, telefono;
    SegurosCoche base;

    public Dueño(String id, String n, String d, String t){
        this.id = id;
        nombre = n;
        domicilio = d;
        telefono = t;
    }
    public Dueño(Activity activity){
        base = new SegurosCoche(activity,"buffete",null,1);
    }

    public boolean insertar(Dueño dueño){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("ID",dueño.id);
            data.put("NOMBRE",dueño.nombre);
            data.put("DOMICILIO",dueño.domicilio);
            data.put("TELEFONO",dueño.telefono);
            long res = tabla.insert("DUEÑO","null",data);
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

    public Dueño consultar(String id){
        Dueño pl = null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM DUEÑO WHERE ID=?";
            String claves[] = {id};

            Cursor c = tabla.rawQuery(SQL,claves);
            if(c.moveToFirst()){
                pl = new Dueño(c.getString(0),c.getString(1),c.getString(2)
                        ,c.getString(3));
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return pl;
    }//consultar

    public Dueño[] consulta(){
        Dueño[] resultado=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM DUEÑO";


            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Dueño[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Dueño(c.getString(0),c.getString(1),c.getString(2)
                            ,c.getString(3));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }//consultar

    public boolean eliminar(Dueño dueño){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String[] data = {""+dueño.id};
            long res = tabla.delete("DUEÑO","ID=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }//eliminar

    public boolean actualizar(Dueño dueño){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("ID",dueño.id);
            data.put("NOMBRE",dueño.nombre);
            data.put("DOMICILIO",dueño.domicilio);
            data.put("TELEFONO",dueño.telefono);
            String[] clave = {dueño.id};
            long res = tabla.update("DUEÑO",data,null,clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }//actualizar
}
