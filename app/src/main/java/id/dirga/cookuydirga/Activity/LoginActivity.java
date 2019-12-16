package id.dirga.cookuydirga.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.dirga.cookuydirga.Entity.User;
import id.dirga.cookuydirga.R;
import id.dirga.cookuydirga.Repository.UserRepository;
import id.dirga.cookuydirga.ViewModel.UserViewModel;

import static id.dirga.cookuydirga.Utils.IntentRequestCode.REGISTER_RC;
import static id.dirga.cookuydirga.Utils.PreferenceString.EMAIL;
import static id.dirga.cookuydirga.Utils.PreferenceString.ID;
import static id.dirga.cookuydirga.Utils.PreferenceString.NAMA;
import static id.dirga.cookuydirga.Utils.PreferenceString.PASSWORD;
import static id.dirga.cookuydirga.Utils.PreferenceString.SHARED_PREFS;

public class LoginActivity extends AppCompatActivity {

    private UserViewModel mUserViewModel;

    EditText email;
    EditText password;
    Button buttonLogin;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        email = (EditText) findViewById(R.id.login_email_hint);
        password = (EditText) findViewById(R.id.login_password_hint);

        buttonLogin = (Button) findViewById(R.id.login_button);
        buttonRegister = (Button) findViewById(R.id.register_button_on_login);

        populateAdmin();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginEmail = email.getText().toString();
                String loginPassword = password.getText().toString();
                boolean loginValid = mUserViewModel.checkValidLogin(loginEmail, loginPassword);

                if (loginValid) {
                    User user = mUserViewModel.getUserByEmail(loginEmail);
                    saveData(user);
                    Toast.makeText(getApplicationContext(), loginEmail + " sukses login", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                } else {
                    Toast.makeText(getApplicationContext(), "User " + loginEmail +" tidak valid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(register, REGISTER_RC);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_RC){
            if (resultCode == RESULT_OK) {
                String nama = data.getStringExtra("NAMA");
                String email = data.getStringExtra("EMAIL");
                String password = data.getStringExtra("PASSWORD");

                User user = new User(nama, email, password);
                mUserViewModel.insert(user);
                Toast.makeText(getApplicationContext(), email + " sukses terdaftar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void populateAdmin() {
        String populateData;
        try {
            populateData = mUserViewModel.getUserByEmail("admin").getEmail();
            Log.d("TEST", populateData);
        } catch(Exception e) {
            Log.e("ERROR", e.getMessage());
            mUserViewModel.insert(new User("admin", "admin", "admin"));
        }
    }

    public void saveData(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(ID, user.getId());
        editor.putString(NAMA, user.getNama());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(PASSWORD, user.getPassword());

        editor.apply();
    }

}
