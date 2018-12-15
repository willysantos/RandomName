package willy.com.randomname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_principal extends AppCompatActivity {

    Button mBotonRestaurante;
    Button mBotonNombre;
    Button mBotonNumero;
    Button mBotonSalir;
    Button mBotonLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        mBotonNombre = findViewById(R.id.activity_menuprincipal_btn_nombres);
        mBotonRestaurante = findViewById(R.id.activity_menuprincipak_btn_restaurante);
        mBotonNumero = findViewById(R.id.activity_menuprincipal_btn_numeros);
        mBotonSalir = findViewById(R.id.activity_menu_principal_btn_salir);
        mBotonLugares =  findViewById(R.id.activity_menuprincipal_btn_lugares);

        mBotonNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_principal.this ,Nombres.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        mBotonRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_principal.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        mBotonNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_principal.this, Numeros.class);
                startActivity(intent);
            }
        });
        mBotonLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_principal.this, Lugares.class);
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });


    }
    public void Salir(View view){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
