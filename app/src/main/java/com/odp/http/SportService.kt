package com.odp.http

import com.odp.bean.SportList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author  ChenHh
 * @time   2018/12/12 10:54
 * @des  sportService
 **/
interface SportService {

    @GET("index")
    fun getSportData(@Query("type") type: String,
                     @Query("key") key: String): Observable<SportList>
}

