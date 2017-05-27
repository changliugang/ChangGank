package com.movie.chang.changmovie.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 干货文章
 * Created by changliugang on 2017/5/27 10:21
 * mail：changliugang@sina.com
 */
public class GankArticle {

    /**
     * "_id": "590859ce421aa90c83a513b5",
     * "createdAt": "2017-05-02T18:05:02.17Z",
     * "desc": "Android \u591a\u72b6\u6001\u52a0\u8f7d\u5e03\u5c40\u7684\u5f00\u53d1 Tips",
     * "images": [
     * "http://img.gank.io/c1239688-bf22-46a7-ab46-b23bfb8d32da"
     * ],
     * "publishedAt": "2017-05-26T13:43:32.128Z",
     * "source": "chrome",
     * "type": "Android",
     * "url": "http://gudong.name/2017/04/26/loading_layout_practice.html",
     * "used": true,
     * "who": "\u5495\u549a"
     */

    private String _id;
    // 创建时间
    private Date createdAt;
    // 发布时间
    private Date publishedAt;
    // 文章描述
    private String desc;
    // 文章配图
    private List<String> images;
    // 分类
    private String type;
    // 文章地址
    private String url;
    // 文章作者
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        List<String> imagesWithLimit = new ArrayList<>();
        for (String imgUrl : images) {
            imagesWithLimit.add(imgUrl+"?imageView2/0/w/100");
        }
        return imagesWithLimit;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
