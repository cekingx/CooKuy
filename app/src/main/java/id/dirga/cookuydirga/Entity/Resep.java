package id.dirga.cookuydirga.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_resep", foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "user_id"
))
public class Resep {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "bahan")
    private String bahan;

    @ColumnInfo(name = "cara_masak")
    private String caraMasak;

    @ColumnInfo(name = "foto")
    private String foto;

    public Resep(int userId, String judul, String bahan, String caraMasak, String foto) {
        this.userId = userId;
        this.judul = judul;
        this.bahan = bahan;
        this.caraMasak = caraMasak;
        this.foto = foto;
    }

    public Resep() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getCaraMasak() {
        return caraMasak;
    }

    public void setCaraMasak(String caraMasak) {
        this.caraMasak = caraMasak;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUserIdString() {
        return Integer.toString(userId);
    }

}
