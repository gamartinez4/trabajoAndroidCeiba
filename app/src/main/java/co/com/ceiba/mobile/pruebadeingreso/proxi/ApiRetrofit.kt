package co.com.ceiba.mobile.pruebadeingreso.proxi


import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRetrofit {

    @GET("{path}")
    fun getUsers(
        @Path("path") stringOfPath:String
    ):Observable<Response<String>>

    @GET("{path}")
    fun getPost(@Path("path") stringOfPath:String,
                @Query("userId") stringQuery:String)
    : Observable<Response<String>>



}