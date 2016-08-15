package yd.miwu.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import yd.miwu.R;

/**
 * Created by 256 on 2016/6/2.
 */
public class SecondCustomProgressDialog extends Dialog {
    private Context context = null;
    private static SecondCustomProgressDialog customProgressDialog = null;

    public SecondCustomProgressDialog(Context context) {
        super(context);
        this.context=context;
    }

    public SecondCustomProgressDialog(Context context, int themeResId) {
        super(context, themeResId);

    }
    public static SecondCustomProgressDialog createDialog(Context context){
        customProgressDialog = new SecondCustomProgressDialog(context, R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.layout_custom_progressdialog_two);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        return customProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus){

        if (customProgressDialog == null){
            return;
        }

        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    public SecondCustomProgressDialog setTitile(String strTitle){
        return customProgressDialog;
    }

    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public SecondCustomProgressDialog setMessage(String strMessage){
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);

        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }
        return customProgressDialog;
    }

}
