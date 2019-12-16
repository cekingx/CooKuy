package id.dirga.cookuydirga.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dirga.cookuydirga.DAO.ResepAndUserDao;
import id.dirga.cookuydirga.DAO.ResepDao;
import id.dirga.cookuydirga.Database.CooKuyDatabase;
import id.dirga.cookuydirga.Entity.Resep;
import id.dirga.cookuydirga.Entity.ResepAndUser;

public class ResepRepository {
    private ResepDao mResepDao;
    private LiveData<List<Resep>> mAllResep;
    private ResepAndUserDao mResepAndUserDao;
    private LiveData<List<ResepAndUser>> mAllResepAndUser;

    public ResepRepository(Application application) {
        CooKuyDatabase db = CooKuyDatabase.getDatabase(application);
        mResepDao = db.resepDao();
        mResepAndUserDao = db.resepAndUserDao();
        mAllResep = mResepDao.getAllResep();
        mAllResepAndUser = mResepAndUserDao.getAllResepAndUser();
    }

    public LiveData<List<Resep>> getAllResep() {
        return mAllResep;
    }

    public LiveData<List<ResepAndUser>> getAllResepAndUser() {
        return mAllResepAndUser;
    }

    public ResepAndUser getResepAndUserByResepId(int id) {
        return mResepAndUserDao.getResepAndUserByResepId(id);
    }

    public void insert(Resep resep) {
        new insertResepAsyncTask(mResepDao).execute(resep);
    }

    public void update(Resep resep) {
        new updateResepAsyncTask(mResepDao).execute(resep);
    }

    public void delete(Resep resep) {
        new deleteResepAsyncTask(mResepDao).execute(resep);
    }

    public void deleteById(int resepId) {
        new deleteResepByIdAsyncTask(mResepDao).execute(resepId);
    }

    public static class insertResepAsyncTask extends AsyncTask<Resep, Void, Void> {

        private ResepDao mAsyncResepDao;

        insertResepAsyncTask(ResepDao dao) {
            mAsyncResepDao = dao;
        }

        @Override
        protected Void doInBackground(Resep... reseps) {
            mAsyncResepDao.insert(reseps[0]);
            return null;
        }
    }

    public static class updateResepAsyncTask extends AsyncTask<Resep, Void, Void> {

        private ResepDao mAsyncResepDao;

        updateResepAsyncTask(ResepDao dao){
            mAsyncResepDao = dao;
        }

        @Override
        protected Void doInBackground(Resep... reseps) {
            mAsyncResepDao.update(reseps[0]);
            return null;
        }
    }

    public static class deleteResepAsyncTask extends AsyncTask<Resep, Void, Void> {

        private ResepDao mAsyncResepDao;

        deleteResepAsyncTask(ResepDao dao){
            mAsyncResepDao = dao;
        }

        @Override
        protected Void doInBackground(Resep... reseps) {
            mAsyncResepDao.delete(reseps[0]);
            return null;
        }
    }

    public static class deleteResepByIdAsyncTask extends AsyncTask<Integer, Void, Void> {

        private ResepDao mAsyncResepDao;

        deleteResepByIdAsyncTask(ResepDao dao){
            this.mAsyncResepDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            mAsyncResepDao.deleteById(integers[0]);
            return null;
        }
    }
}
