package com.odp.bean;

import java.util.List;

/**
 * @author ChenHh
 * @time 2018/11/23 14:40
 * @des ${TODO}
 **/
public class androidBean {

    /**
     * error : false
     * results : [{"_id":"5bebebc89d21223dd5066107","createdAt":"2018-11-14T09:32:56.867Z","desc":"可以轻松自定义效果的视图自由切换容器","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7y95o0zg30cw0cm1kx"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"web","type":"Android","url":"https://github.com/Marksss/AndroidAutoSwitcher","used":true,"who":"Mark"},{"_id":"5bed1d3e9d21223d7a1b0ad6","createdAt":"2018-11-15T07:16:14.289Z","desc":"一个简单好用的密码、验证码输入框，多样式可选。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7yf5gwdg30hs0qou0y","https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7yjo250g30hs0qo7wl"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"web","type":"Android","url":"https://github.com/WGwangguan/SeparatedEditText","used":true,"who":"WGwangguan"},{"_id":"5bf228439d21223ddba8ca18","createdAt":"2018-11-19T03:04:35.596Z","desc":"使用Android MotionLayout构建的简单轮播。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7ylv4h1g307i0dckjl"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/faob-dev/MotionLayoutCarousel","used":true,"who":"lijinshanmx"},{"_id":"5bf2290c9d21223ddba8ca1a","createdAt":"2018-11-19T03:07:56.130Z","desc":"Android库在同一个叠加层上展示/突出显示多个视图。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7ym8i9qj30u01hcwfq"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/ujwalthote/MultiLamp","used":true,"who":"lijinshanmx"},{"_id":"5bf229989d21223ddba8ca1c","createdAt":"2018-11-19T03:10:16.925Z","desc":"适用于Android的轻量级刮刮卡查看库。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7ymvayig305008wt9k"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/myinnos/AndroidScratchCard","used":true,"who":"lijinshanmx"},{"_id":"5bf229be9d21223ddba8ca1d","createdAt":"2018-11-19T03:10:54.0Z","desc":"一个堆叠标签的库。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7ynfvk3j30u011ignu"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/kongzue/StackLabel","used":true,"who":"lijinshanmx"},{"_id":"5bf229dd9d21223dd5066111","createdAt":"2018-11-19T03:11:25.124Z","desc":"该库创建了一个浮动菜单，如应用程序momo，vtcpay，wepay。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7yprfb8g30bs0hcqv8","https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7yqtcpcg30bs0hctz6","https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7yrfdttg30bs0hcb29"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/ATHBK/CoordinatorMenu","used":true,"who":"lijinshanmx"},{"_id":"5bf22a249d21223dd5066112","createdAt":"2018-11-19T03:12:36.290Z","desc":"Flutter实现的snapping lists库。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7z04lwxg307i0dc4qs"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/ariedov/flutter_snaplist","used":true,"who":"lijinshanmx"},{"_id":"5bf22a459d21223ddba8ca1e","createdAt":"2018-11-19T03:13:09.249Z","desc":"Android图片选择器，仿微信相册图片选择器，支持自定义！","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7z1rhx2g30hs0a01kz","https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7z40iw0g30hs0a0u11"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/Hu12037102/MediaSelector","used":true,"who":"lijinshanmx"},{"_id":"5bf22a8d9d21223dd88989f1","createdAt":"2018-11-19T03:14:21.616Z","desc":"适用于Android的分割线View。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7z4gwhuj30f9087jrh","https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7z4ov74j30fa09q74g"],"publishedAt":"2018-11-19T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/mhashim6/SeparatorView","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5bebebc89d21223dd5066107
         * createdAt : 2018-11-14T09:32:56.867Z
         * desc : 可以轻松自定义效果的视图自由切换容器
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7gy1fxd7y95o0zg30cw0cm1kx"]
         * publishedAt : 2018-11-19T00:00:00.0Z
         * source : web
         * type : Android
         * url : https://github.com/Marksss/AndroidAutoSwitcher
         * used : true
         * who : Mark
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
