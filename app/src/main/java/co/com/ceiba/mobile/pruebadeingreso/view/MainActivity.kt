package co.com.ceiba.mobile.pruebadeingreso.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.CustomAdapterUser
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.proxi.RetrofitController
import co.com.ceiba.mobile.pruebadeingreso.rest.ViewModelClass
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity()  {

    var listaUsers = ArrayList<UserResponse>()
    private val retrofitController = RetrofitController()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(applicationContext)
        val realm = Realm.getInstance( RealmConfiguration.Builder().allowWritesOnUiThread(true).build())
        recyclerViewSearchResults.layoutManager = LinearLayoutManager(applicationContext)
        if(realm.isEmpty) {
            retrofitController.executeAPI(
                stringOfPath = "users",
                goodFunction = {
                    if (it.code().toString() == "200") {
                        val jsonData = JSONArray(it.body().toString())
                        for (i in 0 until jsonData.length()) {
                            val userResponse = UserResponse()
                            val jsonData2 = (jsonData[i] as JSONObject)
                            userResponse.userId = jsonData2.getString("id")
                            userResponse.name = jsonData2.getString("name")
                            userResponse.email = jsonData2.getString("email")
                            userResponse.phone = jsonData2.getString("phone")


                            listaUsers.add(userResponse)

                            // Log.e("prueba","${userResponse.userId}  ${userResponse.name}  ${userResponse.email}    ${userResponse.phone}")
                        }
                        realm?.executeTransaction { it.insert(listaUsers) }
                        recyclerViewSearchResults.adapter = CustomAdapterUser(
                             listaUsers,
                            this@MainActivity
                        )
                    } else {
                        Log.e("fes", "malo")
                    }
                },
                badFunction = {
                    Log.e("fallo", "fallo")
                }
            )
        }else{
            listaUsers = realm!!.where<UserResponse>().findAll().toList() as ArrayList<UserResponse>
            recyclerViewSearchResults.adapter = CustomAdapterUser(listaUsers,this@MainActivity)
        }

        editTextSearch.addTextChangedListener(object:
            TextWatcher {override fun afterTextChanged(s: Editable?) {

        }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val textoBuscar = editTextSearch.text.toString()
                var responseModelFilt  = listaUsers
                if(textoBuscar != "") {
                    val responseModelFiltAux = ArrayList<UserResponse>()
                    for (respuesta in responseModelFilt) {
                        if (respuesta.name.contains(textoBuscar)) responseModelFiltAux.add(respuesta)
                    }
                    responseModelFilt = responseModelFiltAux
                }
                recyclerViewSearchResults.adapter = CustomAdapterUser(responseModelFilt,this@MainActivity)
            }

        })

    }

}