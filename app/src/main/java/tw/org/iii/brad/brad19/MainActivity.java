package tw.org.iii.brad.brad19;
//*0切好版面,創好3個fragment,將fragment中的TextView文字改為該頁
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Fragment[] fs = new Fragment[5];            //*1 用陣列一次放所有frag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        fs[0] = new P0();                                   //*5 新增前後兩個頁面
        fs[1] = new P1();
        fs[2] = new P2();
        fs[3] = new P3();
        fs[4] = new P4();

        initActionBar();                                    //*8

        viewPager.setAdapter(new MyPagerAdapter());  //*4 新招
//        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager())); //*2 原招,下面規定要帶參數,此處就給參數
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){   //*6  此處為滑動頁面
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.v("brad","p : " + position); //*6 position表示切換之後換到的頁面
                if(position == 0){                                          //當user切到0時
                    viewPager.setCurrentItem(1,true);      //強迫回到第一頁,此處item也是從0計算
                }else if(position == fs.length-1){                            //當user切到最後一頁時(index從0計算,故length-1是最後)
                    viewPager.setCurrentItem(fs.length-2,true);//強迫回到最後一頁的前一頁 (才有彈回的效果)
                }else{
                    actionBar.setSelectedNavigationItem(position-1);          //*16,當viewPager動時(滑動頁面)也一起更改actionBar上的
                }
            }
        });
        viewPager.setCurrentItem(1,true);

    }

    private ActionBar actionBar;        //*10
    private void initActionBar(){
        actionBar = getSupportActionBar(); //一個activity只有一組actionBar,故用get
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//*10
        MyTabListener myTabListener = new MyTabListener();          //*12
        actionBar.addTab(actionBar.newTab().setText("Lali1").setTabListener(myTabListener));    //*13塞進去setTL裡面
        actionBar.addTab(actionBar.newTab().setText("Lali2").setTabListener(myTabListener));
        actionBar.addTab(actionBar.newTab().setText("Lali3").setTabListener(myTabListener));
    }

    private class MyTabListener implements  ActionBar.TabListener { //*11
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {       //此處是,當切換ActionBar的tab時,底下的頁面也跟著動
            viewPager.setCurrentItem(tab.getPosition()+1,true);     //*14 ActionBar的tab,只有做三個,index也是從0起,故此處要+1
//            Log.v("brad","tab --"+tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    public void gotoPage1(View view){
        viewPager.setCurrentItem(1,true);
    }
    public void gotoPage2(View view){
        viewPager.setCurrentItem(2,true);
    }
    public void gotoPage3(View view){
        viewPager.setCurrentItem(3,true);
    }   //*7 按上方按鈕也可切頁面

    private class  MyPagerAdapter extends FragmentStatePagerAdapter{        //*3
        public MyPagerAdapter(){
            super(MainActivity.this.getSupportFragmentManager()); //*4新招
        }

//        public MyPagerAdapter(@NonNull FragmentManager fm) {  //*3原招
//            super(fm);
//        }

        @NonNull
        @Override
        public Fragment getItem(int position) { //問第幾個,回那個
            return fs[position];
        }       //*3

        @Override
        public int getCount() { //問我們有幾個,有3個
            return fs.length;
        }                           //*3

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {            //*15 設定viewPager的title
            String [] title = {"","VPtab1","VPtab2","VPtab3",""};   //frag有5個,position也是5個,但前後我們不想顯示,故
            return title[position];
        }
    }
}
