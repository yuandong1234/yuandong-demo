package yd.miwu.mode;

import java.io.Serializable;

/**
 * Created by 256 on 2016/8/4.
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String reason;
    public int error_code;
    public ResultBean result;
}
