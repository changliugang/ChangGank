package com.movie.chang.changmovie.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import me.xdj.view.MultiStateView;

/**
 * RecyclerView配置的帮助类
 * Created by changliugang on 2017/4/1 10:17
 * mail：changliugang@sina.com
 */
public class RefreshHelper {
    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MultiStateView multiStateView;
    private BaseQuickAdapter<?, BaseViewHolder> adapter;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private BaseQuickAdapter.RequestLoadMoreListener loadMoreListener;
    private OnItemClickListener onItemClickListener;
    private MultiStateView.OnInflateListener inflateListener;
    private int dividerWidth = 1;
    private int dividerColor = android.R.color.transparent;

    private boolean isSwipeProgressShowAtFirst = true;

    public RefreshHelper(Builder builder) {
        this.context = builder.context;
        this.recyclerView = builder.recyclerView;
        this.swipeRefreshLayout = builder.swipeRefreshLayout;
        this.multiStateView = builder.multiStateView;
        this.adapter = builder.adapter;
        this.refreshListener = builder.refreshListener;
        this.loadMoreListener = builder.loadMoreListener;
        this.onItemClickListener = builder.onItemClickListener;
        this.inflateListener = builder.inflateListener;
        this.dividerWidth = builder.dividerWidth;
        this.dividerColor = builder.dividerColor;
        this.isSwipeProgressShowAtFirst = builder.isSwipeProgressShowAtFirst;

    }

    public static class Builder {

        private Context context;
        private RecyclerView recyclerView;
        private SwipeRefreshLayout swipeRefreshLayout;
        private MultiStateView multiStateView;
        private BaseQuickAdapter<?, BaseViewHolder> adapter;
        private SwipeRefreshLayout.OnRefreshListener refreshListener;
        private BaseQuickAdapter.RequestLoadMoreListener loadMoreListener;
        private OnItemClickListener onItemClickListener;
        private MultiStateView.OnInflateListener inflateListener;
        private int dividerWidth = 1;
        private int dividerColor = android.R.color.transparent;
        private boolean isSwipeProgressShowAtFirst = true;


        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder recyclerView(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            return this;
        }

        public Builder swipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
            this.swipeRefreshLayout = swipeRefreshLayout;
            return this;
        }

        public Builder multiStateView(MultiStateView multiStateView) {
            this.multiStateView = multiStateView;
            return this;
        }

        public Builder adapter(BaseQuickAdapter<?, BaseViewHolder> adapter) {
            this.adapter = adapter;
            return this;
        }

        public Builder refreshListener(SwipeRefreshLayout.OnRefreshListener refreshListener) {
            this.refreshListener = refreshListener;
            return this;
        }

        public Builder loadMoreListener(BaseQuickAdapter.RequestLoadMoreListener loadMoreListener) {
            this.loadMoreListener = loadMoreListener;
            return this;
        }

        public Builder onItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder inflateListener(MultiStateView.OnInflateListener inflateListener) {
            this.inflateListener = inflateListener;
            return this;
        }

        public Builder dividerWidth(int dividerWidth) {
            this.dividerWidth = dividerWidth;
            return this;
        }


        public Builder dividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        public Builder isSwipeProgressShowAtFirst(boolean enable) {
            this.isSwipeProgressShowAtFirst = enable;
            return this;
        }

        public RefreshHelper bulid() {
            return new RefreshHelper(this);
        }

    }

    public void work() {
        if (swipeRefreshLayout != null && refreshListener != null)
            swipeRefreshLayout.setOnRefreshListener(refreshListener);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            // 分割线方向，宽度，颜色
            recyclerView.addItemDecoration(new SimpleItemDivider(context, LinearLayoutManager.VERTICAL,
                    dividerWidth, context.getResources().getColor(dividerColor)));
            if (onItemClickListener != null)
                recyclerView.addOnItemTouchListener(onItemClickListener);
            if (loadMoreListener != null) {
                adapter.openLoadAnimation();
                adapter.setOnLoadMoreListener(loadMoreListener, recyclerView);
                adapter.setEnableLoadMore(true);
            }
            if (adapter != null)
                recyclerView.setAdapter(adapter);
            if (inflateListener != null)
                multiStateView.setOnInflateListener(inflateListener);
            if (swipeRefreshLayout != null && isSwipeProgressShowAtFirst) {
                swipeRefreshLayout.setProgressViewOffset(false, 0, 100);
                swipeRefreshLayout.setRefreshing(true);
            }
        }

    }

}
