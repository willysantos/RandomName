package willy.com.randomname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Numeros extends AppCompatActivity {

    TextView mResultado;
    EditText mComienzo;
    EditText mFinal;
    Button mIntento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);

        mResultado = findViewById(R.id.activity_numeros_tv_resultado);
        mComienzo = findViewById(R.id.activity_numeros_edt_empiezo);
        mFinal = findViewById(R.id.activity_numeros_edt_terminar);
        mIntento = findViewById(R.id.activity_numeros_btn_aleatorio);
    }

    public void Aleatorio(View view){
        String num = mComienzo.getText().toString();
        String num2 = mFinal.getText().toString();
        int n = Integer.parseInt(num);
        int n2 = Integer.parseInt(num2);

        int numero = (int) (Math.random() * n2);
        String valor = String.valueOf(numero);
        mResultado.setText(valor);
    }
}
