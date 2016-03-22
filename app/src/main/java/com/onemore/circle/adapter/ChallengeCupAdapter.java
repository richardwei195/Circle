package com.onemore.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onemore.circle.R;
import com.onemore.circle.bean.ChallengeCup;
import com.onemore.circle.bean.HomePage;

import java.util.List;

/**
 * Created by jiangwei on 16/3/17.
 */
public class ChallengeCupAdapter extends RecyclerView.Adapter<ChallengeCupAdapter.ViewHolder> {
    List<ChallengeCup> contents;

    public ChallengeCupAdapter(List<ChallengeCup> contents ) {
        this.contents = contents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_challege ,parent , false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTitle.setText(contents.get(position).getTitle());
        holder.mContent.setText(contents.get(position).getContent());

    }


    @Override
    public int getItemCount() {
        Log.i("count" , contents.size() + "");
        return contents.size() ;

    }


   public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitle , mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_list_challege_title);
            mContent = (TextView) itemView.findViewById(R.id.tv_list_challege_content);
        }
    }

}
