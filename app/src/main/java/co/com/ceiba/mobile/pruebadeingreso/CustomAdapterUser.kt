package co.com.ceiba.mobile.pruebadeingreso

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.rest.ViewModelClass
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity
import kotlinx.android.synthetic.main.activity_post.view.email
import kotlinx.android.synthetic.main.activity_post.view.phone
import kotlinx.android.synthetic.main.user_list_item.view.*

class CustomAdapterUser(
    private val dataSet: ArrayList<UserResponse>,
    private val activity: MainActivity
    ) :
    RecyclerView.Adapter<CustomAdapterUser.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       // val textView: TextView

        init {
            itemView.btn_view_post.setOnClickListener {

                val intent = Intent(activity,PostActivity::class.java)
                intent.putExtra("userid",dataSet[absoluteAdapterPosition].userId)
                intent.putExtra("name",dataSet[absoluteAdapterPosition].name)
                intent.putExtra("email",dataSet[absoluteAdapterPosition].email)
                intent.putExtra("phone",dataSet[absoluteAdapterPosition].phone)
                activity.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.name.text = dataSet[position].name
        viewHolder.itemView.phone.text = dataSet[position].phone
        viewHolder.itemView.email.text = dataSet[position].email
    }


    override fun getItemCount() = dataSet.size

}