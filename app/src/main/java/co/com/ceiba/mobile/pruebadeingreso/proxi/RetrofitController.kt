package co.com.ceiba.mobile.pruebadeingreso.proxi


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

class RetrofitController {

    fun executeAPI(
        stringOfPath: String,
        stringOfQuery: String? = null,
        goodFunction: (response:Response<String>) -> Any,
        badFunction: () -> Any
    ): @NonNull Disposable? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(ApiRetrofit::class.java)
        val method = if(stringOfQuery==null)retrofit.getUsers(stringOfPath)
        else retrofit.getPost(stringOfPath,stringOfQuery)
       return method
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        goodFunction(it)
                    },
                    {
                        badFunction()
                    }
                )
    }

}