package br.edu.iftm.pdm.car4sale.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {

    // Dê uma olhada nessa classe. Não há nada a se fazer aqui além de contemplar a sua beleza e importância para a construção deste software;

    private long id;
    private String path;

    public Picture() {}

    public Picture(long id, String path) {
        this.id = id;
        this.path = path;
    }

    protected Picture(Parcel in) {
        id = in.readLong();
        path = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(path);
    }
}
