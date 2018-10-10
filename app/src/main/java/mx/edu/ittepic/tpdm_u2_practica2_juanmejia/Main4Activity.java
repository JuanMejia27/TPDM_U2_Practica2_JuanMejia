package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    EditText diddueño,dnombre, ddomicilio, dtelefono;
    Button dmodificar, dborrar;
    Dueño dueño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        dueño = new Dueño(this);

        String id= getIntent().getExtras().getString("id");
        String nombre = getIntent().getExtras().getString("nombre");
        final String domicilio = getIntent().getExtras().getString("domicilio");
        String telefono = getIntent().getExtras().getString("telefono");

        diddueño = findViewById(R.id.diddueño);
        dnombre = findViewById(R.id.dnombre);
        ddomicilio = findViewById(R.id.ddomicilio);
        dtelefono = findViewById(R.id.dtelefono);

        dmodificar = findViewById(R.id.dmodificar);
        dborrar = findViewById(R.id.dborrar);

        diddueño.setText(id);
        dnombre.setText(nombre);
        ddomicilio.setText(domicilio);
        dtelefono.setText(telefono);

        dmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = dueño.actualizar(new Dueño(diddueño.getText().toString(),dnombre.getText().toString()
                        ,ddomicilio.getText().toString(),dtelefono.getText().toString()));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("Exito").setMessage("se actualizó").show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo actualizar").show();
                }
            }
        });

        dborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = dueño.eliminar(new Dueño(diddueño.getText().toString(),dnombre.getText().toString()
                        ,ddomicilio.getText().toString(),dtelefono.getText().toString()));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("Exito").setMessage("se eliminó").show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo eliminar").show();
                }

            }
        });
    }
}
