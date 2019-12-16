package id.dirga.cookuydirga.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.dirga.cookuydirga.R;

public class AddResepActivity extends AppCompatActivity {

    TextView addResepTitle;
    EditText namaResep;
    EditText bahanResep;
    EditText caraMasak;
    Button addResepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resep);

        addResepTitle = (TextView) findViewById(R.id.add_resep_title);
        namaResep = (EditText) findViewById(R.id.nama_resep);
        bahanResep = (EditText) findViewById(R.id.bahan_resep);
        caraMasak = (EditText) findViewById(R.id.cara_masak_resep);
        addResepButton = (Button) findViewById(R.id.add_resep_button);

        addResepTitle.setText("Tambah Resep");
        addResepButton.setText("ADD");

        addResepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaResepIntent = namaResep.getText().toString();
                String bahanResepIntent = bahanResep.getText().toString();
                String caraMasakIntent = caraMasak.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("NAMA_RESEP", namaResepIntent);
                intent.putExtra("BAHAN_RESEP", bahanResepIntent);
                intent.putExtra("CARA_MASAK", caraMasakIntent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
