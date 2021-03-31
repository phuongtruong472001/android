package com.example.demo9;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuBook implements Parcelable {
    private String imageLink;
    private String title;
    private String author;
    private int numOfPage;
    private long  price;
    private String description;
    private String categoty;
    private float rateStar;
    private float numOfReview;
    private float DanhGia;
//    String author..... bla bla cac kieu
    // khi code mấy cái này thì thêm private vào


    public MenuBook(String imageLink, String title, String author, int numOfPage, long price, String description, String categoty, float rateStar, float numOfReview, float danhGia) {
        this.imageLink = imageLink;
        this.title = title;
        this.author = author;
        this.numOfPage = numOfPage;
        this.price = price;
        this.description = description;
        this.categoty = categoty;
        this.rateStar = rateStar;
        this.numOfReview = numOfReview;
        DanhGia = danhGia;
    }

    protected MenuBook(Parcel in) {
        imageLink = in.readString();
        title = in.readString();
        author = in.readString();
        numOfPage = in.readInt();
        price = in.readLong();
        description = in.readString();
        categoty = in.readString();
        rateStar = in.readFloat();
        numOfReview = in.readFloat();
        DanhGia = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageLink);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(numOfPage);
        dest.writeLong(price);
        dest.writeString(description);
        dest.writeString(categoty);
        dest.writeFloat(rateStar);
        dest.writeFloat(numOfReview);
        dest.writeFloat(DanhGia);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MenuBook> CREATOR = new Creator<MenuBook>() {
        @Override
        public MenuBook createFromParcel(Parcel in) {
            return new MenuBook(in);
        }

        @Override
        public MenuBook[] newArray(int size) {
            return new MenuBook[size];
        }
    };

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumOfPage() {
        return numOfPage;
    }

    public void setNumOfPage(int numOfPage) {
        this.numOfPage = numOfPage;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public float getRateStar() {
        return rateStar;
    }

    public void setRateStar(float rateStar) {
        this.rateStar = rateStar;
    }

    public float getNumOfReview() {
        return numOfReview;
    }

    public void setNumOfReview(float numOfReview) {
        this.numOfReview = numOfReview;
    }

    public float getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(float danhGia) {
        DanhGia = danhGia;
    }

    public static Creator<MenuBook> getCREATOR() {
        return CREATOR;
    }
}
