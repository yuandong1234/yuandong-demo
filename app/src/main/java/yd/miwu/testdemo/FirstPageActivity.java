package yd.miwu.testdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import yd.miwu.R;

public class FirstPageActivity extends AppCompatActivity {

    private Handler handler;
    private boolean backBtnPressed=false;
    private Runnable run=new Runnable() {
        @Override
        public void run() {
            backBtnPressed=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        handler=new Handler();


    }
    @Override
    public void onBackPressed() {
        if (backBtnPressed) {
            super.onBackPressed();
        }else {
            backBtnPressed = true;
            Toast.makeText(this, "再次点击返回键退出", Toast.LENGTH_SHORT).show();
            handler.postDelayed(run, 5000);
        }
    }

}
