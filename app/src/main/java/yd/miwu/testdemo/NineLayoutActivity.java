package yd.miwu.testdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yd.miwu.R;
import yd.miwu.adapter.MainAdapter;
import yd.miwu.mode.Image;

public class NineLayoutActivity extends Activity {

    private ListView listView;
    private List<List<Image>> imagesList;
    

    private String[][] images=new String[][]{
            {"http://img2.imgtn.bdimg.com/it/u=1072968893,2819664717&fm=21&gp=0.jpg","250","250"}
            ,{"http://img3.douban.com/view/photo/photo/public/p2249526036.jpg","640","960"}
            ,{"http://img3.imgtn.bdimg.com/it/u=3203590320,1773962659&fm=15&gp=0.jpg","250","250"}
            ,{"http://img1.imgtn.bdimg.com/it/u=2043053332,595701561&fm=15&gp=0.jpg","250","250"}
            ,{"http://img2.imgtn.bdimg.com/it/u=713854318,2431604842&fm=21&gp=0.jpg","250","250"}
            ,{"http://img1.imgtn.bdimg.com/it/u=4173339838,1372764763&fm=21&gp=0.jpg","250","250"}
            ,{"http://img2.imgtn.bdimg.com/it/u=1392496607,3782485621&fm=15&gp=0.jpg","250","250"}
            ,{"http://img2.imgtn.bdimg.com/it/u=2657343186,2859219402&fm=21&gp=0.jpg","250","250"}
            ,{"http://image48.360doc.com/DownloadImg/2012/01/0817/20611434_14.jpg","1280","800"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_layout);
        listView= (ListView) findViewById(R.id.lv_main);
        initData();
        listView.setAdapter(new MainAdapter(NineLayoutActivity.this, imagesList));
    }
    private void initData(){
        imagesList=new ArrayList<>();
        //这里单独添加一条单条的测试数据，用来测试单张的时候横竖图片的效果
        ArrayList<Image> singleList=new ArrayList<>();
        singleList.add(new Image(images[8][0],Integer.parseInt(images[8][1]),Integer.parseInt(images[8][2])));
        imagesList.add(singleList);

        //从一到9生成9条朋友圈内容，分别是1~9张图片
        for(int i=0;i<9;i++){
            ArrayList<Image> itemList=new ArrayList<>();
            for(int j=0;j<=i;j++){
                itemList.add(new Image(images[j][0],Integer.parseInt(images[j][1]),Integer.parseInt(images[j][2])));
            }
            imagesList.add(itemList);
        }

    }
}
