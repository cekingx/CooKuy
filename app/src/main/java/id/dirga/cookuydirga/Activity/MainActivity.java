package id.dirga.cookuydirga.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import id.dirga.cookuydirga.Adapter.RecyclerItemClickListener;
import id.dirga.cookuydirga.Adapter.ResepListAdapter;
import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.Entity.ResepAndUser;
import id.dirga.cookuydirga.Entity.User;
import id.dirga.cookuydirga.R;
import id.dirga.cookuydirga.ViewModel.ResepViewModel;
import id.dirga.cookuydirga.ViewModel.UserViewModel;

import static id.dirga.cookuydirga.Utils.IntentRequestCode.ADD_RESEP_RC;
import static id.dirga.cookuydirga.Utils.IntentRequestCode.EDIT_RESEP_RC;
import static id.dirga.cookuydirga.Utils.PreferenceString.ID;
import static id.dirga.cookuydirga.Utils.PreferenceString.SHARED_PREFS;

public class MainActivity extends AppCompatActivity {

    private ResepViewModel mResepViewModel;
    private Button editButton, deleteButton;
    private TextView resepTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ResepListAdapter resepListAdapter = new ResepListAdapter(this, getLoginUserId());
        recyclerView.setAdapter(resepListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mResepViewModel = new ViewModelProvider(this).get(ResepViewModel.class);
//        mResepViewModel.getAllResep().observe(this, new Observer<List<Resep>>() {
//            @Override
//            public void onChanged(List<Resep> reseps) {
//                resepListAdapter.setReseps(reseps);
//            }
//        });

        mResepViewModel.getAllResepAndUser().observe(this, new Observer<List<ResepAndUser>>() {
            @Override
            public void onChanged(List<ResepAndUser> resepAndUsers) {
                resepListAdapter.setResepAndUsers(resepAndUsers);
                Log.d("ADAPTER", "Ked di setResepAndUsers Main Activity");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addResep = new Intent(MainActivity.this, AddResepActivity.class);
                startActivityForResult(addResep, ADD_RESEP_RC);
            }
        });

        // Recycler View on item click
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    editButton = view.findViewById(R.id.edit_button2);
                    deleteButton = view.findViewById(R.id.delete_button2);
                    resepTitle = view.findViewById(R.id.judul_resep2);

                    resepTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent viewResep = new Intent(MainActivity.this, ViewResepActivity.class);
                            viewResep.putExtra("NAMA_RESEP", resepListAdapter.getResepAt(position).getJudul());
                            viewResep.putExtra("BAHAN_RESEP", resepListAdapter.getResepAt(position).getBahan());
                            viewResep.putExtra("CARA_MASAK", resepListAdapter.getResepAt(position).getCaraMasak());
                            startActivity(viewResep);
                        }
                    });

                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = resepListAdapter.getResepAt(position).getId();
                            mResepViewModel.deleteById(id);
                        }
                    });

                    editButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent editResep = new Intent(MainActivity.this, EditResepActivity.class);
                            editResep.putExtra("ID", resepListAdapter.getResepAt(position).getId());
                            editResep.putExtra("USER_ID", resepListAdapter.getResepAt(position).getUserId());
                            editResep.putExtra("NAMA_RESEP", resepListAdapter.getResepAt(position).getJudul());
                            editResep.putExtra("BAHAN_RESEP", resepListAdapter.getResepAt(position).getBahan());
                            editResep.putExtra("CARA_MASAK", resepListAdapter.getResepAt(position).getCaraMasak());
                            startActivityForResult(editResep, EDIT_RESEP_RC);
                        }
                    });
                }
            })
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RESEP_RC){
            if (resultCode == RESULT_OK) {
                int userId = getLoginUserId();
                String judul = data.getStringExtra("NAMA_RESEP");
                String bahan = data.getStringExtra("BAHAN_RESEP");
                String caraMasak = data.getStringExtra("CARA_MASAK");

                Resep resep = new Resep(userId, judul, bahan, caraMasak, "xxx");
                mResepViewModel.insert(resep);
                Toast.makeText(getApplicationContext(), judul + " Sukses Ditambahkan", Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == EDIT_RESEP_RC){
            if (resultCode == RESULT_OK) {
                int id = data.getIntExtra("ID", 1);
                int userId = data.getIntExtra("USER_ID", 1);
                String judul = data.getStringExtra("NAMA_RESEP");
                String bahan = data.getStringExtra("BAHAN_RESEP");
                String caraMasak = data.getStringExtra("CARA_MASAK");

                Resep resep = new Resep(userId, judul, bahan, caraMasak, "xxx");
                resep.setId(id);
                mResepViewModel.update(resep);
                Toast.makeText(getApplicationContext(), judul + " Sukses Diubah", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int getLoginUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int id = sharedPreferences.getInt(ID, 1);
        return id;
    }

    public void populateResep() {
        mResepViewModel.insert(new Resep(1, "Nasi Goreng", "Nasi", "Digoreng", "xxx"));
        mResepViewModel.insert(new Resep(1, "Nasi Bakar", "Nasi", "Dibakar", "xxx"));
        mResepViewModel.insert(new Resep(1, "Nasi Uduk", "Nasi", "Dimasak pakek santen", "xxx"));
    }
}
