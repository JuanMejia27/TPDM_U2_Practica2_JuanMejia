package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    ListView listadueños;
    Dueño du;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listadueños = findViewById(R.id.listadueños);
        du = new Dueño(this);

        listadueños.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int fi, long l) {
                final Dueño[] s = du.consulta();
                final int i = fi;

                AlertDialog.Builder alerta = new AlertDialog.Builder(Main3Activity.this);
                alerta.setTitle("Detalle de "+s[i].nombre)
                        .setMessage("id: "+s[i].id+"\nDomicilio: "+s[i].domicilio+"\nTelefono: "
                                +s[i].telefono+"\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {

                                Toast.makeText(Main3Activity.this,""+s[i].id+" "+s[i].nombre+" "+s[i].domicilio+" "+s[i].telefono,
                                        Toast.LENGTH_LONG).show();
                                Intent i44 = new Intent(Main3Activity.this, Main4Activity.class);
                                i44.putExtra("id",s[i].id);
                                i44.putExtra("nombre",s[i].nombre);
                                i44.putExtra("domicilio",s[i].telefono);
                                i44.putExtra("telefono",s[i].telefono);
                                startActivity(i44);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        try {
            String nombres[] = {"No hay polizas capturados aun"};
            Dueño[] s = du.consulta();
            if (s!=null) {
                nombres = new String[s.length];
                for (int i = 0; i < nombres.length; i++) {
                    nombres[i] = s[i].nombre;
                }
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, nombres);
            listadueños.setAdapter(adaptador);
        } catch (Exception e){

        }
    }
}
