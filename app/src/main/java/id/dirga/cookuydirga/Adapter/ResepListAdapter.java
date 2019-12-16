package id.dirga.cookuydirga.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.Entity.ResepAndUser;
import id.dirga.cookuydirga.Entity.User;
import id.dirga.cookuydirga.R;

import static android.content.Context.MODE_PRIVATE;
import static id.dirga.cookuydirga.Utils.PreferenceString.ID;
import static id.dirga.cookuydirga.Utils.PreferenceString.SHARED_PREFS;

public class ResepListAdapter extends RecyclerView.Adapter<ResepListAdapter.ResepViewHolder> {

    private LayoutInflater mInflater;
    private List<Resep> mReseps;
    private List<ResepAndUser> mResepAndUsers;
    private int loggedUserId;

    public class ResepViewHolder extends RecyclerView.ViewHolder {

        private TextView judulResep, pemilikResep;
        private Button editButton, deleteButton;

        public ResepViewHolder(View itemView) {
            super(itemView);
            judulResep = itemView.findViewById(R.id.judul_resep2);
            pemilikResep = itemView.findViewById(R.id.pemilik_resep2);

            editButton = itemView.findViewById(R.id.edit_button2);
            deleteButton = itemView.findViewById(R.id.delete_button2);
        }
    }

    public ResepListAdapter(Context context, int userId) {
        mInflater = LayoutInflater.from(context);
        loggedUserId = userId;
    }

    @Override
    public ResepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ADAPTER", "Ked di onCreateViewHolder Adapter");
        View view = mInflater.inflate(R.layout.resep_item2, parent, false);
        return new ResepViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ResepViewHolder holder, int position) {
//        if (mReseps != null) {
//            Resep currentResep = mReseps.get(position);
//            holder.judulResep.setText(currentResep.getJudul());
//            holder.pemilikResep.setText(currentResep.getUserIdString());
//            if (currentResep.getUserId() == loggedUserId) {
//                holder.editButton.setVisibility(View.VISIBLE);
//                holder.deleteButton.setVisibility(View.VISIBLE);
//            } else {
//                holder.editButton.setVisibility(View.GONE);
//                holder.deleteButton.setVisibility(View.GONE);
//            }
//        } else {
//            holder.judulResep.setText("Tidak Ada Resep");
//        }
//    }

    @Override
    public void onBindViewHolder(@NonNull ResepViewHolder holder, int position) {
        Log.d("ADAPTER", "Ked di onBindViewHolder Adapter");
        if (mResepAndUsers != null) {
            ResepAndUser currentResep = mResepAndUsers.get(position);
            holder.judulResep.setText(currentResep.getResep().getJudul());
            holder.pemilikResep.setText(currentResep.getResepOwner().getNama());
            if (currentResep.getResepOwner().getId() == loggedUserId) {
                holder.editButton.setVisibility(View.VISIBLE);
                holder.deleteButton.setVisibility(View.VISIBLE);
            } else {
                holder.editButton.setVisibility(View.INVISIBLE);
                holder.deleteButton.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.judulResep.setText("Tidak Ada Resep");
        }
    }

    @Override
    public int getItemCount() {
        if (mResepAndUsers != null) {
            int test = mResepAndUsers.size();
            Log.d("ADAPTER", "Ked " + Integer.toString(test));
            return mResepAndUsers.size();
        } else {
            return 0;
        }
    }

    public void setReseps(List<Resep> reseps) {
        mReseps = reseps;
        notifyDataSetChanged();
    }

    public void setResepAndUsers(List<ResepAndUser> resepAndUsers) {
        mResepAndUsers = resepAndUsers;
        notifyDataSetChanged();
        Log.d("ADAPTER", "Ked di setResepAndUsers Adapter");
    }

    public Resep getResepAt(int position) {
        return mResepAndUsers.get(position).getResep();
    }

}
