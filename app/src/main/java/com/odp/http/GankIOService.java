package com.odp.http;

import com.odp.bean.GankIoDataList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ChenHh
 * @time 2018/11/2 14:35
 * @des gankIo的数据服务
 **/
public interface GankIOService {
    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankIoDataList> getGankIoData(@Path("type") String id, @Path("pre_page") int pre_page, @Path("page") int page);

    /**
     * 获取视频数据
     * @param count 条数
     * @param page  开始index
     * @return video data
     */
    @GET("search/query/listview/category/休息视频/count/{count}/page/{page}")
    Observable<GankIoDataList> getVideoData( @Path("count") int count, @Path("page") int page);
}
