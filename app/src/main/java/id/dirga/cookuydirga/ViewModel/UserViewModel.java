package id.dirga.cookuydirga.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.dirga.cookuydirga.Entity.User;
import id.dirga.cookuydirga.Repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;

    private LiveData<List<User>> mAllUsers;

    public UserViewModel(Application application) {
        super(application);
        mUserRepository = new UserRepository(application);
        mAllUsers = mUserRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public User getUserByEmail(String email) {
        return mUserRepository.getUserByEmail(email);
    }

    public void insert(User user) {
        mUserRepository.insert(user);
    }

    public boolean checkValidLogin(String email, String password) {
        return mUserRepository.isValidAccount(email, password);
    }
}
