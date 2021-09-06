package co.com.ceiba.mobile.pruebadeingreso.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class PostResponse: RealmObject(){
    @PrimaryKey var id:String = ""
    var title = ""
    var body = ""
}