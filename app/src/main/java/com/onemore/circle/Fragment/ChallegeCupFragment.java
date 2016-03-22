package com.onemore.circle.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.onemore.circle.R;
import com.onemore.circle.adapter.ChallengeCupAdapter;
import com.onemore.circle.bean.ChallengeCup;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by jiangwei on 16/3/16.
 */
public class ChallegeCupFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private static final int ITEM_COUNT = 100;

    private List<ChallengeCup> mContentItems = new ArrayList<ChallengeCup>();

    public static ChallegeCupFragment newInstance() {
        return new ChallegeCupFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        InitQuery();

        mAdapter = new RecyclerViewMaterialAdapter(new ChallengeCupAdapter(mContentItems ));
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    private void InitQuery() {

        BmobQuery<ChallengeCup> query = new BmobQuery<ChallengeCup>();
        query.findObjects(getActivity(), new FindListener<ChallengeCup>() {
            @Override
            public void onSuccess(List<ChallengeCup> list) {
                for (ChallengeCup page : list) {
                    mContentItems.add(page);



                }
                Log.i("lis" , mContentItems.size()+ "");
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
