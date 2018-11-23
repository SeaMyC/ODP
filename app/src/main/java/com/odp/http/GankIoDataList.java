package com.odp.http;

import java.util.List;

/**
 * @author ChenHh
 * @time 2018/11/2 14:43
 * @des gank 的数据实体类
 **/
public class GankIoDataList {

    /**
     * error : false
     * results : [{"_id":"5bcd71979d21220315c663fc","createdAt":"2018-10-22T06:43:35.440Z","desc":"2018-10-22","publishedAt":"2018-10-22T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg","used":true,"who":"lijinshanmx"},{"_id":"5bc434ac9d212279160c4c9e","createdAt":"2018-10-15T06:33:16.497Z","desc":"2018-10-15","publishedAt":"2018-10-15T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fw8wzdua6rj30sg0yc7gp.jpg","used":true,"who":"lijinshanmx"},{"_id":"5bbb0de09d21226111b86f1c","createdAt":"2018-10-08T07:57:20.978Z","desc":"2018-10-08","publishedAt":"2018-10-08T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fw0vdlg6xcj30j60mzdk7.jpg","used":true,"who":"lijinshanmx"},{"_id":"5ba206ec9d2122610aba3440","createdAt":"2018-09-19T08:21:00.295Z","desc":"2018-09-19","publishedAt":"2018-09-19T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fvexaq313uj30qo0wldr4.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b9771a29d212206c1b383d0","createdAt":"2018-09-11T07:41:22.491Z","desc":"2018-09-11","publishedAt":"2018-09-11T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fv5n6daacqj30sg10f1dw.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b830bba9d2122031f86ee51","createdAt":"2018-08-27T04:21:14.703Z","desc":"2018-08-27","publishedAt":"2018-08-28T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fuo54a6p0uj30sg0zdqnf.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b7b836c9d212201e982de6e","createdAt":"2018-08-21T11:13:48.989Z","desc":"2018-08-21","publishedAt":"2018-08-21T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fuh5fsvlqcj30sg10onjk.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b74e9409d21222c52ae4cb4","createdAt":"2018-08-16T11:02:24.289Z","desc":"2018-08-16","publishedAt":"2018-08-16T00:00:00.0Z","source":"api","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fubd0blrbuj30ia0qp0yi.jpg","used":true,"who":"lijinshan"},{"_id":"5b7102749d2122341d563844","createdAt":"2018-08-13T12:00:52.458Z","desc":"2018-08-13","publishedAt":"2018-08-13T00:00:00.0Z","source":"api","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqly1fu7xueh1gbj30hs0uwtgb.jpg","used":true,"who":"lijinshan"},{"_id":"5b6bad449d21226f45755582","createdAt":"2018-08-09T10:56:04.962Z","desc":"2018-08-09","publishedAt":"2018-08-09T00:00:00.0Z","source":"web","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqgy1fu39hosiwoj30j60qyq96.jpg","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<GankIoDataBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankIoDataBean> getResults() {
        return results;
    }

    public void setResults(List<GankIoDataBean> results) {
        this.results = results;
    }

}
