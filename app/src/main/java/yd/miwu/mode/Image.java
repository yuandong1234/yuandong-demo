package yd.miwu.mode;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Image {

    private String url;
    private int width;
    private int height;

    public Image(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {

        return "image---->>url="+url+"width="+width+"height"+height;
    }
}
