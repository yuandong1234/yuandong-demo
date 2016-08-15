package yd.miwu.testdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;
import yd.miwu.util.Utils;

public class ClickAbleTextViewActivity extends Activity {

    @ViewInject(R.id.textView)
    private TextView textView;

    private String url="凤凰体育讯北京时间7月11日凌晨3点，引人瞩目的2016年欧洲杯决赛打响，" +
            "葡萄牙1-0力克法国。上半场，格列兹曼头球吊射险破门，C罗伤退，葡萄牙王牌泪撒赛场。" +
            "下半场，双方调兵遣将，西索科和纳尼获得得分机会但没把握，吉尼亚克中柱，比赛进入加时。" +
            "第109分钟，埃德远射绝杀。葡萄牙历史上首次获得欧洲杯冠军。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_able_text_view);
        ViewUtils.inject(this);
        initView();
    }
    private void initView(){
        //点击后的背景颜色(HighLightColor)属于TextView的属性，Android4.0以上默认是淡绿色，低版本的是黄色。
        //为了避免点击产生颜色，直接设置点击颜色为透明
        textView.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spannableString = new SpannableString(url);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ((TextView)widget).setHighlightColor(getResources().getColor(android.R.color.transparent));
                Toast.makeText(ClickAbleTextViewActivity.this,"你选择的文字："+url.substring(3,10), Toast.LENGTH_LONG).show();

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
                ds.setTextSize(Utils.sp2px(ClickAbleTextViewActivity.this,17));
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        };
        spannableString.setSpan(clickableSpan, 3, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
