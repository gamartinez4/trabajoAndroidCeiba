package co.com.ceiba.mobile.pruebadeingreso

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.models.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.rest.ViewModelClass
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity
import kotlinx.android.synthetic.main.activity_post.view.*
import kotlinx.android.synthetic.main.post_list_item.view.*
import kotlinx.android.synthetic.main.user_list_item.view.*

class CustomAdapterPost(
    private val dataSet: ArrayList<PostResponse>
) :
    RecyclerView.Adapter<CustomAdapterPost.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.title.text = dataSet[position].title
        viewHolder.itemView.body.text = dataSet[position].body
    }

    override fun getItemCount() = dataSet.size

}