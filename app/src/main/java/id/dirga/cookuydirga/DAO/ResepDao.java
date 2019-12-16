package id.dirga.cookuydirga.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.dirga.cookuydirga.Entity.Resep;

@Dao
public interface ResepDao {

    @Insert
    void insert(Resep resep);

    @Update
    void update(Resep resep);

    @Delete
    void delete(Resep resep);

    @Query("DELETE FROM tb_resep WHERE id=:id")
    void deleteById(int id);

    @Query("DELETE FROM tb_resep")
    void deleteAllResep();

    @Query("SELECT * FROM tb_resep WHERE id = :id")
    Resep getResepById(int id);

    @Query("SELECT * FROM tb_resep")
    LiveData<List<Resep>> getAllResep();

    @Query("SELECT nama from tb_user WHERE id=:userId")
    String getOwner(int userId);


}
