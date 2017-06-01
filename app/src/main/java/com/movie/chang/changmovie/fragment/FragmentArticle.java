package com.movie.chang.changmovie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.movie.chang.changmovie.R;
import com.movie.chang.changmovie.activity.WebViewActivity;
import com.movie.chang.changmovie.config.Constant;
import com.movie.chang.changmovie.entity.GankArticle;
import com.movie.chang.changmovie.retrofit.RetrofitFactory;
import com.movie.chang.changmovie.retrofit.api.GankApi;
import com.movie.chang.changmovie.utils.RefreshHelper;

import butterknife.BindView;
import me.xdj.view.SimpleMultiStateView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static me.xdj.view.MultiStateView.STATE_CONTENT;

/**
 * 文章Fragment
 * Created by changliugang on 2017/5/27 14:19
 * mail：changliugang@sina.com
 */
public class FragmentArticle extends LazyLoadFragment {

    private static final String ARG_TIMELINE_TYPE = "ARTICLE_TYPE";
    @BindView(R.id.list_recyclerview)
    RecyclerView listRecyclerview;
    @BindView(R.id.list_swipeLayout)
    SwipeRefreshLayout listSwipeLayout;
    @BindView(R.id.multi_state_view)
    SimpleMultiStateView multiStateView;

    protected BaseQuickAdapter<GankArticle, BaseViewHolder> mAdapter;

    private int type;// 区分是哪种类型的文章

    private int mCurrentCounter = 1;// 当前加载数据数量

    private boolean isLoadOver = false;

    public static FragmentArticle newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TIMELINE_TYPE, type);
        FragmentArticle fragment = new FragmentArticle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(ARG_TIMELINE_TYPE);
    }

//       @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_article, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    public int getLayout() {
        return R.layout.fragment_article;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new BaseQuickAdapter<GankArticle, BaseViewHolder>(R.layout.item_article, null) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, GankArticle article) {
                baseViewHolder.setText(R.id.item_article_title, article.getDesc());
                baseViewHolder.setText(R.id.item_article_author, article.getWho());
//                baseViewHolder.setText(R.id.item_article_publish_date, FormatCurrentDate.
//                        getTimeRange(getActivity(),article.getPublishedAt()));
                baseViewHolder.setText(R.id.item_article_publish_date,  DateUtils.getRelativeTimeSpanString(getActivity(), //格式化时间，最多显示到分钟。最后参数设定显示的格式
                        article.getPublishedAt().getTime()));
                if (article.getImages() != null && article.getImages().size() > 0)
                    Glide.with(FragmentArticle.this).load(article.getImages().get(0))
                            .into((ImageView) baseViewHolder.getView(R.id.item_article_icon));
            }
        };

        new RefreshHelper.Builder().adapter(mAdapter).multiStateView(multiStateView).recyclerView(listRecyclerview)
                .swipeRefreshLayout(listSwipeLayout).context(getActivity())
                .loadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        if (isLoadOver) {
                            mAdapter.loadMoreEnd();
                        } else {
                            mCurrentCounter++;
                            request();
                        }
                    }
                }).refreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadOver = false;
                mAdapter.setEnableLoadMore(false);
                new Handler().postDelayed(() -> {
                    mCurrentCounter = 1;
                    request();
                    mAdapter.setEnableLoadMore(true);
                }, 500);
            }
        }).onItemClickListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankArticle item = mAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEBVIEW_TITLE, item.getDesc());
                intent.putExtra(WebViewActivity.WEBVIEW_URL, item.getUrl());
                startActivity(intent);
            }
        }).bulid().work();
    }

    @Override
    public void loadData() {
        super.loadData();
        request();
    }

    private void request() {
        String typeString = "";
        switch (type) {
            case Constant.ANDROID:
                typeString = "Android";
                break;
            case 2:
                typeString = "iOS";
                break;
            case 3:
                typeString = "福利";
                break;

        }
        RetrofitFactory.getControllerSingleTonOperation(GankApi.class).getGankArticles(typeString, 10, mCurrentCounter)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(page -> {
                    if (page != null && page.getResults() != null) {
                        if (page.getResults().size() > 0) {
                            multiStateView.setViewState(STATE_CONTENT);
                            if (mCurrentCounter == 1) {
                                mAdapter.setNewData(page.getResults());
                            } else {
                                mAdapter.addData(page.getResults());
                            }
                            mAdapter.loadMoreComplete();

                        } else {
                            // 空数据界面
//                            multiStateView.setViewState(STATE_EMPTY);
                            mAdapter.loadMoreEnd(true);
                            isLoadOver = true;
                        }

                    }

                }, throwable -> {
                    listSwipeLayout.setRefreshing(false);
                }, () -> listSwipeLayout.setRefreshing(false));
    }
}
