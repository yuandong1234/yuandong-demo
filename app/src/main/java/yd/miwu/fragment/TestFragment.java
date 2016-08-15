package yd.miwu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yd.miwu.R;

/**
 * Created by 256 on 2016/5/31.
 */
public class TestFragment extends Fragment {
    private String title;
    public TestFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle!=null){
            title=bundle.getString("title");
        }

        View view=inflater.inflate(R.layout.fragment_for_test,null);
        TextView textView=(TextView)view.findViewById(R.id.title);
        textView.setText(title);

        return view;
    }
}
