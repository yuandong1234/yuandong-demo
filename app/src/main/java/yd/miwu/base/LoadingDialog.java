package yd.miwu.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import yd.miwu.R;

/**
 * Created by 256 on 2016/6/14.
 */
public class LoadingDialog extends Dialog {


    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_dialog_loading, null);
		/*//显示动画
		ImageView rocketImage = (ImageView) view
				.findViewById(R.id.circle_image);
		rocketImage.setImageResource(R.drawable.base_loading_circle_gif);
		AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage
				.getDrawable();
		rocketAnimation.start();*/
        //设定布局
        this.getWindow().setGravity(Gravity.CENTER);
        this.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        this.getWindow().setContentView(view);
        this.setCanceledOnTouchOutside(false);

    }

    @Override
    public void onBackPressed() {
        if(isShowing()){//显示的时候不允许后退
            return;
        }else{
            super.onBackPressed();
        }
    }
}
