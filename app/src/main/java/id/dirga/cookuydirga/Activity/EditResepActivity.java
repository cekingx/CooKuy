package id.dirga.cookuydirga.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.R;

public class EditResepActivity extends AppCompatActivity {

    TextView editResepTitle;
    EditText namaResep;
    EditText bahanResep;
    EditText caraMasak;
    Button editResepButton;
    Resep editResep;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resep);

        editResepTitle = (TextView) findViewById(R.id.add_resep_title);
        namaResep = (EditText) findViewById(R.id.nama_resep);
        bahanResep = (EditText) findViewById(R.id.bahan_resep);
        caraMasak = (EditText) findViewById(R.id.cara_masak_resep);
        editResepButton = (Button) findViewById(R.id.add_resep_button);

        editResepTitle.setText("Edit Resep");
        editResepButton.setText("EDIT");

        Intent intent = getIntent();
        editResep = new Resep();
        editResep.setId(intent.getIntExtra("ID", 1));
        editResep.setUserId(intent.getIntExtra("USER_ID", 1));
        editResep.setJudul(intent.getStringExtra("NAMA_RESEP"));
        editResep.setBahan(intent.getStringExtra("BAHAN_RESEP"));
        editResep.setCaraMasak(intent.getStringExtra("CARA_MASAK"));

        namaResep.setText(editResep.getJudul());
        bahanResep.setText(editResep.getBahan());
        caraMasak.setText(editResep.getCaraMasak());

        editResepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idIntent = editResep.getId();
                int userIdIntent = editResep.getUserId();
                String namaResepIntent = namaResep.getText().toString();
                String bahanResepIntent = bahanResep.getText().toString();
                String caraMasakIntent = caraMasak.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("ID", idIntent);
                intent.putExtra("USER_ID", userIdIntent);
                intent.putExtra("NAMA_RESEP", namaResepIntent);
                intent.putExtra("BAHAN_RESEP", bahanResepIntent);
                intent.putExtra("CARA_MASAK", caraMasakIntent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
