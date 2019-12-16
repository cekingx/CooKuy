package id.dirga.cookuydirga.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.dirga.cookuydirga.Entity.ResepAndUser;

@Dao
public interface ResepAndUserDao {
    @Query("SELECT * FROM tb_resep")
    LiveData<List<ResepAndUser>> getAllResepAndUser();

    @Query("SELECT * FROM tb_resep WHERE id=:id")
    ResepAndUser getResepAndUserByResepId(int id);
}
