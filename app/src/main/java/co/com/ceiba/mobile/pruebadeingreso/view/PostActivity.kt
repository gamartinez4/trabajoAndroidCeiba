package co.com.ceiba.mobile.pruebadeingreso.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.CustomAdapterPost
import co.com.ceiba.mobile.pruebadeingreso.CustomAdapterUser
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.models.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.proxi.RetrofitController
import co.com.ceiba.mobile.pruebadeingreso.rest.ViewModelClass
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_post.*
import org.json.JSONArray
import org.json.JSONObject

class PostActivity : AppCompatActivity() {

    val listaPosts = ArrayList<PostResponse>()
    private val retrofitController = RetrofitController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        name.text = intent.getStringExtra("name")
        phone.text = intent.getStringExtra("phone")
        email.text = intent.getStringExtra("email")

        recyclerViewPostsResults.layoutManager = LinearLayoutManager(applicationContext)

        retrofitController.executeAPI(
            stringOfPath = "posts",
            stringOfQuery = intent.getStringExtra("userid"),
            goodFunction = {
                if (it.code().toString() == "200") {
                    val jsonData = JSONArray(it.body().toString())
                    for(i in 0 until jsonData.length()) {
                        val postResponse = PostResponse()
                        val jsonData2 = (jsonData[i] as JSONObject)
                        postResponse.id = jsonData2.getString("id")
                        postResponse.title = jsonData2.getString("title")
                        postResponse.body = jsonData2.getString("body")
                        listaPosts.add(postResponse)
                        //Log.e("prueba","id:${postResponse.id}   titulo:${postResponse.title}   post:${ postResponse.body}")
                        recyclerViewPostsResults.adapter = CustomAdapterPost(listaPosts)
                    }
                }else{
                    Log.e("fesfbein","mal")
                }

            },
            badFunction = {
                Log.e("fesfbein","malote")
            })
    }

}