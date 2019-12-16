package id.dirga.cookuydirga.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.dirga.cookuydirga.R;

public class RegisterActivity extends AppCompatActivity {

    EditText nama;
    EditText email;
    EditText password;

    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = (EditText) findViewById(R.id.register_nama_hint);
        email = (EditText) findViewById(R.id.register_email_hint);
        password = (EditText) findViewById(R.id.register_password_hint);

        daftar = (Button) findViewById(R.id.register_button);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaIntent = nama.getText().toString();
                String emailIntent = email.getText().toString();
                String passwordIntent = password.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("NAMA", namaIntent);
                intent.putExtra("EMAIL", emailIntent);
                intent.putExtra("PASSWORD", passwordIntent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
