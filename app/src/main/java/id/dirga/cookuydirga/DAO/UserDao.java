package id.dirga.cookuydirga.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.dirga.cookuydirga.Entity.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM tb_user WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM tb_user WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);

    @Query("SELECT * FROM tb_user")
    LiveData<List<User>> getAllUser();

    @Query("DELETE FROM tb_user")
    void deleteAllUser();
}
