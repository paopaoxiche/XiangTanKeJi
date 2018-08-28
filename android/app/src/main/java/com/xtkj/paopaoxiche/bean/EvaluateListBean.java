package com.xtkj.paopaoxiche.bean;

import java.util.List;

/**
 * 评论列表
 *
 * @author sky on 2018/8/28
 * @since 1.0
 */
public class EvaluateListBean {

    /**
     * code : 200
     * data : [{"avatar":"http://localhost:8080/avatars/2018/08/18/3a773e19d68447978e69619bed6f86e9.png","content":"vasivnaisdvasdv","id":9,"nickname":"","rating":5,"time":1535414827000}]
     * msg : 请求成功
     * pageIndex : 0
     * pageSize : 20
     * totalCount : 1
     */

    private int code;
    private String msg;
    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private List<DataBean> data;

    public int getCode() { return code;}

    public void setCode(int code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public int getPageIndex() { return pageIndex;}

    public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex;}

    public int getPageSize() { return pageSize;}

    public void setPageSize(int pageSize) { this.pageSize = pageSize;}

    public int getTotalCount() { return totalCount;}

    public void setTotalCount(int totalCount) { this.totalCount = totalCount;}

    public List<DataBean> getData() { return data;}

    public void setData(List<DataBean> data) { this.data = data;}

    public static class DataBean {
        /**
         * avatar : http://localhost:8080/avatars/2018/08/18/3a773e19d68447978e69619bed6f86e9.png
         * content : vasivnaisdvasdv
         * id : 9
         * nickname :
         * rating : 5
         * time : 1535414827000
         */

        private String avatar;
        private String content;
        private int id;
        private String nickname;
        private int rating;
        private long time;

        public String getAvatar() { return avatar;}

        public void setAvatar(String avatar) { this.avatar = avatar;}

        public String getContent() { return content;}

        public void setContent(String content) { this.content = content;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getNickname() { return nickname;}

        public void setNickname(String nickname) { this.nickname = nickname;}

        public int getRating() { return rating;}

        public void setRating(int rating) { this.rating = rating;}

        public long getTime() { return time;}

        public void setTime(long time) { this.time = time;}
    }
}
