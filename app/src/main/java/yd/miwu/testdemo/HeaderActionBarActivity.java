package yd.miwu.testdemo;

import android.app.ActionBar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.achep.header2actionbar.FadingActionBarHelper;

import yd.miwu.R;
import yd.miwu.fragment.ListViewFragment;

public class HeaderActionBarActivity extends Activity {

    private ActionBar actionBar;
    private FadingActionBarHelper fadingActionBarHelper;
    private static final String LINK_TO_GITHUB = "https://github.com/AChep/Header2ActionBar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_action_bar);
        actionBar=getActionBar();
       //使用自定义的布局的ActionBar
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //actionBar.setDisplayShowCustomEnabled(true);
        //actionBar.setCustomView(R.layout.layout_custom_actionbar);
        //TextView title=(TextView)actionBar.getCustomView().findViewById(R.id.actionbar_title);
        //title.setText("HeaderActionBarActivity");
         fadingActionBarHelper=new FadingActionBarHelper(actionBar,getResources().getDrawable(R.drawable.background_custom_actionbar));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListViewFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_github) {
            Uri uri = Uri.parse(LINK_TO_GITHUB);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public FadingActionBarHelper getFadingActionBarHelper() {
        return fadingActionBarHelper;
    }
}
