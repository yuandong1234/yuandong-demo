package yd.miwu.base;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yd.miwu.R;

public class NetBaseActivity extends BaseActivity implements NetBaseInterface {

    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_base);
    }


    @Override
    public Dialog createDialog() {
        if (dialog == null)
            dialog = new LoadingDialog(this, R.style.basedDialogStyle);
        return dialog;
    }
}
