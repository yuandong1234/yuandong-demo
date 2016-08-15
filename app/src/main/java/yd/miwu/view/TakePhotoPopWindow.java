package yd.miwu.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import yd.miwu.R;

/**
 * Created by 256 on 2016/7/5.
 */
public class TakePhotoPopWindow extends PopupWindow {
    private static final String TAG = "FinishProjectPopupWindows";

    private View mView;
    public Button takePhoto, pickPhoto, cancel;

    public TakePhotoPopWindow(Context context,View.OnClickListener itemsOnClick) {
        super(context);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.layout_photo_popwindow, null);

        takePhoto = (Button) mView.findViewById(R.id.takePhoto);
        pickPhoto = (Button) mView.findViewById(R.id.pickPhoto);
        cancel = (Button) mView.findViewById(R.id.cancel);

        // 设置按钮监听
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        takePhoto.setOnClickListener(itemsOnClick);
        pickPhoto.setOnClickListener(itemsOnClick);
        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Animation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

    }
}
