package mx.edu.ittepic.tpdm_u2_practica2_juanmejia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText clave;
    Button buscar;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(i);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Main2Activity.this);
                Poliza poli = new Poliza(Main2Activity.this);
                final Poliza polizas[] = poli.consulta();
                final int pos = i;


                alerta.setTitle("Detalle de "+polizas[pos].iddueño)
                        .setMessage("Tipo de poliza: "+polizas[pos].tipopoliza+"\nID: "+polizas[pos].idcoche
                                +"\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {

                                Intent dpr = new Intent(Main2Activity.this, Main6Activity.class);
                                dpr.putExtra("idcoche",polizas[pos].idcoche);
                                dpr.putExtra("modelo",polizas[pos].modelo);
                                dpr.putExtra("marca",polizas[pos].marca);
                                dpr.putExtra("año",polizas[pos].año);
                                dpr.putExtra("fechainicio",polizas[pos].fechaInicio);
                                dpr.putExtra("precio",polizas[pos].precio);
                                dpr.putExtra("tipopoliza",polizas[pos].tipopoliza);
                                dpr.putExtra("iddueño",polizas[pos].iddueño);

                                startActivity(dpr);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.capturadueño:
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.detalledueño:
                Intent i2 = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(i2);
                break;
        }
        return true;
    }

    protected void onStart(){
        super.onStart();
        Poliza poli = new Poliza(this);
        Poliza polizas[] = poli.consulta();
        if(polizas==null){
            Toast.makeText(this,"NO HAY EXPEDIENTES",Toast.LENGTH_LONG).show();
        } else {
            String NoExp[] = new String[polizas.length];
            for (int i = 0; i < NoExp.length; i++) {
                NoExp[i] = polizas[i].idcoche + "\n" + polizas[i].iddueño;
            }
            ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NoExp);
            lista.setAdapter(adap);
        }
    }

}
