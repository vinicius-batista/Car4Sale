package br.edu.iftm.pdm.car4sale.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Car implements Parcelable {

    // Dê uma olhada nessa classe. Não há nada a se fazer aqui além de contemplar a sua beleza e importância para a construção deste software;

    private long id;
    private String model;
    private String brand;
    private String chassis;
    private String licensePlate; // vehicle registration plate
    private float price;
    private int pctDiscount;
    private boolean isOnSale;
    private ArrayList<Picture> pictures;

    public Car() {}

    public Car(long id, String model, String brand, String chassis, String licensePlate, float price, int pctDiscount, boolean isOnSale, ArrayList<Picture> pictures) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.chassis = chassis;
        this.licensePlate = licensePlate;
        this.price = price;
        this.pctDiscount = pctDiscount;
        this.isOnSale = isOnSale;
        this.pictures = pictures;
    }

    public Car(long id, String model, String brand, String chassis, String licensePlate, float price, int pctDiscount, boolean isOnSale) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.chassis = chassis;
        this.licensePlate = licensePlate;
        this.price = price;
        this.pctDiscount = pctDiscount;
        this.isOnSale = isOnSale;
    }

    protected Car(Parcel in) {
        id = in.readLong();
        model = in.readString();
        brand = in.readString();
        chassis = in.readString();
        licensePlate = in.readString();
        price = in.readFloat();
        pctDiscount = in.readInt();
        isOnSale = in.readByte() != 0;
        pictures = in.createTypedArrayList(Picture.CREATOR);
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getChassis() {
        return chassis;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public float getPrice() {
        return price;
    }

    public int getPctDiscount() {
        return pctDiscount;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public float getTotalPrice() {
        return this.price * (100 - this.pctDiscount) * 0.01f;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPctDiscount(int pctDiscount) {
        this.pctDiscount = pctDiscount;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(model);
        parcel.writeString(brand);
        parcel.writeString(chassis);
        parcel.writeString(licensePlate);
        parcel.writeFloat(price);
        parcel.writeInt(pctDiscount);
        parcel.writeByte((byte) (isOnSale ? 1 : 0));
        parcel.writeTypedList(pictures);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Car)) return false;
        if(obj == this) return true;
        return ((Car) obj).id == this.id;
    }
}
