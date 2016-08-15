package yd.miwu.winxindemo;

import java.io.Serializable;

/**
 * Created by 256 on 2016/7/4.
 */
public class ImageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String imageId;
    public String sourcePath;
    public String thumbnailPath;
    public boolean isSelected = false;

    @Override
    public String toString() {
        return "ImageBean{" +
                "imageId='" + imageId + '\'' +
                ", sourcePath='" + sourcePath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
