package willy.com.randomname;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Lugares extends AppCompatActivity {

    ImageButton mBotonAgregar;
    Button mBotonEncontrar;
    TextView mResultado;
    ListView mLista;
    EditText mItem;

    ArrayAdapter<String> infomacion;
    String[] arreglo;
    ArrayList<Datos> mDatos;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

        mBotonAgregar =  findViewById(R.id.activity_lugares_iv_ingresar);
        mBotonEncontrar = findViewById(R.id.activity_lugares_btn_encontrar);
        mResultado =  findViewById(R.id.activity_lugares_tv_resultado);
        mLista =  findViewById(R.id.activity_lugares_listview);
        mItem = findViewById(R.id.main_activity_lugares_edt_ingresar);

        id = getIntent().getIntExtra("id", 1);



        cargar();
    }

    public void GuardarDatos(View view){
        String nombre = mItem.getText().toString();

        BasedeDatos basedeDatos = new BasedeDatos(this,"DEMODB", null,1);

        SQLiteDatabase db = basedeDatos.getWritableDatabase();
        if (db != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_evento", id);
            contentValues.put("nombre", nombre);

            db.insert("datos", null, contentValues);
            Toast.makeText(this, "Dato ingresado correctamente", Toast.LENGTH_SHORT).show();
        }
        mItem.setText("");
        cargar();
    }

    public void cargar(){
        BasedeDatos basedeDatos = new BasedeDatos(this,"DEMODB", null,1);
        SQLiteDatabase db = basedeDatos.getWritableDatabase();

//        if (db != null){
//            Cursor c = db.rawQuery("select *from datos where id_evento = "+id, null);
//            int cantidad = c.getCount();
//            int i = 0;
//            arreglo = new String[cantidad];
//            if(c.moveToFirst()) {
//                do {
//                    String linea = c.getString(2);
//                    arreglo[i] = linea;
//                    i++;
//                } while (c.moveToNext());
//            }
//            infomacion = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
//            mLista.setAdapter(infomacion);
//        }
        if (db != null){
            Cursor c = db.rawQuery("select *from datos where id_evento = "+id, null);
            int cantidad = c.getCount();
            int i = 0;
            arreglo = new String[cantidad];
            if (c.moveToFirst()){
                do{
                    int cod = c.getInt(0);
                    String titulo = c.getString(2);
                    mDatos.add(new Datos(cod,titulo));
                }while (c.moveToNext());
                Adaptador adaptador = new Adaptador(getApplicationContext(),mDatos);
                mLista.setAdapter(adaptador);
            }

        }
    }


    public void random2(View view) {
        int indice = new Random().nextInt(arreglo.length);
        String resultado = arreglo[indice];
        mResultado.setText(resultado);
    }
}
