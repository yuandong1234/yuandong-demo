package yd.miwu.winxindemo;

import java.io.Serializable;
import java.util.List;

/**
 * 相册对象
 *
 */
public class ImageBucket implements Serializable{

	private static final long serialVersionUID = 1L;
	public int count = 0;
	public String bucketName;
	public List<ImageBean> imageList;
	public boolean selected = false;
}
