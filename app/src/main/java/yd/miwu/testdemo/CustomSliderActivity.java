package yd.miwu.testdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.komi.slider.ISlider;
import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;
import com.komi.slider.position.SliderPosition;
import com.komi.slider.ui.SliderUi;
import com.komi.slider.ui.adapter.SliderDialogAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Random;

import yd.miwu.R;

public class CustomSliderActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG=CustomSliderActivity.class.getSimpleName();

    @ViewInject(R.id.radio_group)
    private RadioGroup radioGroup;
    @ViewInject(R.id.swich)
    private Switch edgeSwitch;
    @ViewInject(R.id.dialog)
    private RelativeLayout dialog;

    private SliderConfig mConfig;
    private ISlider iSlider;

    private int[] RadioBtnIds = {R.id.rbt_left, R.id.rbt_right, R.id.rbt_top, R.id.rbt_bottom, R.id.rbt_horizontal, R.id.rbt_vertical, R.id.rbt_all};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_slider);
        ViewUtils.inject(this);

        Random random = new Random();
        int position = random.nextInt(SliderPosition.sPositionChildren.length);
        SliderPosition sliderPosition = SliderPosition.sPositionChildren[position];
        ((RadioButton) findViewById(RadioBtnIds[position])).setChecked(true);

        int primaryColor = getResources().getColor(R.color.miwuBaseTitleBarColor);

        mConfig = new SliderConfig.Builder()
                .primaryColor(primaryColor)
                .secondaryColor(Color.TRANSPARENT)
                .position(sliderPosition)
                .edge(false)
                .build();

        iSlider = SliderUtils.attachActivity(this, mConfig);
        edgeSwitch.setOnCheckedChangeListener(this);
        edgeSwitch.setChecked(mConfig.isEdgeOnly());
        radioGroup.setOnCheckedChangeListener(this);
        dialog.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        SliderPosition position;

        switch (checkedId) {
            default:
            case R.id.rbt_left:
                position = SliderPosition.LEFT;
                break;
            case R.id.rbt_right:
                position = SliderPosition.RIGHT;
                break;
            case R.id.rbt_bottom:
                position = SliderPosition.BOTTOM;
                break;
            case R.id.rbt_top:
                position = SliderPosition.TOP;
                break;
            case R.id.rbt_horizontal:
                position = SliderPosition.HORIZONTAL;
                break;
            case R.id.rbt_vertical:
                position = SliderPosition.VERTICAL;
                break;
            case R.id.rbt_all:
                position = SliderPosition.ALL;
                break;
        }
        mConfig.setPosition(position);
        iSlider.setConfig(mConfig);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.swich:
                mConfig.setEdgeOnly(isChecked);
                iSlider.setConfig(mConfig);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG,"触发点击了Dialog....");
        switch (v.getId()){
            case R.id.dialog:

                showSliderDialog();
                break;
        }


    }

    private void showSliderDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this, R.style.DialogTheme)
                .setTitle("Slider Dialog")
                .setMessage("you can slide dialog")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .create();

        dialog.show();
        ViewGroup decorChild = (ViewGroup) ((ViewGroup) dialog.getWindow().getDecorView()).getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) decorChild.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(50, 0, 50, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            decorChild.setBackgroundColor(Color.WHITE);
        }

        SliderConfig config = new SliderConfig.Builder().build();
        config.setPosition(SliderPosition.ALL);
        config.setScrimStartAlpha(0);
        config.setScrimEndAlpha(0);
        config.setEdgeOnly(false);
        SliderUi sliderUi = new SliderDialogAdapter(this, dialog);
        ((SliderDialogAdapter) sliderUi).setLayoutParams(layoutParams);

        SliderUtils.attachUi(null, sliderUi, config);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        iSlider.slideExit();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("*********", "onDestroy()执行了。。。。");
    }
}
