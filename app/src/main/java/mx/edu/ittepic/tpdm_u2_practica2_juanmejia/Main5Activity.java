package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    EditText modelo, marca, año, fechainicio, precio, tipopoliza;
    Spinner piddueño;
    Button pguardar, pregresar;
    Dueño[] dueños;
    Poliza poliza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        modelo = findViewById(R.id.modelo);
        marca = findViewById(R.id.marca);
        año = findViewById(R.id.año);
        fechainicio = findViewById(R.id.fechainicio);
        precio = findViewById(R.id.precio);
        tipopoliza = findViewById(R.id.tipopoliza);
        piddueño = findViewById(R.id.piddueño);
        poliza = new Poliza(this);

        pguardar = findViewById(R.id.pguardar);
        pregresar = findViewById(R.id.pregresar);

        dueños = new Dueño(this).consulta();
        if(dueños.length==0){
            Toast.makeText(this,"NO HAY ABOGADOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
            pguardar.setEnabled(false);
            piddueño.setEnabled(false);
            return;
        }

        String[] nombres = new String[dueños.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = dueños[i].nombre;
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        piddueño.setAdapter(adaptador);

        pguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = piddueño.getSelectedItemPosition();
                Poliza pl = new Poliza(0,modelo.getText().toString(),marca.getText().toString(),Integer.parseInt(año.getText().toString()),
                        fechainicio.getText().toString(),Integer.parseInt(precio.getText().toString()),tipopoliza.getText().toString(),
                        dueños[pos].id);

                boolean respuesta = poliza.insertar(pl);
                if(respuesta) {
                    Toast.makeText(Main5Activity.this,"Se pudo insertar",
                            Toast.LENGTH_LONG).show();
                    modelo.setText("");marca.setText("");año.setText("");fechainicio.setText("");
                    precio.setText("");tipopoliza.setText("");
                } else {
                    Toast.makeText(Main5Activity.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        pregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
