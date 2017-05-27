package com.movie.chang.changmovie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.movie.chang.changmovie.R;
import com.movie.chang.changmovie.entity.GankArticle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xdj.view.SimpleMultiStateView;

/**
 * Created by changliugang on 2017/5/27 14:19
 * mail：changliugang@sina.com
 */
public class FragmentArticle extends BaseFragment {

    @BindView(R.id.list_recyclerview)
    RecyclerView listRecyclerview;
    @BindView(R.id.list_swipeLayout)
    SwipeRefreshLayout listSwipeLayout;
    @BindView(R.id.multi_state_view)
    SimpleMultiStateView multiStateView;

    protected BaseQuickAdapter<GankArticle, BaseViewHolder> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
