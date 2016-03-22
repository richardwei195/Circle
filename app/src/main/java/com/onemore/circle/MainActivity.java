package com.onemore.circle;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.onemore.circle.Fragment.ChallegeCupFragment;
import com.onemore.circle.Fragment.MoreSourceFragment;
import com.onemore.circle.bean.HomePage;
import com.onemore.circle.utils.NetUtils;
import com.onemore.circle.view.UniversalLoadingView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity {
    private static final long NAV_ACCOUNT = 0;
    private static final long NAV_LOGOUT = 1;
    private static final long NAV_SQUARE = 100;
    private static final long NAV_SOURCES = 101;
    private static final long NAV_DISCOVER = 102;
    private static final long NAV_PROFILE = 103;
    private static final long NAV_NOTIFICATION = 104;
    private static final long NAV_SETTINGS = 200;
    private static final long NAV_ABOUT = 201;

    //加载动画
    private Handler mHandler;
    private UniversalLoadingView mLoadingView;

    private ChallegeCupFragment mFragment;
    private SquareFragment fragment;
    private MoreSourceFragment moreSourceFragment;
    private List<HomePage> mListtitle = new ArrayList<HomePage>();

    private Drawer mDrawer;
    private Toolbar mToolbar;
    private  FragmentManager fManager;

    private NetUtils netUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册Bmob
        Bmob.initialize(this, "6d80affba1269f059731ec36386028d5");
        InitData();

        fragment = new SquareFragment();


        //判断网络是否可用
        if (new NetUtils(MainActivity.this).isNetWork(MainActivity.this))
        {
//            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }

        fragment = (SquareFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        moreSourceFragment = new MoreSourceFragment();

        fManager = getFragmentManager();
        setTitle("");
        initWidget();
    }

    private void InitData() {
        mLoadingView = (UniversalLoadingView) findViewById(R.id.loadingView);
        mLoadingView.postLoadState(UniversalLoadingView.State.LOADING);
        BmobQuery<HomePage> query = new BmobQuery<HomePage>();
        query.findObjects(this, new FindListener<HomePage>() {
            @Override
            public void onSuccess(List<HomePage> list) {
                for (HomePage page : list) {
                    mListtitle.add(page);

                }
                fragment.getADFIRST(mListtitle.get(0).getAdvertisement().getFileUrl(MainActivity.this));
                fragment.getADSEC(mListtitle.get(1).getAdvertisement().getFileUrl(MainActivity.this));
                fragment.getADTHIRD(mListtitle.get(2).getAdvertisement().getFileUrl(MainActivity.this));
                fragment.getADFOUTH(mListtitle.get(3).getAdvertisement().getFileUrl(MainActivity.this));
                fragment.getADFIFTH(mListtitle.get(4).getAdvertisement().getFileUrl(MainActivity.this));
                mLoadingView.postLoadState(UniversalLoadingView.State.GONE);

            }

            @Override
            public void onError(int i, String s) {
                Log.i("error", s);
            }
        });
    }

    private void initWidget() {

         mToolbar = fragment.getToolbar();
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.mipmap.menu);
            mToolbar.setPopupTheme(R.style.AppTheme_PopupOverlay);
            setSupportActionBar(mToolbar);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawer.openDrawer();
                }
            });
        }

        /**
         * 初始化 NavDrawer
         */
        String username = "高能的土豆";
        String education = "杭州电子科技大学在读";

        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withHeaderBackground(R.color.md_blue_500)
                .addProfiles(
                        // 添加 AccountHeader 的 Item
                        new ProfileDrawerItem().withName(username).withEmail(education).withIcon(R.mipmap.avatar).withIdentifier(NAV_ACCOUNT),
                        new ProfileSettingDrawerItem().withName(getString(R.string.logout)).withIdentifier(NAV_LOGOUT)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    // 设置 Header 的监听
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {


                        return false;
                    }
                })
                .build();

        mDrawer = new DrawerBuilder().withActivity(mActivity)
                .withAccountHeader(header)
                .addDrawerItems(
                        // 添加 [主要项]
                        new PrimaryDrawerItem().withName(R.string.square).withTextColorRes(R.color.black_54).withIdentifier(NAV_SQUARE),
                        new PrimaryDrawerItem().withName(R.string.looking_for_sources).withTextColorRes(R.color.black_54).withIdentifier(NAV_SOURCES),
                        new PrimaryDrawerItem().withName(R.string.discover).withTextColorRes(R.color.black_54).withIdentifier(NAV_DISCOVER),
                        new PrimaryDrawerItem().withName(R.string.profile).withTextColorRes(R.color.black_54).withIdentifier(NAV_PROFILE),
                        new PrimaryDrawerItem().withName(R.string.notification).withTextColorRes(R.color.black_54).withIdentifier(NAV_NOTIFICATION),
                        // 分割线
                        new DividerDrawerItem(),
                        // 添加 [次要项]
                        new SecondaryDrawerItem().withName(R.string.settings).withIdentifier(NAV_SETTINGS),
                        new SecondaryDrawerItem().withName(R.string.about).withIdentifier(NAV_ABOUT)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    // NavDrawer 的 Item 的点击监听
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem mDrawerItem) {

                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                fragment.getFragmentManager().beginTransaction().show(fragment).commit();
                                fManager.beginTransaction().hide(moreSourceFragment).commit();
                                break;
                            case 2:
                                fragment.getFragmentManager().beginTransaction().hide(fragment).commit();
                                fManager.beginTransaction().replace(R.id.home, moreSourceFragment).commit();

                                break;
                        }

                        //决定点击之后是否回收抽屉
                        return false;
                    }
                })
                .withSelectedItem((int) NAV_SQUARE)
                .build();
    }

    public Drawer getmDrawer(){
        return mDrawer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
