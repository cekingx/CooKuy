package id.dirga.cookuydirga.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ResepAndUser {
    @Embedded
    Resep resep;

    @Relation(entity = User.class, parentColumn = "user_id", entityColumn = "id")
    User resepOwner;

    public Resep getResep() {
        return resep;
    }

    public void setResep(Resep resep) {
        this.resep = resep;
    }

    public User getResepOwner() {
        return resepOwner;
    }

    public void setResepOwner(User resepOwner) {
        this.resepOwner = resepOwner;
    }
}
