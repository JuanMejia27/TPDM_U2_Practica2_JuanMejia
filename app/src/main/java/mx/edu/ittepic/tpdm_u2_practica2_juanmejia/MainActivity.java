package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText iddueño, nombre, telefono, domicilio;
    Button guardar;
    Dueño d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iddueño = findViewById(R.id.iddueño);
        nombre = findViewById(R.id.nombre);
        domicilio = findViewById(R.id.domicilio);
        telefono = findViewById(R.id.telefono);

        guardar = findViewById(R.id.guardarabogado);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                d = new Dueño(MainActivity.this);
                boolean res = d.insertar(new Dueño(iddueño.getText().toString(), nombre.getText().toString()
                        , domicilio.getText().toString(), telefono.getText().toString()));

                if(res) {
                    Toast.makeText(MainActivity.this,"Se pudo insertar",
                            Toast.LENGTH_LONG).show();
                    iddueño.setText("");
                    nombre.setText("");
                    domicilio.setText("");
                    telefono.setText("");
                } else {
                    Toast.makeText(MainActivity.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
