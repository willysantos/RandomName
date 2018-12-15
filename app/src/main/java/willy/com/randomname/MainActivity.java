package willy.com.randomname;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;//
    int id;

    //esto es para el array con los nombres
    ListView lista;
    ArrayAdapter<String> infomacion;
    String[] arreglo;
    //demas funciones
    EditText mTexto;
    ImageButton mBotonAgregar;
    Button mRandom;
    TextView mResultado;
    ImageView mEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = getIntent().getIntExtra("id", 1);
        mBotonAgregar = findViewById(R.id.activity_main_iv_ingresar);
        mTexto = findViewById(R.id.main_activity_pt_ingresar);
        lista = findViewById(R.id.list_view);
        mRandom = findViewById(R.id.activity_main_btn_buscar);
        mResultado = findViewById(R.id.activity_main_tv_texto);
//        eliminar();
        cargar();

    }
    public void GuardarDatos(View view){
        String nombre = mTexto.getText().toString();

        BasedeDatos basedeDatos = new BasedeDatos(this,"DEMODB", null,1);

        SQLiteDatabase db = basedeDatos.getWritableDatabase();
        if (db != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_evento", id);
            contentValues.put("nombre", nombre);

            db.insert("datos", null, contentValues);
            Toast.makeText(this, "Dato ingresado correctamente", Toast.LENGTH_SHORT).show();
        }
        mTexto.setText("");
        cargar();
    }
    public void cargar(){
        BasedeDatos basedeDatos = new BasedeDatos(this,"DEMODB", null,1);
        SQLiteDatabase db = basedeDatos.getWritableDatabase();

        if (db != null){
            Cursor c = db.rawQuery("select *from datos where id_evento = "+id, null);
            int cantidad = c.getCount();
            int i = 0;
            arreglo = new String[cantidad];
            if(c.moveToFirst()) {
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
    public void random2(View view){
        int indice = new Random().nextInt(arreglo.length);
        String resultado = arreglo[indice];
        mResultado.setText(resultado);
    }
    public void eliminar(){
        BasedeDatos basedeDatos = new BasedeDatos(this,"DEMODB", null,1);
        SQLiteDatabase db = basedeDatos.getWritableDatabase();
        db.execSQL("DELETE FROM datos");
    }
}
