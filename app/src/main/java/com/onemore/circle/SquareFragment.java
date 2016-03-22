package com.onemore.circle;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.onemore.circle.Fragment.ChallegeCupFragment;
import com.onemore.circle.bean.HomePage;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {
    private Toolbar mToolbar;
    private MaterialViewPager mViewPager;
    private List<HomePage> mList = new ArrayList<HomePage>();

    private String title1 = "聚合数据" , vpFirst , vpSec , vpThird , vpFouth , vpFifth;

    public Toolbar getToolbar() {
        return mViewPager.getToolbar();
    }

    public SquareFragment() {
        // Required empty public constructor
    }


    public static SquareFragment newInstance() {
        return new SquareFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_square, container, false);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (MaterialViewPager) view.findViewById(R.id.md_view_pager);

        /**
         * 初始化 Toolbar
         */
        mToolbar = mViewPager.getToolbar();

        /**
         * 初始化 MDViewPager
         * 设置 ViewPagerAdapter
         * 设置 ViewPager 切换页面监听
         */
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return RecyclerViewFragment.newInstance();
                    case 1:
                        return RecyclerViewFragment.newInstance();
                    case 2:
                        return ChallegeCupFragment.newInstance();
                    case 3:
                        return RecyclerViewFragment.newInstance();
                    case 4:
                        return RecyclerViewFragment.newInstance();


                }
                return RecyclerViewFragment.newInstance();
            }


            @Override
            public String getPageTitle(int position) {

                switch (position) {
                    case 0:
                        return "聚合集锦";
                    case 1:
                        return "人脉圈";
                    case 2:
                        return "挑战杯";
                    case 3:
                        return "大学生创业赛";
                    case 4:
                        return "电商大赛";
                }
                return "Error";
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_blue_500,
                                "http://file.bmob.cn/M03/F0/B9/oYYBAFbvzTyAFbv4AAOVuw2fkc8699.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_amber_500,
                                vpSec);
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_indigo_500,
                               vpThird);
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_pink_500,
                               vpFouth);
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.md_deep_purple_500,
                               vpFifth);
                }
                return null;
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        /**
         * Header 的点击监听
         */
        View header = view.findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Star Clicked.", Toast.LENGTH_SHORT).show();
            }
        });



    }


    /**
     * @param adurl 广告图片加载
     * @return
     */
        public String  getADFIRST(String adurl){
            return this.vpFirst = adurl;

        }

        public String getADSEC(String adurl){
            return this.vpSec = adurl;

        }

        public String getADTHIRD(String adurl){
        return this.vpThird = adurl;

        }

        public String getADFOUTH(String adurl){
        return this.vpFouth = adurl;

        }

        public String getADFIFTH(String adurl){
        return this.vpFifth = adurl;

        }

}
