package id.slametriyadi.sqlite_imastudio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Nota implements Parcelable {

    int id;
    String judul, deskripsi, tanggal;

    public Nota() {

    }

    protected Nota(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        deskripsi = in.readString();
        tanggal = in.readString();
    }

    public static final Creator<Nota> CREATOR = new Creator<Nota>() {
        @Override
        public Nota createFromParcel(Parcel in) {
            return new Nota(in);
        }

        @Override
        public Nota[] newArray(int size) {
            return new Nota[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.deskripsi);
        dest.writeString(this.tanggal);
    }
}
