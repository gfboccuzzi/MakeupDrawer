package edu.wit.mobilepp.md3;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class CardItemMakeupCollection {
    public Bitmap image;
    public String id;
    public String brand;
    public String product;
    public String category;
    public String shade;
    public String purchase_date;
    public Integer lifespan;
//    public Integer days_left;
public CardItemMakeupCollection() {
    id = "";
    brand = "";
    product = "";
    category = "";
    shade = "";
    purchase_date = "";
    lifespan = 0;
}

    public CardItemMakeupCollection(Parcel in) {
        id = in.readString();
        brand = in.readString();
        product = in.readString();
        category = in.readString();
        shade = in.readString();
        purchase_date = in.readString();
        lifespan = in.readInt();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getShade() {
        return shade;
    }
    public void setShade(String tax) {
        this.shade = shade;
    }
    public String getPurchase_date() {
        return purchase_date;
    }
    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(brand);
        dest.writeString(product);
        dest.writeString(category);
        dest.writeString(shade);
        dest.writeString(purchase_date);
        dest.writeInt(lifespan);

    }

    public static final Parcelable.Creator<CardItemMakeupCollection> CREATOR = new Parcelable.Creator<CardItemMakeupCollection>()
    {
        public CardItemMakeupCollection createFromParcel(Parcel in)
        {
            return new CardItemMakeupCollection(in);
        }
        public CardItemMakeupCollection[] newArray(int size)
        {
            return new CardItemMakeupCollection[size];
        }
    };
}
