package co.com.ceiba.mobile.pruebadeingreso.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class UserResponse:RealmObject() {
    @PrimaryKey var userId:String = ""
    var name:String = ""
    var phone:String = ""
    var email:String = ""
}