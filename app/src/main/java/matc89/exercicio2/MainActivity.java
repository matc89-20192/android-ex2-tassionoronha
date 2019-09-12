package matc89.exercicio2;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String nome = "";
    private TextView textView;
    private Button btnTrocar;
    private static final int TELA_CONFIRMA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnTrocar = (Button) findViewById(R.id.btnTrocar);
        btnTrocar.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(nome)) {
            textView.setText("Oi!");
        } else {
            textView.setText(String.format("Oi, %s!", nome));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textoAtual", nome);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nome = savedInstanceState.getString("textoAtual");
        textView.setText(nome);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TELA_CONFIRMA && resultCode == this.RESULT_OK) {
            nome = data.getExtras().getString("novoNome");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTrocar) {
            Intent intent = new Intent(MainActivity.this, OutraActivity.class);
            intent.putExtra("novoNome", nome);
            startActivityForResult(intent, TELA_CONFIRMA);
        }
    }
}