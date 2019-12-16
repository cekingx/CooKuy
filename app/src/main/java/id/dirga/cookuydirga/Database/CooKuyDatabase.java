package id.dirga.cookuydirga.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import id.dirga.cookuydirga.DAO.ResepAndUserDao;
import id.dirga.cookuydirga.DAO.ResepDao;
import id.dirga.cookuydirga.DAO.UserDao;
import id.dirga.cookuydirga.Entity.Like;
import id.dirga.cookuydirga.Entity.Note;
import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.Entity.User;

@Database(entities = {
        User.class,
        Resep.class,
        Note.class,
        Like.class},
        version = 1,
        exportSchema = false
)
public abstract class CooKuyDatabase extends RoomDatabase {
    public static Context ctx;
    public abstract UserDao userDao();
    public abstract ResepDao resepDao();
    public abstract ResepAndUserDao resepAndUserDao();

    public static volatile CooKuyDatabase INSTANCE;

    public static CooKuyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CooKuyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CooKuyDatabase.class, "db_cookuy")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
//                @Override
//                public void run() {
//                    User dirga = new User("dirga", "cekingx@gmail.com", "123");
//                    getDatabase(ctx).userDao().insert(dirga);
//                }
//            });
//        }
//    };
}

