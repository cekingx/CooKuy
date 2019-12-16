package id.dirga.cookuydirga.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dirga.cookuydirga.DAO.UserDao;
import id.dirga.cookuydirga.Database.CooKuyDatabase;
import id.dirga.cookuydirga.Entity.User;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;
    private User mUserByEmail;

    public UserRepository(Application application) {
        CooKuyDatabase db = CooKuyDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUser();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public User getUserByEmail(String email) {

        mUserByEmail = mUserDao.getUserByEmail(email);
        return mUserByEmail;
    }

    public void insert(User user) {
        new insertUserAsyncTask(mUserDao).execute(user);
    }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;

        insertUserAsyncTask(UserDao dao) {
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncUserDao.insert(users[0]);
            return null;
        }
    }

    public boolean isValidAccount(String email, String password) {
        try {
            User user = mUserDao.getUserByEmail(email);
            return user.getPassword().equals(password);
        } catch (Exception e) {
            return false;
        }
    }
}
