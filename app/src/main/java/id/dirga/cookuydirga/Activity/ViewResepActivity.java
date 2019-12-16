package id.dirga.cookuydirga.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.R;

public class ViewResepActivity extends AppCompatActivity {

    TextView viewResepTitle;
    TextView namaResep;
    TextView bahanResep;
    TextView caraMasak;
    Button backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resep);

        viewResepTitle = (TextView) findViewById(R.id.add_resep_title);
        namaResep = (TextView) findViewById(R.id.nama_resep);
        bahanResep = (TextView) findViewById(R.id.bahan_resep);
        caraMasak = (TextView) findViewById(R.id.cara_masak_resep);
        backToMain = (Button) findViewById(R.id.add_resep_button);

        viewResepTitle.setText("RESEP");
        backToMain.setText("BACK");

        Intent intent = getIntent();
        namaResep.setText(intent.getStringExtra("NAMA_RESEP"));
        bahanResep.setText(intent.getStringExtra("BAHAN_RESEP"));
        caraMasak.setText(intent.getStringExtra("CARA_MASAK"));

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
