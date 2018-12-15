package willy.com.randomname;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Nombres extends AppCompatActivity {

    EditText mInput;
    ListView lista;
    ImageButton mInsertar;
    Button mRandom;
    TextView mTexto;

    ArrayAdapter<String> infomacion;
    int id;
    String[] arreglo;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres);
        id = getIntent().getIntExtra("id", 2);
        mInput = findViewById(R.id.activity_nombre_edt_ingresar);
        lista = findViewById(R.id.activity_nombres_lista);
        mInsertar = findViewById(R.id.activity_nombres_btn_ingresar);
        mRandom = findViewById(R.id.activity_nombres_btn_random);
        mTexto = findViewById(R.id.activity_nombres_texto);


        cargar();
    }

    public void GuardarDatos(View view){
        String nombre = mInput.getText().toString();

        BasedeDatos basedeDatos = new BasedeDatos(this,"DEMODB", null,1);

        SQLiteDatabase db = basedeDatos.getWritableDatabase();
        if (db != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_evento", id);
            contentValues.put("nombre", nombre);

            db.insert("datos", null, contentValues);
            Toast.makeText(this, "Dato ingresado correctamente", Toast.LENGTH_SHORT).show();
        }
        mInput.setText("");
        cargar();
    }

    public void cargar() {
        BasedeDatos basedeDatos = new BasedeDatos(this, "DEMODB", null, 1);
        SQLiteDatabase db = basedeDatos.getWritableDatabase();

        if (db != null) {
            Cursor c = db.rawQuery("select *from datos where id_evento = "+id, null);
            int cantidad = c.getCount();
            int i = 0;
            arreglo = new String[cantidad];
            if (c.moveToFirst()) {
                do {
                    String linea = c.getString(2);
                    arreglo[i] = linea;
                    i++;
                } while (c.moveToNext());
            }
            infomacion = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
            lista.setAdapter(infomacion);
        }
    }
    public void Random(View view){
        int indice = new Random().nextInt(arreglo.length);
        String resultado = arreglo[indice];
        mTexto.setText(resultado);
    }

}
