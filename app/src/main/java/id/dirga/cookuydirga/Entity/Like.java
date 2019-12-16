package id.dirga.cookuydirga.Entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_like", foreignKeys = @ForeignKey(
        entity = Note.class,
        parentColumns = "id",
        childColumns = "resep_id"
))
public class Like {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "resep_id")
    private int resepId;

    public Like(int resepId) {
        this.resepId = resepId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResepId() {
        return resepId;
    }

    public void setResepId(int resepId) {
        this.resepId = resepId;
    }
}
