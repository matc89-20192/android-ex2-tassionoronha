package matc89.exercicio2;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText editText;
    private String novoNome;
    private Button btnConfirmar;
    private Button btnCancelar;

    protected  void listenButtons(){
        Intent intent = getIntent();

        novoNome = intent.getStringExtra("novoNome");
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(novoNome, TextView.BufferType.EDITABLE);

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    editText.setText("");
                }
                return false;
            }
        });

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
        btnConfirmar.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);
        listenButtons();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String meutexto = savedInstanceState.getString("textoPendente");
        editText.setText(meutexto);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textoPendente", editText.getText().toString());
    }

    private void updateName(){
        Intent data;
        data = new Intent();
        data.putExtra("novoNome", editText.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }

    private void cancelOperation(){
        Intent data;
        data = new Intent();
        setResult(RESULT_CANCELED, data);
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnConfirmar:
                updateName();
                break;
            case R.id.btnCancelar:
                cancelOperation();
                break;
        }
    }
}