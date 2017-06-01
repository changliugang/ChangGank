package com.movie.chang.changmovie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * ViewPager懒加载Fragment
 * Created by chang on 2016/8/8 13:48
 * mail：changliugang@sina.com
 */
public abstract class LazyLoadFragment extends BaseFragment {

    private boolean isViewCreated;// 控件是否初始化完成
    private boolean isLoadDataCompleted;// 数据是否已加载完毕

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        isViewCreated = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted)
            loadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint())
            loadData();
    }

    public void loadData() {
        isLoadDataCompleted = true;
    }

    public abstract int getLayout();

}
