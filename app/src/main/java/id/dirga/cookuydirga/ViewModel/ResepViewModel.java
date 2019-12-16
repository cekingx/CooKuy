package id.dirga.cookuydirga.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.Entity.ResepAndUser;
import id.dirga.cookuydirga.Repository.ResepRepository;

public class ResepViewModel extends AndroidViewModel {

    private ResepRepository mResepRepository;

    private LiveData<List<Resep>> mAllResep;
    private LiveData<List<ResepAndUser>> mAllResepAndUser;

    public ResepViewModel(Application application) {
        super(application);
        mResepRepository = new ResepRepository(application);
        mAllResep = mResepRepository.getAllResep();
        mAllResepAndUser = mResepRepository.getAllResepAndUser();
    }

    public LiveData<List<ResepAndUser>> getAllResepAndUser() {
        return mAllResepAndUser;
    }

    public LiveData<List<Resep>> getAllResep() {
        return mAllResep;
    }

    public ResepAndUser getResepAndUserByResepId(int id) {
        return mResepRepository.getResepAndUserByResepId(id);
    }

    public void insert(Resep resep) {
        mResepRepository.insert(resep);
    }

    public void update(Resep resep) {
        mResepRepository.update(resep);
    }

    public void delete(Resep resep) {
        mResepRepository.delete(resep);
    }

    public void deleteById(int resepId){
        mResepRepository.deleteById(resepId);
    }
}
