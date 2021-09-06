package co.com.ceiba.mobile.pruebadeingreso.rest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.models.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.models.UserResponse


class ViewModelClass : ViewModel() {

    var listaUsers = ArrayList<UserResponse>()

}