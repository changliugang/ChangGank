package com.movie.chang.changmovie.entity;

import java.util.List;

/**
 * 电影分页
 * Created by changliugang on 2017/5/27 09:36
 * mail：changliugang@sina.com
 */
public class Page<T> {

    // 请求成功 false ,否者 反之
    private boolean error;

    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
