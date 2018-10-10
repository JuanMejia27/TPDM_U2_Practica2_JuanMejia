package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {
    EditText modelo2, marca2, año2, fechainicio2, precio2, tipopoliza2;
    Spinner piddueño2;
    Button pmodificar, peliminar, pregresar2;
    int id;
    Dueño[] dueños;
    Poliza poliza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        modelo2 = findViewById(R.id.modelo2);
        marca2 = findViewById(R.id.marca2);
        año2 = findViewById(R.id.año2);
        fechainicio2 = findViewById(R.id.fechainicio2);
        precio2 = findViewById(R.id.precio2);
        tipopoliza2 = findViewById(R.id.tipopoliza2);
        piddueño2 = findViewById(R.id.piddueño2);

        pmodificar = findViewById(R.id.pmodificar);
        peliminar = findViewById(R.id.peliminar);
        pregresar2 = findViewById(R.id.pregresar2);

        id = getIntent().getExtras().getInt("id");
        String mx= getIntent().getExtras().getString("modelo");
        String max = getIntent().getExtras().getString("marca");
        String ax = getIntent().getExtras().getString("año");
        String fx = getIntent().getExtras().getString("fechainicio");
        String px = getIntent().getExtras().getString("precio");
        String tx = getIntent().getExtras().getString("tipopoliza");
        int iddx = getIntent().getExtras().getInt("iddueño");

        modelo2.setText(mx);
        marca2.setText(max);
        año2.setText(ax);
        fechainicio2.setText(fx);
        precio2.setText(px);
        tipopoliza2.setText(tx);
        piddueño2.setPrompt(""+piddueño2);


        dueños = new Dueño(this).consulta();
        if(dueños.length==0){
            Toast.makeText(this,"NO HAY ABOGADOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
            pmodificar.setEnabled(false);peliminar.setEnabled(false);
            piddueño2.setEnabled(false);
            return;
        }
        final String[] nombres = new String[dueños.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = dueños[i].nombre;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        piddueño2.setAdapter(adaptador);
        poliza = new Poliza(this);

        pregresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        peliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(poliza.eliminar(new Poliza(0,modelo2.getText().toString(),marca2.getText().toString(),
                        Integer.parseInt(año2.getText().toString()),fechainicio2.getText().toString(),
                        Float.parseFloat(precio2.getText().toString()),tipopoliza2.getText().toString(),
                        dueños[piddueño2.getSelectedItemPosition()].id))){
                    Toast.makeText(Main6Activity.this,"SE BORRO POLIZA",
                            Toast.LENGTH_LONG).show();
                    /*idabogado.setEnabled(false);
                    descripcion.setEnabled(false);
                    cliente.setEnabled(false);
                    fecha.setEnabled(false);
                    idabogado.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);*/
                } else {
                    Toast.makeText(Main6Activity.this,"NO SE BORRO POLIZA",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        pmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(poliza.actualizar(new Poliza(0,modelo2.getText().toString(),marca2.getText().toString(),
                        Integer.parseInt(año2.getText().toString()),fechainicio2.getText().toString(),
                        Float.parseFloat(precio2.getText().toString()),tipopoliza2.getText().toString(),
                        dueños[piddueño2.getSelectedItemPosition()].id))){
                    Toast.makeText(Main6Activity.this,"SE MODIFICO POLIZA",
                            Toast.LENGTH_LONG).show();
                    /*idabogado.setEnabled(false);
                    descripcion.setEnabled(false);
                    cliente.setEnabled(false);
                    fecha.setEnabled(false);
                    idabogado.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);*/
                } else {
                    Toast.makeText(Main6Activity.this,"NO SE MODIFICO POLIZA",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
